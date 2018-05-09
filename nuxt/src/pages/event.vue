<template>
  <transition
    appear
    enter-active-class="animated zoomIn"
    leave-active-class="animated zoomOut"
  >
    <q-page padding style="width: 1000px; max-width: 90vw;">

    </q-page>
  </transition>
</template>

<script>
import AXIOS from 'axios'
export default {
  name: 'event',
  data: function () {
    return {
      event: [],
      showMenu: []
    }
  },
  props: ['id'],
  beforeMount () {
    let self = this
    AXIOS.get('/api/event/' + this.id)
      .then(response => {
        self.event = response.data
      })
  }
}
</script>

<style scoped>

</style>
