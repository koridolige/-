// 全局类型声明

interface Window {
  echarts: any;
}

// API响应类型定义
interface ApiResponse<T = any> {
  success: boolean;
  code: number;
  data: T;
  message: string;
} 