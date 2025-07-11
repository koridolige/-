import request from './index'

// 获取文档列表
export function getDocList(params: any) {
  const ebookId = params.ebookId
  return request({
    url: `/doc/list/${ebookId}`,
    method: 'get'
  })
}

// 获取文档详情
export function getDocDetail(id: number) {
  return request({
    url: `/doc/${id}`,
    method: 'get'
  })
}

// 创建文档
export function createDoc(data: any) {
  return request({
    url: '/doc',
    method: 'post',
    data
  })
}

// 更新文档
export function updateDoc(data: any) {
  return request({
    url: '/doc',
    method: 'put',
    data
  })
}

// 删除文档
export function deleteDoc(id: number) {
  return request({
    url: `/doc/${id}`,
    method: 'delete'
  })
}

// 获取文档内容
export function getDocContent(id: number) {
  return request({
    url: `/doc/content/${id}`,
    method: 'get'
  })
}

// 保存文档内容
export function saveDocContent(data: any) {
  return request({
    url: '/doc/content',
    method: 'post',
    data
  })
} 