<template>
  <div class="category-container">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>分类管理</span>
          <div>
            <el-button type="warning" @click="addTestData" style="margin-right: 10px">添加测试数据</el-button>
            <el-button type="info" @click="testApi" style="margin-right: 10px">测试API</el-button>
            <el-button type="primary" @click="handleAdd">新增分类</el-button>
          </div>
        </div>
      </template>
      
      <!-- 搜索表单 -->
      <el-form :inline="true" :model="searchForm" class="search-form">
        <el-form-item label="分类名称">
          <el-input v-model="searchForm.name" placeholder="请输入分类名称" clearable />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleSearch">查询</el-button>
          <el-button @click="resetSearch">重置</el-button>
        </el-form-item>
      </el-form>
      
      <!-- 调试信息 -->
      <div v-if="debugInfo" class="debug-info">
        <pre>{{ debugInfo }}</pre>
      </div>
      
      <!-- 数据表格 -->
      <el-table :data="tableData" stripe border style="width: 100%" v-loading="loading" row-key="id">
        <el-table-column prop="id" label="ID" width="80" />
        <el-table-column prop="name" label="分类名称" />
        <el-table-column prop="sort" label="排序" width="100" />
        <el-table-column prop="parent" label="父分类">
          <template #default="scope">
            {{ getParentName(scope.row.parent) }}
          </template>
        </el-table-column>
        <el-table-column label="操作" width="180">
          <template #default="scope">
            <el-button size="small" @click="handleEdit(scope.row)">编辑</el-button>
            <el-button size="small" type="danger" @click="handleDelete(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      
      <!-- 分页 -->
      <div class="pagination-container">
        <el-pagination
          background
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          :page-size="pageSize"
          :page-sizes="[10, 20, 50, 100]"
          :current-page="currentPage"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
        />
      </div>
    </el-card>
    
    <!-- 分类表单对话框 -->
    <el-dialog
      :title="dialogTitle"
      v-model="dialogVisible"
      width="500px"
      :close-on-click-modal="false"
    >
      <el-form
        ref="categoryFormRef"
        :model="categoryForm"
        :rules="categoryRules"
        label-width="80px"
      >
        <el-form-item label="分类名称" prop="name">
          <el-input v-model="categoryForm.name" placeholder="请输入分类名称" />
        </el-form-item>
        <el-form-item label="父分类" prop="parent">
          <el-select v-model="categoryForm.parent" placeholder="请选择父分类" clearable>
            <el-option label="无" :value="0" />
            <el-option
              v-for="item in categoryOptions"
              :key="item.id"
              :label="item.name"
              :value="item.id"
              :disabled="categoryForm.id === item.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="排序" prop="sort">
          <el-input-number v-model="categoryForm.sort" :min="0" :max="1000" />
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
import request from '@/api';
import { createCategory, deleteCategory, getCategoryList, updateCategory } from '@/api/category';
import type { FormInstance, FormRules } from 'element-plus';
import { ElMessage, ElMessageBox } from 'element-plus';
import { computed, onMounted, reactive, ref } from 'vue';

// 搜索表单
const searchForm = reactive({
  name: ''
})

// 表格数据
const tableData = ref([])
const loading = ref(false)
const total = ref(0)
const pageSize = ref(10)
const currentPage = ref(1)
const categoryOptions = ref([]) // 所有分类选项
const debugInfo = ref('')

// 对话框
const dialogVisible = ref(false)
const dialogTitle = computed(() => categoryForm.id ? '编辑分类' : '新增分类')
const categoryFormRef = ref<FormInstance>()
const categoryForm = reactive({
  id: '',
  name: '',
  parent: 0,
  sort: 0
})

// 添加测试数据
const addTestData = async () => {
  try {
    // 添加根分类
    const rootCategory = {
      name: '海洋生物',
      parent: 0,
      sort: 1
    }
    const rootRes = await createCategory(rootCategory)
    if (!rootRes.success) {
      ElMessage.error('添加根分类失败')
      return
    }
    
    const rootId = rootRes.data.id
    
    // 添加子分类
    const subCategories = [
      { name: '鱼类', parent: rootId, sort: 1 },
      { name: '哺乳类', parent: rootId, sort: 2 },
      { name: '甲壳类', parent: rootId, sort: 3 },
      { name: '软体类', parent: rootId, sort: 4 }
    ]
    
    for (const category of subCategories) {
      await createCategory(category)
    }
    
    ElMessage.success('测试数据添加成功')
    fetchCategoryList()
    fetchAllCategories()
  } catch (error) {
    console.error('添加测试数据失败:', error)
    ElMessage.error('添加测试数据失败')
  }
}

// 测试API
const testApi = async () => {
  try {
    debugInfo.value = '正在测试API...'
    
    // 直接使用axios发送请求
    const response = await request({
      url: '/category/list',
      method: 'get',
      params: {
        page: 1,
        size: 10
      }
    })
    
    debugInfo.value = JSON.stringify(response, null, 2)
    
    if (response.success) {
      tableData.value = response.data.list || []
      total.value = response.data.total || 0
      ElMessage.success('API测试成功')
    }
  } catch (error) {
    console.error('API测试失败:', error)
    debugInfo.value = '测试失败: ' + JSON.stringify(error, null, 2)
    ElMessage.error('API测试失败')
  }
}

