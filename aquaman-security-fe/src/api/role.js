import request from '@/utils/request'

export function queryRoleMenuDetailByRoleId(id) {
  return request({
    url: '/role_menu/role_id/' + id,
    method: 'get'
  })
}

export function queryRoleByAll() {
  return request({
    url: '/role/all',
    method: 'get'
  })
}
