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
                <q-item-tile label>{{event.date.year}}.{{event.date.monthValue}}.{{event.date.dayOfMonth}} {{event.date.hour}}:{{event.date.minute}}</q-item-tile>
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
                <q-item-tile style="font-size: 30px;" color="primary" icon="fa-address-card" />
              </q-item-side>
              <q-item-main>
                <q-item-tile style="font-size: 30px;" class="text-center" label>Előadók</q-item-tile>
              </q-item-main>
            </q-item>
            <q-item @click.native="$router.push('/felhasznalo/' + user.id)" class="shadow-1" v-for="user in event.speakers" :key="user.id">
              <q-item-side image="statics/guy-avatar.png"></q-item-side>
              <q-item-main>
                <q-item-tile label>{{user.name}} {{user.lastName}}</q-item-tile>
                <q-item-tile sublabel>{{user.username}}</q-item-tile>
                <q-item-tile sublabel>{{user.roles[0].role}}</q-item-tile>
              </q-item-main>
              <q-item-side right>
                <q-item-tile icon="fa-edit"></q-item-tile>
              </q-item-side>
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
