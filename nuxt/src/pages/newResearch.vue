<template>
  <transition
    appear
    enter-active-class="animated zoomIn"
    leave-active-class="animated zoomOut"
  >
    <q-layout>
      <q-page-container>
        <q-page padding style="width: 1000px; max-width: 90vw;">
          <q-card inline class="q-ma-sm shadow-24" style="width: 1000px; max-width: 90vw; padding: 24px;">
            <q-item style="margin-bottom: 30px" class="shadow-1 bg-grey-2">
              <q-item-side left>
                <q-item-tile style="font-size: 30px;" color="primary" icon="fa-calendar" />
              </q-item-side>
              <q-item-main>
                <q-item-tile style="font-size: 30px;" class="text-center" label>Új tanulmány létrehozása</q-item-tile>
              </q-item-main>
            </q-item>
            <q-item class="shadow-2 bg-grey-1" style="margin-bottom: 15px;">
              <q-item-side icon="fa-globe" color="primary"></q-item-side>
              <q-item-main>
                <q-input v-model="research.name" float-label="A tanulmány neve"></q-input>
              </q-item-main>
            </q-item>
            <q-item class="shadow-2 bg-grey-1" style="margin-bottom: 15px;">
              <q-item-side icon="fa-newspaper" color="primary"></q-item-side>
              <q-item-main>
                <q-input v-model="research.description" float-label="A tanulmány rövid leírása. Ez fog megjelenni mindenhol ahol a hosszú nem fér ki."></q-input>
              </q-item-main>
            </q-item>
            <q-item class="shadow-2 bg-grey-1" style="margin-bottom: 15px;">
              <q-item-side icon="fa-book" color="primary"></q-item-side>
              <q-item-main>
                <q-input v-model="research.content" type="textarea" float-label="A tanulmány konkrét anyaga"></q-input>
              </q-item-main>
            </q-item>
            <q-btn color="primary" class="float-right" icon-right="fa-sign-in-alt" @click="createNewResearch()" label="Tanulmány létrehozása" />
          </q-card>
        </q-page>
      </q-page-container>
    </q-layout>
  </transition>
</template>

<script>
import AXIOS from 'axios'
import { Notify } from 'quasar'
export default {
  name: 'new-research',
  data: function () {
    return {
      research: {
        name: '',
        content: '',
        date: '',
        categories: []
      }
    }
  },
  methods: {
    createNewResearch () {
      let self = this
      AXIOS.post('/api/research/new', this.$data.research)
        .then(response => {
          self.$router.push('/tanulmany/' + response.data)
          Notify.create({
            type: 'positive',
            color: 'positive',
            position: 'center',
            timeout: 3000,
            message: 'A Tanulmány sikeresen létre lett hozva. Át is irányítottalak a tanulmány oldalára.'
          })
        })
        .catch(error => {
          console.log(error)
          Notify.create({
            type: 'warning',
            color: 'warning',
            position: 'bottom',
            timeout: 2000,
            message: 'Valami hiba történt az tanulmány létrehozásakor.'
          })
        })
    }
  }
}
</script>

<style scoped>

</style>