// 表单验证规则
const categoryRules = reactive<FormRules>({
  name: [
    { required: true, message: '请输入分类名称', trigger: 'blur' },
    { min: 2, max: 50, message: '长度在 2 到 50 个字符', trigger: 'blur' }
  ],
  sort: [
    { required: true, message: '请输入排序值', trigger: 'blur' }
  ]
})

// 获取分类列表
const fetchCategoryList = async () => {
  loading.value = true
  try {
    const params = {
      page: currentPage.value,
      size: pageSize.value
    }
    
    // 如果有搜索名称，则添加到参数中
    if (searchForm.name) {
      params.name = searchForm.name
    }
    
    console.log('请求参数:', params)
    const res = await getCategoryList(params)
    console.log('分类列表响应:', res)
    
    if (res.success) {
      tableData.value = res.data.list || []
      total.value = res.data.total || 0
      
      // 调试信息
      if (tableData.value.length === 0) {
        console.warn('分类列表为空')
      } else {
        console.log('获取到的分类数据:', tableData.value)
      }
    } else {
      ElMessage.error(res.message || '获取分类列表失败')
    }
  } catch (error) {
    console.error('获取分类列表失败:', error)
    ElMessage.error('获取分类列表失败，请稍后重试')
  } finally {
    loading.value = false
  }
}

// 获取所有分类（用于下拉选择）
const fetchAllCategories = async () => {
  try {
    const res = await getCategoryList({ page: 1, size: 1000 })
    console.log('所有分类响应:', res)
    
    if (res.success) {
      categoryOptions.value = res.data.list || []
      
      // 调试信息
      if (categoryOptions.value.length === 0) {
        console.warn('所有分类列表为空')
      } else {
        console.log('获取到的所有分类数据:', categoryOptions.value)
      }
    }
  } catch (error) {
    console.error('获取所有分类失败:', error)
  }
}

// 获取父分类名称
const getParentName = (parentId: number) => {
  if (!parentId) return '无'
  const parent = categoryOptions.value.find((item: any) => item.id === parentId)
  return parent ? parent.name : '无'
}

// 搜索
const handleSearch = () => {
  currentPage.value = 1 // Reset to first page when searching
  fetchCategoryList()
}

// 重置搜索
const resetSearch = () => {
  searchForm.name = ''
  handleSearch()
}

// 分页相关
const handleSizeChange = (val: number) => {
  pageSize.value = val
  fetchCategoryList()
}

const handleCurrentChange = (val: number) => {
  currentPage.value = val
  fetchCategoryList()
}

// 新增分类
const handleAdd = () => {
  resetForm()
  dialogVisible.value = true
}

// 编辑分类
const handleEdit = (row: any) => {
  resetForm()
  // Copy row data to form
  Object.assign(categoryForm, {
    id: row.id,
    name: row.name,
    parent: row.parent,
    sort: row.sort
  })
  dialogVisible.value = true
}

// 删除分类
const handleDelete = (row: any) => {
  ElMessageBox.confirm(
    `确定要删除分类 "${row.name}" 吗? 此操作不可恢复!`,
    '警告',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning',
    }
  ).then(async () => {
    try {
      const res = await deleteCategory(row.id)
      if (res.success) {
        ElMessage.success('删除成功')

        if (tableData.value.length === 1 && currentPage.value > 1) {
          currentPage.value -= 1
        }
        fetchCategoryList()
        fetchAllCategories()
      } else {
        ElMessage.error(res.message || '删除失败')
      }
    } catch (error) {
      console.error('删除分类失败:', error)
      ElMessage.error('删除分类失败，请稍后重试')
    }
  }).catch(() => {
    ElMessage.info('已取消删除')
  })
}

// 重置表单
const resetForm = () => {
  if (categoryFormRef.value) {
    categoryFormRef.value.resetFields()
  }
  Object.assign(categoryForm, {
    id: '',
    name: '',
    parent: 0,
    sort: 0
  })
}


// 提交表单
const submitForm = async () => {
  if (!categoryFormRef.value) return
  
  await categoryFormRef.value.validate(async (valid) => {
    if (valid) {
      try {
        const isEdit = !!categoryForm.id
        const res = isEdit 
          ? await updateCategory(categoryForm)
          : await createCategory(categoryForm)
        
        if (res.success) {
          ElMessage.success(`${isEdit ? '更新' : '创建'}成功`)
          dialogVisible.value = false
          fetchCategoryList()
          fetchAllCategories() // 更新分类选项
        } else {
          ElMessage.error(res.message || `${isEdit ? '更新' : '创建'}失败`)
        }
      } catch (error) {
        console.error(`${categoryForm.id ? '更新' : '创建'}分类失败:`, error)
        ElMessage.error(`${categoryForm.id ? '更新' : '创建'}分类失败，请稍后重试`)
      }
    }
  })
}

onMounted(() => {
  fetchCategoryList()
  fetchAllCategories()
})

</script>

<style scoped lang="scss">
.category-container {
  .card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
  
  .search-form {
    margin-bottom: 20px;
  }
  
  .pagination-container {
    margin-top: 20px;
    display: flex;
    justify-content: flex-end;
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
}
</style> 