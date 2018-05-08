<template>
  <transition
    appear
    enter-active-class="animated zoomIn"
    leave-active-class="animated zoomOut"
  >
    <q-layout>
      <q-page-container>
        <q-page padding style="width: 1000px; max-width: 90vw;">
          <q-list inline class="q-ma-sm shadow-24">
            <q-item>
              <q-card-media overlay-position="top">
                <img src="statics/parallax2.jpg">
                <q-card-title slot="overlay">
                  <h5>{{event.name}}</h5>
                </q-card-title>
              </q-card-media>
            </q-item>
            <q-item style="margin-bottom: 30px" class="shadow-1 bg-grey-2">
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
                <q-item-tile label>{{event.name}}</q-item-tile>
                <q-item-tile sublabel>Esemény neve</q-item-tile>
              </q-item-main>
            </q-item>
            <q-item>
              <q-item-side>
                <q-item-tile color="primary" icon="fa-address-book" />
              </q-item-side>
              <q-item-main>
                <q-item-tile label>{{event.address}}</q-item-tile>
                <q-item-tile sublabel>Helyszín</q-item-tile>
              </q-item-main>
            </q-item>
            <q-item>
              <q-item-side>
                <q-item-tile color="primary" icon="fa-calendar" />
              </q-item-side>
              <q-item-main>
                <q-item-tile label>{{event.date}}</q-item-tile>
                <q-item-tile sublabel>Időpont</q-item-tile>
              </q-item-main>
            </q-item>
            <q-item v-for="field in event.fields" :key="field.subText">
              <q-item-side>
                <q-item-tile color="primary" :icon="field.icon" />
              </q-item-side>
              <q-item-main>
                <q-item-tile label>{{field.text}}</q-item-tile>
                <q-item-tile sublabel>{{field.subText}}</q-item-tile>
              </q-item-main>
            </q-item>
            <q-item>
              <q-item-side>
                <q-item-tile color="primary" icon="fa-address-book" />
              </q-item-side>
              <q-item-main>
                <q-item-tile label>{{event.description}}</q-item-tile>
                <q-item-tile sublabel>Leírás</q-item-tile>
              </q-item-main>
            </q-item>
            <q-item style="margin-bottom: 30px" class="shadow-1 bg-grey-2">
              <q-item-side left>
                <q-item-tile style="font-size: 30px;" color="primary" icon="fa-address-card" />
              </q-item-side>
              <q-item-main>
                <q-item-tile style="font-size: 30px;" class="text-center" label>Előadók</q-item-tile>
              </q-item-main>
            </q-item>
            <carousel-3d :inverse-scaling="1500" :space="800" :controls-visible="true">
              <slide :index="0">
                Slide 1 Content
              </slide>
              <slide :index="1">
                Slide 2 Content
              </slide>
              <slide :index="2">
                <figure>
                  <img src="../statics/parallax2.jpg">
                  <figcaption>
                    <q-card inline class="q-ma-sm">
                      <q-list>
                        <q-item>
                          <q-item-side>
                            <q-item-tile color="primary" icon="local bar" />
                          </q-item-side>
                          <q-item-main>
                            <q-item-tile label>Bar XYZ</q-item-tile>
                            <q-item-tile sublabel>Have a drink.</q-item-tile>
                          </q-item-main>
                        </q-item>
                        <q-item>
                          <q-item-side>
                            <q-item-tile color="red" icon="local gas station" />
                          </q-item-side>
                          <q-item-main>
                            <q-item-tile label>Gas Station</q-item-tile>
                            <q-item-tile sublabel>Fill your gas tank.</q-item-tile>
                          </q-item-main>
                        </q-item>
                        <q-item>
                          <q-item-side>
                            <q-item-tile color="amber" icon="local movies" />
                          </q-item-side>
                          <q-item-main>
                            <q-item-tile label>Cinema XYZ</q-item-tile>
                            <q-item-tile sublabel>Watch a movie.</q-item-tile>
                          </q-item-main>
                        </q-item>
                      </q-list>
                    </q-card>
                  </figcaption>
                </figure>
              </slide>
              <slide :index="3">
                Slide 3 Content
              </slide>
              <slide :index="4">
                Slide 4 Content
              </slide>
              <slide :index="5">
                Slide 5 Content
              </slide>
              <slide :index="6">
                Slide 6 Content
              </slide>
              <slide :index="7">
                Slide 7 Content
              </slide>
            </carousel-3d>
            <!--<q-list class="text-center"-->
                    <!--no-border-->
                    <!--link-->
                    <!--inset-delimiter-->
            <!--&gt;-->
              <!--<q-item @click.native="$router.push('/felhasznalo/' + user.id)" class="shadow-1" v-for="user in event.speakers" :key="user.id">-->
                <!--<q-item-side image="statics/guy-avatar.png"></q-item-side>-->
                <!--<q-item-main>-->
                  <!--<q-item>-->
                    <!--<q-item-side left style="width: 20%">-->
                      <!--<q-item-tile label>{{user.name}} {{user.lastName}}</q-item-tile>-->
                      <!--<q-item-tile sublabel>{{user.username}}</q-item-tile>-->
                      <!--<q-item-tile sublabel>{{user.roles[0].role}}</q-item-tile>-->
                    <!--</q-item-side>-->
                    <!--<q-item-main style="max-width: 50%">-->
                      <!--{{user.description}}-->
                    <!--</q-item-main>-->
                  <!--</q-item>-->
                <!--</q-item-main>-->
                <!--<q-item-side right>-->
                  <!--<q-item-tile icon="fa-edit"></q-item-tile>-->
                <!--</q-item-side>-->
              <!--</q-item>-->
            <!--</q-list>-->
          </q-list>
        </q-page>
      </q-page-container>
    </q-layout>
  </transition>
</template>

<script>
import AXIOS from 'axios'
export default {
  name: 'event',
  data: function () {
    return {
      event: [],
      showMenu: []
    }
  },
  props: ['id'],
  beforeMount () {
    let self = this
    AXIOS.get('/api/event/' + this.id)
      .then(response => {
        self.event = response.data
      })
  }
}
</script>

<style scoped>
.carousel-3d-container figure {
  margin:0;
}

.carousel-3d-container figcaption {
  position: absolute;
  background-color: rgba(0, 0, 0, 0.5);
  color: #fff;
  bottom: 0;
  position: absolute;
  bottom: 0;
  padding: 15px;
  font-size: 12px;
  min-width: 100%;
  box-sizing: border-box;
}
</style>
