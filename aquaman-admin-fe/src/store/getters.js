const getters = {
  sidebar: state => state.app.sidebar,
  device: state => state.app.device,
  tagsView: state => state.app.tagsView,
  visitedViews: state => state.tagsView.visitedViews,
  cachedViews: state => state.tagsView.cachedViews,
  token: state => state.user.token,
  avatar: state => state.user.avatar,
  name: state => state.user.name,
  roles: state => state.user.roles,
  currentLoginUserMenu: state => state.menu.currentLoginUserMenu,
  privilege: state => state.menu.privilege,
  permission_routes: state => state.permission.routes
}
export default getters
