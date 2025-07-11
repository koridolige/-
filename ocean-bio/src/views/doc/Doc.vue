<template>
  <div class="doc-container">
    <el-card class="doc-card">
      <template #header>
        <div class="card-header">
          <div class="header-left">
            <el-button @click="goBack" icon="Back">返回</el-button>
            <span class="ebook-name">{{ ebookInfo.name }}</span>
          </div>
          <el-button type="primary" @click="handleAddDoc">新增文档</el-button>
        </div>
      </template>

      <el-row :gutter="20">
        <!-- 左侧文档树 -->
        <el-col :span="6">
          <div class="doc-tree-container">
            <div class="tree-header">
              <span>文档目录</span>
              <el-button type="primary" link @click="refreshDocs">刷新</el-button>
            </div>
            <el-tree ref="docTreeRef" :data="docTree" node-key="id" :props="defaultProps" :expand-on-click-node="false"
              @node-click="handleNodeClick" highlight-current default-expand-all>
              <template #default="{ node, data }">
                <div class="custom-tree-node">
                  <span>{{ node.label }}</span>
                  <div class="node-actions">
                    <el-button type="primary" link @click.stop="handleEditDoc(data)">编辑</el-button>
                    <el-button type="danger" link @click.stop="handleDeleteDoc(data)">删除</el-button>
                  </div>
                </div>
              </template>
            </el-tree>
          </div>
        </el-col>

        <!-- 修改右侧内容编辑部分 -->
        <el-col :span="18">
          <div class="doc-content-container" v-if="currentDoc.id">
            <div class="content-header">
              <h3>{{ currentDoc.name }}</h3>
            </div>
            <div class="content-editor">
              <el-input
                v-model="contentForm.content"
                type="textarea"
                :rows="15"
                placeholder="请输入文档内容"
              />
              <div class="content-actions">
                <el-button type="primary" @click="saveContent">保存内容</el-button>
              </div>
            </div>
          </div>
          <div class="doc-placeholder" v-else>
            <el-empty description="请选择或创建文档" />
          </div>
        </el-col>

        <!-- 文档表单对话框 -->
        <el-dialog :title="dialogTitle" v-model="dialogVisible" width="500px" :close-on-click-modal="false">
          <el-form ref="docFormRef" :model="docForm" :rules="docRules" label-width="80px">
            <el-form-item label="名称" prop="name">
              <el-input v-model="docForm.name" placeholder="请输入文档名称" />
            </el-form-item>
            <el-form-item label="父文档" prop="parent">
              <el-select v-model="docForm.parent" placeholder="请选择父文档" clearable>
                <el-option label="无" :value="0" />
                <el-option v-for="item in docOptions" :key="item.id" :label="item.name" :value="item.id"
                  :disabled="docForm.id === item.id" />
              </el-select>
            </el-form-item>
            <el-form-item label="排序" prop="sort">
              <el-input-number v-model="docForm.sort" :min="0" :max="1000" />
            </el-form-item>
          </el-form>
          <template #footer>
            <div class="dialog-footer">
              <el-button @click="dialogVisible = false">取消</el-button>
              <el-button type="primary" @click="submitForm">确定</el-button>
            </div>
          </template>
        </el-dialog>
      </el-row>
    </el-card>
  </div>
</template>

