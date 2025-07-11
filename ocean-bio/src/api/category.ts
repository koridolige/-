import request from './index';

// 分类接口响应类型
export interface Category {
  id: number;
  parent: number;
  name: string;
  sort: number;
}

// 获取分类列表
export function getCategoryList(params: any) {
  return request({
    url: '/category/list',
    method: 'get',
    params
  })
}

// 获取分类详情
export function getCategoryDetail(id: number) {
  return request({
    url: `/category/${id}`,
    method: 'get'
  })
}

// 创建分类
export function createCategory(data: any) {
  return request({
    url: '/category',
    method: 'post',
    data
  })
}

// 更新分类
export function updateCategory(data: any) {
  return request({
    url: '/category',
    method: 'put',
    data
  })
}

// 删除分类
export function deleteCategory(id: number) {
  return request({
    url: `/category/${id}`,
    method: 'delete'
  })
} 