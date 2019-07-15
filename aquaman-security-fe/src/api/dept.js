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
