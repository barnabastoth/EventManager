<template>
  <q-modal v-model="$store.state.showSendErrorModal" :content-css="{minWidth: '80vw', minHeight: '80vh'}">
    <q-modal-layout>
      <q-toolbar slot="header">
        <q-btn
          flat
          round
          dense
          v-close-overlay
          icon="keyboard_arrow_left"
        />
        <q-toolbar-title>
          Header
        </q-toolbar-title>
      </q-toolbar>

      <q-toolbar slot="header">
        <q-search inverted v-model="search" color="none" />
      </q-toolbar>

      <q-toolbar slot="footer">
        <q-toolbar-title>
          Footer
        </q-toolbar-title>
      </q-toolbar>

      <div class="layout-padding">
        <h1>Modal</h1>

        <q-btn
          color="primary"
          v-close-overlay
          label="Close"
        />

        <p>This is a Modal presenting a Layout.</p>
      </div>
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
