<template>
  <div class="card">
      <div id="login-box">
        <div class="logo">
          <img src="/images/lorempixel.jpg" class="img img-responsive img-circle center-block"/>
          <h1 class="logo-caption"><span class="tweak">B</span>ejelentkezés</h1>
        </div>
        <div class="controls">
          <p>username: {{credentials.username}}</p>
          <p>password: {{credentials.password}}</p>
          <p>{{error}}</p>
          <input v-model="credentials.username" type="text" name="email" placeholder="Email" class="form-control" autocomplete="new-password" />
          <input v-model="credentials.password" type="password" name="password" placeholder="Jelszó" class="form-control" autocomplete="new-password" />
          <button @click="performLogin()" class="btn btn-default btn-block btn-custom">Bejelentkezés</button>
          <button @click="anyad()">anyad</button>
        </div><!-- /.controls -->
      </div><!-- /#login-box -->
  </div>
</template>

<script>
  import {AXIOS} from '../../../http-common'
  export default {
    data: function () {
      return {
        credentials: {
          username: '',
          password: ''
        },
        error: ''
      }
    },
    methods: {
      performLogin () {
        let self = this
        let params = new URLSearchParams()
        params.append('username', self.credentials.username)
        params.append('password', self.credentials.password)
        this.$store.dispatch('login', params)
      },
      anyad () {
        let self = this
        AXIOS({
          method: 'get',
          url: 'http://localhost:8089/user',
          config: { headers: { 'Content-Type': 'application/x-www-form-urlencoded' } }
        }).then(function (response) {
          self.credentials.username = response.data
        })
      }
    }
  }
</script>

<style>
  @import url('https://fonts.googleapis.com/css?family=Nunito');
  @import url('https://fonts.googleapis.com/css?family=Poiret+One');

  #login-container {
    height: 100%;
    background-repeat: no-repeat;    /*background-image: linear-gradient(rgb(12, 97, 33),rgb(104, 145, 162));*/
    background:black;
    position: relative;
  }
  #login-box {
    left: 70%;
    transform: translateX(-50%);
    width: 500px;
    margin: 0 auto;
    border: 1px solid black;
    background: rgba(48, 46, 45, 1);
    min-height: 250px;
    padding: 20px;
    z-index: 9999;
  }
  #login-box .logo .logo-caption {
    font-family: 'Poiret One', cursive;
    color: white;
    text-align: center;
    margin-bottom: 0px;
  }
  #login-box .logo .tweak {
    color: #ff5252;
  }
  #login-box .controls {
    padding-top: 30px;
  }
  #login-box .controls input {
    border-radius: 0px;
    background: rgb(98, 96, 96);
    border: 0px;
    color: white;
    font-family: 'Nunito', sans-serif;
  }
  #login-box .controls input:focus {
    box-shadow: none;
  }
  #login-box .controls input:first-child {
    border-top-left-radius: 2px;
    border-top-right-radius: 2px;
  }
  #login-box .controls input:last-child {
    border-bottom-left-radius: 2px;
    border-bottom-right-radius: 2px;
  }
  #login-box button.btn-custom {
    border-radius: 2px;
    margin-top: 8px;
    background:#ff5252;
    border-color: rgba(48, 46, 45, 1);
    color: white;
    font-family: 'Nunito', sans-serif;
  }
  #login-box button.btn-custom:hover{
    -webkit-transition: all 500ms ease;
    -moz-transition: all 500ms ease;
    -ms-transition: all 500ms ease;
    -o-transition: all 500ms ease;
    transition: all 500ms ease;
    background: rgba(48, 46, 45, 1);
    border-color: #ff5252;
  }
  #particles-js{
    width: 100%;
    height: 100%;
    background-size: cover;
    background-position: 50% 50%;
    position: fixed;
    top: 0px;
    z-index:1;
  }
</style>
