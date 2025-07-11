import request from './index'

/**
 * 生成快照
 */
export function genSnapshot() {
  return request({
    url: '/ebook-snapshot/gen',
    method: 'get'
  })
}

/**
 * 获取统计数据
 */
export function getStatistic() {
  return request({
    url: '/ebook-snapshot/get-statistic',
    method: 'get'
  })
}

/**
 * 获取30天快照统计
 */
export function getStatistic30() {
  return request({
    url: '/ebook-snapshot/get-30-statistic',
    method: 'get'
  })
}

/**
 * 根据ID获取快照
 * @param id 快照ID
 */
export function getEbookSnapshot(id: number) {
  return request({
    url: `/ebook-snapshot/${id}`,
    method: 'get'
  })
}

/**
 * 创建快照
 * @param data 快照数据
 */
export function createEbookSnapshot(data: any) {
  return request({
    url: '/ebook-snapshot',
    method: 'post',
    data
  })
}

/**
 * 更新快照
 * @param data 快照数据
 */
export function updateEbookSnapshot(data: any) {
  return request({
    url: '/ebook-snapshot',
    method: 'put',
    data
  })
}

/**
 * 删除快照
 * @param id 快照ID
 */
export function deleteEbookSnapshot(id: number) {
  return request({
    url: `/ebook-snapshot/${id}`,
    method: 'delete'
  })
} 