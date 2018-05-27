<template>
  <q-list class="text-center"
          no-border
          link
          inset-delimiter
  >
    <q-item @click.native="$router.push('/tanulmany/uj')" class="shadow-1 cursor-pointer">
      <q-item-side image="statics/research.jpg"></q-item-side>
      <q-item-main>
        <q-item-tile label>Új tanulmány létrehozásához</q-item-tile>
        <q-item-tile sublabel>Kattints ide</q-item-tile>
      </q-item-main>
    </q-item>
    <q-item-separator></q-item-separator>
    <q-list-header>Tanulmányok</q-list-header>
    <q-item-separator></q-item-separator>
    <q-item @click.native="$router.push('/tanulmany/' + research.id)" class="shadow-1" v-for="research in researches" :key="research.id">
      <q-item-side image="statics/parallax2.jpg"></q-item-side>
      <q-item-main>
        <q-item-tile label>{{research.name}}</q-item-tile>
        <q-item-tile sublabel>{{research.description}}</q-item-tile>
        <q-item-tile sublabel>{{research.date}}</q-item-tile>
      </q-item-main>
      <q-item-side right>
        <q-item-tile v-if="research.active === 1" color="primary" icon="fa-eye"></q-item-tile>
        <q-item-tile v-if="research.active === 0" color="red" icon="fa-eye-slash"></q-item-tile>
      </q-item-side>
    </q-item>
  </q-list>
</template>

<script>
import AXIOS from 'axios'
export default {
  name: 'research-list',
  data: function () {
    return {
      researches: []
    }
  },
  beforeMount () {
    let self = this
    AXIOS.get('/api/research/')
      .then(response => {
        self.$data.researches = response.data
      })
  }
}
</script>

<style scoped>

</style>
