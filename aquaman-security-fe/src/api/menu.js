import request from '@/utils/request'

/**
 * 用户列表查询
 * @param {*} params
 */
export function queryByTree(query) {
  return request({
    url: '/menu',
    method: 'get',
    params: {
      query
    }
  })
}

/**
 * 查询菜单详情
 */
export function queryById(id) {
  return request({
    url: '/menu/' + id,
    method: 'get'
  })
}
