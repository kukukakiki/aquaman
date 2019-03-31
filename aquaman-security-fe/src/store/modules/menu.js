import { getMenuByCurrentLoginUser } from '@/api/user'

const menu = {
  state: {
    currentLoginUserMenu: []
  },

  mutations: {
    SET_CURRENT_LOGIN_USER_MENU: (state, currentLoginUserMenu) => {
      state.currentLoginUserMenu = currentLoginUserMenu
    }
  },

  actions: {
    // 登录
    GetMenuByCurrentLoginUser({ commit }) {
      return new Promise((resolve, reject) => {
        getMenuByCurrentLoginUser().then(response => {
          const data = response.result
          console.log(data)
          // commit('SET_CURRENT_LOGIN_USER_MENU', data.token)
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    }
  }
}

export default menu
