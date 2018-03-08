<template>
  <div class="card">
    <div class="header">
      <slot name="header">
        <h4 class="title">{{data.title}}</h4>
      </slot>
    </div>
    <div class="content table-responsive table-full-width">
      <p style="text-align: center; padding: 50px" class="category">{{data.description}}</p>
      <br><br>
    </div>
  </div>
</template>

<script>
  import {AXIOS} from '../../http-common'
  export default {
    data () {
      return {
        data: {}
      }
    },
    created () {
      let self = this
      AXIOS.get('http://localhost:8089/api/menu/about').then(function (response) {
        self.data = response.data
        console.log(response.data)
      })
    }
  }
</script>
