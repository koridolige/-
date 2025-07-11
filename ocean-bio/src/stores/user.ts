import { login } from '@/api/user'
import router from '@/router'
import { ElMessage } from 'element-plus'
import { defineStore } from 'pinia'

export const useUserStore = defineStore('user', {
  state: () => ({
    token: localStorage.getItem('token') || '',
    userInfo: null as any
  }),
  
  actions: {
    async login(loginName: string, password: string) {
      try {
        const res = await login(loginName, password)
        if (res.success) {
          this.token = res.data
          localStorage.setItem('token', res.data)
          await this.getUserInfo()
          router.push('/main')
          ElMessage.success('登录成功')
          return true
        } else {
          ElMessage.error(res.message || '登录失败')
          return false
        }
      } catch (error) {
        console.error('登录失败:', error)
        ElMessage.error('登录失败，请稍后重试')
        return false
      }
    },
    
    async getUserInfo() {
      if (!this.token) return
      
      try {
        // 这里假设后端有获取当前用户信息的接口
        // const res = await getUserInfo()
        // if (res.success) {
        //   this.userInfo = res.data
        // }
        
        // 暂时模拟用户信息
        this.userInfo = {
          id: 1,
          loginName: 'admin',
          name: '管理员'
        }
      } catch (error) {
        console.error('获取用户信息失败:', error)
      }
    },
    
    logout() {
      this.token = ''
      this.userInfo = null
      localStorage.removeItem('token')
      router.push('/login')
      ElMessage.success('已退出登录')
    }
  }
}) 