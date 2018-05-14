<template>
  <q-layout-drawer
    v-if="$store.state.isLoggedIn === true"
    side="right"
    v-model="$store.state.siteSettings.rightBarOpen"
    :content-class="$q.theme === 'mat' ? 'bg-grey-2' : null"
  >
    <q-list class="vertical-middle">
      <q-collapsible avatar="statics/guy-avatar.png" :label="this.$store.state.loggedInUser.username">
        <q-item>
          <q-item-side>
            <q-item-tile color="primary" icon="fa-user-md" />
          </q-item-side>
          <q-item-main>
            <q-item-tile label>{{this.$store.state.loggedInUser.profession}}</q-item-tile>
            <q-item-tile sublabel>Foglalkozás</q-item-tile>
          </q-item-main>
        </q-item>
        <q-item>
          <q-item-side>
            <q-item-tile color="red" icon="fa-envelope" />
          </q-item-side>
          <q-item-main>
            <q-item-tile label>{{this.$store.state.loggedInUser.email}}</q-item-tile>
            <q-item-tile sublabel>Email</q-item-tile>
          </q-item-main>
        </q-item>
        <q-item>
          <q-item-side>
            <q-item-tile color="amber" icon="fa-user-secret" />
          </q-item-side>
          <q-item-main>
            <q-item-tile label>{{this.$store.state.loggedInUser.role.role}}</q-item-tile>
            <q-item-tile sublabel>Jogosultságok</q-item-tile>
          </q-item-main>
        </q-item>
      </q-collapsible>
      <q-item class="main-color">
        <q-item-side>
          <q-btn @click="$router.push('/felhasznalo/' + $store.state.loggedInUser.username)" icon="perm identity">
            <q-tooltip>Profil</q-tooltip>
          </q-btn>
          <q-btn @click="logout()" icon="lock">
            <q-tooltip>Kijelentkezés</q-tooltip>
          </q-btn>
          <q-btn icon="settings">
            <q-tooltip>Beállítások</q-tooltip>
          </q-btn>
          <q-btn icon="fa-info-circle">
            <q-tooltip>Segítség</q-tooltip>
          </q-btn>
          <q-btn icon="fa-bug" @click="$store.dispatch('toggleSendErrorModal')">
            <q-tooltip>Hiba jelentés</q-tooltip>
          </q-btn>
        </q-item-side>
      </q-item>
    </q-list>
    <q-list>
      <q-tabs color="bg-grey-2" text-color="primary" align="justify">
        <q-tab default name="events" slot="title" icon="fa-calendar-alt">
          <q-tooltip>Események</q-tooltip>
          <q-chip floating color="red">3</q-chip>
        </q-tab>
        <q-tab name="messages" slot="title" icon="mail">
          <q-tooltip>Üzenetek</q-tooltip>
          <q-chip floating color="red">9</q-chip>
        </q-tab>
        <q-tab name="notifications" slot="title" icon="fa-bell">
          <q-tooltip>Értesítések</q-tooltip>
          <q-chip floating color="red">6</q-chip>
        </q-tab>

        <q-tab-pane name="messages">
          <q-list-header>Bejövő üzenetek</q-list-header>
          <q-item multiline>
            <q-item-side avatar="statics/guy-avatar.png" />
            <q-item-main>
              <q-item-tile label lines="1">Brunch this weekend? Yeah, this weekend. Really. This one.</q-item-tile>
              <q-item-tile sublabel lines="2">
                <span>John Doe</span>
                -- I'll be in your neighborhood doing errands this
                weekend. Do you want to grab brunch?
              </q-item-tile>
            </q-item-main>
            <q-item-side right>
              <q-item-tile stamp>3 óra 12 perc</q-item-tile>
              <q-item-tile icon="mail" color="primary" />
            </q-item-side>
          </q-item>
        </q-tab-pane>

        <q-tab-pane name="notifications">
          <q-list-header>Értesítések</q-list-header>
        </q-tab-pane>

        <q-tab-pane name="events">
          <q-list-header>Közelgő eseményeid</q-list-header>
          <q-card inline class="q-ma-sm">
            <q-card-media>
              <img src="statics/parallax2.jpg">
            </q-card-media>
            <q-list>
              <q-item>
                <q-item-side>
                  <q-item-tile color="primary" icon="local bar" />
                </q-item-side>
                <q-item-main>
                  <q-item-tile label>Bar XYZ</q-item-tile>
                  <q-item-tile sublabel>Have a drink.</q-item-tile>
                </q-item-main>
              </q-item>
              <q-item>
                <q-item-side>
                  <q-item-tile color="red" icon="local gas station" />
                </q-item-side>
                <q-item-main>
                  <q-item-tile label>Gas Station</q-item-tile>
                  <q-item-tile sublabel>Fill your gas tank.</q-item-tile>
                </q-item-main>
              </q-item>
              <q-item>
                <q-item-side>
                  <q-item-tile color="amber" icon="local movies" />
                </q-item-side>
                <q-item-main>
                  <q-item-tile label>Cinema XYZ</q-item-tile>
                  <q-item-tile sublabel>Watch a movie.</q-item-tile>
                </q-item-main>
              </q-item>
            </q-list>
          </q-card>
        </q-tab-pane>

      </q-tabs>
    </q-list>
    <send-error></send-error>

  </q-layout-drawer>
</template>

<script>
import SendError from '../components/SendError'
export default {
  components: {
    SendError
  },
  methods: {
    logout () {
      this.$store.dispatch('logout')
      this.$router.push('/bejelentkezes')
    }
  }
}
</script>
