<template>
  <div class="ebook-container">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>电子书管理</span>
          <div>
            <el-button type="info" @click="handleTestUpload" style="margin-right: 10px">测试上传</el-button>
            <el-button type="primary" @click="handleAdd">新增电子书</el-button>
          </div>
        </div>
      </template>

      <!-- 修改调试信息部分，添加显示控制 -->
      <div v-show="showDebugInfo" class="debug-info">
        <div class="debug-header">
          <el-button type="text" @click="showDebugInfo = false" class="close-debug">
            <el-icon>
              <Close />
            </el-icon>
          </el-button>
        </div>
        <pre>{{ debugInfo }}</pre>
      </div>

      <!-- 搜索表单 -->
      <el-form :inline="true" :model="searchForm" class="search-form">
        <el-form-item label="名称">
          <el-input v-model="searchForm.name" placeholder="请输入电子书名称" clearable />
        </el-form-item>
        <el-form-item label="分类">
          <el-select v-model="searchForm.categoryId" placeholder="请选择分类" clearable>
            <el-option v-for="item in categoryOptions" :key="item.id" :label="item.name" :value="item.id" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleSearch">查询</el-button>
          <el-button @click="resetSearch">重置</el-button>
        </el-form-item>
      </el-form>

      <!-- 电子书列表 -->
      <el-row :gutter="30">
        <el-col :xs="24" :sm="12" :md="8" :lg="6" v-for="(item, index) in tableData" :key="index">
          <el-card shadow="hover" class="ebook-card">
            <div class="ebook-cover">
              <img :src="getImageUrl(item.cover)" alt="封面" class="cover-image" v-if="item.cover" />
              <div v-else class="no-cover">
                <el-icon>
                  <Picture />
                </el-icon>
                <span>暂无封面</span>
              </div>
            </div>
            <div class="ebook-info">
              <h3 class="ebook-title">{{ item.name }}</h3>
              <p class="ebook-desc">{{ item.description || '暂无描述' }}</p>
              <div class="ebook-category">
                <el-tag size="small">{{ getCategoryName(item) }}</el-tag>
              </div>
              <div class="ebook-meta">
                <span>文档: {{ item.docCount || 0 }}</span>
                <span>浏览: {{ item.viewCount || 0 }}</span>
                <span>点赞: {{ item.voteCount || 0 }}</span>
              </div>
              <div class="ebook-actions">
                <el-button type="primary" size="small" @click="handleViewDocs(item)">查看文档</el-button>
                <el-dropdown>
                  <el-button size="small">
                    更多<el-icon class="el-icon--right"><arrow-down /></el-icon>
                  </el-button>
                  <template #dropdown>
                    <el-dropdown-menu>
                      <el-dropdown-item @click="handleEdit(item)">编辑</el-dropdown-item>
                      <el-dropdown-item @click="handleDelete(item)" style="color: #f56c6c;">删除</el-dropdown-item>
                    </el-dropdown-menu>
                  </template>
                </el-dropdown>
              </div>
            </div>
          </el-card>
        </el-col>
      </el-row>

      <!-- 空状态 -->
      <div v-if="tableData.length === 0 && !loading" class="empty-state">
        <el-icon>
          <DocumentDelete />
        </el-icon>
        <p>暂无电子书数据</p>
        <el-button type="primary" @click="handleAdd">添加电子书</el-button>
      </div>

      <!-- 分页 -->
      <div class="pagination-container" v-if="total > 0">
        <el-pagination background layout="total, sizes, prev, pager, next, jumper" :total="total" :page-size="pageSize"
          :page-sizes="[8, 16, 24, 32]" :current-page="currentPage" @size-change="handleSizeChange"
          @current-change="handleCurrentChange" />
      </div>

      <!-- 加载状态 -->
      <div v-if="loading" class="loading-state">
        <el-icon class="loading-icon">
          <Loading />
        </el-icon>
        <p>加载中...</p>
      </div>
    </el-card>

    <!-- 电子书表单对话框 -->
    <el-dialog :title="dialogTitle" v-model="dialogVisible" width="600px" :close-on-click-modal="false">
      <el-form ref="ebookFormRef" :model="ebookForm" :rules="ebookRules" label-width="80px">
        <el-form-item label="名称" prop="name">
          <el-input v-model="ebookForm.name" placeholder="请输入电子书名称" />
        </el-form-item>
        <el-form-item label="分类" prop="categoryId">
          <el-select v-model="ebookForm.categoryId" placeholder="请选择分类">
            <el-option v-for="item in categoryOptions" :key="item.id" :label="item.name" :value="item.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="封面">
          <el-upload class="cover-upload" action="/api/file/upload" :show-file-list="false"
            :on-success="handleCoverSuccess" :on-error="handleCoverError" :before-upload="beforeCoverUpload"
            :headers="uploadHeaders" :with-credentials="true">
            <img v-if="ebookForm.cover" :src="getImageUrl(ebookForm.cover)" class="cover-preview" />
            <el-icon v-else class="upload-icon">
              <Plus />
            </el-icon>
          </el-upload>
          <div class="upload-tip">建议尺寸: 300x400px，支持JPG/PNG格式</div>
          <div v-if="ebookForm.cover" class="current-cover">
            当前封面URL: {{ ebookForm.cover }}
          </div>
        </el-form-item>
        <el-form-item label="描述" prop="description">
          <el-input v-model="ebookForm.description" type="textarea" :rows="4" placeholder="请输入电子书描述" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="submitForm">确定</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup lang="ts">
