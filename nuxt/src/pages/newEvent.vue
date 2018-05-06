<template>
  <transition
    appear
    enter-active-class="animated zoomIn"
    leave-active-class="animated zoomOut"
  >
    <q-page class="flex flex-center" padding>
      <q-card inline class="q-ma-sm shadow-24" style="width: 1000px; max-width: 90vw; padding: 24px;">
        <q-item style="margin-bottom: 30px" class="shadow-1 bg-grey-2">
          <q-item-side left>
            <q-item-tile style="font-size: 30px;" color="primary" icon="fa-calendar" />
          </q-item-side>
          <q-item-main>
            <q-item-tile style="font-size: 30px;" class="text-center" label>Új esemény létrehozása</q-item-tile>
          </q-item-main>
        </q-item>
        <q-stepper color="primary" ref="stepper" alternative-labels>

          <q-step default name="first" title="Alap információk">
            <q-item class="shadow-2 bg-grey-1" style="margin-bottom: 15px;">
              <q-item-side icon="fa-calendar" color="primary"></q-item-side>
              <q-item-main>
                <q-input v-model="event.basicInfo.name" float-label="Az esemény neve"></q-input>
              </q-item-main>
            </q-item>
            <q-item class="shadow-2 bg-grey-1" style="margin-bottom: 15px;">
              <q-item-side icon="fa-map-marker" color="primary"></q-item-side>
              <q-item-main>
                <q-input v-model="event.basicInfo.title" float-label="Az esemény címe"></q-input>
              </q-item-main>
            </q-item>
            <q-item class="shadow-2 bg-grey-1" style="margin-bottom: 15px;">
              <q-item-side icon="fa-clock" color="primary"></q-item-side>
              <q-item-main>
                <q-datetime stack-label="Az esemény időpontja" modal="true" v-model="event.basicInfo.address" clearable format24h type="datetime" />
              </q-item-main>
            </q-item>
            <q-item class="shadow-2 bg-grey-1" style="margin-bottom: 15px;">
              <q-item-side icon="fa-book" color="primary"></q-item-side>
              <q-item-main>
                <q-input v-model="event.basicInfo.description" type="textarea" rows="3" modal="true" float-label="Az esemény leírása"></q-input>
              </q-item-main>
            </q-item>
          </q-step>

          <q-step name="second" title="Extra mezők hozzá adása">
            <q-card color="primary" inline class="q-ma-sm full-width">
              <q-card-main>
                <q-btn
                  icon="fa-info"
                  color="info"
                  style="margin: 10px"
                />
                Hasznos információk
                <q-item-tile>
                  Ezek az extra információk amivel személyre tudod szabni az eseményt. Jelenleg három példa mező van, ezeket nyugodtan töröld, vagy adj hozzájuk újat.
                </q-item-tile>
                <q-item-tile>
                  A mezők olyan sorrendbe fognak megjelenni az eseménynél, amilyen sorrendbe itt húzod őket. Drag'n'drop.
                </q-item-tile>
                <q-item-tile>
                  Az icon átállításához ezen a linken: <a style="color: white; font-weight: bold;" href="https://fontawesome.com/icons?d=gallery&m=free" target="_blank" >KATTINTS RÁM</a>. Keresd ki amelyik tetszik aztán a nevét másold be a mezőhöz az icon részbe. Ha jó akkor azonnal megfog jelenni az ikon mellette.
                </q-item-tile>
              </q-card-main>
            </q-card>
            <q-btn
              v-if="draggableFields === false"
              @click="toggleDraggableFields()"
              class="bg-grey-1 shadow-2"
              style="margin: 20px 0px 20px 0px;"
              icon-right="fa-arrows-alt"
            >
              Mezők mozgatása
            </q-btn>
            <q-btn
              v-if="draggableFields === true"
              @click="toggleDraggableFields()"
              class="bg-grey-1 shadow-2"
              style="margin: 20px 0px 20px 0px;"
              icon-right="fa-edit"
            >
              Mezők szerkesztése
            </q-btn>
            <q-card v-if="draggableFields === false">
              <q-item class="shadow-2 bg-grey-1" v-for="(field, index) in event.fields" :key="field.subText" style="margin-bottom: 15px;">
                <q-item-main>
                  <q-btn
                    color="info"
                    style="margin-bottom: 10px;"
                  >{{index + 1}}</q-btn>
                  <q-field>
                    <q-input v-model="field.subText" float-label="A mező neve"></q-input>
                    <q-input :after="[{icon: field.icon}]" v-model="field.icon" float-label="A mező ikonja"></q-input>
                    <q-input type="textarea" rows="3" v-model="field.text" float-label="A mező szöveges tartalma"></q-input>
                  </q-field>
                  <q-field style="margin-top: 3px;">
                    <q-btn-toggle
                      v-model="field.active"
                      toggle-color="primary"
                      :options="[
                            {label: 'Publikus', value: '1'},
                            {label: 'Privát', value: '0'}
                          ]"
                    />
                    <q-btn class="float-right" color="warning" icon="fa-trash-alt" @click="deleteField(index)"><q-tooltip>Mező törlése</q-tooltip></q-btn>
                  </q-field>
                </q-item-main>
              </q-item>
              <q-item>
                <q-btn color="info" icon-right="fa-plus" @click="addNewField()" label="Új mező hozzáadása" />
              </q-item>
            </q-card>

            <q-card v-if="draggableFields">
              <draggable v-model="event.fields">
                <q-item class="shadow-2 bg-grey-1" v-for="(field, index) in event.fields" :key="field.subText" style="margin-bottom: 15px;">
                  <q-item-main>
                    <q-btn
                      color="info"
                      style="margin-bottom: 10px;"
                    >{{index + 1}}</q-btn>
                    <q-field>
                      <q-item>
                        <q-item-side>
                          <q-item-tile color="primary" :icon="field.icon" />
                        </q-item-side>
                        <q-item-main>
                          <q-item-tile label>{{field.text}}</q-item-tile>
                          <q-item-tile sublabel>{{field.subText}}</q-item-tile>
                        </q-item-main>
                      </q-item>
                    </q-field>
                  </q-item-main>
                </q-item>
              </draggable>
            </q-card>
          </q-step>

          <q-step name="third" title="Előadók beállítása">
            <q-btn color="info" icon-right="fa-plus" @click="openUsersModal()" label="Új előadó hozzáadása" />
            <q-list-header>Kiválasztott előadók</q-list-header>
            <q-card v-for="(speaker, index) in event.speakers" :key="speaker.id" inline class="q-ma-sm shadow-10">
              <q-item>
                <q-item-side avatar="statics/guy-avatar.png" />
                <q-item-main>
                  <q-item-tile label>{{speaker.name}} {{speaker.lastName}}</q-item-tile>
                  <q-item-tile sublabel>{{speaker.username}}</q-item-tile>
                </q-item-main>
              </q-item>
              <q-list>
                <q-item>
                  <q-item-side>
                    <q-item-tile color="primary" icon="fa-user-md" />
                  </q-item-side>
                  <q-item-main>
                    <q-item-tile label>{{speaker.profession}}</q-item-tile>
                    <q-item-tile sublabel>Foglalkozás</q-item-tile>
                  </q-item-main>
                </q-item>
                <q-item>
                  <q-item-side>
                    <q-item-tile color="red" icon="fa-book" />
                  </q-item-side>
                  <q-item-main>
                    <q-item-tile label>{{speaker.description}}</q-item-tile>
                    <q-item-tile sublabel>Bemutatkozós</q-item-tile>
                  </q-item-main>
                </q-item>
              </q-list>
              <q-card-actions>
                <q-btn style="width: 100%" icon-right="fa-trash-alt" @click="deleteSpeaker(index)" color="warning">Előadó törlése</q-btn>
              </q-card-actions>
            </q-card>
          </q-step>

          <q-step name="fifth" title="Térkép beállítása">
            <google-map style="width: 100%; height: 100%; position: absolute; left:0; top:0"
                        :center="{lat: 1.38, lng: 103.8}"
                        :zoom="12"
            >

            </google-map>
          </q-step>

          <q-step name="fourth" title="Esemény beállítása">
            <q-card style="margin-bottom: 20px;">
              <q-item>
                <q-item-side>
                  <q-item-tile color="primary" icon="fa-eye" />
                </q-item-side>
                <q-item-main>
                  <q-field>
                    <p>Maga az esemény:</p>
                    <q-btn-toggle
                      v-model="active"
                      toggle-color="primary"
                      :options="[
                            {label: 'Legyen látható', value: '1'},
                            {label: 'Ne legyen látható', value: '0'}
                          ]"
                    />
                  </q-field>
                </q-item-main>
              </q-item>
            </q-card>
            <q-btn color="primary" class="float-right" icon-right="fa-sign-in-alt" @click="createNewEvent()" label="Esemény létrehozása" />
          </q-step>

          <q-stepper-navigation>
            <q-btn
              flat
              @click="$refs.stepper.previous()"
              label="Vissza"
            />
            <q-btn
              @click="$refs.stepper.next()"
              label="Következő"
            />
          </q-stepper-navigation>
        </q-stepper>
      </q-card>

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
  </transition>
