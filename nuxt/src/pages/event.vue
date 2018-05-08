<template>
  <transition
    appear
    enter-active-class="animated zoomIn"
    leave-active-class="animated zoomOut"
  >
    <q-layout>
      <q-page-container>
        <q-page padding style="width: 1000px; max-width: 90vw;">
          <q-list inline class="q-ma-sm shadow-24">
            <q-item>
              <q-card-media overlay-position="top">
                <img src="statics/parallax2.jpg">
                <q-card-title slot="overlay">
                  <h5>{{event.name}}</h5>
                </q-card-title>
              </q-card-media>
            </q-item>
            <q-item style="margin-bottom: 30px" class="shadow-1 bg-grey-2">
              <q-item-side left>
                <q-item-tile style="font-size: 30px;" color="primary" icon="fa-address-card" />
              </q-item-side>
              <q-item-main>
                <q-item-tile style="font-size: 30px;" class="text-center" label>Információk</q-item-tile>
              </q-item-main>
            </q-item>
            <q-item>
              <q-item-side>
                <q-item-tile color="primary" icon="fa-globe" />
              </q-item-side>
              <q-item-main>
                <q-item-tile label>{{event.name}}</q-item-tile>
                <q-item-tile sublabel>Esemény neve</q-item-tile>
              </q-item-main>
            </q-item>
            <q-item>
              <q-item-side>
                <q-item-tile color="primary" icon="fa-address-book" />
              </q-item-side>
              <q-item-main>
                <q-item-tile label>{{event.address}}</q-item-tile>
                <q-item-tile sublabel>Helyszín</q-item-tile>
              </q-item-main>
            </q-item>
            <q-item>
              <q-item-side>
                <q-item-tile color="primary" icon="fa-calendar" />
              </q-item-side>
              <q-item-main>
                <q-item-tile label>{{event.date}}</q-item-tile>
                <q-item-tile sublabel>Időpont</q-item-tile>
              </q-item-main>
            </q-item>
            <q-item v-for="field in event.fields" :key="field.subText">
              <q-item-side>
                <q-item-tile color="primary" :icon="field.icon" />
              </q-item-side>
              <q-item-main>
                <q-item-tile label>{{field.text}}</q-item-tile>
                <q-item-tile sublabel>{{field.subText}}</q-item-tile>
              </q-item-main>
            </q-item>
            <q-item>
              <q-item-side>
                <q-item-tile color="primary" icon="fa-address-book" />
              </q-item-side>
              <q-item-main>
                <q-item-tile label>{{event.description}}</q-item-tile>
                <q-item-tile sublabel>Leírás</q-item-tile>
              </q-item-main>
            </q-item>
            <q-item style="margin-bottom: 30px" class="shadow-1 bg-grey-2">
              <q-item-side left>
                <q-item-tile style="font-size: 30px;" color="primary" icon="fa-microphone" />
              </q-item-side>
              <q-item-main>
                <q-item-tile style="font-size: 30px;" class="text-center" label>Előadók</q-item-tile>
              </q-item-main>
            </q-item>
            <q-item>
              <carousel-3d
                :disable3d="true"
                border="0"
                :inverse-scaling="1000"
                :space="300"
                :controls-visible="true"
                height="500px"
                width="300"
                animation-speed="1000"
                :autoplay-timeout="3000"
                :autoplay="true"
                start-index="0"
                utoplayHoverPause="true"
              >
                <slide style="background-color: white" :index="index" v-for="(speaker, index) in event.speakers" :key="speaker.id">
                  <q-card inline class="q-ma-sm" style="height:370px; width: 285px;">
                    <q-btn style="width: 100%" icon-right="fa-external-link-alt" @click="$router.push('/felhasznalo/' + speaker.id)" color="info">Esemény megnyitása</q-btn>
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
                          <q-item-tile label>{{speaker.name}} {{speaker.lastName}}</q-item-tile>
                          <q-item-tile sublabel>{{speaker.username}}</q-item-tile>
                        </q-item-main>
                      </q-item>
                      <q-item>
                        <q-item-side>
                          <q-item-tile color="red" icon="fa-user-md" />
                        </q-item-side>
                        <q-item-main>
                          <q-item-tile label>{{speaker.profession}}</q-item-tile>
                        </q-item-main>
                      </q-item>
                    </q-list>
                  </q-card>
                </slide>
              </carousel-3d>
            </q-item>
          </q-list>
        </q-page>
      </q-page-container>
    </q-layout>
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
