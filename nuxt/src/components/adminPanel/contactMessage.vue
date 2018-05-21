<template>
  <q-list class="text-center"
          no-border
          link
          inset-delimiter
  >
    <q-item-separator></q-item-separator>
    <q-list-header>Oldal üzenetek</q-list-header>
    <q-item-separator></q-item-separator>
    <q-item v-for="contactMessage in contactMessages" :key="contactMessage.id">
      <q-item-main>
        <q-collapsible
          v-if="contactMessage.sender !== null"
          :label="'Feladó: ' + contactMessage.sender.username"
          :sublabel="contactMessage.message"
          sublabel-lines="1"
          avatar="../statics/guy-avatar.png"
        >
          <q-item>
            <q-item-side right>
              <q-item-tile color="primary" icon="fa-map-signs"/>
            </q-item-side>
            <q-item-main>
              <q-item-tile sublabel>{{contactMessage.topic}}</q-item-tile>
            </q-item-main>
          </q-item>
          <q-item>
            <q-item-side right>
              <q-item-tile color="primary" icon="fa-clock"/>
            </q-item-side>
            <q-item-main>
              <q-item-tile sublabel>{{contactMessage.date}}</q-item-tile>
            </q-item-main>
          </q-item>
          <q-item>
            <q-item-side right>
              <q-item-tile color="primary" icon="fa-envelope"/>
            </q-item-side>
            <q-item-main>
              <q-item-tile sublabel>{{contactMessage.message}}</q-item-tile>
            </q-item-main>
          </q-item>
        </q-collapsible>
        <q-collapsible
          v-if="contactMessage.sender === null"
          :label="contactMessage.email"
          :sublabel="contactMessage.message"
          sublabel-lines="1"
          avatar="../statics/guy-avatar.png"
        >
          <q-item>
            <q-item-side right>
              <q-item-tile color="primary" icon="fa-map-signs"/>
            </q-item-side>
            <q-item-main>
              <q-item-tile sublabel>{{contactMessage.topic}}</q-item-tile>
            </q-item-main>
          </q-item>
          <q-item>
            <q-item-side right>
              <q-item-tile color="primary" icon="fa-clock"/>
            </q-item-side>
            <q-item-main>
              <q-item-tile sublabel>{{contactMessage.date}}</q-item-tile>
            </q-item-main>
          </q-item>
          <q-item>
            <q-item-side right>
              <q-item-tile color="primary" icon="fa-envelope"/>
            </q-item-side>
            <q-item-main>
              <q-item-tile sublabel>{{contactMessage.message}}</q-item-tile>
            </q-item-main>
          </q-item>
        </q-collapsible>
      </q-item-main>
    </q-item>
  </q-list>
</template>

<script>
import AXIOS from 'axios'
export default {
  name: 'contact-message',
  data: function () {
    return {
      contactMessages: []
    }
  },
  beforeMount () {
    let self = this
    AXIOS.get('/api/contact/message')
      .then(response => {
        self.$data.contactMessages = response.data
        console.log(self.$data.contactMessages)
      })
  }
}
</script>

<style scoped>

</style>
