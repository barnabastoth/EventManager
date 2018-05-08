<template>
  <div id="q-app">
    <router-view />
  </div>
</template>

<script>
import AXIOS from 'axios'
export default {
  name: 'App',
  beforeMount () {
    AXIOS.defaults.baseURL = 'http://localhost:8089'
    this.$store.dispatch('loadSiteSettings')
    this.$store.dispatch('logInUserWithToken')
  }
}
</script>

<style>
</style>
