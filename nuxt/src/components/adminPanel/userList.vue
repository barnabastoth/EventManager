<template>
  <q-list class="text-center"
          no-border
          link
          inset-delimiter
  >
    <q-item-separator></q-item-separator>
    <q-list-header>Felhasználók</q-list-header>
    <q-item-separator></q-item-separator>
    <q-item @click.native="openActionSheet(user, index)" class="shadow-1" v-for="(user, index) in users" :key="user.id">
      <q-item-side image="statics/guy-avatar.png"></q-item-side>
      <q-item-main>
        <q-item-tile label>{{user.name}} {{user.lastName}}</q-item-tile>
        <q-item-tile sublabel>{{user.username}}</q-item-tile>
        <q-item-tile sublabel>{{user.role}}</q-item-tile>
      </q-item-main>
      <q-item-side right>
        <q-item-tile v-if="user.active === 1" color="primary" icon="fa-eye"></q-item-tile>
        <q-item-tile v-if="user.active === 0" color="red" icon="fa-eye-slash"></q-item-tile>
      </q-item-side>
    </q-item>
    <q-action-sheet
      v-model="actionSheet"
      :title="clickedUser.username + ' nevü felhasználó kezelése'"
      :actions="[
      {
        label: 'Megnyitás',
        icon: 'fa-link',
        color: 'primary',
        handler: openUserProfile
      },
      {
        label: 'Szerkesztés',
        icon: 'fa-edit',
        color: 'primary',
        handler: openUserEdit
      },
      {},
      {
        label: 'Tiltás',
        icon: 'fa-ban',
        color: 'red',
        handler: banUser
      },
      {
        label: 'Tiltás levétele',
        icon: 'fa-check',
        color: 'green',
        handler: unBanUser
      },
      {},
      {
        label: 'Admin adás',
        icon: 'fa-plus-circle',
        color: 'info',
        handler: addAdmin
      },
      {
        label: 'Admin elvétel',
        icon: 'fa-minus-circle',
        color: 'warning',
        handler: removeAdmin
      },
      {},
      {
        label: 'Törlés véglegesen',
        icon: 'fa-trash',
        color: 'red',
        handler: deleteUser
      }
    ]"
    />
  </q-list>
</template>

