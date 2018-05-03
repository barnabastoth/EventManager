import Vue from 'vue'
import Vuex from 'vuex'
import { Notify } from 'quasar'
import AXIOS from 'axios'

Vue.use(Vuex)

const TOGGLE_LEFT_BAR = 'TOOGLE_LEFT_BAR'
const TOGGLE_RIGHT_BAR = 'TOGGLE_RIGHT_BAR'
const LOGIN_SUCCESS = 'LOGIN_SUCCESS'
const LOGOUT = 'LOGOUT'
const SET_SITE_SETTINGS = 'SET_SITE_SETTINGS'
const TOGGLE_SEND_ERROR_MODAL = 'TOGGLE_SEND_ERROR_MODAL'

const store = new Vuex.Store({
  state: {
    isLoggedIn: !!localStorage.getItem('Bearer '),
    loggedInUser: [],
    siteSettings: [],
    showSendErrorModal: false
  },
  mutations: {
    [TOGGLE_LEFT_BAR] (state) {
      state.siteSettings.leftBarOpen = !state.siteSettings.leftBarOpen
    },
    [TOGGLE_RIGHT_BAR] (state) {
      state.siteSettings.rightBarOpen = !state.siteSettings.rightBarOpen
    },
    [LOGIN_SUCCESS] (state, user) {
      state.isLoggedIn = true
      state.loggedInUser = user
      state.rightBarOpen = true
      state.leftBarOpen = true
    },
    [LOGOUT] (state) {
      state.isLoggedIn = false
      state.loggedInUser = []
    },
    [SET_SITE_SETTINGS] (state, settings) {
      state.siteSettings = settings
    },
    [TOGGLE_SEND_ERROR_MODAL] (state) {
      state.showSendErrorModal = !state.showSendErrorModal
    }
  },
  actions: {
    toggleSendErrorModal ({ commit }) {
      commit(TOGGLE_SEND_ERROR_MODAL)
    },
    toggleLeftBar ({ commit }) {
      commit(TOGGLE_LEFT_BAR)
    },
    toggleRightBar ({ commit }) {
      commit(TOGGLE_RIGHT_BAR)
    },
    login ({ commit }, credentials) {
      return new Promise((resolve, reject) => {
        AXIOS.post('/api/login', credentials)
          .then(response => {
            localStorage.setItem('Bearer ', response.data[0].token)
            commit(LOGIN_SUCCESS, response.data[1])
            Notify.create({
              type: 'positive',
              color: 'positive',
              position: 'bottom',
              timeout: 2000,
              message: 'Sikeresen bejelentkeztél, üdv újra köztünk!'
            })
            resolve()
          }).catch(error => {
            console.log(error)
            Notify.create({
              type: 'info',
              color: 'info',
              position: 'bottom',
              timeout: 3000,
              message: 'Sajnos a bejelentkezés sikertelen volt, ellenőrizd le újra a felhasználóneved és jelszavad.'
            })
            reject(error)
          })
      })
    },
    logInUserWithToken ({ commit }) {
      let token = localStorage.getItem('Bearer ')
      if (token !== null) {
        let self = this
        AXIOS.post('/api/me', token)
          .then(response => {
            commit(LOGIN_SUCCESS, response.data)
          })
          .catch(error => {
            console.log(error)
            Notify.create({
              type: 'info',
              color: 'info',
              position: 'bottom',
              timeout: 2000,
              message: 'A Tokened lejárt, kérlek lépj be újra az oldalra.'
            })
            self.logout()
          })
      }
    },
    logout ({ commit }) {
      localStorage.removeItem('Bearer ')
      commit(LOGOUT)
      Notify.create({
        type: 'info',
        color: 'info',
        position: 'bottom',
        timeout: 3000,
        message: 'Sikeresen kijelentkeztél. Remélem azért még találkozunk!'
      })
      this.$router.push('/bejelentkezes')
    },
    loadSiteSettings ({ commit }) {
      AXIOS.get('/api/siteSettings')
        .then(response => {
          commit(SET_SITE_SETTINGS, response.data)
        })
    }
  }
})

export default store
