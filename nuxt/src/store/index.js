import Vue from 'vue'
import Vuex from 'vuex'
import { Notify } from 'quasar'
import AXIOS from 'axios'
import Carousel3d from 'vue-carousel-3d'
import Vuelidate from 'vuelidate'

Vue.use(Vuex)
Vue.use(Carousel3d)
Vue.use(Vuelidate)

const LOGIN_SUCCESS = 'LOGIN_S0UCCESS'
const LOGOUT = 'LOGOUT'
const SET_SITE_SETTINGS = 'SET_SITE_SETTINGS'

const TOGGLE_LEFT_BAR = 'TOOGLE_LEFT_BAR'
const TOGGLE_RIGHT_BAR = 'TOGGLE_RIGHT_BAR'
const TOGGLE_EVENT_BAR = 'TOGGLE_EVENT_BAR'
const TOGGLE_SEND_ERROR_MODAL = 'TOGGLE_SEND_ERROR_MODAL'
const TOGGLE_AUTH_MODAL = 'TOGGLE_AUTH_MODAL'

const store = new Vuex.Store({
  state: {
    isLoggedIn: !!localStorage.getItem('token'),
    loggedInUser: [],
    siteSettings: [],
    showSendErrorModal: false,
    showAuthModal: false
  },
  mutations: {
    [LOGIN_SUCCESS] (state, user) {
      state.isLoggedIn = true
      state.loggedInUser = user
      state.rightBarOpen = true
      state.leftBarOpen = true
      state.showAuthModal = false
      AXIOS.defaults.headers.common['Authorization'] = localStorage.getItem('token')
    },
    [LOGOUT] (state) {
      state.isLoggedIn = false
      state.loggedInUser = []
    },
    [TOGGLE_LEFT_BAR] (state) {
      state.siteSettings.leftBarOpen = !state.siteSettings.leftBarOpen
    },
    [TOGGLE_RIGHT_BAR] (state) {
      state.siteSettings.rightBarOpen = !state.siteSettings.rightBarOpen
    },
    [TOGGLE_EVENT_BAR] (state) {
      state.siteSettings.eventBarOpen = !state.siteSettings.eventBarOpen
    },
    [SET_SITE_SETTINGS] (state, settings) {
      state.siteSettings = settings
    },
    [TOGGLE_SEND_ERROR_MODAL] (state) {
      state.showSendErrorModal = !state.showSendErrorModal
    },
    [TOGGLE_AUTH_MODAL] (state) {
      state.showAuthModal = !state.showAuthModal
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
    toggleEventBar ({ commit }) {
      commit(TOGGLE_EVENT_BAR)
    },
    toggleAuthModal ({ commit }) {
      commit(TOGGLE_AUTH_MODAL)
    },
    login ({ commit }, credentials) {
      AXIOS.post('/api/login', credentials)
        .then(response => {
          console.log(response.data)
          localStorage.setItem('token', 'Bearer' + response.data[0].token)
          commit(LOGIN_SUCCESS, response.data[1])
          Notify.create({
            type: 'positive',
            color: 'positive',
            position: 'bottom',
            timeout: 2000,
            message: 'Sikeresen bejelentkeztél, üdv újra köztünk!'
          })
        }).catch(error => {
          console.log(error)
          Notify.create({
            type: 'info',
            color: 'info',
            position: 'bottom',
            timeout: 4000,
            message: 'Sajnos a bejelentkezés sikertelen volt, ellenőrizd le újra a felhasználóneved és a jelszavad.'
          })
        })
    },
    logInUserWithToken ({ commit }) {
      let token = localStorage.getItem('token')
      if (token !== null) {
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
            localStorage.removeItem('token')
            commit(LOGOUT)
          })
      }
    },
    logout ({ commit }) {
      localStorage.removeItem('token')
      commit(LOGOUT)
      Notify.create({
        type: 'info',
        color: 'info',
        position: 'bottom',
        timeout: 3000,
        message: 'Sikeresen kijelentkeztél. Remélem azért még találkozunk!'
      })
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