<script setup lang="ts">
import { createDoc, deleteDoc, getDocContent, getDocList, saveDocContent, updateDoc } from '@/api/doc'
import { getEbookDetail } from '@/api/ebook'
import type { FormInstance, FormRules } from 'element-plus'
import { ElMessage, ElMessageBox } from 'element-plus'
import { computed, onMounted, reactive, ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'

const route = useRoute()
const router = useRouter()
const ebookId = computed(() => route.params.ebookId as string)
const isEditing = ref(false) // 使用 ref 创建响应式变量

// 电子书信息
const ebookInfo = reactive({
  id: '',
  name: '',
  category: '',
  description: ''
})

// 文档树
const docTreeRef = ref()
const docTree = ref([])
const defaultProps = {
  children: 'children',
  label: 'name'
}

// 当前选中的文档
const currentDoc = reactive({
  id: '',
  name: '',
  parent: 0,
  sort: 0
})

// 文档内容
const contentForm = reactive({
  id: '',
  content: ''
})

// 对话框
const dialogVisible = ref(false)
const dialogTitle = computed(() => docForm.id ? '编辑文档' : '新增文档')
const docFormRef = ref<FormInstance>()
const docForm = reactive({
  id: '',
  ebookId: '',
  parent: 0,
  name: '',
  sort: 0
})
const docOptions = ref([]) // 所有文档选项

// 表单验证规则
const docRules = reactive<FormRules>({
  name: [
    { required: true, message: '请输入文档名称', trigger: 'blur' },
    { min: 2, max: 50, message: '长度在 2 到 50 个字符', trigger: 'blur' }
  ],
  sort: [
    { required: true, message: '请输入排序值', trigger: 'blur' }
  ]
})

// 返回上一页
const goBack = () => {
  router.push('/main/ebook')
}

// 获取电子书信息
const fetchEbookInfo = async () => {
  try {
    const res = await getEbookDetail(parseInt(ebookId.value))
    if (res.success) {
      Object.assign(ebookInfo, res.data)
    } else {
      ElMessage.error(res.message || '获取电子书信息失败')
    }
  } catch (error) {
    console.error('获取电子书信息失败:', error)
    ElMessage.error('获取电子书信息失败，请稍后重试')
  }
}

// 获取文档列表
const fetchDocList = async () => {
  try {
    console.log('Fetching doc list for ebookId:', ebookId.value)
    const res = await getDocList({ ebookId: ebookId.value })
    console.log('Doc list response:', res)
    if (res.success) {
      const list = res.data || []
      console.log('Document list:', list)
      docOptions.value = list

      // 构建树形结构
      const tree = []
      const map = {}

      // 先将所有节点存入map
      list.forEach(item => {
        map[item.id] = { ...item, children: [] }
      })

      // 构建树
      list.forEach(item => {
        const node = map[item.id]
        if (item.parent === 0) {
          // 根节点
          tree.push(node)
        } else {
          // 子节点
          if (map[item.parent]) {
            map[item.parent].children.push(node)
          }
        }
      })

      // 排序
      const sortNodes = (nodes) => {
        nodes.sort((a, b) => a.sort - b.sort)
        nodes.forEach(node => {
          if (node.children && node.children.length > 0) {
            sortNodes(node.children)
          }
        })
      }
      sortNodes(tree)

      console.log('Final doc tree:', tree)
      docTree.value = tree
    } else {
      ElMessage.error(res.message || '获取文档列表失败')
    }
  } catch (error) {
    console.error('获取文档列表失败:', error)
    ElMessage.error('获取文档列表失败，请稍后重试')
  }
}

// 刷新文档列表
const refreshDocs = () => {
  fetchDocList()
}

// 点击文档节点
const handleNodeClick = async (data) => {
  Object.assign(currentDoc, data);

  try {
    const res = await getDocContent(data.id);
    console.log('文档内容响应:', res); // 调试日志

    if (res.success) {
      // 确保赋值到正确的响应式对象
      contentForm.id = data.id;
      contentForm.content = res.data.content || ''; // 关键修改：明确取content字段
    }
  } catch (error) {
    console.error('获取内容失败:', error);
  }
};

// 保存文档内容
const saveContent = async () => {
  if (!currentDoc.id) {
    ElMessage.warning('请先选择文档')
    return
  }

  try {
    const res = await saveDocContent({
      docId: currentDoc.id,
      content: contentForm.content
    })

    if (res.success) {
      ElMessage.success('保存成功')
    } else {
      ElMessage.error(res.message || '保存失败')
    }
  } catch (error) {
    console.error('保存文档内容失败:', error)
    ElMessage.error('保存文档内容失败，请稍后重试')
  }
}

// // 在 script 部分添加 handleContentInput 方法
// const handleContentInput = (event) => {
//   contentForm.content = event.target.innerHTML;
// };

// const toggleEditing = () => {
//   isEditing.value = !isEditing.value // 注意要使用 .value 访问 ref 的值
// }

// 新增文档
const handleAddDoc = () => {
  resetDocForm()
  docForm.ebookId = ebookId.value
  dialogVisible.value = true
}

// 编辑文档
const handleEditDoc = (data) => {
  resetDocForm()
  Object.assign(docForm, data)
  dialogVisible.value = true
}

// 删除文档
const handleDeleteDoc = (data) => {
  ElMessageBox.confirm('确定要删除该文档吗？删除后将无法恢复！', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => {
    try {
      const res = await deleteDoc(data.id)
      if (res.success) {
        ElMessage.success('删除成功')

        // 如果删除的是当前选中的文档，清空当前选中状态
        if (currentDoc.id === data.id) {
          currentDoc.id = ''
          currentDoc.name = ''
          contentForm.id = ''
          contentForm.content = ''
        }

        fetchDocList()
      } else {
        ElMessage.error(res.message || '删除失败')
      }
    } catch (error) {
      console.error('删除文档失败:', error)
      ElMessage.error('删除文档失败，请稍后重试')
    }
  }).catch(() => { })
}

// 重置表单
const resetDocForm = () => {
  docForm.id = ''
  docForm.ebookId = ebookId.value
  docForm.parent = 0
  docForm.name = ''
  docForm.sort = 0

  if (docFormRef.value) {
    docFormRef.value.resetFields()
  }
}

// 提交表单
const submitForm = async () => {
  if (!docFormRef.value) return

  await docFormRef.value.validate(async (valid) => {
    if (valid) {
      try {
        const isEdit = !!docForm.id
        const res = isEdit
          ? await updateDoc(docForm)
          : await createDoc(docForm)

        if (res.success) {
          ElMessage.success(`${isEdit ? '更新' : '创建'}成功`)
          dialogVisible.value = false
          fetchDocList()
        } else {
          ElMessage.error(res.message || `${isEdit ? '更新' : '创建'}失败`)
        }
      } catch (error) {
        console.error(`${docForm.id ? '更新' : '创建'}文档失败:`, error)
        ElMessage.error(`${docForm.id ? '更新' : '创建'}文档失败，请稍后重试`)
      }
    }
  })
}

onMounted(() => {
  fetchEbookInfo()
  fetchDocList()
})
</script>

<style scoped lang="scss">
// 更新样式部分
.content-display {
  padding: 15px;
  border: 1px solid #dcdfe6;
  border-radius: 4px;
  min-height: 300px;
  background: #fff;
  outline: none; // 移除编辑时的轮廓

  h1,
  h2,
  h3 {
    margin: 15px 0 10px;
  }

  p {
    margin: 10px 0;
    line-height: 1.5;
  }

  &.editing {
    border-color: #409eff;
    box-shadow: 0 0 0 2px rgba(64, 158, 255, 0.2);
    background: #f5f7fa;
  }
}

.doc-container {
  .doc-card {
    min-height: calc(100vh - 160px);
  }

  .card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;

    .header-left {
      display: flex;
      align-items: center;

      .ebook-name {
        margin-left: 15px;
        font-size: 16px;
        font-weight: bold;
      }
    }
  }

  .doc-tree-container {
    border-right: 1px solid #e8e8e8;
    height: calc(100vh - 240px);
    overflow-y: auto;
    padding-right: 10px;

    .tree-header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin-bottom: 10px;
      padding-bottom: 10px;
      border-bottom: 1px solid #e8e8e8;

      span {
        font-weight: bold;
      }
    }

    .custom-tree-node {
      width: 100%;
      display: flex;
      justify-content: space-between;
      align-items: center;

      .node-actions {
        display: none;
      }

      &:hover .node-actions {
        display: flex;
      }
    }
  }

  .doc-content-container {
    height: calc(100vh - 240px);
    display: flex;
    flex-direction: column;

    .content-header {
      padding-bottom: 10px;
      border-bottom: 1px solid #e8e8e8;
      margin-bottom: 15px;
    }

    .content-editor {
      flex: 1;
      display: flex;
      flex-direction: column;

      .content-actions {
        margin-top: 15px;
        display: flex;
        justify-content: flex-end;
      }
    }
  }

  .doc-placeholder {
    height: calc(100vh - 240px);
    display: flex;
    justify-content: center;
    align-items: center;
  }
}
</style>