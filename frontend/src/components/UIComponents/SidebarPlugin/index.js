import Sidebar from './SideBar.vue'

const SidebarStore = {
  showSidebar: false,
  sidebarLinks: [
    {
      name: 'Főoldal',
      icon: 'ti-home',
      path: '/fooldal'
    },
    {
      name: 'Tanulmányok',
      icon: 'ti-book',
      path: '/tanulmanyok'
    },
    {
      name: 'Események',
      icon: 'ti-world',
      path: '/esemenyek'
    },
    {
      name: 'Rólunk',
      icon: 'ti-text',
      path: '/rolunk'
    },
    {
      name: 'Kapcsolat',
      icon: 'ti-email',
      path: '/kapcsolat'
    },
    {
      name: 'Bejelentkezés',
      icon: 'ti-email',
      path: '/login'
    }
  ],
  displaySidebar (value) {
    this.showSidebar = value
  }
}

const SidebarPlugin = {

  install (Vue) {
    Vue.mixin({
      data () {
        return {
          sidebarStore: SidebarStore
        }
      }
    })

    Object.defineProperty(Vue.prototype, '$sidebar', {
      get () {
        return this.$root.sidebarStore
      }
    })
    Vue.component('side-bar', Sidebar)
  }
}

export default SidebarPlugin
