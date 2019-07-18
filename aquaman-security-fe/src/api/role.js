import request from '@/utils/request'

/**
 * 根据角色ID获取角色对应的菜单
 */
export function queryRoleMenuDetailByRoleId(id) {
  return request({
    url: '/role_menu/role_id/' + id,
    method: 'get'
  })
}

/**
 * 获取所有角色不分页
 */
export function queryRoleByAll() {
  return request({
    url: '/role/all',
    method: 'get'
  })
}

/**
 * 根据用户ID获取用户所属角色集合
 */
export function getRoleByUserId(userId) {
  return request({
    url: '/role/user_role/' + userId,
    method: 'get'
  })
}
