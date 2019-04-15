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
