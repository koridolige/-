<template>
  <div class="user-container">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>用户管理</span>
          <el-button type="primary" @click="handleAdd">新增用户</el-button>
        </div>
      </template>
      
      <!-- 搜索表单 -->
      <el-form :inline="true" :model="searchForm" class="search-form">
        <el-form-item label="登录名">
          <el-input v-model="searchForm.loginName" placeholder="请输入登录名" clearable />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleSearch">查询</el-button>
          <el-button @click="resetSearch">重置</el-button>
        </el-form-item>
      </el-form>
      
      <!-- 数据表格 -->
      <el-table :data="tableData" stripe border style="width: 100%" v-loading="loading">
        <el-table-column prop="id" label="ID" width="80" />
        <el-table-column prop="loginName" label="登录名" />
        <el-table-column prop="name" label="昵称" />
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
    
    <!-- 用户表单对话框 -->
    <el-dialog
      :title="dialogTitle"
      v-model="dialogVisible"
      width="500px"
      :close-on-click-modal="false"
    >
      <el-form
        ref="userFormRef"
        :model="userForm"
        :rules="userRules"
        label-width="80px"
      >
        <el-form-item label="登录名" prop="loginName">
          <el-input v-model="userForm.loginName" placeholder="请输入登录名" />
        </el-form-item>
        <el-form-item label="昵称" prop="name">
          <el-input v-model="userForm.name" placeholder="请输入昵称" />
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="userForm.password" type="password" placeholder="请输入密码" show-password />
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
import { createUser, deleteUser, getUserList, updateUser } from '@/api/user';
import type { FormInstance, FormRules } from 'element-plus';
import { ElMessage, ElMessageBox } from 'element-plus';
import { computed, onMounted, reactive, ref } from 'vue';

// 搜索表单
const searchForm = reactive({
  loginName: ''
})

// 表格数据
const tableData = ref([])
const loading = ref(false)
const total = ref(0)
const pageSize = ref(10)
const currentPage = ref(1)

// 对话框
const dialogVisible = ref(false)
const dialogTitle = computed(() => userForm.id ? '编辑用户' : '新增用户')
const userFormRef = ref<FormInstance>()
const userForm = reactive({
  id: '',
  loginName: '',
  name: '',
  password: ''
})

// 表单验证规则
const userRules = reactive<FormRules>({
  loginName: [
    { required: true, message: '请输入登录名', trigger: 'blur' }
  ],
  name: [
    { required: true, message: '请输入昵称', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 6, message: '密码长度不能小于6位', trigger: 'blur' }
  ]
})

// 获取用户列表
const fetchUserList = async () => {
  loading.value = true
  try {
    const params = {
      page: currentPage.value,
      size: pageSize.value,
      ...searchForm
    }
    const res = await getUserList(params)
    if (res.success) {
      tableData.value = res.data.list
      total.value = res.data.total
    } else {
      ElMessage.error(res.message || '获取用户列表失败')
    }
  } catch (error) {
    console.error('获取用户列表失败:', error)
    ElMessage.error('获取用户列表失败，请稍后重试')
  } finally {
    loading.value = false
  }
}

// 搜索
const handleSearch = () => {
  currentPage.value = 1
  fetchUserList()
}

// 重置搜索
const resetSearch = () => {
  searchForm.loginName = ''
  currentPage.value = 1
  fetchUserList()
}

// 分页相关
const handleSizeChange = (size: number) => {
  pageSize.value = size
  fetchUserList()
}

const handleCurrentChange = (page: number) => {
  currentPage.value = page
  fetchUserList()
}

// 新增用户
const handleAdd = () => {
  resetUserForm()
  dialogVisible.value = true
}

// 编辑用户
const handleEdit = (row: any) => {
  resetUserForm()
  Object.assign(userForm, row)
  userForm.password = '' // 编辑时不显示密码
  dialogVisible.value = true
}

// 删除用户
const handleDelete = (row: any) => {
  ElMessageBox.confirm('确定要删除该用户吗？', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => {
    try {
      const res = await deleteUser(row.id)
      if (res.success) {
        ElMessage.success('删除成功')
        fetchUserList()
      } else {
        ElMessage.error(res.message || '删除失败')
      }
    } catch (error) {
      console.error('删除用户失败:', error)
      ElMessage.error('删除用户失败，请稍后重试')
    }
  }).catch(() => {})
}

// 重置表单
const resetUserForm = () => {
  userForm.id = ''
  userForm.loginName = ''
  userForm.name = ''
  userForm.password = ''
  
  if (userFormRef.value) {
    userFormRef.value.resetFields()
  }
}

// 提交表单
const submitForm = async () => {
  if (!userFormRef.value) return
  
  await userFormRef.value.validate(async (valid) => {
    if (valid) {
      try {
        const isEdit = !!userForm.id
        const res = isEdit 
          ? await updateUser(userForm)
          : await createUser(userForm)
        
        if (res.success) {
          ElMessage.success(`${isEdit ? '更新' : '创建'}成功`)
          dialogVisible.value = false
          fetchUserList()
        } else {
          ElMessage.error(res.message || `${isEdit ? '更新' : '创建'}失败`)
        }
      } catch (error) {
        console.error(`${userForm.id ? '更新' : '创建'}用户失败:`, error)
        ElMessage.error(`${userForm.id ? '更新' : '创建'}用户失败，请稍后重试`)
      }
    }
  })
}

onMounted(() => {
  fetchUserList()
})
</script>

<style scoped lang="scss">
.user-container {
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
}
</style> 