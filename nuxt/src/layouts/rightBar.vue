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
            <q-item-tile color="primary" icon="fa-user" />
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
            <q-item-tile color="amber" icon="fa-user-md" />
          </q-item-side>
          <q-item-main>
            <q-item-tile label>{{this.$store.state.loggedInUser.roles[0].role}}</q-item-tile>
            <q-item-tile sublabel>Jogosultságok</q-item-tile>
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
          <q-btn @click="logout()" icon="lock">
            <q-tooltip>Kijelentkezés</q-tooltip>
          </q-btn>
          <q-btn icon="settings">
            <q-tooltip>Beállítások</q-tooltip>
          </q-btn>
        </q-item-side>
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
export default {
  methods: {
    logout () {
      this.$store.dispatch('logout')
    }
  }
}
</script>
