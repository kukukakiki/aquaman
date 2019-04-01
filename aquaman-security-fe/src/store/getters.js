const getters = {
  sidebar: state => state.app.sidebar,
  device: state => state.app.device,
  tagsView: state => state.app.tagsView,
  token: state => state.user.token,
  avatar: state => state.user.avatar,
  name: state => state.user.name,
  roles: state => state.user.roles,
  currentLoginUserMenu: state => state.menu.currentLoginUserMenu,
  privilege: state => state.menu.privilege
}
export default getters
