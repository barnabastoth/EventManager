import DashboardLayout from '../components/Dashboard/Layout/DashboardLayout.vue'
// GeneralViews
import NotFound from '../components/GeneralViews/NotFoundPage.vue'

// Admin pages
import Overview from '../components/Dashboard/Views/Overview.vue'
import UserProfile from '../components/Dashboard/Views/UserProfile.vue'
import Notifications from '../components/Dashboard/Views/Notifications.vue'
import Icons from '../components/Dashboard/Views/Icons.vue'
import Maps from '../components/Dashboard/Views/Maps.vue'
import Typography from '../components/Dashboard/Views/Typography.vue'
import TableList from '../components/Dashboard/Views/TableList.vue'
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

const routes = [
  {
    path: '/',
    component: DashboardLayout,
    redirect: 'fooldal'
  },
  {
    path: '/',
    component: DashboardLayout,
    children: [
      {
        path: 'fooldal',
        name: 'Főoldal',
        component: Overview
      },
      {
        path: 'tanulmanyok',
        name: 'Tanulmányok',
        component: UserProfile
      },
      {
        path: 'esemenyek',
        name: 'Események',
        component: Notifications
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
      }, {
        path: 'typography',
        name: 'typography',
        component: Typography
      },
      {
        path: 'table-list',
        name: 'table-list',
        component: TableList
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
