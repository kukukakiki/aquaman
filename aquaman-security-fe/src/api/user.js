import request from '@/utils/request'

/**
 * 查询当前用户菜单
 */
export function getMenuByCurrentLoginUser() {
  return request({
    url: '/user/menu',
    method: 'get'
  })
}

/**
 * 用户列表查询
 * @param {*} params
 */
export function queryUserByPage(query) {
  return request({
    url: '/user',
    method: 'get',
    params: {
      query
    }
  })
}