</template>

<style>
</style>

<script>
import AXIOS from 'axios'
import { Notify } from 'quasar'
import draggable from 'vuedraggable'
// import {load, Map, Marker} from 'vue-google-maps'
export default {
  name: 'newEvent',
  components: {
    draggable
  },
  data: function () {
    return {
      usersModal: false,
      draggableFields: false,
      users: [],
      event: {
        basicInfo: {
          name: '',
          address: '',
          date: '',
          description: ''
        },
        fields: [
          {
            text: '',
            subText: 'Dresscode',
            active: '1',
            icon: 'fa-suitcase'
          },
          {
            text: '',
            subText: 'Jegy ár',
            active: '1',
            icon: 'fa-ticket-alt'
          },
          {
            text: '',
            subText: 'Jegy link',
            active: '1',
            icon: 'fa-link'
          }
        ],
        speakers: [
          {
            id: this.$store.state.loggedInUser.id,
            username: this.$store.state.loggedInUser.username,
            name: this.$store.state.loggedInUser.name,
            lastName: this.$store.state.loggedInUser.lastName,
            profession: this.$store.state.loggedInUser.profession,
            description: this.$store.state.loggedInUser.description
          }
        ],
        map: {

        },
        settings: {
          active: '1'
        }
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
    },
    addNewField () {
      if (this.$data.event.fields.length > 0 && this.$data.event.fields[this.$data.event.fields.length - 1].subText === 'Új mező') {
        Notify.create({
          type: 'info',
          color: 'info',
          position: 'bottom',
          timeout: 3000,
          message: 'Már kész van egy új mező'
        })
      } else {
        this.$data.event.fields.push({
          text: '',
          subText: 'Új mező',
          active: '1',
          icon: 'fa-file'
        })
      }
    },
    deleteField (index) {
      let name = this.$data.event.fields[index].subText
      this.$data.event.fields.splice(index, 1)
      Notify.create({
        type: 'positive',
        color: 'positive',
        position: 'center',
        timeout: 3000,
        message: 'Sikeresen törölted a következő mezőt: ' + name
      })
    },
    deleteSpeaker (index) {
      let username = this.$data.event.speakers[index].username
      this.$data.event.speakers.splice(index, 1)
      Notify.create({
        type: 'positive',
        color: 'positive',
        position: 'center',
        timeout: 3000,
        message: 'Sikeresen törölted a következő előadót: ' + username
      })
    },
    openUsersModal () {
      let self = this
      AXIOS.get('/api/user')
        .then(response => {
          self.$data.users = response.data
          self.$data.usersModal = true
        })
    },
    addUserToSpeakers (user) {
      this.$data.event.speakers.push({
        id: user.id,
        username: user.username,
        name: user.name,
        lastName: user.lastName,
        profession: user.profession,
        description: user.description
      })
      this.$data.usersModal = false
    },
    toggleDraggableFields () {
      if (this.$data.draggableFields) {
        this.$data.draggableFields = false
        Notify.create({
          type: 'info',
          color: 'info',
          position: 'bottom',
          timeout: 2000,
          message: 'Az elemekek most már cserélhető módban vannak'
        })
      } else {
        this.$data.draggableFields = true
        Notify.create({
          type: 'info',
          color: 'info',
          position: 'bottom',
          timeout: 2000,
          message: 'Az elemekek most már cserélhető módban vannak'
        })
      }
    }
  }
}
</script>
