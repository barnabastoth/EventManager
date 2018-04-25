<template>
  <q-layout>
    <q-page-container>
      <q-page>
        <div style="width: 700px; max-width: 90vw;" class="absolute-center">
          <div
            class="main-color shadow-1 row inline flex-center text-white"
            :class="`bg-black`"
          >
          </div>
          <q-input float-label="Felhasználónév" v-model="login.username" inverted color="primary" type="email" :before="[{icon: 'fa-address-card', handler () {}}]"/>
          <br>
          <q-input float-label="Jelszó" v-model="login.password" inverted color="primary" type="password" :before="[{icon: 'fa-key'}]" :after="[{icon: 'done', condition: login.password.length >= 5, handler () {}}]" />
          <br>
          <q-btn color="primary" icon-right="fa-sign-in-alt" @click="performLogin()" label="Bejelentkezés" />
        </div>
      </q-page>
    </q-page-container>
  </q-layout>
</template>

<script>
import AXIOS from 'axios'
export default {
  data: function () {
    return {
      login: {
        username: '',
        password: '',
        errors: {}
      },
      register: {
        username: '',
        email: '',
        password: '',
        errors: {}
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
        .catch(() => {
          self.$router.push('/bejelentkezés')
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
