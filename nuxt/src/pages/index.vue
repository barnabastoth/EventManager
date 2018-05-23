<template>
  <transition
    appear
    enter-active-class="animated zoomIn"
    leave-active-class="animated zoomOut"
  >
    <q-page class="flex flex-center">
      <eventBar></eventBar>
    </q-page>
  </transition>
</template>

<style>
</style>

<script>
import eventBar from '../components/eventBar'
export default {
  name: 'PageIndex',
  components: {
    eventBar
  }
}
</script>
