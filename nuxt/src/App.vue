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
    this.$store.dispatch('logInUserWithToken')
    this.$store.dispatch('loadSiteSettings')
  }
}
</script>

<style>
</style>
