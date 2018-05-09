<template>
  <transition
    appear
    enter-active-class="animated zoomIn"
    leave-active-class="animated zoomOut"
  >
    <carousel-3d
      :disable3d="true" border="0"
      :inverse-scaling="1000"
      :space="400"
      :controls-visible="true"
      height="500px"
      width="300"
      animation-speed="1000"
      :autoplay-timeout="3000"
      :autoplay="true"
      start-index="0"
      utoplayHoverPause="true"
    >
      <slide style="background-color: white" :index="index " v-for="(event, index) in events" :key="event.id">
        <q-card inline class="q-ma-sm shadow-10" style="height:370px; width: 285px;">
          <q-btn style="width: 100%" icon-right="fa-external-link-alt" @click="$router.push('/esemeny/' + event.id)" color="primary">Esemény megnyitása</q-btn>
          <q-card-media>
            <img style="display: block;
                           width: auto;
                           height: auto;
                           max-height: 200px" src="statics/parallax2.jpg">
          </q-card-media>
          <q-list>
            <q-item>
              <q-item-side>
                <q-item-tile color="primary" icon="local bar" />
              </q-item-side>
              <q-item-main>
                <q-item-tile label>{{event.name}}</q-item-tile>
                <!--<q-item-tile sublabel>{{event.date}}</q-item-tile>-->
              </q-item-main>
            </q-item>
            <q-item>
              <q-item-side>
                <q-item-tile color="red" icon="fa-sender-md" />
              </q-item-side>
              <q-item-main>
                <q-item-tile label>{{event.description}}</q-item-tile>
              </q-item-main>
            </q-item>
          </q-list>
        </q-card>
      </slide>
    </carousel-3d>
  </transition>
</template>

<script>
import AXIOS from 'axios'
export default {
  name: 'event-bar',
  data: function () {
    return {
      events: []
    }
  },
  beforeMount () {
    let self = this
    AXIOS.get('/api/event/')
      .then(response => {
        self.$data.events = response.data
      })
  }
}
</script>

<style scoped>

</style>
