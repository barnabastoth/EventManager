<template>
  <transition
    appear
    enter-active-class="animated zoomIn"
    leave-active-class="animated zoomOut"
  >
    <q-layout>
      <q-page-container>
        <q-page padding style="width: 1000px; max-width: 90vw;">
          <q-list inline class="q-ma-sm shadow-24">
            <q-item>
              <q-card>
                <q-card-media overlay-position="top">
                  <img src="statics/parallax2.jpg">
                  <q-card-title slot="overlay">
                    <h5>{{event.name}}</h5>
                  </q-card-title>
                </q-card-media>
              </q-card>
            </q-item>
            <q-item class="flex">
              <q-card style="margin: 5px;" v-show="isUserAnAttendee === false" @click.native="attendEvent()" class="shadow-5 cursor-pointer">
                <q-item>
                  <q-item-side color="primary" icon="fa-user-plus" />
                  <q-item-main>
                    <q-item-tile label>Feliratkozás</q-item-tile>
                  </q-item-main>
                </q-item>
              </q-card>
              <q-card style="margin: 5px;" v-show="isUserAnAttendee" @click.native="unAttendEvent()" class="shadow-5 cursor-pointer">
                <q-item>
                  <q-item-side color="negative" icon="fa-user-times" />
                  <q-item-main>
                    <q-item-tile label>Leiratkozás</q-item-tile>
                  </q-item-main>
                </q-item>
              </q-card>
              <q-card style="margin: 5px;" @click.native="showAttendeesModal = true" class="shadow-5 cursor-pointer"
                      v-if="$store.state.loggedInUser.role.role === 'Admin' || $store.state.loggedInUser.role.role === 'Tulajdonos'">
                <q-item>
                  <q-item-side color="info" icon="fa-users" />
                  <q-item-main>
                    <q-item-tile label>Feliratkozottak</q-item-tile>
                  </q-item-main>
                </q-item>
              </q-card>
              <q-card style="margin: 5px;" @click.native="$router.push('/esemeny/' + $data.event.id + '/szerkesztes')" class="shadow-5 cursor-pointer"
                      v-if="$store.state.loggedInUser.role.role === 'Admin' || $store.state.loggedInUser.role.role === 'Tulajdonos'">
                <q-item>
                  <q-item-side color="info" icon="fa-edit" />
                  <q-item-main>
                    <q-item-tile label>Szerkesztés</q-item-tile>
                  </q-item-main>
                </q-item>
              </q-card>
              <q-card style="margin: 5px;" @click.native="deleteEvent()" class="shadow-5 cursor-pointer"
                      v-if="$store.state.loggedInUser.role.role === 'Admin' || $store.state.loggedInUser.role.role === 'Tulajdonos'">
                <q-item>
                  <q-item-side color="negative" icon="fa-trash-alt" />
                  <q-item-main>
                    <q-item-tile label>Esemény törlése</q-item-tile>
                  </q-item-main>
                </q-item>
              </q-card>
            </q-item>
            <q-item style="margin-bottom: 30px" class="shadow-1 bg-grey-2">
              <q-item-side left>
                <q-item-tile style="font-size: 30px;" color="primary" icon="fa-address-card" />
              </q-item-side>
              <q-item-main>
                <q-item-tile style="font-size: 30px;" class="text-center" label>Információk</q-item-tile>
              </q-item-main>
            </q-item>
            <q-item>
              <q-item-side>
                <q-item-tile color="primary" icon="fa-globe" />
              </q-item-side>
              <q-item-main>
                <q-item-tile label>{{event.name}}</q-item-tile>
                <q-item-tile sublabel>Esemény neve</q-item-tile>
              </q-item-main>
            </q-item>
            <q-item>
              <q-item-side>
                <q-item-tile color="primary" icon="fa-map-marker" />
              </q-item-side>
              <q-item-main>
                <q-item-tile label>{{event.address}}</q-item-tile>
                <q-item-tile sublabel>Helyszín</q-item-tile>
              </q-item-main>
            </q-item>
            <q-item>
              <q-item-side>
                <q-item-tile color="primary" icon="fa-clock" />
              </q-item-side>
              <q-item-main>
                <q-item-tile label>{{event.date}}</q-item-tile>
                <q-item-tile sublabel>Időpont</q-item-tile>
              </q-item-main>
            </q-item>

            <q-item v-for="field in event.fields" :key="field.id">
              <q-item-side>
                <q-item-tile color="primary" :icon="field.icon" />
              </q-item-side>
              <q-item-main>
                <q-item-tile label>{{field.text}}</q-item-tile>
                <q-item-tile sublabel>{{field.subText}}</q-item-tile>
              </q-item-main>
            </q-item>

            <q-item>
              <q-item-side>
                <q-item-tile color="primary" icon="fa-book" />
              </q-item-side>
              <q-item-main>
                <q-item-tile label>{{event.description}}</q-item-tile>
                <q-item-tile sublabel>Leírás</q-item-tile>
              </q-item-main>
            </q-item>

            <q-item style="margin-bottom: 30px" class="shadow-1 bg-grey-2">
              <q-item-side left>
                <q-item-tile style="font-size: 30px;" color="primary" icon="fa-microphone" />
              </q-item-side>
              <q-item-main>
                <q-item-tile style="font-size: 30px;" class="text-center" label>Előadók</q-item-tile>
              </q-item-main>
            </q-item>

            <q-item class="flex flex-center">
              <q-card inline class="q-ma-sm" v-for="speaker in event.speakers" :key="speaker.id">
                <q-btn style="width: 100%" icon-right="fa-external-link-alt" @click="$router.push('/felhasznalo/' + speaker.id)" color="info">Profil megnyitása</q-btn>
                <q-card-media>
                  <img style="display: block;
                       width: auto;
                       height: auto;
                       max-height: 200px" src="statics/parallax2.jpg">
                </q-card-media>
                <q-list>
                  <q-item>
                    <q-item-side>
                      <q-item-tile color="primary" icon="local bar" />
                    </q-item-side>
                    <q-item-main>
                      <q-item-tile label>{{speaker.name}} {{speaker.lastName}}</q-item-tile>
                      <q-item-tile sublabel>{{speaker.username}}</q-item-tile>
                    </q-item-main>
                  </q-item>
                  <q-item>
                    <q-item-side>
                      <q-item-tile color="red" icon="fa-user-md" />
                    </q-item-side>
                    <q-item-main>
                      <q-item-tile label>{{speaker.profession}}</q-item-tile>
                    </q-item-main>
                  </q-item>
                </q-list>
              </q-card>
            </q-item>

            <q-item style="margin-bottom: 30px" class="shadow-1 bg-grey-2">
              <q-item-side left>
                <q-item-tile style="font-size: 30px;" color="primary" icon="fa-comment-alt" />
              </q-item-side>
              <q-item-main>
                <q-item-tile style="font-size: 30px;" class="text-center" label>Hozzászólások</q-item-tile>
              </q-item-main>
            </q-item>

            <q-item>
              <q-btn color="info" icon-right="fa-plus" @click="showNewCommentSection = !showNewCommentSection" label="Hozzászólás írása" />
            </q-item>

            <q-item class="shadow-24" style="margin: 10px" v-show="showNewCommentSection">
              <q-item-side avatar="statics/guy-avatar.png" />
              <q-item-main>
                <q-item-tile>{{this.$store.state.loggedInUser.username}}</q-item-tile>
                <q-item-tile><q-input v-model="newComment.message" float-label="Hozzzászólás szövege"></q-input></q-item-tile>
                <q-item-tile class="float-right">
                  <q-btn
                  icon-right="fa-step-forward"
                  color="primary"
                  style="margin-top: 5px"
                  @click="addNewComment()"
                  >
                    Hozzászólás küldése
                  </q-btn>
                </q-item-tile>
              </q-item-main>
            </q-item>

            <q-item>
              {{event.comments}}
            </q-item>

            <!--<q-item v-for="comment in event.comments" :key="comment.id">-->
              <!--<q-item-side avatar="statics/guy-avatar.png" />-->
              <!--<q-item-main>-->
                <!--<q-item-tile>{{comment.username}}</q-item-tile>-->
                <!--<q-item-tile>{{comment.user.lastName}} {{comment.user.name}}</q-item-tile>-->
                <!--<q-item-tile>{{comment.date}}</q-item-tile>-->
                <!--<q-item-tile>{{comment.message}}</q-item-tile>-->
              <!--</q-item-main>-->
            <!--</q-item>-->

          </q-list>

          <q-modal v-model="showAttendeesModal">
            <q-card style="padding: 10px; width: 500px; max-width: 90vw">
              <q-list class="text-center"
                      no-border
                      link
                      inset-delimiter
              >
                <q-btn
                  icon="fa-times"
                  class="float-right"
                  color="info"
                  @click="showAttendeesModal = false"
                >
                  <q-tooltip>Bezárás</q-tooltip>
                </q-btn>
                <q-list-header>Feliratkozott felhasználók</q-list-header>
                <q-item-separator></q-item-separator>
                <q-item @click.native="$router.push('/felhasznalo/' + user.username)" style="margin-bottom: 10px" class="shadow-1" v-for="user in event.attendees" :key="user.id">
                  <q-item-side avatar="statics/guy-avatar.png" />
                  <q-item-main>
                    <q-item-tile label>{{user.name}} {{user.lastName}}</q-item-tile>
                    <q-item-tile sublabel>{{user.username}}</q-item-tile>
                  </q-item-main>
                </q-item>
              </q-list>
            </q-card>
          </q-modal>

        </q-page>
      </q-page-container>
    </q-layout>
  </transition>
