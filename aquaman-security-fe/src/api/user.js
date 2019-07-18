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
 * 根据用户ID获取用户角色关系
 */
export function getUserRoleByUserId(userId) {
  return request({
    url: '/user_role/user_id/' + userId,
    method: 'get'
  })
}

