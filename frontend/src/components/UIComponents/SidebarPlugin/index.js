import Sidebar from './SideBar.vue'

const SidebarStore = {
  showSidebar: false,
  sidebarLinks: [
    {
      name: 'Főoldal',
      icon: 'ti-panel',
      path: '/fooldal'
    },
    {
      name: 'Tanulmányok',
      icon: 'ti-user',
      path: '/tanulmanyok'
    },
    {
      name: 'Események',
      icon: 'ti-view-list-alt',
      path: '/esemenyek'
    },
    {
      name: 'Rólunk',
      icon: 'ti-text',
      path: '/rolunk'
    },
    {
      name: 'Kapcsolat',
      icon: 'ti-pencil-alt2',
      path: '/kapcsolat'
    },
    {
      name: 'Table list',
      icon: 'ti-text',
      path: '/table-list'
    },
    {
      name: 'Typography',
      icon: 'ti-pencil-alt2',
      path: '/typography'
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
