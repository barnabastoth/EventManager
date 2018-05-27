<template>
  <transition
    appear
    enter-active-class="animated zoomIn"
    leave-active-class="animated zoomOut"
  >
    <q-layout>
      <q-page-container>
        <q-page padding style="width: 1000px; max-width: 90vw;">
          <q-item class="shadow-2 bg-grey-3">
            <q-item-side left>
              <q-item-tile style="font-size: 30px;" color="primary" icon="fa-globe" />
            </q-item-side>
            <q-item-main>
              <q-item-tile style="font-size: 30px;" class="text-center" label>{{research.name}}</q-item-tile>
            </q-item-main>
          </q-item>
          <q-card class="shadow-24" style="padding: 24px;">
            <q-item style="margin-bottom: 30px" class="shadow-2 bg-grey-2">
              <q-item-side left>
                <q-item-tile style="font-size: 30px;" color="primary" icon="fa-user-secret" />
              </q-item-side>
              <q-item-main>
                <q-item-tile style="font-size: 30px;" class="text-center" label>
                  <q-btn
                    @click="$router.push('/tanulmany/' + research.id + '/szerkeszto')"
                    style="min-width: 40%"
                    color="info"
                    icon-right="fa-edit"
                    push
                  >
                    Szerkesztő megnyitása</q-btn>
                  <q-btn
                    style="min-width: 40%"
                    color="positive"
                    icon-right="fa-clock"
                    push
                  >
                    Tanulmány beállitásai</q-btn>
                </q-item-tile>
              </q-item-main>
            </q-item>
            <q-item style="margin-bottom: 30px" class="bg-grey-2">
              <q-item-side left>
                <q-item-tile style="font-size: 30px;" color="primary" icon="fa-address-card" />
              </q-item-side>
              <q-item-main>
                <q-item-tile style="font-size: 30px;" class="text-center" label>Információk</q-item-tile>
              </q-item-main>
            </q-item>

            <q-item>
              <q-item-side>
                <q-item-tile color="primary" icon="fa-globe" />
              </q-item-side>
              <q-item-main>
                <q-item-tile label>{{research.name}}</q-item-tile>
                <q-item-tile sublabel>Tanulmány neve</q-item-tile>
              </q-item-main>
            </q-item>
            <q-item>
              <q-item-side>
                <q-item-tile color="primary" icon="fa-book" />
              </q-item-side>
              <q-item-main>
                <q-item-tile label>{{research.description}}</q-item-tile>
                <q-item-tile sublabel>Tanulmány bemutatása</q-item-tile>
              </q-item-main>
            </q-item>
            <q-item>
              <q-item-side>
                <q-item-tile color="primary" icon="fa-clock" />
              </q-item-side>
              <q-item-main>
                <q-item-tile label>{{research.date}}</q-item-tile>
                <q-item-tile sublabel>Publikálva az oldalon</q-item-tile>
              </q-item-main>
            </q-item>
            <q-item>
              <q-item-side>
                <q-item-tile color="primary" icon="fa-bookmark" />
              </q-item-side>
              <q-item-main>
                <q-item-tile label>Kategóriák</q-item-tile>
                <q-item-tile sublabel v-for="category in research.categories" :key="category.id">
                  <q-btn @click="$router.push('/kategoria/' + category.name)" flat push :icon-right="category.icon">{{category.name}}</q-btn>
                </q-item-tile>
              </q-item-main>
            </q-item>

            <q-item style="margin-bottom: 30px; margin-top: 30px;" class="bg-grey-2">
              <q-item-side left>
                <q-item-tile style="font-size: 30px;" color="primary" icon="fa-book" />
              </q-item-side>
              <q-item-main>
                <q-item-tile style="font-size: 30px;" class="text-center" label>Tanulmány</q-item-tile>
              </q-item-main>
            </q-item>

            <q-item class="shadow-1">
              <q-item-main>
                <q-item-tile v-html="research.content"></q-item-tile>
              </q-item-main>
            </q-item>
          </q-card>
        </q-page>
      </q-page-container>
    </q-layout>
  </transition>
</template>

<script>
// import { Notify } from 'quasar'
import AXIOS from 'axios'
export default {
  name: 'research',
  data: function () {
    return {
      research: {}
    }
  },
  props: ['id'],
  beforeMount () {
    let self = this
    AXIOS.get('/api/research/' + self.id)
      .then(response => {
        self.$data.research = response.data
      })
  }
}
</script>

<style scoped>

</style>
