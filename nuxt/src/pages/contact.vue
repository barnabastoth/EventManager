<template>
  <transition
    appear
    enter-active-class="animated zoomIn"
    leave-active-class="animated zoomOut"
  >
    <q-layout>
      <q-page-container>
        <q-page padding style="width: 1000px; max-width: 90vw;">
          <q-card inline class="q-ma-sm shadow-24" style="padding: 24px;width: 1000px; max-width: 90vw;">
            <q-item style="margin-bottom: 30px" class="shadow-1 bg-grey-2">
              <q-item-side left>
                <q-item-tile style="font-size: 30px;" color="primary" icon="fa-envelope" />
              </q-item-side>
              <q-item-main>
                <q-item-tile style="font-size: 30px;" class="text-center" label>Kapcsolat</q-item-tile>
              </q-item-main>
            </q-item>
            <q-card color="primary" inline class="q-ma-sm full-width">
              <q-card-main>
                <q-btn
                  icon="fa-info"
                  color="info"
                  style="margin: 10px"
                />
                Információ
                <q-item-tile>
                  {{this.$store.state.siteSettings.contactPageText}}
                </q-item-tile>
              </q-card-main>
            </q-card>
            <q-item class="shadow-2 bg-grey-1" style="margin-bottom: 15px;">
              <q-item-side icon="fa-at" color="primary"></q-item-side>
              <q-item-main>
                <q-input v-model="contactMessage.email" float-label="Email címed. Ha azt szeretnéd, hogy válaszoljunk"></q-input>
              </q-item-main>
            </q-item>
            <q-item class="shadow-2 bg-grey-1" style="margin-bottom: 15px;">
              <q-item-side icon="fa-bookmark" color="primary"></q-item-side>
              <q-item-main>
                <q-input v-model="contactMessage.topic" float-label="Üzeneted témája"></q-input>
              </q-item-main>
            </q-item>
            <q-item class="shadow-2 bg-grey-1" style="margin-bottom: 15px;">
              <q-item-side icon="fa-comment" color="primary"></q-item-side>
              <q-item-main>
                <q-input v-model="contactMessage.message" type="textarea" rows="2" float-label="Ide írd az üzeneted"></q-input>
              </q-item-main>
            </q-item>
            <q-btn style="margin-top: 10px;" @click="sendSystemMessage()" color="primary" icon-right="fa-share-square" label="Üzenet küldése" />
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
  name: 'contact',
  data: function () {
    return {
      contactMessage: {
        email: '',
        message: '',
        topic: '',
        userId: ''
      }
    }
  },
  methods: {
    sendSystemMessage () {
      let self = this
      if (this.$store.state.isLoggedIn) {
        this.$data.contactMessage.userId = this.$store.state.loggedInUser.id
      }
      AXIOS.post('/api/contact/message/new', this.$data.contactMessage)
        .then(response => {
          Notify.create({
            type: 'positive',
            color: 'positive',
            position: 'bottom',
            timeout: 4000,
            message: 'Az üzeneted sikeresen ellett küldve. Válaszolunk ahogy tudunk!'
          })
          self.$router.push('/')
        })
        .catch(error => {
          console.log(error)
        })
    }
  }
}
</script>

<style scoped>

</style>
