<template>
  <q-layout view="lHh Lpr lFf">
    <topBar></topBar>
    <leftBar></leftBar>
    <rightBar></rightBar>
    <q-page-container>
      <router-view></router-view>
    </q-page-container>
  </q-layout>
</template>

<script>
import leftBar from './leftBar'
import rightBar from './rightBar'
import topBar from './topBar'

export default {
  name: 'LayoutDefault',
  components: {
    leftBar,
    rightBar,
    topBar
  }
}
</script>

<style>
</style>
