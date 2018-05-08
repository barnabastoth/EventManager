<template>
  <transition
    appear
    enter-active-class="animated zoomIn"
    leave-active-class="animated zoomOut"
  >
    <carousel-3d :inverse-scaling="1500" :space="700" :controls-visible="true"  v-show="$store.state.siteSettings.eventBarOpen">
      <slide :index="0">
        Slide 1 Content
      </slide>
      <slide :index="1">
        Slide 2 Content
      </slide>
      <slide :index="2">
        <figure>
          <img src="../statics/parallax2.jpg">
          <figcaption>
            <q-card inline class="q-ma-sm">
              <q-list>
                <q-item>
                  <q-item-side>
                    <q-item-tile color="primary" icon="local bar" />
                  </q-item-side>
                  <q-item-main>
                    <q-item-tile label>Bar XYZ</q-item-tile>
                    <q-item-tile sublabel>Have a drink.</q-item-tile>
                  </q-item-main>
                </q-item>
                <q-item>
                  <q-item-side>
                    <q-item-tile color="red" icon="local gas station" />
                  </q-item-side>
                  <q-item-main>
                    <q-item-tile label>Gas Station</q-item-tile>
                    <q-item-tile sublabel>Fill your gas tank.</q-item-tile>
                  </q-item-main>
                </q-item>
                <q-item>
                  <q-item-side>
                    <q-item-tile color="amber" icon="local movies" />
                  </q-item-side>
                  <q-item-main>
                    <q-item-tile label>Cinema XYZ</q-item-tile>
                    <q-item-tile sublabel>Watch a movie.</q-item-tile>
                  </q-item-main>
                </q-item>
              </q-list>
            </q-card>
          </figcaption>
        </figure>
      </slide>
      <slide :index="3">
        Slide 3 Content
      </slide>
      <slide :index="4">
        Slide 4 Content
      </slide>
      <slide :index="5">
        Slide 5 Content
      </slide>
      <slide :index="6">
        Slide 6 Content
      </slide>
      <slide :index="7">
        Slide 7 Content
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
  beforeCreate () {
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
