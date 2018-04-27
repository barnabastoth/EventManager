<template>
  <q-modal position="right" v-model="$store.state.showSendErrorModal" :content-css="{minWidth: '20vw', minHeight: '40vh', maxHeighth: '80vw'}">
    <q-modal-layout>
      <q-toolbar slot="header">
        <q-btn
          flat
          round
          dense
          v-close-overlay
          icon="fa-bug"
        />
        <q-toolbar-title>
          Hiba bejelentése
        </q-toolbar-title>
        <q-btn
          class="float-right"
          icon="fa-times-circle"
          @click="$store.dispatch('toggleSendErrorModal')"
          color="negative"
        />
      </q-toolbar>

      <q-toolbar slot="footer">
        <q-toolbar-title class="text-right">
          <q-btn
            class="float-right"
            icon="fa-envelope"
            color="warning"
            label="Hiba elküldése a fejlesztőnek"
          />
        </q-toolbar-title>
      </q-toolbar>
      <q-toolbar class="q-ma-sm full-width">
        <div class="row">
          <div>
            <q-card color="primary" inline class="q-ma-sm full-width">
              <q-card-main class="text-center">
                Kérlek töltsd ki az összes mezőt a hibával kapcsolatban.
              </q-card-main>
            </q-card>
          </div>

          <div class="q-ma-sm full-width">
            <q-input float-label="Oldal címe ahol a hibát észlelte" inverted color="grey" type="email" :before="[{icon: 'fa-link', handler () {}}]"/>
          </div>
          <div class="q-ma-sm full-width">
            <q-input v-model="area" inverted color="grey" float-label="Hiba leírása" type="textarea" />
          </div>
        </div>
      </q-toolbar>

    </q-modal-layout>
  </q-modal>
</template>

<script>
export default {
  name: 'send-error'
}
</script>

<style scoped>

</style>
