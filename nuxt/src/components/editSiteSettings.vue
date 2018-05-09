<template>
  <q-list class="text-center"
          no-border
          inset-delimiter
  >
    <q-item>
      <q-item-side>
        <q-item-tile color="primary" icon="fa-eye" />
      </q-item-side>
      <q-item-main>
        <q-eventField>
          <p>Az oldal megnyitásakor a bal oldali menü</p>
          <q-btn-toggle
            v-model="siteSettings.leftBarOpen"
            toggle-color="primary"
            :options="[
                        {label: 'Legyen látható', value: true},
                        {label: 'Ne legyen látható', value: false}
                      ]"
          />
        </q-eventField>
      </q-item-main>
    </q-item>
    <q-item>
      <q-item-side>
        <q-item-tile color="primary" icon="fa-eye" />
      </q-item-side>
      <q-item-main>
        <p>Az oldal megnyitásakor a jobb oldali felhasználói panel</p>
        <q-btn-toggle
          v-model="siteSettings.rightBarOpen"
          toggle-color="primary"
          :options="[
                      {label: 'Legyen látható', value: true},
                      {label: 'Ne legyen látható', value: false}
                    ]"
        />
      </q-item-main>
    </q-item>
    <q-item>
      <q-item-side>
        <q-item-tile color="primary" icon="fa-eye" />
      </q-item-side>
      <q-item-main>
        <q-input v-model="siteSettings.contactPageText" type="textarea" rows="2" float-label="Az itt lévő szöveg fog megjelenni a kapcsolat oldalon"></q-input>
      </q-item-main>
    </q-item>
    <q-item style="margin-top: 10px"><q-btn color="primary" class="float-right" icon-right="fa-sign-in-alt" @click="saveSiteSettings()" label="Beállítások elmentése" /></q-item>
  </q-list>
</template>

<script>
import AXIOS from 'axios'
import { Notify } from 'quasar'
export default {
  name: 'edit-site-settings',
  data: function () {
    return {
      siteSettings: {}
    }
  },
  methods: {
    saveSiteSettings () {
      AXIOS.post('/api/siteSettings/edit', this.$data.siteSettings)
        .then(() => {
          Notify.create({
            type: 'positive',
            color: 'positive',
            position: 'bottom',
            timeout: 2000,
            message: 'Az oldal beállításai elmentődtek, mostantól minden új látogató ezeket fogja használni.!'
          })
        })
    }
  },
  beforeMount () {
    let self = this
    AXIOS.get('/api/siteSettings')
      .then(response => {
        self.$data.siteSettings = response.data
      })
  }
}
</script>

<style scoped>

</style>
