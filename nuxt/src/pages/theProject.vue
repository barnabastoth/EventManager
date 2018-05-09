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
                <q-item-tile style="font-size: 30px;" color="primary" icon="fa-globe" />
              </q-item-side>
              <q-item-main>
                <q-item-tile style="font-size: 30px;" class="text-center" label>A Projekt</q-item-tile>
              </q-item-main>
            </q-item>
            <q-card-media>
              <img src="../statics/programming.png" class="responsive">
            </q-card-media>
            <q-list separator>
              <q-collapsible icon="fa-copyright" label="A Projekt céljai">
                <div>
                  <p>
                    Az elmúlt hónapokban sokat foglalkoztam Single Page Application fejlesztéssel saját Projekt miatt,
                    <br>
                    ezért úgy gondoltam, hogy belerakok egy-két extrát a projektbe, pusztán tanulás céljával.
                  </p>
                  <p>Ezek a következők:</p>
                  <ul>
                    <li>
                      Az alkalmazás fejlesztését teljesen szét szeparáltam frontend és backendre.
                      <br> Saját magam által írt specifikációk alapján fejlesztettem le a Frontendet anélkül, hogy a Backend meglett volna.
                      <br> Minimális hibával sikerült vakon kompatibilisre fejleszteni..
                    </li>
                    <li>Json Web Token alapú Authentikációt fejlesztettem az alkalmazásba.</li>
                    <li>User felvitel Regisztrációval lehetséges.</li>
                    <li>Userből lehet egyszerre egy vagy többet is törölni.</li>
                    <li>User törléshez bejelentkezés szükséges.</li>
                    <li>Index oldal</li>
                    <li>Vállalható design</li>
                  </ul>
                </div>
              </q-collapsible>
              <q-collapsible icon="fa-code" label="Az applikáció során felhasznált főbb technológiák">
                <div>
                  <q-list>
                    <q-list-header inset>Frontend</q-list-header>
                    <q-item>
                      <q-item-side avatar="statics/vue.png" />
                      <q-item-main label="Vue JS" />
                    </q-item>
                    <q-item>
                      <q-item-side avatar="statics/nuxt.png" />
                      <q-item-main label="Nuxt" />
                    </q-item>
                    <q-item>
                      <q-item-side avatar="statics/quasar.jpg" />
                      <q-item-main label="Quasar Framework" />
                    </q-item>
                    <q-item-separator inset />
                    <q-list-header inset>Backend</q-list-header>
                    <q-item>
                      <q-item-side avatar="statics/spring-boot.png" />
                      <q-item-main label="Spring Boot" />
                    </q-item>
                    <q-item>
                      <q-item-side avatar="statics/spring-security.png" />
                      <q-item-main label="Spring Security" />
                    </q-item>
                    <q-item>
                      <q-item-side avatar="statics/postgresql.png" />
                      <q-item-main label="PostgreSQL" />
                    </q-item>
                    <q-item>
                      <q-item-side avatar="statics/hibernate.jpg" />
                      <q-item-main label="Hibernate" />
                    </q-item>
                  </q-list>
                </div>
              </q-collapsible>

              <q-collapsible icon="fa-user-secret" label="Rólam">
                <div>
                  <img src="../statics/barnabas-toth.png" alt="Barnabás Tóth" class="responsive">
                </div>
              </q-collapsible>
            </q-list>
          </q-card>
        </q-page>
      </q-page-container>
    </q-layout>
  </transition>
</template>

<script>
export default {
  name: 'about'
}
</script>

<style scoped>

</style>
