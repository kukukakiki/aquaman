import request from '@/utils/request'

export function getMenuByCurrentLoginUser() {
  return request({
    url: '/user/menu',
    method: 'get'
  })
}
