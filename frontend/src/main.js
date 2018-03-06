import Vue from 'vue'
import VueRouter from 'vue-router'

// Plugins
import GlobalComponents from './globalComponents'
import GlobalDirectives from './globalDirectives'
import Notifications from './components/UIComponents/NotificationPlugin'
import SideBar from './components/UIComponents/SidebarPlugin'
import App from './App'
import Vuex from 'vuex'

// router setup
import routes from './router/routes'

// library imports
import Chartist from 'chartist'
import 'bootstrap/dist/css/bootstrap.css'
import './assets/sass/paper-dashboard.scss'
import 'es6-promise/auto'
import VModal from 'vue-js-modal'
import {AXIOS} from './components/http-common'

// plugin setup
Vue.use(VueRouter)
Vue.use(VModal)
Vue.use(GlobalComponents)
Vue.use(GlobalDirectives)
Vue.use(Notifications)
Vue.use(SideBar)
Vue.use(Vuex)

// configure router
const router = new VueRouter({
  routes, // short for routes: routes
  linkActiveClass: 'active'
})

// global library setup
Object.defineProperty(Vue.prototype, '$Chartist', {
  get () {
    return this.$root.Chartist
  }
})

const LOGIN = 'LOGIN'
const LOGIN_SUCCESS = 'LOGIN_SUCCESS'
const LOGOUT = 'LOGOUT'

const store = new Vuex.Store({
  state: {
    isLoggedIn: !!localStorage.getItem('token'),
    profile: {}
  },
  mutations: {
    [LOGIN] (state) {
      state.pending = true
    },
    [LOGIN_SUCCESS] (state) {
      state.isLoggedIn = true
      state.pending = false
    },
    [LOGOUT] (state) {
      state.isLoggedIn = false
    }
  },
  actions: {
    login ({ commit }, creds) {
      commit(LOGIN) // show spinner
      AXIOS({
        method: 'post',
        url: 'http://localhost:8089/api/login',
        data: creds,
        config: { headers: { 'Content-type': 'application/x-www-form-urlencoded' } }
      }).then(function (response) {
        localStorage.setItem('token', 'Bearer ' + response.data.token)
        AXIOS.defaults.headers.common['Authorization'] = localStorage.getItem('token')
      })
      commit(LOGIN_SUCCESS)
    },
    logout ({ commit }) {
      localStorage.removeItem('token')
      commit(LOGOUT)
    }
  },
  getters: {
    isLoggedIn: state => {
      return state.isLoggedIn
    },
    profile: state => {
      return state.profile
    }
  }
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  render: h => h(App),
  router,
  store,
  data: {
    Chartist: Chartist
  }
})
