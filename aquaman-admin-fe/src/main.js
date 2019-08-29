import Vue from 'vue'

import 'normalize.css/normalize.css' // A modern alternative to CSS resets

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import locale from 'element-ui/lib/locale/lang/zh-CN' // lang i18n

import '@/styles/index.scss' // global css

import App from './App'
import store from './store'
import router from './router'

import '@/icons' // icon
import '@/permission' // permission control
import dateTime from '@/utils/dateTime' // 时间格式化工具类
import logger from '@/utils/logger' // 控制台数据控制类

/**
 * This project originally used easy-mock to simulate data,
 * but its official service is very unstable,
 * and you can build your own service if you need it.
 * So here I use Mock.js for local emulation,
 * it will intercept your request, so you won't see the request in the network.
 * If you remove `../mock` it will automatically request easy-mock data.
 */
// import '../mock' // simulation data

Vue.use(ElementUI, { size: 'small', locale })
Vue.use(dateTime)
Vue.use(logger)

Vue.config.productionTip = false

// 定义全局按钮验证指令
Vue.directive('show_button', {
  bind: function(el, binding) {
    if (!Vue.prototype.$_show_button(binding.value)) {
      el.parentNode.removeChild(el)
    }
  }
})
// 权限检查方法
Vue.prototype.$_show_button = function(value) {
  let isExist = false
  const userButtonAuthority = sessionStorage.getItem('user_button_authority')
  if (userButtonAuthority === undefined || userButtonAuthority === null) {
    return false
  }
  const buttonperms = userButtonAuthority.split(',')
  for (let i = 0; i < buttonperms.length; i++) {
    if (buttonperms[i].indexOf(value) > -1) {
      isExist = true
      break
    }
  }
  return isExist
}

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
