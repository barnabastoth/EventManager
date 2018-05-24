<template>
  <q-list class="text-center"
          no-border
          inset-delimiter
  >
    <q-item-separator></q-item-separator>
    <q-list-header>Oldal üzenetek</q-list-header>
    <q-item-separator></q-item-separator>
    <q-item class="shadow-1" v-for="(contactMessage, index) in contactMessages" :key="contactMessage.id">
      <q-item-main>
        <q-collapsible
          v-if="contactMessage.sender !== null && contactMessage.isRead === true"
          :label="'Feladó: ' + contactMessage.sender.username"
          :sublabel="'Üzenet:  ' + contactMessage.message"
          sublabel-lines="1"
          right-icon="fa-eye"
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
          <q-item>
            <q-btn
              v-if="contactMessage.isRead === false"
              color="primary"
              icon-right="fa-eye"
              push
              @click="readMessage(contactMessage.id, index)"
            >
              Olvasottnak jelölés</q-btn>
            <q-btn
              v-if="contactMessage.isRead === true"
              color="info"
              icon-right="fa-eye-slash"
              push
              @click="unReadMessage(contactMessage.id, index)"
            >
              Olvasatlannak jelölés</q-btn>
          </q-item>
        </q-collapsible>

        <q-collapsible
          v-if="contactMessage.sender !== null && contactMessage.isRead === false"
          :label="'Feladó: ' + contactMessage.sender.username"
          :sublabel="'Üzenet:  ' + contactMessage.message"
          sublabel-lines="1"
          right-icon="fa-eye-slash"
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
          <q-item>
            <q-btn
              v-if="contactMessage.isRead === false"
              color="primary"
              icon-right="fa-eye"
              push
              @click="readMessage(contactMessage.id, index)"
            >
              Olvasottnak jelölés</q-btn>
            <q-btn
              v-if="contactMessage.isRead === true"
              color="info"
              icon-right="fa-eye-slash"
              push
              @click="unReadMessage(contactMessage.id, index)"
            >
              Olvasatlannak jelölés</q-btn>
          </q-item>
        </q-collapsible>

        <q-collapsible
          v-if="contactMessage.sender === null && contactMessage.isRead === true"
          :label="'Feladó: ' + contactMessage.email"
          :sublabel="'Üzenet:  ' + contactMessage.message"
          sublabel-lines="1"
          right-icon="fa-eye"
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
          <q-item>
            <q-btn
              v-if="contactMessage.isRead === false"
              color="primary"
              icon-right="fa-eye"
              push
              @click="readMessage(contactMessage.id, index)"
            >
              Olvasottnak jelölés</q-btn>
            <q-btn
              v-if="contactMessage.isRead === true"
              color="info"
              icon-right="fa-eye-slash"
              push
              @click="unReadMessage(contactMessage.id, index)"
            >
              Olvasatlannak jelölés</q-btn>
          </q-item>
        </q-collapsible>

        <q-collapsible
          v-if="contactMessage.sender === null && contactMessage.isRead === false"
          :label="'Feladó: ' + contactMessage.email"
          :sublabel="'Üzenet:  ' + contactMessage.message"
          sublabel-lines="1"
          right-icon="fa-eye-slash"
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
          <q-item>
            <q-btn
              v-if="contactMessage.isRead === false"
              color="primary"
              icon-right="fa-eye"
              push
              @click="readMessage(contactMessage.id, index)"
            >
              Olvasottnak jelölés</q-btn>
            <q-btn
              v-if="contactMessage.isRead === true"
              color="info"
              icon-right="fa-eye-slash"
              push
              @click="unReadMessage(contactMessage.id, index)"
            >
              Olvasatlannak jelölés</q-btn>
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
    AXIOS.get('/api/contactMessage')
      .then(response => {
        self.$data.contactMessages = response.data
      })
  },
  methods: {
    readMessage (id, index) {
      let self = this
      if (self.$data.contactMessages[index].isRead === false) {
        AXIOS.get('/api/contactMessage/' + id + '/read')
          .then(() => {
            self.$data.contactMessages[index].isRead = true
          })
      }
    },
    unReadMessage (id, index) {
      let self = this
      if (self.$data.contactMessages[index].isRead === true) {
        AXIOS.get('/api/contactMessage/' + id + '/unRead')
          .then(() => {
            self.$data.contactMessages[index].isRead = false
          })
      }
    }
  }
}
</script>

<style scoped>

</style>
