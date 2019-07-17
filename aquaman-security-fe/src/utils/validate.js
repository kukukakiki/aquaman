/**
 * Created by jiachenpan on 16/11/18.
 */
import { Message } from 'element-ui'

export function isExternal(path) {
  return /^(https?:|mailto:|tel:)/.test(path)
}

/**
 * 非空字符串校验
 * @param {*} val
 */
export function isNotEmpty(val) {
  return val !== undefined && val !== null && val !== ''
}

/**
 * 非空数字校验
 * @param {*} val
 */
export function isCollectionNotEmpty(val) {
  return val !== undefined && val !== null && val !== '' && val.length > 0
}

/**
 * 返回校验
 * @param {*} val
 */
export function resultValidate(val) {
  return val === '0000'
}

/**
 * 返回校验
 * @param {*} response
 * @param {*} isShow
 */
export function resultSuccessShowMsg(response, isShow) {
  if (resultValidate(response.code)) {
    Message({
      message: response.msg,
      type: 'success',
      duration: 5 * 1000
    })
  }
}
