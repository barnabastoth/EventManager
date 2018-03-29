<template>
  <q-layout view="lHh Lpr lFf">
    <q-layout-header>
      <q-toolbar
        color="dark"
        inverted
      >
        <q-btn
          @click="leftDrawerOpen = !leftDrawerOpen"
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
              @click="rightDrawerOpen = !rightDrawerOpen"
              aria-label="Menu"
            >
              <q-icon title="Felhasználói Panel" name="fa-angle-down"/>
            </q-btn>
          </q-item-side>
        </q-item>
      </q-toolbar>
    </q-layout-header>

    <q-layout-drawer
      side="left"
      v-model="leftDrawerOpen"
      :content-class="$q.theme === 'mat' ? 'bg-grey-2' : null"
    >
      <q-list highlight>
        <q-list-header>Menü</q-list-header>
        <q-item>
          <q-item-side icon="home" inverted color="green" />
          <q-item-main>
            <q-item-tile label>Főoldal</q-item-tile>
            <q-item-tile sublabel>Hírek, újdonságok</q-item-tile>
          </q-item-main>
          <q-item-side right icon="info" />
        </q-item>
        <q-item>
          <q-item-side icon="folder" inverted color="amber" />
          <q-item-main>
            <q-item-tile label>Tanulmányok</q-item-tile>
          </q-item-main>
        </q-item>
        <q-item>
          <q-item-side icon="fa-calendar" inverted color="amber" />
          <q-item-main>
            <q-item-tile label>Események</q-item-tile>
          </q-item-main>
        </q-item>
        <q-item>
          <q-item-side icon="fa-address-card" inverted color="amber" />
          <q-item-main>
            <q-item-tile label>Kapcsolat</q-item-tile>
          </q-item-main>
        </q-item>
        <q-item>
          <q-item-side icon="fa-address-card" inverted color="amber" />
          <q-item-main>
            <q-item-tile label>Kapcsolat</q-item-tile>
          </q-item-main>
        </q-item>
      </q-list>
    </q-layout-drawer>

    <q-layout-drawer
      side="right"
      v-model="rightDrawerOpen"
      :content-class="$q.theme === 'mat' ? 'bg-grey-2' : null"
    >
      <q-card class="q-ma-sm main-color">
        <q-list class="vertical-middle">
          <q-collapsible avatar="statics/guy-avatar.png" label="Barnabás Tóth">
            <div>
              <q-item>
                <q-item-side>
                  <q-item-tile color="primary" icon="perm identity" />
                </q-item-side>
                <q-item-main>
                  <q-item-tile label>Bar XYZ</q-item-tile>
                  <q-item-tile sublabel>Have a drink.</q-item-tile>
                </q-item-main>
              </q-item>
              <q-item>
                <q-item-side>
                  <q-item-tile color="red" icon="lock open" />
                </q-item-side>
                <q-item-main>
                  <q-item-tile label>Gas Station</q-item-tile>
                  <q-item-tile sublabel>Fill your gas tank.</q-item-tile>
                </q-item-main>
              </q-item>
              <q-item>
                <q-item-side>
                  <q-item-tile color="amber" icon="settings" />
                </q-item-side>
                <q-item-main>
                  <q-item-tile label>Cinema XYZ</q-item-tile>
                  <q-item-tile sublabel>Watch a movie.</q-item-tile>
                </q-item-main>
              </q-item>
            </div>
          </q-collapsible>
          <q-item class="main-color shadow-2">
            <q-item-side>
              <q-btn><q-icon name="perm identity" /></q-btn>
              <q-btn><q-icon name="lock open" /></q-btn>
              <q-btn><q-icon name="settings" /></q-btn>
              <q-btn><q-icon name="fa-bell"/></q-btn>
              <q-btn><q-icon name="fa-bell"/></q-btn>
            </q-item-side>
          </q-item>
        </q-list>
      </q-card>
      <q-lis>
        <q-list-header>Recent chats</q-list-header>
        <q-item>
          <q-item-side>
            <q-item-tile avatar>
              <img src="statics/guy-avatar.png">
            </q-item-tile>
          </q-item-side>
          <q-item-main label="John Doe" />
          <q-item-side>
            <q-item-tile icon="chat_bubble" color="green" />
          </q-item-side>
        </q-item>
        <q-item>
          <q-item-side avatar="statics/guy-avatar.png" />
          <q-item-main label="Jim Doe" />
          <q-item-side right icon="chat_bubble" />
        </q-item>
        <q-item-separator />
        <q-list-header>Previous chats</q-list-header>
        <q-item>
          <q-item-side avatar="statics/guy-avatar.png" />
          <q-item-main label="Jack Doe" />
        </q-item>
      </q-lis>
    </q-layout-drawer>

    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script>
import { openURL } from 'quasar'

export default {
  name: 'LayoutDefault',
  data () {
    return {
      leftDrawerOpen: this.$q.platform.is.desktop,
      rightDrawerOpen: this.$q.platform.is.desktop
    }
  },
  methods: {
    openURL
  }
}
</script>

<style>
</style>