import { getCategoryList } from '@/api/category'
import { createEbook, deleteEbook, getEbookList, updateEbook } from '@/api/ebook'
import type { FormInstance, FormRules } from 'element-plus'
import { ElMessage, ElMessageBox } from 'element-plus'
import { onMounted, reactive, ref } from 'vue'
import { useRouter } from 'vue-router'

// 使用路由
const router = useRouter()

// 添加状态变量
const showDebugInfo = ref(false)

// 新增处理函数
const handleTestUpload = async () => {
  await testUpload() // 调用原有测试上传功能
  showDebugInfo.value = true // 上传完成后显示调试信息
}

// 调试信息
const debugInfo = ref('')

// 状态管理
const tableData = ref([])
const categoryOptions = ref([])
const total = ref(0)
const currentPage = ref(1)
const pageSize = ref(8)
const dialogVisible = ref(false)
const dialogTitle = ref('')
const ebookFormRef = ref<FormInstance>()
const loading = ref(false)

// 搜索表单
const searchForm = reactive({
  name: '',
  categoryId: undefined as number | undefined
})

// 电子书表单
const ebookForm = reactive({
  id: undefined as number | undefined,
  name: '',
  categoryId: undefined as number | undefined,
  cover: '',
  description: '',
  category1Id: undefined as number | undefined,
  category2Id: undefined as number | undefined,
  docCount: undefined as number | undefined,
  viewCount: undefined as number | undefined,
  voteCount: undefined as number | undefined
})

// 表单验证规则
const ebookRules = reactive<FormRules>({
  name: [
    { required: true, message: '请输入电子书名称', trigger: 'blur' },
    { min: 2, max: 50, message: '长度在 2 到 50 个字符', trigger: 'blur' }
  ],
  categoryId: [
    { required: true, message: '请选择分类', trigger: 'change' }
  ]
})

// 上传请求头
const uploadHeaders = {
  Authorization: `Bearer ${localStorage.getItem('token') || ''}`
}

// 获取电子书列表
const fetchEbookList = async () => {
  try {
    loading.value = true
    const params = {
      page: currentPage.value,
      size: pageSize.value,
      name: searchForm.name,
      categoryId: searchForm.categoryId
    }

    const res = await getEbookList(params)

    // 调试信息
    debugInfo.value = JSON.stringify({
      requestParams: params,
      responseData: res.data,
      tableData: res.data.list || []
    }, null, 2)

    tableData.value = res.data.list || []
    total.value = res.data.total || 0

  } catch (error) {
    console.error('获取电子书列表失败:', error)
    ElMessage.error('获取电子书列表失败')
  } finally {
    loading.value = false
  }
}

// 获取所有分类
const fetchAllCategories = async () => {
  try {
    const res = await getCategoryList()

    // 处理可能的响应格式
    if (Array.isArray(res.data)) {
      categoryOptions.value = res.data
    } else if (res.data && Array.isArray(res.data.list)) {
      categoryOptions.value = res.data.list
    } else if (res.data && Array.isArray(res.data.List)) {
      categoryOptions.value = res.data.List
    } else {
      console.error('无法识别的分类数据结构:', res.data)
      categoryOptions.value = []
    }
  } catch (error) {
    console.error('获取分类列表失败:', error)
    ElMessage.error('获取分类列表失败')
    categoryOptions.value = []
  }
}

// 获取分类名称
const getCategoryName = (item: any) => {
  try {
    const categoryId = item.categoryId || item.category1Id
    if (!categoryId) return '未分类'

    const category = categoryOptions.value.find((c: any) => c.id === categoryId)
    return category ? category.name : '未知分类'
  } catch (error) {
    console.error('获取分类名称出错:', error)
    return '分类错误'
  }
}

