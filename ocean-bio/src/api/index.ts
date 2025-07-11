import router from '@/router'
import axios from 'axios'
import { ElMessage } from 'element-plus'

// 创建axios实例
const service = axios.create({
  baseURL: '/api',
  timeout: 10000
})

// 请求拦截器
service.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('token')
    if (token) {
      config.headers['Authorization'] = `Bearer ${token}`
    }
    console.log('发送请求:', config.url, config.method, config.params || config.data)
    return config
  },
  (error) => {
    console.error('请求错误:', error)
    return Promise.reject(error)
  }
)

// 响应拦截器
service.interceptors.response.use(
  (response) => {
    const res = response.data
    console.log('接收响应:', response.config.url, res)
    
    // 如果返回的是文件流，直接返回
    if (response.config.responseType === 'blob') {
      return response
    }
    
    // 正常返回数据
    if (res.code === 200) {
      return {
        success: true,
        code: res.code,
        data: res.data,
        message: res.message
      }
    }
    
    // 处理特定错误码
    if (res.code === 401) {
      ElMessage.error('登录已过期，请重新登录')
      localStorage.removeItem('token')
      router.push('/login')
    } else {
      ElMessage.error(res.message || '请求失败')
    }
    
    return {
      success: false,
      code: res.code,
      data: null,
      message: res.message || '请求失败'
    }
  },
  (error) => {
    console.error('响应错误:', error)
    
    // 处理401错误
    if (error.response && error.response.status === 401) {
      ElMessage.error('登录已过期，请重新登录')
      localStorage.removeItem('token')
      router.push('/login')
    } else {
      const errorMsg = error.response?.data?.message || error.message || '请求失败，请稍后重试'
      ElMessage.error(errorMsg)
      console.error('详细错误信息:', error.response?.data || error)
    }
    
    return {
      success: false,
      code: error.response?.status || 500,
      data: null,
      message: error.message || '请求失败，请稍后重试'
    }
  }
)

export default service 