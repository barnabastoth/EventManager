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
                <q-input v-model="event.basicInfo.address" float-label="Helyszín"></q-input>
              </q-item-main>
            </q-item>
            <q-item class="shadow-2 bg-grey-1" style="margin-bottom: 15px;">
              <q-item-side icon="fa-clock" color="primary"></q-item-side>
              <q-item-main>
                <q-datetime stack-label="Az esemény időpontja" modal="true" v-model="event.basicInfo.date" clearable format24h type="datetime" />
              </q-item-main>
            </q-item>
            <q-item class="shadow-2 bg-grey-1" style="margin-bottom: 15px;">
              <q-item-side icon="fa-book" color="primary"></q-item-side>
              <q-item-main>
                <q-input v-model="event.basicInfo.description" float-label="Az esemény leírása"></q-input>
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
                  A mezők megjelenésének sorrendjének változtatásához kattints a Mezők Mozgatása gombra.
                </q-item-tile>
                <q-item-tile>
                  Az icon átállításához ezen a linken: <a style="color: white; font-weight: bold;" href="https://fontawesome.com/icons?d=gallery&m=free" target="_blank" >KATTINTS RÁM</a>. Keresd ki amelyik tetszik aztán a nevét másold be a mezőhöz az icon részbe. Ha jó akkor azonnal megfog jelenni az ikon mellette.
                </q-item-tile>
                <q-item-tile>
                  Itt tudsz megadni extra információt, mint például: Útvonal tömegközlekedéssel
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
              <q-item class="shadow-2 bg-grey-1" v-for="(eventField, index) in event.fields" :key="eventField.subText" style="margin-bottom: 15px;">
                <q-item-main>
                  <q-btn
                    color="info"
                    style="margin-bottom: 10px;"
                  >{{index + 1}}</q-btn>
                  <q-eventField>
                    <q-input v-model="eventField.subText" float-label="A mező neve"></q-input>
                    <q-input :after="[{icon: eventField.icon}]" v-model="eventField.icon" float-label="A mező ikonja"></q-input>
                    <q-input type="textarea" rows="3" v-model="eventField.text" float-label="A mező szöveges tartalma"></q-input>
                  </q-eventField>
                  <q-eventField style="margin-top: 3px;">
                    <q-btn-toggle
                      v-model="eventField.active"
                      toggle-color="primary"
                      :options="[
                            {label: 'Publikus', value: '1'},
                            {label: 'Privát', value: '0'}
                          ]"
                    />
                    <q-btn class="float-right" color="warning" icon="fa-trash-alt" @click="deleteField(index)"><q-tooltip>Mező törlése</q-tooltip></q-btn>
                  </q-eventField>
                </q-item-main>
              </q-item>
              <q-item>
                <q-btn color="info" icon-right="fa-plus" @click="addNewField()" label="Új mező hozzáadása" />
              </q-item>
            </q-card>

            <q-card v-if="draggableFields">
              <draggable v-model="event.fields">
                <q-item class="shadow-2 bg-grey-1" v-for="(eventField, index) in event.fields" :key="eventField.subText" style="margin-bottom: 15px;">
                  <q-item-main>
                    <q-btn
                      color="info"
                      style="margin-bottom: 10px;"
                    >{{index + 1}}</q-btn>
                    <q-eventField>
                      <q-item>
                        <q-item-side>
                          <q-item-tile color="primary" :icon="eventField.icon" />
                        </q-item-side>
                        <q-item-main>
                          <q-item-tile label>{{eventField.text}}</q-item-tile>
                          <q-item-tile sublabel>{{eventField.subText}}</q-item-tile>
                        </q-item-main>
                      </q-item>
                    </q-eventField>
                  </q-item-main>
                </q-item>
              </draggable>
            </q-card>
          </q-step>

          <q-step name="third" title="Előadók beállítása">
            <q-btn color="info" icon-right="fa-plus" @click="openUsersModal()" label="Új előadó hozzáadása" />
            <q-list-header>Kiválasztott előadók</q-list-header>
            <q-card v-for="(speaker, index) in event.speakers" :key="speaker.id" inline class="q-ma-sm shadow-10" style="width: 100%">
              <q-item>
                <q-item-side avatar="statics/guy-avatar.png" />
                <q-item-main>
                  <q-item-tile label>{{speaker.name}} {{speaker.lastName}}</q-item-tile>
                  <q-item-tile sublabel>{{speaker.username}}</q-item-tile>
                  <q-item-tile sublabel>{{speaker.id}}</q-item-tile>
                </q-item-main>
              </q-item>
              <q-list>
                <q-item>
                  <q-item-side>
                    <q-item-tile color="primary" icon="fa-sender-md" />
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

          <q-step name="fourth" title="Esemény beállítása">
            <q-card style="margin-bottom: 20px;">
              <q-item>
                <q-item-side>
                  <q-item-tile color="primary" icon="fa-eye" />
                </q-item-side>
                <q-item-main>
                  <q-eventField>
                    <p>Maga az esemény:</p>
                    <q-btn-toggle
                      v-model="event.settings.active"
                      toggle-color="primary"
                      :options="[
                            {label: 'Legyen látható', value: '1'},
                            {label: 'Ne legyen látható', value: '0'}
                          ]"
                    />
                  </q-eventField>
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
          <q-item class="shadow-1" v-for="sender in users" :key="sender.id">
            <q-item-side avatar="statics/guy-avatar.png" />
            <q-item-main>
              <q-item-tile label>{{sender.name}} {{sender.lastName}}</q-item-tile>
              <q-item-tile sublabel>{{sender.username}}</q-item-tile>
            </q-item-main>
            <q-item-side right>
              <q-btn @click="addUserToSpeakers(sender)" icon="fa-plus" color="primary"></q-btn>
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
            subText: 'Jegy ar',
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
        settings: {
          active: '1'
        }
      }
    }
  },
  methods: {
    createNewEvent () {
      let self = this
      AXIOS.post('/api/event/new', this.$data.event)
        .then(response => {
          self.$router.push('/esemeny/' + response.data)
          Notify.create({
            type: 'positive',
            color: 'positive',
            position: 'center',
            timeout: 3000,
            message: 'Az esemény sikeresen létre lett hozva. Át is irányítottunk az esemény oldalára.'
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
          message: 'Már kész van egy új mező, először szerkeszd azt, azután csinálhatsz többet.'
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
      AXIOS.get('/api/sender')
        .then(response => {
          self.$data.users = response.data
          self.$data.usersModal = true
        })
    },
    addUserToSpeakers (sender) {
      let searchedUser = this.$data.event.speakers[sender.id]
      if (searchedUser === undefined) {
        this.$data.event.speakers.push({
          id: sender.id,
          username: sender.username,
          name: sender.name,
          lastName: sender.lastName,
          profession: sender.profession,
          description: sender.description
        })
        this.$data.usersModal = false
      } else {
        Notify.create({
          type: 'warning',
          color: 'warning',
          position: 'bottom',
          timeout: 2000,
          message: 'Ez a felhasználó már hozzá van adva az előadokhóz, válassz másikat.'
        })
      }
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
