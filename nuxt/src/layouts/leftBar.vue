<template>
<q-layout-drawer
  side="left"
  v-model="$store.state.siteSettings.leftBarOpen"
  :content-class="$q.theme === 'mat' ? 'bg-grey-1' : null"
>
  <q-list class="text-center"
          no-border
          link
          inset-delimiter
  >
    <img @click="$router.push('/')" alt="Quasar logo" src="../assets/quasar-logo-full.svg">
    <q-item @click.native="$router.push('/')" class="cursor-pointer">
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
    <q-item @click.native="$router.push('/esemenyek')" class="cursor-pointer">
      <q-item-side icon="fa-calendar" inverted color="amber" />
      <q-item-main>
        <q-item-tile label>Események</q-item-tile>
      </q-item-main>
    </q-item>
    <q-item @click.native="$router.push('/projekt')" class="cursor-pointer">
      <q-item-side icon="fa-calendar" inverted color="amber" />
      <q-item-main>
        <q-item-tile label>A Projekt</q-item-tile>
      </q-item-main>
    </q-item>
    <q-item @click.native="$router.push('/kapcsolat')" class="cursor-pointer">
      <q-item-side icon="fa-address-card" inverted color="amber" />
      <q-item-main>
        <q-item-tile label>Kapcsolat</q-item-tile>
      </q-item-main>
    </q-item>
    <q-item v-show="$store.state.isLoggedIn" @click.native="$router.push('/admin')" class="cursor-pointer shadow-1 bg-grey-3">
      <q-item-side icon="fa-user-secret" inverted color="primary" />
      <q-item-main>
        <q-item-tile label>Admin Panel</q-item-tile>
      </q-item-main>
    </q-item>
  </q-list>
</q-layout-drawer>
</template>

<script>
export default {}
</script>
