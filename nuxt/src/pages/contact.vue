<template>
  <transition
    appear
    enter-active-class="animated zoomIn"
    leave-active-class="animated zoomOut"
  >
    <q-layout>
      <q-page-container>
        <q-page padding style="width: 700px; max-width: 90vw;">
          <q-list>
            <q-list-header>Kapcsolat</q-list-header>
            <q-item>
              <q-input v-model="message" type="textarea" float-label="Ide ird az üzenetet" />
            </q-item>
            <q-item>
              <q-btn @click="sendSystemMessage()" color="primary" icon-right="fa-calendar" label="Üzenet küldése" />
            </q-item>
          </q-list>

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
      message: {
        email: '',
        message: '',
        userId: ''
      }
    }
  },
  methods: {
    sendSystemMessage () {
      if(this.$data.message.sender === '') {
        this.$data.message.userId = this.$store.state.loggedInUser.id
      }
      AXIOS.post('/api/contact/message/new', this.$data.message)
        .then(response => {
          Notify.create({
            type: 'positive',
            color: 'positive',
            position: 'bottom',
            timeout: 2000,
            message: 'Az üzenet sikeresen ellett küldve.!'
          })
          console.log(response)
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
