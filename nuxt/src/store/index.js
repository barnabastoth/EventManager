import Vue from 'vue'
import Vuex from 'vuex'
import { Notify } from 'quasar'

Vue.use(Vuex)

const TOGGLE_LEFT_BAR = 'TOOGLE_LEFT_BAR'
const TOGGLE_RIGHT_BAR = 'TOGGLE_RIGHT_BAR'
const LOGIN_SUCCESS = 'LOGIN_SUCCESS'
const LOGOUT = 'LOGOUT'

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
    },
    [LOGIN_SUCCESS] (state, user) {
      state.loggedInUser = user
      state.isLoggedIn = true
    },
    [LOGOUT] (state) {
      state.isLoggedIn = false
    }
  },
  actions: {
    toggleLeftBar ({ commit }) {
      commit(TOGGLE_LEFT_BAR)
    },
    toggleRightBar ({ commit }) {
      commit(TOGGLE_RIGHT_BAR)
    },
    login ({ commit }, user) {
      commit(LOGIN_SUCCESS, user)
      Notify.create({
        type: 'positive',
        color: 'positive',
        position: 'bottom',
        timeout: 3000,
        message: 'Sikeresen bejelentkeztél, üdv újra köztünk!'
      })
    },
    logout ({ commit }) {
      Notify.create({
        type: 'info',
        color: 'info',
        position: 'bottom',
        timeout: 3000,
        message: 'Sikeresen kijelentkeztél. Remélem azért még találkozunk!'
      })
      commit(LOGOUT)
    }
  }
})

export default store
