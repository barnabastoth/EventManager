<template>
  <transition
    appear
    enter-active-class="animated zoomIn"
    leave-active-class="animated zoomOut"
  >
    <q-layout>
      <q-page-container>
        <q-page padding style="width: 1000px; max-width: 90vw;">
          <q-card inline class="q-ma-sm shadow-24">
            <q-item>
              <q-item-side avatar="statics/guy-avatar.png" />
              <q-item-main>
                <q-item-tile label>{{user.lastName}} {{user.name}}</q-item-tile>
                <q-item-tile sublabel>{{user.username}}</q-item-tile>
              </q-item-main>
            </q-item>
            <q-card-media>
              <img src="statics/parallax2.jpg">
            </q-card-media>
            <q-tabs color="primary" class="shadow-24" glossy>
              <q-tab default name="Információk" slot="title" icon="fa-user" label="Információk" />
              <q-tab name="Kommentek" slot="title" icon="fa-comment-alt" label="Kommentek" />
              <q-tab name="Események" slot="title" icon="fa-calendar" label="Események" />

              <q-tab-pane name="Információk">
                <q-list>
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
                      <q-item-tile color="primary" icon="fa-user" />
                    </q-item-side>
                    <q-item-main>
                      <q-item-tile label>{{this.$store.state.loggedInUser.profession}}</q-item-tile>
                      <q-item-tile sublabel>Foglalkozás</q-item-tile>
                    </q-item-main>
                  </q-item>
                  <q-item>
                    <q-item-side>
                      <q-item-tile color="primary" icon="fa-file-alt" />
                    </q-item-side>
                    <q-item-main>
                      <q-item-tile label>{{this.$store.state.loggedInUser.description}}</q-item-tile>
                      <q-item-tile sublabel>Rólam</q-item-tile>
                    </q-item-main>
                  </q-item>
                  <q-item>
                    <q-item-side>
                      <q-item-tile color="amber" icon="fa-user-md" />
                    </q-item-side>
                    <q-item-main>
                      <q-item-tile label>{{this.$store.state.loggedInUser.role.role}}</q-item-tile>
                      <q-item-tile sublabel>Jogosultságok</q-item-tile>
                    </q-item-main>
                  </q-item>
                  <q-item>
                    <q-item-side>
                      <q-item-tile color="amber" icon="fa-calendar" />
                    </q-item-side>
                    <q-item-main>
                      <q-item-tile label>{{this.$store.state.loggedInUser.memberSince}}</q-item-tile>
                      <q-item-tile sublabel>Csatlakozott</q-item-tile>
                    </q-item-main>
                  </q-item>
                </q-list>
              </q-tab-pane>

              <q-tab-pane name="Kommentek">
                {{this.$store.state.loggedInUser.comments}}
              </q-tab-pane>

              <q-tab-pane name="Események">
                {{this.$store.state.loggedInUser.attendedEvents}}
                <!--<q-item @click.native="$router.push('/esemeny/' + event.id)" class="shadow-1" v-for="event in this.$store.state.loggedInUser.speakerAt" :key="event.id">-->
                  <!--<q-item-side image="statics/parallax2.jpg"></q-item-side>-->
                  <!--<q-item-main>-->
                    <!--<q-item-tile label>{{event.name}}</q-item-tile>-->
                    <!--<q-item-tile sublabel>{{event.address}}</q-item-tile>-->
                    <!--<q-item-tile sublabel>{{event.date}}</q-item-tile>-->
                  <!--</q-item-main>-->
                  <!--<q-item-side right>-->
                    <!--<q-item-tile icon="fa-edit"></q-item-tile>-->
                  <!--</q-item-side>-->
                <!--</q-item>-->
              </q-tab-pane>
            </q-tabs>
          </q-card>

        </q-page>
      </q-page-container>
    </q-layout>
  </transition>
</template>

<script>
import AXIOS from 'axios'
export default {
  name: 'profile',
  data: function () {
    return {
      user: []
    }
  },
  props: ['username'],
  beforeMount () {
    let self = this
    AXIOS.get('/api/user/' + this.username)
      .then(response => {
        self.user = response.data
      })
  }
}
</script>

<style scoped>

</style>
