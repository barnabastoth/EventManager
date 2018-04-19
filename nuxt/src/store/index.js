import Vue from 'vue'
import Vuex from 'vuex'
import { Notify } from 'quasar'
import AXIOS from 'axios'

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
    [LOGIN_SUCCESS] (state, response) {
      localStorage.setItem('Bearer ', response[0].token)
      state.isLoggedIn = true
      state.loggedInUser = response[1]
      state.rightBarOpen = true
      state.leftBarOpen = true
    },
    [LOGOUT] (state) {
      state.isLoggedIn = false
      localStorage.removeItem('Bearer ')
    }
  },
  actions: {
    toggleLeftBar ({ commit }) {
      commit(TOGGLE_LEFT_BAR)
    },
    toggleRightBar ({ commit }) {
      commit(TOGGLE_RIGHT_BAR)
    },
    login ({ commit }, credentials) {
      AXIOS.post('http://localhost:8089/api/login', credentials)
        .then(function (response) {
          console.log(response)
          commit(LOGIN_SUCCESS, response.data)
          Notify.create({
            type: 'positive',
            color: 'positive',
            position: 'bottom',
            timeout: 3000,
            message: 'Sikeresen bejelentkeztél, üdv újra köztünk!'
          })
        }).catch(function (error) {
          console.log(error)
          Notify.create({
            type: 'positive',
            color: 'positive',
            position: 'bottom',
            timeout: 3000,
            message: 'Sikeresen bejelentkeztél, üdv újra köztünk!'
          })
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
