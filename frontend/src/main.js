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
    [LOGIN_SUCCESS] (state, profile) {
      state.isLoggedIn = true
      state.pending = false
      state.profile = profile
    },
    [LOGOUT] (state) {
      state.isLoggedIn = false
    }
  },
  actions: {
    login ({ commit }, profile) {
      commit(LOGIN_SUCCESS, profile) // show spinner
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
  }
})
