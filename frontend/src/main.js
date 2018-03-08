import Vue from 'vue'
import VueRouter from 'vue-router'

// Plugins
import GlobalComponents from './globalComponents'
import GlobalDirectives from './globalDirectives'
import Notifications from './components/UIComponents/NotificationPlugin'
import SideBar from './components/UIComponents/SidebarPlugin'
import App from './App'
import Vuex from 'vuex'
import PaperNotification from 'src/components/UIComponents/NotificationPlugin/Notification.vue'

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
    loggedInUserName: ''
  },
  mutations: {
    [LOGIN] (state) {
      state.pending = true
    },
    [LOGIN_SUCCESS] (state, profile) {
      state.isLoggedIn = true
      state.pending = false
      state.loggedInUserId = profile.id
      state.loggedInUserName = profile.username
    },
    [LOGOUT] (state) {
      state.isLoggedIn = false
    }
  },
  actions: {
    login ({ commit }, response) {
      localStorage.setItem('token', 'Bearer ' + response.data[0].token)
      localStorage.setItem('userName', response.data[1].username)
      AXIOS.defaults.headers.common['Authorization'] = localStorage.getItem('token')
      commit(LOGIN_SUCCESS, response.data[1])
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
    loggedInUserName: state => {
      return state.loggedInUserName
    }
  }
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  render: h => h(App),
  router,
  store,
  PaperNotification,
  data: {
    Chartist: Chartist
  },
  methods: {
    notify () {
      this.$notifications.notify(
        {
          message: 'Üdv újra köztünk! <br> Sikeresen bejelentkeztél!',
          icon: 'ti-heart',
          horizontalAlign: 'center',
          verticalAlign: 'top',
          type: 'success'
        })
    }
  },
  created () {
    store.state.loggedInUserName = localStorage.getItem('userName')
    // if (this.$store.getters.loggedInUserName == null) {
    //   this.$store.dispatch('logout')
    //   this.$router.push('/login')
    //   this.$notifications.notify(
    //     {
    //       message: 'Upsz, valami hiba történt, kérlek jelentkezz be újra.!',
    //       icon: 'ti-thought',
    //       horizontalAlign: 'center',
    //       verticalAlign: 'top',
    //       type: 'info'
    //     })
    // }
  }
})
