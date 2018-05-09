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
            <q-item>
              <q-card v-show="isUserAnAttendee === false" @click.native="attendEvent()" class="shadow-5 cursor-pointer">
                <q-item>
                  <q-item-side color="primary" icon="fa-user-plus" />
                  <q-item-main>
                    <q-item-tile label>Feliratkozás</q-item-tile>
                  </q-item-main>
                </q-item>
              </q-card>
              <q-card v-show="isUserAnAttendee" @click.native="unAttendEvent()" class="shadow-5 cursor-pointer">
                <q-item>
                  <q-item-side color="negative" icon="fa-user-times" />
                  <q-item-main>
                    <q-item-tile label>Leiratkozás</q-item-tile>
                  </q-item-main>
                </q-item>
              </q-card>
              <q-card @click.native="unAttendEvent()" class="shadow-5 cursor-pointer">
                <q-item>
                  <q-item-side color="info" icon="fa-users" />
                  <q-item-main>
                    <q-item-tile label>Feliratkozottak</q-item-tile>
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
            <q-item v-for="eventField in event.eventFields" :key="eventField.subText">
              <q-item-side>
                <q-item-tile color="primary" :icon="eventField.icon" />
              </q-item-side>
              <q-item-main>
                <q-item-tile label>{{eventField.text}}</q-item-tile>
                <q-item-tile sublabel>{{eventField.subText}}</q-item-tile>
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
                <q-item-tile style="font-size: 30px;" class="text-center" label>Kommentek</q-item-tile>
              </q-item-main>
            </q-item>
          </q-list>
          <q-modal v-model="usersModal">
            <q-btn
              icon="fa-times"
              class="float-right"
              color="info"
              @click="usersModal = false"
            />
            <q-list class="text-center"
                    no-border
                    link
                    inset-delimiter
            >
              <q-item-separator></q-item-separator>
              <q-list-header>Felhasználók</q-list-header>
              <q-item-separator></q-item-separator>
              <q-item class="shadow-1" v-for="user in users" :key="user.id">
                <q-item-side avatar="statics/guy-avatar.png" />
                <q-item-main>
                  <q-item-tile label>{{user.name}} {{user.lastName}}</q-item-tile>
                  <q-item-tile sublabel>{{user.username}}</q-item-tile>
                </q-item-main>
                <q-item-side right>
                  <q-btn @click="addUserToSpeakers(user)" icon="fa-plus" color="primary"></q-btn>
                </q-item-side>
              </q-item>
            </q-list>
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
      isUserAnAttendee: false,
      showAttendeesModal: false,
      showMenu: []
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
    attendEvent () {
      AXIOS.get('/api/event/' + this.$data.event.id + '/attend')
        .then(() => {
          this.$data.isUserAnAttendee = true
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
    }
  }
}
</script>

<style scoped>

</style>
