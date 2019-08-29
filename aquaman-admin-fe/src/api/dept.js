import request from '@/utils/request'

/**
 * 获取所有部门树
 * @param {*} params
 */
export function queryAllByTree() {
  return request({
    url: '/dept/all',
    method: 'get'
  })
}
export function queryById(id) {
  return request({
    url: '/dept/' + id,
    method: 'get'
  })
}
export function queryUserPageByDeptId(id) {
  return request({
    url: '/dept/user_page/' + id,
    method: 'get'
  })
}
