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
              <q-icon v-if="rightDrawerOpen === false" title="Felhasználói Panel" name="fa-angle-left"/>
              <q-icon v-if="rightDrawerOpen === true" title="Felhasználói Panel" name="fa-angle-right"/>
            </q-btn>
          </q-item-side>
        </q-item>
      </q-toolbar>
    </q-layout-header>

    <q-layout-drawer
      side="left"
      v-model="leftDrawerOpen"
      :content-class="$q.theme === 'mat' ? 'bg-grey-1' : null"
    >
      <q-list class="text-center" >
        <img alt="Quasar logo" src="../assets/quasar-logo-full.svg">
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
      <q-list class="vertical-middle">
        <q-btn
          color="secondary"
          style="width:200px;"
          @click="$router.push('/profile')"
        >Go back</q-btn>
        <q-collapsible avatar="statics/guy-avatar.png" label="Barnabás Tóth">
          <q-item>
            <q-item-side>
              <q-item-tile color="primary" icon="perm identity" />
            </q-item-side>
            <q-item-main>
              <q-item-tile label>{{this.$store.state}}</q-item-tile>
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
        </q-collapsible>
        <q-item class="main-color">
          <q-item-side>
            <q-btn><q-icon name="perm identity" /></q-btn>
            <q-btn><q-icon name="lock open" /></q-btn>
            <q-btn><q-icon name="settings" /></q-btn>
            <q-btn><q-icon name="fa-bell"/></q-btn>
            <q-btn><q-icon name="fa-bell"/></q-btn>
          </q-item-side>
        </q-item>
    </q-list>
    <q-tabs inverted color="bg-grey-2" text-color="primary" align="justify">
      <q-tab default name="mails" slot="title" icon="mail" label="Mails" />
      <q-tab name="alarms" slot="title" icon="alarm" label="Alarms" />
      <q-tab name="movies" slot="title" icon="movie" label="Movies" />

      <q-tab-pane name="mails">
        Emails tab
      </q-tab-pane>
      <q-tab-pane name="alarms">
        <q-list>
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
      </q-list>
      </q-tab-pane>
      <q-tab-pane name="movies">
        Movies tab
      </q-tab-pane>
    </q-tabs>
    <q-list>
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
      </q-list>
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
