<template>
  <q-modal v-model="$store.state.showSendErrorModal" :content-css="{minWidth: '100vw', minHeight: '80vh'}">
    <div style="padding: 20px; width: 100%; max-width: 90%; background-color: black;" class="absolute-center">
      <q-card color="primary" inline class="q-ma-sm full-width">
        <q-card-main class="text-center">
          Kérlek töltsd ki az összes mezőt a hibával kapcsolatban.
        </q-card-main>
      </q-card>
      <q-input float-label="Oldal címe ahol a hibát észlelte" inverted color="grey" type="email" :before="[{icon: 'fa-link', handler () {}}]"/>
      <q-input v-model="area" inverted color="grey" float-label="Hiba leírása" type="textarea" />
      <q-btn
        class="float-right"
        icon="fa-envelope"
        color="primary"
        label="Hiba elküldése a fejlesztőnek"
      />
      <q-btn
        class="float-right"
        icon="fa-times-circle"
        @click="$store.dispatch('toggleSendErrorModal')"
        color="primary"
        label="Vissza az oldalra"
      />
    </div>
  </q-modal>
</template>

<script>
export default {
  name: 'send-error'
}
</script>

<style scoped>

</style>
