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
                <q-item-tile style="font-size: 30px;" color="primary" icon="fa-user-secret" />
              </q-item-side>
              <q-item-main>
                <q-item-tile style="font-size: 30px;" class="text-center" label>Admin Panel</q-item-tile>
              </q-item-main>
            </q-item>
            <q-tabs color="primary" class="shadow-1" glossy>
              <q-tab default name="Események" slot="title" icon="fa-calendar" label="Események" />
              <q-tab name="Felhasználók" slot="title" icon="fa-user" label="Felhasználók" />
              <q-tab name="Oldalak" slot="title" icon="fa-link" label="Oldalak" />
              <q-tab name="Oldal üzenetek" slot="title" icon="fa-envelope" label="Oldal üzenetek" />
              <q-tab name="Beállítások" slot="title" icon="fa-cog" label="Beállítások" />

              <q-tab-pane name="Események">
                <q-list class="text-center"
                        no-border
                        link
                        inset-delimiter
                >
                  <q-item @click.native="$router.push('/esemeny/uj')" class="shadow-1 cursor-pointer">
                    <q-item-side image="statics/blank-calendar.png"></q-item-side>
                    <q-item-main>
                      <q-item-tile label>Új esemény létrehozásához</q-item-tile>
                      <q-item-tile sublabel>Kattints ide</q-item-tile>
                    </q-item-main>
                  </q-item>
                  <q-item-separator></q-item-separator>
                  <q-list-header>Események</q-list-header>
                  <q-item-separator></q-item-separator>
                  <q-item @click.native="$router.push('/esemeny/' + event.id)" class="shadow-1" v-for="event in events" :key="event.id">
                    <q-item-side image="statics/parallax2.jpg"></q-item-side>
                    <q-item-main>
                      <q-item-tile label>{{event.name}}</q-item-tile>
                      <q-item-tile sublabel>{{event.address}}</q-item-tile>
                      <q-item-tile sublabel>{{event.date}}</q-item-tile>
                    </q-item-main>
                    <q-item-side right>
                      <q-item-tile icon="fa-edit"></q-item-tile>
                    </q-item-side>
                  </q-item>
                </q-list>
              </q-tab-pane>

              <q-tab-pane name="Felhasználók">
                <q-list class="text-center"
                        no-border
                        link
                        inset-delimiter
                >
                  <q-item-separator></q-item-separator>
                  <q-list-header>Felhasználók</q-list-header>
                  <q-item-separator></q-item-separator>
                  <q-item @click.native="$router.push('/felhasznalo/' + user.username)" class="shadow-1" v-for="user in users" :key="user.id">
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
              </q-tab-pane>

              <q-tab-pane name="Oldalak">
                asdsad
              </q-tab-pane>

              <q-tab-pane name="Oldal üzenetek">
                <q-item class="shadow-2 bg-grey-1" v-for="contactMessage in contactMessages" :key="contactMessage.id" style="margin-bottom: 15px;">
                  <q-item-main>
                    <q-btn
                      color="info"
                      style="margin-bottom: 10px;"
                    >{{contactMessage.id}}</q-btn>
                    <q-eventField>
                      <q-input v-model="contactMessage.sender.username" :after="[{icon: fa-user}]" readonly="true" float-label="Üzenet feladója"></q-input>
                      <q-input v-model="contactMessage.topic" readonly="true" float-label="Üzenet témája"></q-input>
                      <q-input v-model="contactMessage.email" :after="[{icon: fa-envelope}]" readonly="true" float-label="Feladó email címe"></q-input>
                      <q-input type="textarea" rows="3" readonly="true" v-model="contactMessage.message" float-label="Üzenet szövege"></q-input>
                    </q-eventField>
                  </q-item-main>
                </q-item>
              </q-tab-pane>

              <q-tab-pane name="Beállítások">
                <editSiteSettings></editSiteSettings>
              </q-tab-pane>
            </q-tabs>
          </q-card>
        </q-page>
      </q-page-container>
    </q-layout>
  </transition>
</template>

<script>
import AXIOS from 'axios'
import editSiteSettings from '../components/editSiteSettings'
export default {
  name: 'adminPanel',
  components: {
    editSiteSettings
  },
  data: function () {
    return {
      events: [],
      users: [],
      contactMessages: []
    }
  },
  beforeCreate () {
    let self = this
    AXIOS.get('/api/event/')
      .then(response => {
        self.$data.events = response.data
      })
    AXIOS.get('/api/user')
      .then(response => {
        self.$data.users = response.data
      })
    AXIOS.get('/api/contact/message')
      .then(response => {
        self.$data.contactMessages = response.data
      })
  }
}
</script>

<style scoped>

</style>
