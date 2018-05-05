<template>
  <transition
    appear
    enter-active-class="animated zoomIn"
    leave-active-class="animated zoomOut"
  >
    <q-layout>
      <q-page-container>
        <q-page padding style="width: 1000px; max-width: 90vw;">
            <q-card inline class="q-ma-sm shadow-24">
              <q-card-media overlay-position="top">
                <img src="statics/parallax2.jpg">

                <q-card-title slot="overlay">
                  {{event.fields['title'].text}}
                  <span slot="subtitle">{{event.fields['name'].text}}</span>
                </q-card-title>
              </q-card-media>
              <q-card-actions class="bg-grey-3">
                <q-btn icon="fa-info" label="Információk" flat></q-btn>
                <q-btn flat icon="fa-volume-up" label="Fellépők"></q-btn>
                <q-btn flat icon="fa-map-signs" label="Közlekedés"></q-btn>
                <q-btn flat icon="fa-comment" label="Hozzászólások"></q-btn>
              </q-card-actions>
              <q-card-main>
                <q-item>
                  <q-item-side>
                    <q-item-tile color="primary" icon="fa-globe" />
                  </q-item-side>
                  <q-item-main>
                    <q-item-tile label>{{event.fields['name'].text}}</q-item-tile>
                    <q-item-tile sublabel>Név</q-item-tile>
                  </q-item-main>
                </q-item>
                <q-item>
                  <q-item-side>
                    <q-item-tile color="primary" icon="fa-address-book" />
                  </q-item-side>
                  <q-item-main>
                    <q-item-tile label>{{event.fields['address'].text}}</q-item-tile>
                    <q-item-tile sublabel>Cím</q-item-tile>
                  </q-item-main>
                </q-item>
                <q-item>
                  <q-item-side>
                    <q-item-tile color="primary" icon="fa-calendar" />
                  </q-item-side>
                  <q-item-main>
                    <!--<q-item-tile label>{{event.date.year}}.{{event.date.monthValue}}.{{event.date.dayOfMonth}} {{event.date.hour}}:{{event.date.minute}}</q-item-tile>-->
                    <q-item-tile sublabel>Időpont</q-item-tile>
                  </q-item-main>
                </q-item>
                <q-item>
                  <q-item-side>
                    <q-item-tile color="primary" icon="fa-clock" />
                  </q-item-side>
                  <q-item-main>
                    <q-item-tile label>{{event.fields['duration'].text}}</q-item-tile>
                    <q-item-tile sublabel>Időtartalom</q-item-tile>
                  </q-item-main>
                </q-item>
                <q-item>
                  <q-item-side>
                    <q-item-tile color="primary" icon="fa-user" />
                  </q-item-side>
                  <q-item-main>
                    <q-item-tile label>{{event.name}}</q-item-tile>
                    <q-item-tile sublabel>Esemény neve</q-item-tile>
                  </q-item-main>
                </q-item>
                <q-item>
                  <q-item-side>
                    <q-item-tile color="primary" icon="fa-user" />
                  </q-item-side>
                  <q-item-main>
                    <q-item-tile label>{{event.name}}</q-item-tile>
                    <q-item-tile sublabel>Esemény neve</q-item-tile>
                  </q-item-main>
                </q-item>
                <q-item>
                  <q-item-side>
                    <q-item-tile color="primary" icon="fa-ticket-alt" />
                  </q-item-side>
                  <q-item-main>
                    <q-item-tile label>{{event.price}}</q-item-tile>
                    <q-item-tile sublabel>Jegyár</q-item-tile>
                  </q-item-main>
                </q-item>
                <q-item>
                  <q-item-side>
                    <q-item-tile color="primary" icon="fa-link" />
                  </q-item-side>
                  <q-item-main>
                    <q-item-tile label>{{event.ticketLink}}</q-item-tile>
                    <q-item-tile sublabel>Link a jegyhez</q-item-tile>
                  </q-item-main>
                </q-item>
              </q-card-main>
            </q-card>
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
