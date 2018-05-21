<template>
  <q-list class="text-center"
          no-border
          link
          inset-delimiter
  >
    <q-item-separator></q-item-separator>
    <q-list-header>Felhasználók</q-list-header>
    <q-item-separator></q-item-separator>
    <q-item @click.native="openActionSheet(user)" class="shadow-1" v-for="user in users" :key="user.id">
      <q-item-side image="statics/guy-avatar.png"></q-item-side>
      <q-item-main>
        <q-item-tile label>{{user.name}} {{user.lastName}}</q-item-tile>
        <q-item-tile sublabel>{{user.username}}</q-item-tile>
        <q-item-tile sublabel>{{user.role}}</q-item-tile>
      </q-item-main>
      <q-item-side right>
        <q-item-tile icon="fa-edit"></q-item-tile>
      </q-item-side>
    </q-item>
    <q-action-sheet
      v-model="actionSheet"
      :title="clickedUser.username + ' nevü felhasználó'"
      :actions="[
      {
        label: 'Megnyitása',
        icon: 'fa-link',
        color: 'primary',
        handler: openUserProfile
      },
      {
        label: 'Szerkesztése',
        icon: 'fa-edit',
        color: 'primary',
        handler: openUserEdit
      },
      {
        label: 'Kitiltása',
        icon: 'fa-ban',
        color: 'red',
        handler: bannUser
      },
      {},
      {
        label: 'Admin adása',
        icon: 'fa-plus-circle',
        color: 'info',
        handler: addAdmin
      },
      {
        label: 'Admin elvétele',
        icon: 'fa-minus-circle',
        color: 'warning',
        handler: removeAdmin
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
      clickedUser: ''
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
    openActionSheet (user) {
      this.$data.clickedUser = user
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
    bannUser () {
      console.log('asd')
    },
    addAdmin () {
      AXIOS.get('/api/user/' + this.$data.clickedUser.username + '/addAdmin')
        .then(response => {
          console.log(response)
          Notify.create({
            type: 'positive',
            color: 'positive',
            position: 'bottom',
            timeout: 2000,
            message: response.data
          })
        })
        .catch(error => {
          console.log(error)
          Notify.create({
            type: 'info',
            color: 'info',
            position: 'bottom',
            timeout: 2000,
            message: error.data
          })
        })
    },
    removeAdmin () {
      let self = this
      AXIOS.get('/api/user/' + this.$data.clickedUser.username + '/removeAdmin')
        .then(() => {
          Notify.create({
            type: 'positive',
            color: 'positive',
            position: 'bottom',
            timeout: 2000,
            message: self.$data.clickedUser.username + ' nevü felhasználó mostantól csak Felhasználói jogosultságokkal rendelkezik.'
          })
        })
        .catch(error => {
          console.log(error)
          Notify.create({
            type: 'info',
            color: 'info',
            position: 'bottom',
            timeout: 2000,
            message: self.$data.clickedUser.username + ' tulajdonos az oldalon, ezért nincs jogod megváltoztatni az ő jogait.'
          })
        })
    }
  }
}
</script>

<style scoped>

</style>
