<template>
  <transition
    appear
    enter-active-class="animated zoomIn"
    leave-active-class="animated zoomOut"
  >
    <q-layout>
      <q-page-container>
        <q-page padding style="width: 1000px; max-width: 90vw;">
          <q-card inline class="q-ma-sm shadow-24" style="width: 1000px; max-width: 90vw;">
            <q-item>
              <q-item-side avatar="statics/guy-avatar.png" />
              <q-item-main>
                <q-item-tile label>{{user.lastName}} {{user.name}}</q-item-tile>
                <q-item-tile sublabel>{{user.username}}</q-item-tile>
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
                  A kivánt adatok megváltoztatása után kattints a változtatások mentése gombra
                </q-item-tile>
              </q-card-main>
            </q-card>
            <q-item class="shadow-2 bg-grey-1" style="margin-bottom: 15px;">
              <q-item-side icon="fa-address-card" color="primary"></q-item-side>
              <q-item-main>
                <q-input v-model="user.name" float-label="Becenév"></q-input>
              </q-item-main>
            </q-item>
            <q-item class="shadow-2 bg-grey-1" style="margin-bottom: 15px;">
              <q-item-side icon="fa-calendar" color="primary"></q-item-side>
              <q-item-main>
                <q-input v-model="user.lastName" float-label="Keresztnév"></q-input>
              </q-item-main>
            </q-item>
            <q-item class="shadow-2 bg-grey-1" style="margin-bottom: 15px;">
              <q-item-side icon="fa-calendar" color="primary"></q-item-side>
              <q-item-main>
                <q-input v-model="user.email" float-label="Email"></q-input>
              </q-item-main>
            </q-item>
            <q-item class="shadow-2 bg-grey-1" style="margin-bottom: 15px;">
              <q-item-side icon="fa-user-md" color="primary"></q-item-side>
              <q-item-main>
                <q-input v-model="user.profession" float-label="Foglalkozás"></q-input>
              </q-item-main>
            </q-item>
            <q-item class="shadow-2 bg-grey-1" style="margin-bottom: 15px;">
              <q-item-side icon="fa-calendar" color="primary"></q-item-side>
              <q-item-main>
                <q-input v-model="user.description" type="textarea" float-label="Bemutatkozó"></q-input>
              </q-item-main>
            </q-item>
            <q-item>
              <q-item-main class="shadow-10" style="padding: 20px;">
                <q-card color="primary" inline class="q-ma-sm full-width">
                  <q-card-main>
                    <q-btn
                      icon="fa-key"
                      color="info"
                      style="margin: 10px"
                    />
                    Jelszó megváltoztatásához töltsd ki az alábbi mezőket.
                    </q-card-main>
                </q-card>
                <q-item class="shadow-2 bg-grey-1" style="margin-bottom: 15px;">
                  <q-item-side icon="fa-calendar" color="primary"></q-item-side>
                  <q-item-main>
                    <q-input v-model="oldPassword1" type="text" float-label="Régi jelszavad"></q-input>
                  </q-item-main>
                </q-item>
                <q-item class="shadow-2 bg-grey-1" style="margin-bottom: 15px;">
                  <q-item-side icon="fa-calendar" color="primary"></q-item-side>
                  <q-item-main>
                    <q-input v-model="oldPassword2" type="text" float-label="Régi jelszavad mégegyszer"></q-input>
                  </q-item-main>
                </q-item>
                <q-item class="shadow-2 bg-grey-1" style="margin-bottom: 15px;">
                  <q-item-side icon="fa-calendar" color="primary"></q-item-side>
                  <q-item-main>
                    <q-input v-model="user.password" type="text" float-label="Új jelszavad"></q-input>
                  </q-item-main>
                </q-item>
              </q-item-main>
            </q-item>
            <q-item>
              <q-btn
                push
                align="right"
                color="primary"
                icon-right="fa-edit"
                @click="saveProfileChanges()"
              >
                Változtatások mentése
              </q-btn>
            </q-item>
          </q-card>
        </q-page>
      </q-page-container>
    </q-layout>
  </transition>
</template>

<script>
import store from '../store'
import AXIOS from 'axios'
import { Notify } from 'quasar'
export default {
  name: 'edit-profile',
  data: function () {
    return {
      user: [],
      oldPassword: '',
      newPassword1: '',
      newPassword2: ''
    }
  },
  props: ['username'],
  beforeMount () {
    let self = this
    AXIOS.get('/api/user/' + this.username + '/edit')
      .then(response => {
        self.user = response.data
      })
  },
  beforeRouteEnter: (to, from, next) => {
    if (store.getters.isUserAnAdminOrOwner ||
        store.state.isLoggedInUser.username === this.username) {
      next()
    } else {
      this.$router.push('/noAccess')
    }
  },
  methods: {
    saveProfileChanges () {
      let self = this
      AXIOS.post('/api/user/' + this.username + '/edit', this.$data.user)
        .then(() => {
          self.$router.push('/felhasznalo/' + self.username)
          Notify.create({
            type: 'positive',
            color: 'positive',
            position: 'bottom',
            timeout: 3000,
            message: 'A változtatásaid sikeresen ellettek mentve.'
          })
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
