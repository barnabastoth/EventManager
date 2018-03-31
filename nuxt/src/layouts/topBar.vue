<template>
  <q-layout-header>
    <q-toolbar
      color="dark"
      inverted
    >
      <q-btn
        @click="$store.dispatch('toggleLeftBar')"
        aria-label="Menu"
      >
        <q-icon name="menu"/>
      </q-btn>

      <q-toolbar-title>
        Event Manager
        <div slot="subtitle">Esemény központú közösségi oldal</div>
      </q-toolbar-title>
      <div v-if="$store.state.isLoggedIn" @click="$store.dispatch('toggleRightBar')">
        <q-tooltip>Felhasználói Panel</q-tooltip>
        <q-item class="main-color cursor-pointer">
          <q-item-side avatar="statics/guy-avatar.png" />
          <q-item-main label="Barnabás Tóth"/>
          <q-item-side right>
            <q-btn
              aria-label="Menu"
            >
              <q-icon v-if="$store.state.rightBarOpen === false" title="Felhasználói Panel" name="fa-angle-left"/>
              <q-icon v-if="$store.state.rightBarOpen === true" title="Felhasználói Panel" name="fa-angle-right"/>
            </q-btn>
          </q-item-side>
        </q-item>
      </div>
      <div class="cursor-pointer" @click="$store.dispatch('login')" v-if="$store.state.isLoggedIn === false">
        <q-item>
          <q-item-side icon="lock open" inverted color="green" />
          <q-item-main>
            <q-item-tile label>Belépés</q-item-tile>
            <q-item-tile sublabel>Regisztráció</q-item-tile>
          </q-item-main>
        </q-item>
      </div>
    </q-toolbar>
  </q-layout-header>
</template>

<script>
export default {}
</script>
