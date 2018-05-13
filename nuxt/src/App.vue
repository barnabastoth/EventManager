<template>
  <div id="q-app">
    <router-view />
  </div>
</template>

<script>
import AXIOS from 'axios'
export default {
  name: 'App',
  beforeCreate () {
    AXIOS.defaults.baseURL = 'http://localhost:8089'
    this.$store.dispatch('logInUserWithToken').then(() => {
      this.$store.dispatch('loadSiteSettings')
    })
  }
}
</script>

<style>
</style>
