import axios from 'axios'
import { Message, MessageBox } from 'element-ui'
import store from '../store'
import { getToken } from '@/utils/auth'
import Vue from 'vue'
import qs from 'qs'

// 创建axios实例
const service = axios.create({
  baseURL: process.env.BASE_API, // api 的 base_url
  timeout: 5000, // 请求超时时间
  withCredentials: true
})
let loading = null
// request拦截器
service.interceptors.request.use(
  config => {
    if (store.getters.token) {
      config.headers['Authorization'] = 'Bearer ' + getToken() // 让每个请求携带自定义token 请根据实际情况自行修改
    } else {
      config.headers['Authorization'] = process.env.HEANER_AUTHO_BASIC
    }
    config.headers['Content-Type'] = 'application/x-www-form-urlencoded'
    if (config.method === 'post' || config.method === 'put') {
      if (config.data !== undefined && config.data !== null) {
        if (config.data.form !== undefined && config.data.form !== null) {
          config.data = qs.stringify({
            ...config.data.form
          })
        } else {
          config.data = qs.stringify({
            ...config.data
          })
        }
      }
    } else if (config.method === 'get') {
      if (config.params !== undefined && config.params !== null) {
        if (config.params.query !== undefined && config.params.query !== null) {
          config.params = {
            ...config.params.query === '' ? null : config.params.query
          }
        } else {
          Message({
            message: '查询入参必须为Query对象',
            type: 'error',
            duration: 5 * 1000
          })
        }
      }
      // 开启loading遮照
      loading = Vue.prototype.$loading({
        lock: true,
        text: '加载中'
      })
    }
    return config
  },
  error => {
    // Do something with request error
    console.log(error) // for debug
    Promise.reject(error)
  }
)

// response 拦截器
service.interceptors.response.use(
  response => {
    closeLoading()
    /**
     * code为非0000是抛错 可结合自己业务进行修改
     */
    const res = response.data
    if (res.code !== '0000') {
      Message({
        message: res.message,
        type: 'error',
        duration: 5 * 1000
      })

      // 1003:用户名Token失效;
      if (res.code === '1002') {
        MessageBox.confirm(
          '你已被登出，可以取消继续留在该页面，或者重新登录',
          '确定登出',
          {
            confirmButtonText: '重新登录',
            cancelButtonText: '取消',
            type: 'warning'
          }
        ).then(() => {
          store.dispatch('FedLogOut').then(() => {
            location.reload() // 为了重新实例化vue-router对象 避免bug
          })
        })
      }
      return Promise.reject('error')
    } else {
      return response.data
    }
  },
  error => {
    console.log('err' + error) // for debug
    closeLoading()
    Message({
      message: error.message,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)

/**
 * 关闭loading遮照
 */
function closeLoading() {
  if (loading !== null) {
    // 解决访问速度过快，视觉闪屏的问题
    setTimeout(() => {
      loading.close()
    }, 1000)
  }
}

export default service
