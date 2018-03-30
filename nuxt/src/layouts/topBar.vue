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
      <q-item class="main-color">
        <q-item-side avatar="statics/guy-avatar.png" />
        <q-item-main label="Barnabás Tóth"/>
        <q-item-side right>
          <q-btn
            @click="$store.dispatch('toggleRightBar')"
            aria-label="Menu"
          >
            <q-icon v-if="$store.state.rightBarOpen === false" title="Felhasználói Panel" name="fa-angle-left"/>
            <q-icon v-if="$store.state.rightBarOpen === true" title="Felhasználói Panel" name="fa-angle-right"/>
          </q-btn>
        </q-item-side>
      </q-item>
    </q-toolbar>
  </q-layout-header>
</template>

<script>
export default {}
</script>
