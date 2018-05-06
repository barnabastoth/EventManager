<template>
  <transition
    appear
    enter-active-class="animated zoomIn"
    leave-active-class="animated zoomOut"
  >
    <q-page class="flex flex-center" padding style="width: 1000px; max-width: 90vw;">
      <q-card inline class="q-ma-sm shadow-24" style="width: 1000px; max-width: 90vw; padding: 24px;">
        <q-item style="margin-bottom: 30px" class="shadow-1 bg-grey-2">
          <q-item-side left>
            <q-item-tile style="font-size: 30px;" color="primary" icon="fa-calendar" />
          </q-item-side>
          <q-item-main>
            <q-item-tile style="font-size: 30px;" class="text-center" label>Új esemény létrehozása</q-item-tile>
          </q-item-main>
        </q-item>
        <q-card-main>
          <q-item class="shadow-2 bg-grey-1" v-for="(field, index) in event" :key="field.subText" style="margin-bottom: 15px;">
            <q-item-main>
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
        </q-card-main>
        <q-btn color="info" icon-right="fa-plus" @click="addNewField()" label="Új mező hozzáadása" />
        <q-btn color="primary" class="float-right" icon-right="fa-sign-in-alt" @click="createNewEvent()" label="Esemény létrehozása" />
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
      event: [
        {
          text: '',
          subText: 'Cím',
          active: '1',
          icon: 'fa-address-book'
        },
        {
          text: '',
          subText: 'Név',
          active: '1',
          icon: 'fa-address-book'
        },
        {
          text: '',
          subText: 'Helyszín',
          active: '1',
          icon: 'fa-map-marker'
        },
        {
          text: '',
          subText: 'Leírás',
          active: '1',
          icon: 'fa-clock'
        },
        {
          text: '',
          subText: 'Jegy ár',
          active: '1',
          icon: 'fa-ticket-alt'
        },
        {
          text: '',
          subText: 'Időtartalom',
          active: '1',
          icon: 'fa-clock'
        },
        {
          text: '',
          subText: 'Jegy link',
          active: '1',
          icon: 'fa-link'
        },
        {
          text: '',
          subText: 'Időpontja',
          active: '1',
          icon: 'fa-calendar'
        }
      ],
      active: '1'
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
      if (this.$data.event[this.$data.event.length - 1].subText === 'Új mező') {
        Notify.create({
          type: 'info',
          color: 'info',
          position: 'bottom',
          timeout: 3000,
          message: 'Már kész van egy új mező'
        })
      } else {
        this.$data.event.push({
          text: '',
          subText: 'Új mező',
          active: '1',
          icon: 'fa-file'
        })
      }
    },
    deleteField (index) {
      let name = this.$data.event[index].subText
      this.$data.event.splice(index, 1)
      Notify.create({
        type: 'info',
        color: 'info',
        position: 'bottom',
        timeout: 3000,
        message: 'Sikeresen törölted a következő mezőt: ' + name
      })
    }
  }
}
</script>
