<template>
  <q-layout-drawer
    v-if="$store.state.isLoggedIn === true"
    side="right"
    v-model="$store.state.rightBarOpen"
    :content-class="$q.theme === 'mat' ? 'bg-grey-2' : null"
  >
    <q-list class="vertical-middle">
      <q-collapsible avatar="statics/guy-avatar.png" :label="this.$store.state.loggedInUser.username">
        <q-item>
          <q-item-side>
            <q-item-tile color="primary" icon="perm identity" />
          </q-item-side>
          <q-item-main>
            <q-item-tile label>{{this.$store.state.loggedInUser.profession}}</q-item-tile>
            <q-item-tile sublabel>Foglalkozás</q-item-tile>
          </q-item-main>
        </q-item>
        <q-item>
          <q-item-side>
            <q-item-tile color="red" icon="email" />
          </q-item-side>
          <q-item-main>
            <q-item-tile label>{{this.$store.state.loggedInUser.email}}</q-item-tile>
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
          <q-btn icon="perm identity">
            <q-tooltip>Profil</q-tooltip>
          </q-btn>
          <q-btn icon="message">
            <q-tooltip>Üzenetek</q-tooltip>
          </q-btn>
          <q-btn icon="fa-bell">
            <q-tooltip>Értesítések</q-tooltip>
          </q-btn>
          <q-btn @click="$store.dispatch('logout')" icon="lock">
            <q-tooltip>Kijelentkezés</q-tooltip>
          </q-btn>
          <q-btn icon="settings">
            <q-tooltip>Beállítások</q-tooltip>
          </q-btn>
        </q-item-side>
      </q-item>
      <q-item>
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
      </q-item>
    </q-list>
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
</template>

<script>
export default {}
</script>
