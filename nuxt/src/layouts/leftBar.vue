<template>
<q-layout-drawer
  side="left"
  v-model="$store.state.siteSettings.leftBarOpen"
  :content-class="$q.theme === 'mat' ? 'bg-grey-1' : null"
>
  <q-list class="text-center" >
    <img @click="$router.push('/')" alt="Quasar logo" src="../assets/quasar-logo-full.svg">
    <q-item class="cursor-pointer">
      <q-item-side icon="home" inverted color="green" />
      <q-item-main>
        <q-item-tile label>Főoldal</q-item-tile>
        <q-item-tile sublabel>Hírek, újdonságok</q-item-tile>
      </q-item-main>
      <q-item-side right icon="info" />
    </q-item>
    <q-item class="cursor-pointer">
      <q-item-side icon="folder" inverted color="amber" />
      <q-item-main>
        <q-item-tile label>Tanulmányok</q-item-tile>
      </q-item-main>
    </q-item>
    <q-item class="cursor-pointer">
      <q-item-side icon="fa-calendar" inverted color="amber" />
      <q-item-main>
        <q-item-tile label>Események</q-item-tile>
      </q-item-main>
    </q-item>
    <q-item class="cursor-pointer">
      <q-item-side icon="fa-address-card" inverted color="amber" />
      <q-item-main>
        <q-item-tile label>Kapcsolat</q-item-tile>
      </q-item-main>
    </q-item>
  </q-list>
</q-layout-drawer>
</template>

<script>
export default {}
</script>
