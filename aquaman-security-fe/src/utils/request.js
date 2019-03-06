import axios from 'axios'
import { Message } from 'element-ui'
import store from '@/store'
import { getToken } from '@/utils/auth'
import qs from 'qs'

// create an axios instance
const service = axios.create({
  baseURL: process.env.BASE_API, // api 的 base_url
  timeout: 5000, // 请求超时时间
  withCredentials: true
})

// request interceptor
service.interceptors.request.use(
  config => {
    // Do something before request is sent
    // if (store.getters.token) {
    // 让每个请求携带token-- ['X-Token']为自定义key 请根据实际情况自行修改
    // config.headers['X-Token'] = getToken()
    // }
    // 首先从sessionStorage中获取accessToken
    const accountToken = sessionStorage.getItem('accessToken')
    // 页面级缓存store获取或sessionStorage中获取accessToken非空判断
    if (store.getters.token || accountToken !== undefined) {
      config.headers['Authorization'] = 'Bearer ' + (store.getters.token ? getToken() : accountToken) // 让每个请求携带自定义token 请根据实际情况自行修改
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
        if (config.params.formQuery !== undefined && config.params.formQuery !== null) {
          config.params = {
            ...config.params.formQuery
          }
        } else {
          config.params = {
            ...config.params.formQuery
          }
        }
      }
    }
    return config
  },
  error => {
    // Do something with request error
    console.log(error) // for debug
    Promise.reject(error)
  }
)

// response interceptor
service.interceptors.response.use(
  response => response,
  /**
   * 下面的注释为通过在response里，自定义code来标示请求状态
   * 当code返回如下情况则说明权限有问题，登出并返回到登录页
   * 如想通过 xmlhttprequest 来状态码标识 逻辑可写在下面error中
   * 以下代码均为样例，请结合自生需求加以修改，若不需要，则可删除
   */
  // response => {
  //   const res = response.data
  //   if (res.code !== 20000) {
  //     Message({
  //       message: res.message,
  //       type: 'error',
  //       duration: 5 * 1000
  //     })
  //     // 50008:非法的token; 50012:其他客户端登录了;  50014:Token 过期了;
  //     if (res.code === 50008 || res.code === 50012 || res.code === 50014) {
  //       // 请自行在引入 MessageBox
  //       // import { Message, MessageBox } from 'element-ui'
  //       MessageBox.confirm('你已被登出，可以取消继续留在该页面，或者重新登录', '确定登出', {
  //         confirmButtonText: '重新登录',
  //         cancelButtonText: '取消',
  //         type: 'warning'
  //       }).then(() => {
  //         store.dispatch('FedLogOut').then(() => {
  //           location.reload() // 为了重新实例化vue-router对象 避免bug
  //         })
  //       })
  //     }
  //     return Promise.reject('error')
  //   } else {
  //     return response.data
  //   }
  // },
  error => {
    console.log('err' + error) // for debug
    Message({
      message: error.message,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)

export default service
