<template>
  <transition
    appear
    enter-active-class="animated zoomIn"
    leave-active-class="animated zoomOut"
  >
    <q-layout>
      <q-page-container>
        <q-page padding style="width: 1000px; max-width: 90vw;">
          <q-card inline class="q-ma-sm shadow-24" style="padding: 24px;width: 1000px; max-width: 90vw;">
            <q-item style="margin-bottom: 30px" class="shadow-1 bg-grey-2">
              <q-item-side left>
                <q-item-tile style="font-size: 30px;" color="primary" icon="fa-user-secret" />
              </q-item-side>
              <q-item-main>
                <q-item-tile style="font-size: 30px;" class="text-center" label>Admin Panel</q-item-tile>
              </q-item-main>
            </q-item>
            <q-tabs color="primary" class="shadow-1" glossy>
              <q-tab default name="Események" slot="title" icon="fa-calendar" label="Események" />
              <q-tab name="Felhasználók" slot="title" icon="fa-user" label="Felhasználók" />
              <q-tab name="Oldalak" slot="title" icon="fa-link" label="Oldalak" />
              <q-tab name="Oldal üzenetek" slot="title" icon="fa-envelope" label="Oldal üzenetek" />
              <q-tab name="Beállítások" slot="title" icon="fa-cog" label="Beállítások" />

              <q-tab-pane name="Események">
                <eventList></eventList>
              </q-tab-pane>

              <q-tab-pane name="Felhasználók">
                <userList></userList>
              </q-tab-pane>

              <q-tab-pane name="Oldalak">
                Oldalak
              </q-tab-pane>

              <q-tab-pane name="Oldal üzenetek">
                <contactMessage></contactMessage>
              </q-tab-pane>

              <q-tab-pane name="Beállítások">
                <editSiteSettings></editSiteSettings>
              </q-tab-pane>

            </q-tabs>
          </q-card>
        </q-page>
      </q-page-container>
    </q-layout>
  </transition>
</template>

<script>
import editSiteSettings from '../components/adminPanel/editSiteSettings'
import contactMessage from '../components/adminPanel/contactMessage'
import userList from '../components/adminPanel/userList'
import eventList from '../components/adminPanel/eventList'
export default {
  name: 'adminPanel',
  components: {
    editSiteSettings,
    contactMessage,
    userList,
    eventList
  }
}
</script>

<style scoped>

</style>
