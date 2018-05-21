<template>
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
        <q-item-tile v-if="event.active === '1'" color="primary" icon="fa-eye"></q-item-tile>
        <q-item-tile v-if="event.active === '0'" color="red" icon="fa-eye-slash"></q-item-tile>
      </q-item-side>
    </q-item>
  </q-list>
</template>

<script>
import AXIOS from 'axios'
export default {
  name: 'event-list',
  data: function () {
    return {
      events: []
    }
  },
  beforeMount () {
    let self = this
    AXIOS.get('/api/event/')
      .then(response => {
        self.$data.events = response.data
      })
  }
}
</script>

<style scoped>

</style>
