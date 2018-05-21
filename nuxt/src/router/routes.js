import store from '../store'
import router from '../router'
import { Notify } from 'quasar'

export default [
  {
    path: '/',
    component: () => import('layouts/default'),
    children: [
      {
        path: '',
        component: () => import('pages/index')
      },
      {
        path: '/bejelentkezes',
        component: () => import('pages/auth'),
        beforeEnter: (to, from, next) => {
          if (store.state.isLoggedIn) {
            router.push('/noAccess')
            Notify.create({
              type: 'info',
              color: 'info',
              position: 'bottom',
              timeout: 3000,
              message: 'Már bevagy jelentkezve.!'
            })
          }
          next()
        }
      },
      {
        path: '/projekt',
        component: () => import('pages/theProject')
      },
      {
        path: '/kapcsolat',
        component: () => import('pages/contact')
      },
      {
        path: '/idovonal',
        component: () => import('pages/eventsTimeLine')
      },
      {
        path: '/felhasznalo/:username',
        props: true,
        component: () => import('pages/profile')
      },
      {
        path: '/felhasznalo/:username/szerkesztes',
        props: true,
        component: () => import('pages/editProfile')
      },
      { path: '/esemeny/uj',
        component: () => import('pages/newEvent')
      },
      {
        path: '/esemeny/:id',
        props: true,
        component: () => import('pages/event')
      },
      {
        path: '/esemeny/:id/szerkesztes',
        props: true,
        component: () => import('pages/editEvent'),
        beforeEnter: (to, from, next) => {
          if (store.state.loggedInUser.role.role === 'Admin' ||
            store.state.loggedInUser.role.role === 'Tulajdonos') {
            next()
          } else {
            router.push('/noAccess')
          }
        }
      },
      {
        path: '/admin',
        component: () => import('pages/adminPanel'),
        beforeEnter: (to, from, next) => {
          if (store.state.loggedInUser.role.role === 'Admin' ||
            store.state.loggedInUser.role.role === 'Tulajdonos') {
            next()
          } else {
            router.push('/noAccess')
          }
        }
      }
    ]
  },
  {
    path: '/noAccess',
    component: () => import('pages/noAccess')
  },

  { // Always leave this as last one
    path: '*',
    component: () => import('pages/404')
  }
]