</template>

<script>
import AXIOS from 'axios'
import { Notify } from 'quasar'
export default {
  name: 'event',
  data: function () {
    return {
      event: [],
      newComment: {
        message: ''
      },
      isUserAnAttendee: false,
      showAttendeesModal: false,
      showNewCommentSection: false
    }
  },
  props: ['id'],
  beforeMount () {
    let self = this
    AXIOS.get('/api/event/' + this.id)
      .then(response => {
        self.event = response.data
      })
  },
  methods: {
    addNewComment () {
      console.log(this.$data.event.id)
      console.log(this.$store.state.loggedInUser.id)
      this.$data.newComment.userId = this.$store.state.loggedInUser.id
      this.$data.newComment.eventId = this.$data.event.id
      AXIOS.post('/api/event/comment/new', this.$data.newComment)
        .then(() => {
          this.$data.showNewCommentSection = false
          this.$data.event.comments.push(this.$data.newComment)
          this.$data.newComment = {message: ''}
          Notify.create({
            type: 'positive',
            color: 'positive',
            position: 'bottom',
            timeout: 2000,
            message: 'A kommented hozzá lett adva az eseményhez.'
          })
        })
    },
    attendEvent () {
      let self = this
      AXIOS.get('/api/event/' + this.$data.event.id + '/attend')
        .then(response => {
          self.$data.isUserAnAttendee = true
          self.$data.event.attendees = response.data
          Notify.create({
            type: 'positive',
            color: 'positive',
            position: 'bottom',
            timeout: 3000,
            message: 'Sikeresen feliratkoztál az eseményre!'
          })
        })
    },
    unAttendEvent () {
      this.$data.isUserAnAttendee = false
      Notify.create({
        type: 'info',
        color: 'info',
        position: 'bottom',
        timeout: 2000,
        message: 'Sikeresen leiratkoztál az eseményről!!'
      })
    },
    deleteEvent () {
      let self = this
      this.$q.dialog({
        title: 'Figyelem! Biztos véglegesen törölni akarod az eseményt?',
        message: 'Írd be az esemény pontos nevét, hogy törölni tudd az eseményt. Az esemény neve: ' + self.$data.event.name,
        color: 'primary',
        ok: {
          push: true,
          color: 'warning',
          label: 'Végleges törlés.'
        },
        cancel: {
          push: true,
          color: 'info',
          label: 'Vissza'
        },
        preventClose: true,
        noBackdropDismiss: false, // gets set to "true" automatically if preventClose is "true"
        noEscDismiss: false, // gets set to "true" automatically if preventClose is "true"
        position: 'bottom',
        prompt: {
          model: '',
          type: 'text' // optional
        }
      })
        .then(data => {
          if (data === self.$data.event.name) {
            AXIOS.get('/api/event/' + self.id + '/delete')
              .then(() => {
                self.$router.push('/')
                Notify.create({
                  type: 'info',
                  color: 'info',
                  position: 'bottom',
                  timeout: 5000,
                  message: 'Az esemény véglegesen törölve lett az adatbázisbol.'
                })
              })
          } else {
            Notify.create({
              type: 'info',
              color: 'info',
              position: 'bottom',
              timeout: 5000,
              message: 'A beírt név nem helyes, biztos, hogy kiakarod törölni ezt az eseményt?'
            })
          }
        })
    }
  }
}
</script>

<style scoped>

</style>
