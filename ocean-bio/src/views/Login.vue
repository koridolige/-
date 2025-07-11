<template>
  <div class="login-container">
    <div class="login-box">
      <div class="login-title">
        <h2>海洋生物系统</h2>
      </div>
      <el-form ref="loginFormRef" :model="loginForm" :rules="loginRules" label-width="0">
        <el-form-item prop="loginName">
          <el-input v-model="loginForm.loginName" prefix-icon="User" placeholder="请输入登录名" />
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="loginForm.password" prefix-icon="Lock" type="password" placeholder="请输入密码" show-password />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" class="login-button" :loading="loading" @click="handleLogin">登录</el-button>
        </el-form-item>
        <div class="register-link">
          <span>还没有账号？</span>
          <el-button type="text" @click="goToRegister">立即注册</el-button>
        </div>
      </el-form>
    </div>
  </div>
</template>

<script setup lang="ts">
import { useUserStore } from '@/stores/user';
import type { FormInstance, FormRules } from 'element-plus';
import { ElMessage } from 'element-plus';
import { reactive, ref } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();
const userStore = useUserStore()
const loading = ref(false)
const loginFormRef = ref<FormInstance>()
const loginForm = reactive({
  loginName: '',
  password: ''
})

const loginRules = reactive<FormRules>({
  loginName: [
    { required: true, message: '请输入登录名', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' }
  ]
})

const handleLogin = async () => {
  if (!loginFormRef.value) return
  
  await loginFormRef.value.validate(async (valid) => {
    if (valid) {
      loading.value = true
      try {
        const result = await userStore.login(loginForm.loginName, loginForm.password)
        if (!result) {
          loading.value = false
        }
      } catch (error) {
        console.error('登录失败:', error)
        ElMessage.error('登录失败，请稍后重试')
        loading.value = false
      }
    }
  })
}

const goToRegister = () => {
  router.push('/register');
}
</script>

<style scoped lang="scss">
.login-container {
  height: 100vh;
  width: 100vw;
  background-image: linear-gradient(to bottom right, #1890ff, #13c2c2);
  display: flex;
  justify-content: center;
  align-items: center;
}

.login-box {
  width: 400px;
  padding: 30px;
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.login-title {
  text-align: center;
  margin-bottom: 30px;
  
  h2 {
    color: #1890ff;
    font-size: 28px;
  }
}

.login-button {
  width: 100%;
}

.register-link {
  text-align: center;
  margin-top: 15px;
}
</style> 