<template>
  <transition
    appear
    enter-active-class="animated zoomIn"
    leave-active-class="animated zoomOut"
  >
    <q-page class="flex flex-center" padding style="width: 1000px; max-width: 90vw;">
      <q-card inline class="q-ma-sm shadow-24" style="padding: 24px;">
        <q-item style="margin-bottom: 30px" class="shadow-1 bg-grey-2">
          <q-item-side left>
            <q-item-tile style="font-size: 30px;" color="primary" icon="fa-calendar" />
          </q-item-side>
          <q-item-main>
            <q-item-tile style="font-size: 30px;" class="text-center" label>Új esemény létrehozása</q-item-tile>
          </q-item-main>
        </q-item>
        <q-card-media overlay-position="bottom">
          <img src="statics/parallax2.jpg">

          <q-card-title slot="overlay">
            <span slot="subtitle">Kép kiválasztása</span>
          </q-card-title>
        </q-card-media>
        <q-card-main>
          <q-item>
            <q-item-side>
              <q-item-tile color="primary" icon="fa-globe" />
            </q-item-side>
            <q-item-main>
              <q-field>
                <q-input v-model="event.title.text" float-label="Esemény címe röviden">
                  <q-toggle
                    v-model="event.title.active"
                    checked-icon="visibility_off"
                    unchecked-icon="visibility"
                    style="margin-left: 25px"
                    true-value="0"
                    false-value="1"
                  />
                </q-input>
              </q-field>
            </q-item-main>
          </q-item>
          <q-item>
          <q-item-side>
            <q-item-tile color="primary" icon="fa-address-book" />
          </q-item-side>
          <q-item-main>
            <q-field>
              <q-input v-model="event.name.text" float-label="Esemény neve hosszabban">
                <q-toggle
                  v-model="event.name.active"
                  checked-icon="visibility_off"
                  unchecked-icon="visibility"
                  style="margin-left: 25px"
                  true-value="0"
                  false-value="1"
                />
              </q-input>
            </q-field>
          </q-item-main>
          </q-item>

          <q-item>
            <q-item-side>
              <q-item-tile color="primary" icon="fa-address-book" />
            </q-item-side>
            <q-item-main>
              <q-field>
                <q-input v-model="event.description.text" type="textarea" rows="2" float-label="Esemény leírása">
                  <q-toggle
                    v-model="event.description.active"
                    checked-icon="visibility_off"
                    unchecked-icon="visibility"
                    style="margin-left: 25px"
                    true-value="0"
                    false-value="1"
                  />
                </q-input>
              </q-field>
            </q-item-main>
          </q-item>

          <q-item>
            <q-item-side>
              <q-item-tile color="primary" icon="fa-calendar" />
            </q-item-side>
            <q-item-main>
              <q-field>
                <q-input v-model="event.date.text" float-label="Esemény időpontja">
                  <q-toggle
                    v-model="event.date.active"
                    checked-icon="visibility_off"
                    unchecked-icon="visibility"
                    style="margin-left: 25px"
                    true-value="0"
                    false-value="1"
                  />
                </q-input>
              </q-field>
            </q-item-main>
          </q-item>

          <q-item>
            <q-item-side>
              <q-item-tile color="primary" icon="fa-clock" />
            </q-item-side>
            <q-item-main>
              <q-field>
                <q-input v-model="event.duration.text" float-label="Esemény időtartalma">
                  <q-toggle
                    v-model="event.duration.active"
                    checked-icon="visibility_off"
                    unchecked-icon="visibility"
                    style="margin-left: 25px"
                    true-value="0"
                    false-value="1"
                  />
                </q-input>
              </q-field>
            </q-item-main>
          </q-item>

          <q-item>
            <q-item-side>
              <q-item-tile color="primary" icon="fa-map-marker" />
            </q-item-side>
            <q-item-main>
              <q-field>
                <q-input v-model="event.address.text" float-label="Esemény címe">
                  <q-toggle
                    v-model="event.address.active"
                    checked-icon="visibility_off"
                    unchecked-icon="visibility"
                    style="margin-left: 25px"
                    true-value="0"
                    false-value="1"
                  />
                </q-input>
              </q-field>
            </q-item-main>
          </q-item>

          <q-item>
            <q-item-side>
              <q-item-tile color="primary" icon="fa-ticket-alt" />
            </q-item-side>
            <q-item-main>
              <q-field>
                <q-input v-model="event.price.text" float-label="Jegy ára">
                  <q-toggle
                    v-model="event.price.active"
                    checked-icon="visibility_off"
                    unchecked-icon="visibility"
                    style="margin-left: 25px"
                    true-value="0"
                    false-value="1"
                  />
                </q-input>
              </q-field>
            </q-item-main>
          </q-item>

          <q-item>
            <q-item-side>
              <q-item-tile color="primary" icon="fa-link" />
            </q-item-side>
            <q-item-main>
              <q-field>
                <q-input v-model="event.ticketLink.text" float-label="Jegyhez link">
                  <q-toggle
                    v-model="event.ticketLink.active"
                    checked-icon="visibility_off"
                    unchecked-icon="visibility"
                    style="margin-left: 25px"
                    true-value="0"
                    false-value="1"
                  />
                </q-input>
              </q-field>
            </q-item-main>
          </q-item>

          <q-item>
            <q-item-side>
              <q-item-tile color="primary" icon="fa-eye" />
            </q-item-side>
            <q-item-main>
              <q-field>
                <p>Maga az esemény:</p>
                <q-btn-toggle
                  v-model="event.active"
                  toggle-color="primary"
                  :options="[
                            {label: 'Legyen látható', value: '1'},
                            {label: 'Ne legyen látható', value: '0'}
                          ]"
                />
              </q-field>
            </q-item-main>
          </q-item>
        </q-card-main>
        <q-btn color="primary" icon-right="fa-sign-in-alt" @click="createNewEvent()" label="Esemény létrehozása" />
      </q-card>
    </q-page>
  </transition>
</template>

<style>
</style>

<script>
import AXIOS from 'axios'
import { Notify } from 'quasar'
export default {
  name: 'newEvent',
  data: function () {
    return {
      event: {
        title: {
          text: '',
          active: '1'
        },
        name: {
          text: '',
          active: '1'
        },
        address: {
          text: '',
          active: '1'
        },
        description: {
          text: '',
          active: '1'
        },
        price: {
          text: '',
          active: '1'
        },
        duration: {
          text: '',
          active: '1'
        },
        ticketLink: {
          text: '',
          active: '1'
        },
        date: {
          text: '',
          active: '1'
        },
        active: '1'
      }
    }
  },
  methods: {
    createNewEvent () {
      AXIOS.post('/api/event/new', this.$data.event)
        .then(response => {
          self.$router.push('/esemeny/' + response.data)
          Notify.create({
            type: 'positive',
            color: 'positive',
            position: 'bottom',
            timeout: 3000,
            message: 'Az eseményt sikeresen létre lett hozva. Át is irányítottunk az esemény oldalára.'
          })
        })
        .catch(error => {
          console.log(error)
          Notify.create({
            type: 'warning',
            color: 'warning',
            position: 'bottom',
            timeout: 2000,
            message: 'Valami hiba történt az esemény létrehozásakor.'
          })
        })
    }
  }
}
</script>
