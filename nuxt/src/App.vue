<template>
  <div id="q-app">
    <router-view />
  </div>
</template>

<script>
export default {
  name: 'App',
  created () {
    this.$store.dispatch('logInUserWithToken')
    this.$store.dispatch('loadSiteSettings')
  }
}
</script>

<style>
</style>
