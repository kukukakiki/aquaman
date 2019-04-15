import request from '@/utils/request'

/**
 * 查询分页
 * @param {*} type
 * @param {*} query
 */
export function queryByPage(type, query) {
  return request({
    url: '/' + type,
    method: 'get',
    params: {
      query
    }
  })
}

/**
 * 查询详情
 * @param {*} type
 * @param {*} id
 */
export function queryById(type, id) {
  return request({
    url: '/' + type + '/' + id,
    method: 'get'
  })
}

/**
 * 保存
 * @param {*} type
 * @param {*} form
 */
export function save(type, form) {
  return request({
    url: '/' + type,
    method: 'post',
    data: {
      form
    }
  })
}

/**
 * 修改
 * @param {*} type
 * @param {*} form
 */
export function edit(type, form) {
  return request({
    url: '/' + type,
    method: 'put',
    data: {
      form
    }
  })
}
