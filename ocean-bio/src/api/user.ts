import request from './index'

// 用户登录
export function login(loginName: string, password: string) {
  return request({
    url: '/user/login',
    method: 'post',
    data: { loginName, password }
  })
}

// 用户注册
export function register(data: any) {
  return request({
    url: '/user/register',
    method: 'post',
    data
  })
}

// 获取用户列表
export function getUserList(params: any) {
  return request({
    url: '/user/list',
    method: 'get',
    params
  })
}

// 获取用户详情
export function getUserDetail(id: number) {
  return request({
    url: `/user/${id}`,
    method: 'get'
  })
}

// 创建用户
export function createUser(data: any) {
  return request({
    url: '/user',
    method: 'post',
    data
  })
}

// 更新用户
export function updateUser(data: any) {
  return request({
    url: '/user',
    method: 'put',
    data
  })
}

// 删除用户
export function deleteUser(id: number) {
  return request({
    url: `/user/${id}`,
    method: 'delete'
  })
} 