// 处理图片URL
const getImageUrl = (url?: string) => {
  if (!url) return ''

  // 如果已经是完整URL则直接返回
  if (url.startsWith('http') || url.startsWith('https') || url.startsWith('/')) {
    return url
  }

  // 使用正确的后端接口路径
  return `/api/file/${encodeURIComponent(url)}`
}

// 搜索
const handleSearch = () => {
  currentPage.value = 1
  fetchEbookList()
}

// 重置搜索
const resetSearch = () => {
  searchForm.name = ''
  searchForm.categoryId = undefined
  handleSearch()
}

// 分页相关
const handleSizeChange = (size: number) => {
  pageSize.value = size
  currentPage.value = 1
  fetchEbookList()
}

const handleCurrentChange = (page: number) => {
  currentPage.value = page
  fetchEbookList()
}

// 新增电子书
const handleAdd = () => {
  dialogTitle.value = '新增电子书'
  resetEbookForm()
  dialogVisible.value = true
}

// 编辑电子书
const handleEdit = (item: any) => {
  dialogTitle.value = '编辑电子书'

  // 复制所有属性
  Object.keys(item).forEach(key => {
    if (key in ebookForm) {
      // @ts-ignore
      ebookForm[key] = item[key]
    }
  })

  dialogVisible.value = true
}

// 删除电子书
const handleDelete = async (item: any) => {
  try {
    await ElMessageBox.confirm(`确定删除电子书 "${item.name}" 吗?`, '提示', {
      type: 'warning'
    })

    await deleteEbook(item.id)
    ElMessage.success('删除成功')
    fetchEbookList()
  } catch (error) {
    console.error('删除失败:', error)
  }
}

// 查看文档
const handleViewDocs = (item: any) => {
  router.push(`/main/doc/${item.id}`)
}

// 封面上传前的校验
const beforeCoverUpload = (file: any) => {
  const isImage = file.type.startsWith('image/')
  const isLt5M = file.size / 1024 / 1024 < 5

  if (!isImage) {
    ElMessage.error('上传封面图片只能是图片格式!')
    return false
  }
  if (!isLt5M) {
    ElMessage.error('上传封面图片大小不能超过 5MB!')
    return false
  }

  return true
}

// 封面上传成功回调
const handleCoverSuccess = (res: any) => {
  console.log('上传响应:', res)
  if (res && res.code === 200) {
    // 使用文件名构建URL
    const filename = res.data
    ebookForm.cover = filename
    debugInfo.value = '上传成功: ' + JSON.stringify(res, null, 2)
    ElMessage.success('封面上传成功')
  } else {
    const errorMsg = res?.message || '封面上传失败'
    debugInfo.value = '上传失败: ' + errorMsg
    ElMessage.error(errorMsg)
  }
}

// 封面上传错误回调
const handleCoverError = (error: any) => {
  console.error('封面上传错误:', error)
  debugInfo.value = '上传错误: ' + JSON.stringify(error, null, 2)
  ElMessage.error('封面上传失败，请检查网络连接或重新上传')
}

// 重置表单
const resetEbookForm = () => {
  ebookForm.id = undefined
  ebookForm.name = ''
  ebookForm.categoryId = undefined
  ebookForm.description = ''
  ebookForm.cover = ''
  ebookForm.category1Id = undefined
  ebookForm.category2Id = undefined
  ebookForm.docCount = undefined
  ebookForm.viewCount = undefined
  ebookForm.voteCount = undefined

  if (ebookFormRef.value) {
    ebookFormRef.value.resetFields()
  }
}

// 提交表单
const submitForm = async () => {
  if (!ebookFormRef.value) return

  try {
    // 验证表单
    const valid = await ebookFormRef.value.validate()
    if (!valid) return

    const isEdit = !!ebookForm.id
    const submitData = { ...ebookForm }

    console.log('提交数据:', submitData)

    // 调用API
    const res = isEdit
      ? await updateEbook(submitData)
      : await createEbook(submitData)

    if (res.code === 200) {
      ElMessage.success(`${isEdit ? '更新' : '创建'}成功`)
      dialogVisible.value = false
      fetchEbookList()
    } else {
      throw new Error(res.message || `${isEdit ? '更新' : '创建'}失败`)
    }
  } catch (error: any) {
    console.error('操作失败:', error)
    ElMessage.error(error.message || '操作失败，请稍后重试')
  }
}

