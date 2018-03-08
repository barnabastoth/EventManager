<template>
  <nav class="navbar navbar-default">
    <div class="container-fluid">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle" :class="{toggled: $sidebar.showSidebar}" @click="toggleSidebar">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar bar1"></span>
          <span class="icon-bar bar2"></span>
          <span class="icon-bar bar3"></span>
        </button>
        <a class="navbar-brand">{{routeName}}</a>
      </div>
      <div class="collapse navbar-collapse">
        <ul class="nav navbar-nav navbar-right">
         <drop-down v-if="this.$store.getters.isLoggedIn === true" title="5 Értesítés" icon="ti-bell">
           <li><a href="#">Notification 1</a></li>
           <li><a href="#">Notification 2</a></li>
           <li><a href="#">Notification 3</a></li>
           <li><a href="#">Notification 4</a></li>
           <li><a href="#">Another notification</a></li>
         </drop-down>
          <li v-if="this.$store.getters.isLoggedIn === true" class="open">
            <router-link :to="'/profil/' + $store.state.loggedInUserName">
              <a class="dropdown-toggle btn-magnify" data-toggle="dropdown">
                <i class="ti-user"></i>
                <p>{{ $store.state.loggedInUserName }}</p>
              </a>
            </router-link>

          </li>
          <li v-if="this.$store.getters.isLoggedIn === true" class="open">
            <a @click="performLogout()" href="#" class="dropdown-toggle btn-magnify" data-toggle="dropdown">
              <i class="ti-unlock"></i>
              <p>Kijelentkezés</p>
            </a>
          </li>
          <li v-if="this.$store.getters.isLoggedIn === false">
            <a @click="openLoginPage()" href="#" class="btn-rotate">
              <i class="ti-user"></i>
              <p>
                Belépés/Regisztráció
              </p>
            </a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>
<script>
  export default {
    computed: {
      routeName () {
        const {name} = this.$route
        return this.capitalizeFirstLetter(name)
      }
    },
    data () {
      return {
        activeNotifications: false
      }
    },
    methods: {
      capitalizeFirstLetter (string) {
        return string.charAt(0).toUpperCase() + string.slice(1)
      },
      toggleNotificationDropDown () {
        this.activeNotifications = !this.activeNotifications
      },
      closeDropDown () {
        this.activeNotifications = false
      },
      toggleSidebar () {
        this.$sidebar.displaySidebar(!this.$sidebar.showSidebar)
      },
      hideSidebar () {
        this.$sidebar.displaySidebar(false)
      },
      openLoginPage () {
        this.$router.push('login')
      },
      performLogout () {
        this.$store.dispatch('logout')
        this.$router.push('/login')
        this.$notifications.notify(
          {
            message: 'Sikeresen kijelentkeztél. <br> Remélem azért még találkozunk!',
            icon: 'ti-heart',
            horizontalAlign: 'center',
            verticalAlign: 'top',
            type: 'info'
          })
      }
    }
  }

</script>
<style>
  .navbar {
    -webkit-box-shadow: 0px 1px 5px 0.5px rgba(0,0,0,0.75);
    -moz-box-shadow: 0px 1px 5px 0.5px rgba(0,0,0,0.75);
    box-shadow: 0px 1px 5px 0.5px rgba(0,0,0,0.75);
  }
</style>
