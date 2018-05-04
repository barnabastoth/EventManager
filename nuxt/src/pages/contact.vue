<template>
  <transition
    appear
    enter-active-class="animated zoomIn"
    leave-active-class="animated zoomOut"
  >
    <q-layout>
      <q-page-container>
        <q-page padding style="width: 700px; max-width: 90vw;">
          <q-card inline class="q-ma-sm shadow-24" style="padding: 24px;">
            <q-item style="margin-bottom: 30px" class="shadow-1 bg-grey-2">
              <q-item-side left>
                <q-item-tile style="font-size: 30px;" color="primary" icon="fa-address-card" />
              </q-item-side>
              <q-item-main>
                <q-item-tile style="font-size: 30px;" class="text-center" label>Kapcsolat</q-item-tile>
              </q-item-main>
            </q-item>
            <q-card-media>
              <img src="../statics/contact.jpg" class="responsive">
            </q-card-media>
            <q-list>
              <q-item>
                <q-item-side>
                  <q-item-tile color="red" icon="fa-envelope" />
                </q-item-side>
                <q-item-main>
                  <q-item-tile label>{{this.$store.state.loggedInUser.email}}</q-item-tile>
                  <q-item-tile sublabel>Email</q-item-tile>
                </q-item-main>
              </q-item>
              <q-item>
                <q-item-side>
                  <q-item-tile color="red" icon="fa-skype" />
                </q-item-side>
                <q-item-main>
                  <q-item-tile label>{{this.$store.state.loggedInUser.email}}</q-item-tile>
                  <q-item-tile sublabel>Skype</q-item-tile>
                </q-item-main>
              </q-item>
              <q-item>
                <q-item-side>
                  <q-item-tile color="red" icon="fa-discord" />
                </q-item-side>
                <q-item-main>
                  <q-item-tile label>{{this.$store.state.loggedInUser.email}}</q-item-tile>
                  <q-item-tile sublabel>Discord</q-item-tile>
                </q-item-main>
              </q-item>
            </q-list>
          </q-card>
        </q-page>
      </q-page-container>
    </q-layout>
  </transition>
</template>

<script>
export default {
  name: 'contact'
}
</script>

<style scoped>

</style>
