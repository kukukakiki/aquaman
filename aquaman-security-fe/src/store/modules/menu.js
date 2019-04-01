import { getMenuByCurrentLoginUser } from '@/api/user'

const menu = {
  state: {
    currentLoginUserMenu: [],
    privilege: false
  },

  mutations: {
    SET_CURRENT_LOGIN_USER_MENU: (state, currentLoginUserMenu) => {
      state.currentLoginUserMenu = currentLoginUserMenu
    },
    SET_PRIVILEGE: (state, privilege) => {
      state.privilege = privilege
    }
  },

  actions: {
    // 登录
    GetMenuByCurrentLoginUser({ commit }) {
      return new Promise((resolve, reject) => {
        getMenuByCurrentLoginUser().then(response => {
          // commit('SET_CURRENT_LOGIN_USER_MENU', data.token)
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },
    SetPrivilege({ commit }, privilege) {
      commit('SET_PRIVILEGE', privilege)
    }
  }
}

export default menu
