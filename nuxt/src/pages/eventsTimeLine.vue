<template>
  <transition
    appear
    enter-active-class="animated zoomIn"
    leave-active-class="animated zoomOut"
  >
    <q-layout>
      <q-page-container>
        <q-page padding style="width: 1000px; max-width: 90vw;">
          <q-timeline color="red" class="shadow-24" style="padding: 24px; margin-top: 50px;">
            <q-item style="margin-bottom: 30px" class="shadow-1 bg-grey-2">
              <q-item-side left>
                <q-item-tile style="font-size: 30px;" color="primary" icon="fa-globe" />
              </q-item-side>
              <q-item-main>
                <q-item-tile style="font-size: 30px;" class="text-center" label>Események</q-item-tile>
              </q-item-main>
            </q-item>
            <q-timeline-entry
              v-for="event in events" :key="event.id"
              :title="event.name"
              :subtitle="event.date"
              :side="event.side"
            >
              <div>{{event.description}}</div>
              <q-btn size="sm" @click="$router.push('/esemeny/' + event.id)" color="primary" icon-right="fa-external-link-alt" label="Megnyitás" />
            </q-timeline-entry>
          </q-timeline>
        </q-page>
      </q-page-container>
    </q-layout>
  </transition>
</template>

<script>
import AXIOS from 'axios'
export default {
  name: 'events',
  data: function () {
    return {
      events: []
    }
  },
  beforeCreate () {
    let self = this
    AXIOS.get('/api/event/')
      .then(response => {
        self.$data.events = response.data
        for (let i = 0; i < response.data.length; i++) {
          if (i % 2 === 0) {
            self.$data.events[i].side = 'left'
          } else {
            self.$data.events[i].side = 'right'
          }
        }
      })
  }
}
</script>

<style scoped>

</style>
