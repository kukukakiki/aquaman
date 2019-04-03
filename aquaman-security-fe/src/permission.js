import router from './router'
import store from './store'
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css' // progress bar style
import { Message } from 'element-ui'
import { getToken } from '@/utils/auth' // getToken from cookie
import { settingSidebar } from '@/utils/sidebar'
import { constantRouterMap } from '@/router'

NProgress.configure({ showSpinner: false })// NProgress configuration

const whiteList = ['/login'] // 不重定向白名单
router.beforeEach((to, from, next) => {
  NProgress.start()
  if (getToken()) {
    if (to.path === '/login') {
      next({ path: '/' })
      NProgress.done() // if current page is dashboard will not trigger	afterEach hook, so manually handle it
    } else {
      if (store.getters.privilege === false) {
        // 拉取登陆用户菜单信息
        store.dispatch('GetMenuByCurrentLoginUser').then(res => {
          // 动态添加菜单代码，测试需要从后台获取
          const sidebarMap = settingSidebar(res.result)
          // 合并默认菜单和后台获取的动态菜单
          sidebarMap.concat(constantRouterMap)
          // 将最后合并的菜单map复制给routes用户右侧的菜单显示
          router.options.routes = sidebarMap
          store.dispatch('permission/generateRoutes', sidebarMap)
          // 该设置是用来控制
          router.addRoutes(sidebarMap)
          // 避免页面刷新菜单重复加载，如不测试会出现：重复加载/页面死循环
          store.dispatch('SetPrivilege', { privilege: false })
          // 避免动态设置路由未生生效
          next({ ...to, replace: true })
        }).catch((err) => {
          store.dispatch('FedLogOut').then(() => {
            Message.error(err || 'Verification failed, please login again')
            next({ path: '/' })
          })
        })
      } else {
        next()
      }
    }
  } else {
    if (whiteList.indexOf(to.path) !== -1) {
      next()
    } else {
      next(`/login?redirect=${to.path}`) // 否则全部重定向到登录页
      NProgress.done()
    }
  }
})

router.afterEach(() => {
  NProgress.done() // 结束Progress
})
