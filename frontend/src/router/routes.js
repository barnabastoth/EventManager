// GeneralViews
import NotFound from '../components/GeneralViews/NotFoundPage.vue'

// Admin pages
import UserProfile from '../components/Dashboard/Views/UserProfile.vue'
import AllEvents from '../components/Dashboard/Views/AllEvents'
import Icons from '../components/Dashboard/Views/Icons.vue'
import Maps from '../components/Dashboard/Views/Maps.vue'
import Vue from 'vue'
import Router from 'vue-router'
import Home from '../components/Dashboard/Views/Home'
import Content from '../components/Dashboard/Layout/Content'
import Event from '../components/Dashboard/Views/Event'
import Login from '../components/Dashboard/Views/Authentication/Login'

Vue.use(Router)

const routes = [
  {
    path: '/',
    component: Content,
    redirect: 'fooldal',
    children: [
      {
        path: 'fooldal',
        name: 'Főoldal',
        component: Home
      },
      {
        path: 'tanulmanyok',
        name: 'Tanulmányok',
        component: UserProfile
      },
      {
        path: 'esemenyek',
        name: 'Események',
        component: AllEvents
      },
      {
        path: 'rolunk',
        name: 'Rólunk',
        component: Icons
      },
      {
        path: 'kapcsolat',
        name: 'Kapcsolat',
        component: Maps
      },
      {
        path: 'esemeny/:id',
        name: 'Esemény',
        component: Event,
        props: true
      }, {
        path: 'login',
        name: 'Belépés/Regisztráció',
        component: Login
      }
    ]
  },
  { path: '*', component: NotFound }
]

/**
 * Asynchronously load view (Webpack Lazy loading compatible)
 * The specified component must be inside the Views folder
 * @param  {string} name  the filename (basename) of the view to load.
function view(name) {
   var res= require('../components/Dashboard/Views/' + name + '.vue');
   return res;
};**/

export default routes
