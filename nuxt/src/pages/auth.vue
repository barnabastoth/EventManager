<template>
  <transition
    appear
    enter-active-class="animated zoomIn"
    leave-active-class="animated zoomOut"
  >
    <q-layout>
      <q-page-container>
        <q-page padding style="width: 700px; max-width: 90vw;">
          <q-tabs color="primary" class="shadow-24" glossy align="justify">
            <q-tab default name="Belépés" slot="title" icon="fa-user" label="Belépés" />
            <q-tab name="Regisztráció" slot="title" icon="fa-lock-open" label="Regisztriáció" />

            <q-tab-pane name="Belépés">
              <q-item>
                <q-item-side icon="fa-info" inverted color="green" />
                <q-item-main>
                  <q-item-tile label>Üdvözöllek újra köztünk, itt tudsz belépni.</q-item-tile>
                </q-item-main>
              </q-item>
              <q-item v-show="login.hasErrors">
                <q-item-side icon="fa-danger" inverted color="green" />
                <q-item-main>
                  <q-item-tile label>{{login.errors}}</q-item-tile>
                </q-item-main>
              </q-item>
              <br>
              <q-input float-label="Felhasználónév" v-model="login.username" inverted color="primary" type="text" :before="[{icon: 'fa-address-card', handler () {}}]"/>
              <br>
              <q-input float-label="Jelszó" v-model="login.password" inverted color="primary" type="password" :before="[{icon: 'fa-key', handler () {}}]" />
              <br>
              <q-btn color="primary" icon-right="fa-sign-in-alt" @click="performLogin()" label="Bejelentkezés" />
            </q-tab-pane>

            <q-tab-pane name="Regisztráció">
              <q-item>
                <q-item-side icon="fa-info" inverted color="green" />
                <q-item-main>
                  <q-item-tile label>Kérlek valós adatokat adj meg a regisztráció során</q-item-tile>
                </q-item-main>
              </q-item>
              <q-item v-show="register.hasErrors">
                <q-item-side icon="fa-exclamation-triangle" inverted color="red" />
                <q-item-main>
                  <q-item-tile label>{{register.errors}}</q-item-tile>
                </q-item-main>
              </q-item>
              <br>
              <q-input float-label="Felhasználónév" v-model="register.username" inverted color="primary" type="text" :before="[{icon: 'fa-address-card', handler () {}}]"/>
              <br>
              <q-input float-label="Email cím" v-model="register.email" inverted color="primary" type="email" :before="[{icon: 'fa-envelope', handler () {}}]" />
              <br>
              <q-input float-label="Jelszó" v-model="register.password" inverted color="primary" type="password" :before="[{icon: 'fa-key', handler () {}}]" />
              <br>
              <q-btn color="primary" icon-right="fa-sign-in-alt" @click="performRegistration()" label="Regisztráció" />
            </q-tab-pane>
          </q-tabs>
        </q-page>
      </q-page-container>
    </q-layout>
  </transition>
</template>

<script>
import AXIOS from 'axios'
export default {
  data: function () {
    return {
      login: {
        username: '',
        password: '',
        errors: {},
        hasErrors: false
      },
      register: {
        username: '',
        email: '',
        password: '',
        errors: {},
        hasErrors: false
      }
    }
  },
  methods: {
    clearErrors () {
      this.$data.login.errors = ['asd']
      this.$data.register.errors = ['asd']
    },
    performLogin () {
      let self = this
      this.$store.dispatch('login', {'username': this.$data.login.username, 'password': this.$data.login.password})
        .then(() => {
          self.$router.push('/')
        })
        .catch((error) => {
          self.login.errors = error.response.data
          self.login.hasErrors = true
        })
    },
    performRegistration () {
      let self = this
      AXIOS.post('http://localhost:8089/api/register', this.$data.register)
        .then(function (response) {
          self.login.username = self.register.username
          self.login.password = self.register.password
          self.login.errors = {}
          self.performLogin()
        })
        .catch(function (error) {
          self.register.errors = error.response.data
          self.register.hasErrors = true
        })
    },
    toogleAuth () {
      document.querySelector('.cont').classList.toggle('s--signup')
    }
  }
}
</script>

<style>

</style>
