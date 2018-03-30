import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const TOGGLE_LEFT_BAR = 'TOOGLE_LEFT_BAR'
const TOGGLE_RIGHT_BAR = 'TOGGLE_RIGHT_BAR'

const store = new Vuex.Store({
  state: {
    isLoggedIn: !!localStorage.getItem('token'),
    loggedInUser: [],
    leftBarOpen: true,
    rightBarOpen: true
  },
  mutations: {
    [TOGGLE_LEFT_BAR] (state) {
      state.leftBarOpen = !state.leftBarOpen
    },
    [TOGGLE_RIGHT_BAR] (state) {
      state.rightBarOpen = !state.rightBarOpen
    }
  },
  actions: {
    toggleLeftBar ({ commit }) {
      commit(TOGGLE_LEFT_BAR)
    },
    toggleRightBar ({ commit }) {
      commit(TOGGLE_RIGHT_BAR)
    }
  }
})

export default store
