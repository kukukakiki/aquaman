import Layout from '@/views/layout/Layout'
import { constantRouterMap, asyncRouterMap } from '@/router/index'

export function settingSidebar(items) {
  if (items !== undefined && items !== null) {
    items.forEach(item => {
      asyncRouterMap.push({
        path: '/' + item.code,
        component: Layout,
        name: item.name,
        meta: { title: item.name },
        children: childrenComponent(item.code, item.children),
        hidden: item.display === '0'
      })
    })
  }
  return constantRouterMap.concat(asyncRouterMap)
}

function childrenComponent(rootPath, children) {
  var asyncRouterMapChildren = []
  if (children !== undefined && children !== null) {
    children.forEach(citem => {
      asyncRouterMapChildren.push({
        path: '/' + rootPath + '/' + citem.code,
        name: citem.name,
        component: () => import('@/views' + citem.router),
        meta: { title: citem.name },
        // children: cildrenButton(rootPath + '/' + citem.code, citem.children),
        hidden: citem.display === '0'
      })
      if (citem.children !== undefined && citem.children !== null && citem.children.length > 0) {
        asyncRouterMapChildren = asyncRouterMapChildren.concat(cildrenButton(rootPath, citem.children))
      }
    })
  }
  return asyncRouterMapChildren
}

function cildrenButton(rootPath, children) {
  var asyncRouterMapButton = []
  children.forEach(citem => {
    asyncRouterMapButton.push({
      path: '/' + rootPath + '/' + citem.code,
      name: citem.name,
      component: () => import('@/views' + citem.router),
      meta: { title: citem.name },
      hidden: citem.display === '0'
    })
    // 将登陆用户所有按钮权限缓存
    const userButtonAuthority = sessionStorage.getItem('user_button_authority')
    if (userButtonAuthority !== undefined && userButtonAuthority !== null) {
      sessionStorage.setItem('user_button_authority', userButtonAuthority.concat(citem.code + ','))
    } else {
      sessionStorage.setItem('user_button_authority', [].concat(citem.code + ','))
    }
  })
  return asyncRouterMapButton
}
