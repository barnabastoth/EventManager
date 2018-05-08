<template>
  <q-layout view="lHh Lpr lFf">
    <topBar></topBar>
    <leftBar></leftBar>
    <rightBar></rightBar>
    <q-page-container>
      <eventBar></eventBar>
      <router-view></router-view>
    </q-page-container>
  </q-layout>
</template>

<script>
import leftBar from './leftBar'
import rightBar from './rightBar'
import topBar from './topBar'
import eventBar from './eventBar'

export default {
  name: 'LayoutDefault',
  components: {
    leftBar,
    rightBar,
    topBar,
    eventBar
  }
}
</script>

<style>
</style>
