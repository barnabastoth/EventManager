<template>
  <transition
    appear
    enter-active-class="animated zoomIn"
    leave-active-class="animated zoomOut"
  >
    <q-page class="flex flex-center">
      {{this.$store.state.siteSettings.eventBarOpen}}
      <img alt="Quasar logo" src="../assets/quasar-logo-full.svg">
    </q-page>
  </transition>
</template>

<style>
</style>

<script>
export default {
  name: 'PageIndex'
}
</script>
