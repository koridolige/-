import request from './index'

// 获取电子书列表
export function getEbookList(params: any) {
  return request({
    url: '/ebook/list',
    method: 'get',
    params
  })
}

// 获取电子书详情
export function getEbookDetail(id: number) {
  return request({
    url: `/ebook/${id}`,
    method: 'get'
  })
}

// 创建电子书
export function createEbook(data: any) {
  return request({
    url: '/ebook',
    method: 'post',
    data
  })
}

// 更新电子书
export function updateEbook(data: any) {
  return request({
    url: '/ebook',
    method: 'put',
    data
  })
}

// 删除电子书
export function deleteEbook(id: number) {
  return request({
    url: `/ebook/${id}`,
    method: 'delete'
  })
} 