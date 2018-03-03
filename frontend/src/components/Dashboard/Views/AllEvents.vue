<template>
    <div class="row">
      <div class="col-md-12">
        <div class="card">
          <TimeLine :title="events.title" :data="events.data">

          </TimeLine>
        </div>
      </div>

    </div>
</template>
<script>
  import TimeLine from '../../UIComponents/TimeLine'
  import {AXIOS} from '../../http-common'

  export default {
    components: {
      TimeLine
    },
    data () {
      return {
        events: {
          title: 'Stripped Table',
          data: []
        }
      }
    },
    created () {
      let self = this
      AXIOS.get('http://localhost:8089/api/event/')
        .then(function (response) {
          self.events.data = response.data
          console.log(response)
        })
    }
  }

</script>
<style>

</style>
