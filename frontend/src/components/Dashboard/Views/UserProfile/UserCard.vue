<template>
  <div class="card card-user">
    <div class="image">
      <img src="static/img/background.jpg" alt="...">
    </div>
    <div class="content">
      <div class="author">
        <img class="avatar border-white" :src="'http://localhost:8089/api/user/profilepic/' + user.id" alt="...">
        <h4 class="title">{{user.lastName}} {{user.name}}
          <br>
          <a href="#">
            <small>@{{user.username}}</small>
          </a>
        </h4>
      </div>
      <p class="description text-center">
        {{user.description}}
      </p>
    </div>
    <hr>
    <div class="text-center">
      <div class="row">
        <div class="col-md-5 col-md-offset-1">
          <h5>Foglalkozás
            <br>
            <small>{{user.profession}}</small>
          </h5>
        </div>
        <div class="col-md-5 col-md-offset">
          <h5>Weboldal
            <br>
            <small>{{user.website}}</small>
          </h5>
        </div>
      </div>
      <div class="row">
        <div class="col-md-5 col-md-offset-1">
          <h5>Csatlakozott
            <br>
            <small>{{user.memberSince.year}}.{{user.memberSince.monthValue}}.{{user.memberSince.dayOfMonth}}</small>
          </h5>
        </div>
        <div class="col-md-5 col-md-offset">
          <h5>Kommentek
            <br>
            <small>{{user.comments.length}}</small>
          </h5>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
  export default {
    props: {
      user: {}
    }
  }

</script>
<style>

</style>
