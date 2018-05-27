<template>
  <transition
    appear
    enter-active-class="animated zoomIn"
    leave-active-class="animated zoomOut"
  >
    <q-layout>
      <q-page-container>
        <q-page padding style="width: 100%; max-width: 100vw;">
          <q-card class="shadow-24" style="padding: 24px;">
            <q-item class="shadow-2 bg-grey-3">
              <q-item-side left>
                <q-item-tile style="font-size: 30px;" color="primary" icon="fa-edit" />
              </q-item-side>
              <q-item-main>
                <q-item-tile style="font-size: 30px;" class="text-center" label>Szerkesztő</q-item-tile>
              </q-item-main>
            </q-item>
            <q-item class="shadow-2 bg-grey-1" style="margin-bottom: 15px;">
              <q-item-side icon="fa-book" color="primary"></q-item-side>
              <q-item-main>
                <q-editor
                  v-model="research.content"
                  :toolbar="[
                        ['bold', 'italic', 'strike', 'underline', 'subscript', 'superscript'],
                        ['token', 'hr', 'link', 'custom_btn'],
                        ['print', 'fullscreen'],
                        [
                          {
                            label: $q.i18n.editor.formatting,
                            icon: $q.icon.editor.formatting,
                            list: 'no-icons',
                            options: ['p', 'h1', 'h2', 'h3', 'h4', 'h5', 'h6', 'code']
                          },
                          {
                            label: $q.i18n.editor.fontSize,
                            icon: $q.icon.editor.fontSize,
                            fixedLabel: true,
                            fixedIcon: true,
                            list: 'no-icons',
                            options: ['size-1', 'size-2', 'size-3', 'size-4', 'size-5', 'size-6', 'size-7']
                          },
                          {
                            label: $q.i18n.editor.defaultFont,
                            icon: $q.icon.editor.font,
                            fixedIcon: true,
                            list: 'no-icons',
                            options: ['default_font', 'arial', 'arial_black', 'comic_sans', 'courier_new', 'impact', 'lucida_grande', 'times_new_roman', 'verdana']
                          },
                          'removeFormat'
                        ],
                        ['quote', 'unordered', 'ordered', 'outdent', 'indent'],
                        [
                          {
                            label: $q.i18n.editor.align,
                            icon: $q.icon.editor.align,
                            fixedLabel: true,
                            list: 'only-icons',
                            options: ['left', 'center', 'right', 'justify']
                          },
                          {
                            label: $q.i18n.editor.align,
                            icon: $q.icon.editor.align,
                            fixedLabel: true,
                            options: ['left', 'center', 'right', 'justify']
                          }
                        ],
                        ['undo', 'redo']
                      ]"
                  :fonts="{
                      arial: 'Arial',
                      arial_black: 'Arial Black',
                      comic_sans: 'Comic Sans MS',
                      courier_new: 'Courier New',
                      impact: 'Impact',
                      lucida_grande: 'Lucida Grande',
                      times_new_roman: 'Times New Roman',
                      verdana: 'Verdana'
                    }"
                />
                <q-btn color="primary" class="float-right" icon-right="fa-save" @click="createNewResearch()" label="Mentés és bezárás" />
              </q-item-main>
            </q-item>
          </q-card>
        </q-page>
      </q-page-container>
    </q-layout>
  </transition>
</template>

<script>
import { Notify } from 'quasar'
import AXIOS from 'axios'
export default {
  name: 'research-editor',
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
  },
  methods: {
    createNewResearch () {
      let self = this
      AXIOS.post('/api/research/edit', this.$data.research)
        .then(response => {
          self.$router.push('/tanulmany/' + response.data)
          Notify.create({
            type: 'positive',
            color: 'positive',
            position: 'center',
            timeout: 3000,
            message: 'A Tanulmány sikeresen mentésre került..'
          })
        })
        .catch(error => {
          console.log(error)
          Notify.create({
            type: 'warning',
            color: 'warning',
            position: 'bottom',
            timeout: 2000,
            message: 'Valami hiba történt a tanulmány frissítése közbe.'
          })
        })
    }
  }
}
</script>

<style scoped>

</style>