<script>
import AXIOS from 'axios'
import { Notify } from 'quasar'
export default {
  name: 'user-list',
  data: function () {
    return {
      users: [],
      actionSheet: false,
      clickedUser: '',
      clickedUserIndex: 0
    }
  },
  beforeMount () {
    let self = this
    AXIOS.get('/api/user/')
      .then(response => {
        self.$data.users = response.data
      })
  },
  methods: {
    openActionSheet (user, index) {
      this.$data.clickedUser = user
      this.$data.clickedUserIndex = index
      this.$data.actionSheet = true
    },
    openUserProfile () {
      let self = this
      this.$router.push('/felhasznalo/' + this.$data.clickedUser.username)
      Notify.create({
        type: 'positive',
        color: 'positive',
        position: 'bottom',
        timeout: 2000,
        message: 'Megnyitottam az ' + self.$data.clickedUser.username + ' nevü felhasználó profilját.'
      })
    },
    openUserEdit () {
      let self = this
      this.$router.push('/felhasznalo/' + this.$data.clickedUser.username + '/szerkesztes')
      Notify.create({
        type: 'positive',
        color: 'positive',
        position: 'bottom',
        timeout: 2000,
        message: 'Megnyitottam az ' + self.$data.clickedUser.username + ' nevü felhasználó profiljának szerkesztő felületét.'
      })
    },
    addAdmin () {
      let self = this
      AXIOS.get('/api/user/' + this.$data.clickedUser.username + '/addAdmin')
        .then(response => {
          if (response.data['Role'] !== undefined) {
            self.$data.users[self.$data.clickedUserIndex].role = response.data['Role']
            Notify.create({
              type: 'positive',
              color: 'positive',
              position: 'bottom',
              timeout: 2000,
              message: response.data['Message']
            })
          } else {
            Notify.create({
              type: 'info',
              color: 'info',
              position: 'bottom',
              timeout: 2000,
              message: response.data['Message']
            })
          }
        })
        .catch(error => {
          console.log(error)
          Notify.create({
            type: 'info',
            color: 'info',
            position: 'bottom',
            timeout: 2000,
            message: self.$data.clickedUser.username + ' nevü felhasználó nem található a rendszerben.'
          })
        })
    },
    removeAdmin () {
      let self = this
      AXIOS.get('/api/user/' + this.$data.clickedUser.username + '/removeAdmin')
        .then(response => {
          if (response.data['Role'] !== undefined) {
            self.$data.users[self.$data.clickedUserIndex].role = response.data['Role']
            Notify.create({
              type: 'positive',
              color: 'positive',
              position: 'bottom',
              timeout: 2000,
              message: response.data['Message']
            })
          } else {
            Notify.create({
              type: 'info',
              color: 'info',
              position: 'bottom',
              timeout: 2000,
              message: response.data['Message']
            })
          }
        })
        .catch(error => {
          console.log(error)
          Notify.create({
            type: 'info',
            color: 'info',
            position: 'bottom',
            timeout: 2000,
            message: self.$data.clickedUser.username + ' nevü felhasználó nem található a rendszerben.'
          })
        })
    },
    banUser () {
      let self = this
      AXIOS.get('/api/user/' + this.$data.clickedUser.username + '/ban')
        .then(response => {
          if (response.data['Active'] !== undefined) {
            self.$data.users[self.$data.clickedUserIndex].active = response.data['Active']
            Notify.create({
              type: 'positive',
              color: 'positive',
              position: 'bottom',
              timeout: 2000,
              message: response.data['Message']
            })
          } else {
            Notify.create({
              type: 'info',
              color: 'info',
              position: 'bottom',
              timeout: 2000,
              message: response.data['Message']
            })
          }
        })
        .catch(error => {
          console.log(error)
          Notify.create({
            type: 'info',
            color: 'info',
            position: 'bottom',
            timeout: 2000,
            message: self.$data.clickedUser.username + ' nevü felhasználó nem található a rendszerben.'
          })
        })
    },
    unBanUser () {
      let self = this
      AXIOS.get('/api/user/' + this.$data.clickedUser.username + '/unban')
        .then(response => {
          if (response.data['Active'] !== undefined) {
            self.$data.users[self.$data.clickedUserIndex].active = response.data['Active']
            Notify.create({
              type: 'positive',
              color: 'positive',
              position: 'bottom',
              timeout: 2000,
              message: response.data['Message']
            })
          } else {
            Notify.create({
              type: 'info',
              color: 'info',
              position: 'bottom',
              timeout: 2000,
              message: response.data['Message']
            })
          }
        })
        .catch(error => {
          console.log(error)
          Notify.create({
            type: 'info',
            color: 'info',
            position: 'bottom',
            timeout: 2000,
            message: self.$data.clickedUser.username + ' nevü felhasználó nem található a rendszerben.'
          })
        })
    },
    deleteUser () {
      let self = this
      this.$q.dialog({
        title: 'Figyelem! Biztos véglegesen törölni akarod ezt a felhasználót?',
        message: 'Írd be a felhasználó pontos Felhasználói nevét, hogy törölhesd. A felhasználói neve: ' + self.$data.clickedUser.username,
        color: 'primary',
        ok: {
          push: true,
          color: 'warning',
          label: 'Végleges törlés.'
        },
        cancel: {
          push: true,
          color: 'info',
          label: 'Vissza'
        },
        preventClose: true,
        noBackdropDismiss: false, // gets set to "true" automatically if preventClose is "true"
        noEscDismiss: false, // gets set to "true" automatically if preventClose is "true"
        position: 'bottom',
        prompt: {
          model: '',
          type: 'text' // optional
        }
      })
        .then(data => {
          if (data.trim().toUpperCase() === self.$data.clickedUser.username.toUpperCase()) {
            AXIOS.get('/api/user/' + this.$data.clickedUser.username + '/delete')
              .then(() => {
                self.$data.users.splice(self.$data.clickedUserIndex, 1)
                Notify.create({
                  type: 'positive',
                  color: 'positive',
                  position: 'bottom',
                  timeout: 2000,
                  message: self.$data.clickedUser.username + ' nevü felhasználó törölve lett az adatbázisból.'
                })
              })
              .catch(() => {
                Notify.create({
                  type: 'info',
                  color: 'info',
                  position: 'bottom',
                  timeout: 2000,
                  message: 'Ehez a művelethez Tulajdonosi jogosultság szükséges.'
                })
              })
          } else {
            Notify.create({
              type: 'info',
              color: 'info',
              position: 'bottom',
              timeout: 3000,
              message: 'A beírt név nem helyes, biztos, hogy kiakarod törölni ezt a felhasználót?'
            })
          }
        })
    }
  }
}
</script>

<style scoped>

</style>
