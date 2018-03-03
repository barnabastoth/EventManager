<template>
  <div>
    {{event}}
  </div>
</template>

<script>
  import {AXIOS} from '../../http-common'

  export default {
    props: ['id'],
    data () {
      return {
        event: []
      }
    },
    created () {
      let self = this
      AXIOS.get('http://localhost:8089/api/event/' + this.id)
        .then(function (response) {
          self.event = response.data
          console.log(response)
        })
    }
  }

</script>
