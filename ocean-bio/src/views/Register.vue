<template>
  <div class="register-container">
    <div class="register-box">
      <div class="register-title">
        <h2>用户注册</h2>
      </div>
      <el-form ref="registerFormRef" :model="registerForm" :rules="registerRules" label-width="80px">
        <el-form-item label="登录名" prop="loginName">
          <el-input v-model="registerForm.loginName" placeholder="请输入登录名" />
        </el-form-item>
        <el-form-item label="昵称" prop="name">
          <el-input v-model="registerForm.name" placeholder="请输入昵称" />
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="registerForm.password" type="password" placeholder="请输入密码" show-password />
        </el-form-item>
        <el-form-item label="确认密码" prop="confirmPassword">
          <el-input v-model="registerForm.confirmPassword" type="password" placeholder="请再次输入密码" show-password />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" class="register-button" :loading="loading" @click="handleRegister">注册</el-button>
        </el-form-item>
        <div class="login-link">
          <span>已有账号？</span>
          <el-button type="text" @click="goToLogin">返回登录</el-button>
        </div>
      </el-form>
    </div>
  </div>
</template>

<script setup lang="ts">
import { register } from '@/api/user';
import type { FormInstance, FormRules } from 'element-plus';
import { ElMessage } from 'element-plus';
import { reactive, ref } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();
const loading = ref(false);
const registerFormRef = ref<FormInstance>();
const registerForm = reactive({
  loginName: '',
  name: '',
  password: '',
  confirmPassword: ''
});

// 校验两次密码是否一致
const validateConfirmPassword = (rule: any, value: string, callback: any) => {
  if (value !== registerForm.password) {
    callback(new Error('两次输入的密码不一致'));
  } else {
    callback();
  }
};

const registerRules = reactive<FormRules>({
  loginName: [
    { required: true, message: '请输入登录名', trigger: 'blur' },
    { min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }
  ],
  name: [
    { required: true, message: '请输入昵称', trigger: 'blur' },
    { min: 2, max: 20, message: '长度在 2 到 20 个字符', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 6, message: '密码长度不能小于6位', trigger: 'blur' }
  ],
  confirmPassword: [
    { required: true, message: '请再次输入密码', trigger: 'blur' },
    { validator: validateConfirmPassword, trigger: 'blur' }
  ]
});

const handleRegister = async () => {
  if (!registerFormRef.value) return;
  
  await registerFormRef.value.validate(async (valid) => {
    if (valid) {
      loading.value = true;
      try {
        // 去掉确认密码字段
        const { confirmPassword, ...userData } = registerForm;
        
        const res = await register(userData);
        if (res.success) {
          ElMessage.success('注册成功，请登录');
          router.push('/login');
        } else {
          ElMessage.error(res.message || '注册失败');
        }
      } catch (error) {
        console.error('注册失败:', error);
        ElMessage.error('注册失败，请稍后重试');
      } finally {
        loading.value = false;
      }
    }
  });
};

const goToLogin = () => {
  router.push('/login');
};
</script>

<style scoped lang="scss">
.register-container {
  height: 100vh;
  width: 100vw;
  background-image: linear-gradient(to bottom right, #1890ff, #13c2c2);
  display: flex;
  justify-content: center;
  align-items: center;
}

.register-box {
  width: 500px;
  padding: 30px;
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.register-title {
  text-align: center;
  margin-bottom: 30px;
  
  h2 {
    color: #1890ff;
    font-size: 28px;
  }
}

.register-button {
  width: 100%;
}

.login-link {
  text-align: center;
  margin-top: 15px;
}
</style> 