// 测试上传功能
const testUpload = async () => {
  try {
    const formData = new FormData()
    // 创建一个简单的测试文件
    const blob = new Blob(['test'], { type: 'text/plain' })
    const file = new File([blob], 'test.txt', { type: 'text/plain' })
    formData.append('file', file)

    debugInfo.value = '正在测试上传...'

    const response = await fetch('/api/file/upload', {
      method: 'POST',
      headers: {
        'Authorization': `Bearer ${localStorage.getItem('token') || ''}`
      },
      body: formData
    })

    const result = await response.json()
    debugInfo.value = JSON.stringify(result, null, 2)

    if (result.code === 200) {
      ElMessage.success('测试上传成功，文件名: ' + result.data)
      // 测试访问URL
      const url = '/api/file/download?name=' + encodeURIComponent(result.data)
      debugInfo.value += '\n访问URL: ' + url
    } else {
      throw new Error(result.message || '测试上传失败')
    }
  } catch (error: any) {
    console.error('测试上传失败:', error)
    debugInfo.value = '测试失败: ' + (error.message || JSON.stringify(error, null, 2))
    ElMessage.error('测试上传失败: ' + error.message)
  }
}

// 初始化
onMounted(() => {
  fetchEbookList()
  fetchAllCategories()
})
</script>

<style scoped lang="scss">
.ebook-container {
  .card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }

  .search-form {
    margin-bottom: 30px;
  }

  .pagination-container {
    margin-top: 30px;
    display: flex;
    justify-content: flex-end;
  }

  .ebook-card {
    margin-bottom: 30px;
    height: 100%;
    display: flex;
    flex-direction: column;
    transition: all 0.3s ease;

    &:hover {
      transform: translateY(-5px);
      box-shadow: 0 6px 16px rgba(0, 0, 0, 0.12);
    }

    .ebook-cover {
      height: 300px;
      overflow: hidden;
      display: flex;
      justify-content: center;
      align-items: center;
      background-color: #f8f9fa;
      border-bottom: 1px solid #ebeef5;

      .cover-image {
        width: 100%;
        height: 100%;
        object-fit: cover;
        transition: transform 0.3s ease;
      }

      .no-cover {
        display: flex;
        flex-direction: column;
        align-items: center;
        color: #909399;

        .el-icon {
          font-size: 60px;
          margin-bottom: 10px;
        }
      }
    }

    .ebook-info {
      padding: 15px;
      flex-grow: 1;
      display: flex;
      flex-direction: column;

      .ebook-title {
        font-size: 16px;
        margin-bottom: 10px;
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
        color: #303133;
      }

      .ebook-desc {
        font-size: 14px;
        color: #606266;
        margin-bottom: 15px;
        flex-grow: 1;
        display: -webkit-box;
        -webkit-box-orient: vertical;
        -webkit-line-clamp: 3;
        overflow: hidden;
        text-overflow: ellipsis;
        line-height: 1.5;
        min-height: 60px;
      }

      .ebook-category {
        margin-bottom: 10px;
      }

      .ebook-meta {
        display: flex;
        justify-content: space-between;
        font-size: 12px;
        color: #909399;
        margin-bottom: 10px;
      }

      .ebook-actions {
        display: flex;
        justify-content: space-between;
      }
    }
  }

  .cover-upload {
    width: 200px;
    height: 300px;
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: #fafafa;

    .cover-preview {
      width: 100%;
      height: 100%;
      object-fit: cover;
    }

    .upload-icon {
      font-size: 28px;
      color: #8c939d;
    }
  }

  .upload-tip {
    font-size: 12px;
    color: #606266;
    margin-top: 10px;
  }

  .current-cover {
    font-size: 12px;
    color: #909399;
    margin-top: 10px;
    word-break: break-all;
  }

  .debug-info {
    margin: 10px 0;
    padding: 10px;
    background-color: #f8f8f8;
    border: 1px solid #ddd;
    border-radius: 4px;
    overflow: auto;
    max-height: 300px;

    pre {
      margin: 0;
      white-space: pre-wrap;
      word-break: break-all;
      font-family: monospace;
      font-size: 12px;
    }
  }

  .empty-state {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 40px 0;
    color: #909399;

    .el-icon {
      font-size: 60px;
      margin-bottom: 20px;
    }

    p {
      margin-bottom: 20px;
    }
  }

  .loading-state {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 40px 0;

    .loading-icon {
      font-size: 40px;
      color: #409EFF;
      margin-bottom: 10px;
      animation: rotate 2s linear infinite;
    }

    @keyframes rotate {
      from {
        transform: rotate(0deg);
      }

      to {
        transform: rotate(360deg);
      }
    }
  }
}
</style>