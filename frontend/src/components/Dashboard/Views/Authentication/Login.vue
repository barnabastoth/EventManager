<template>
  <div class="card">
    <div class="cont">
      <div @keyup.enter="performLogin()" class="form sign-in">
        <br><br>
        <h2>Jó újra látni!</h2>
        <br>
        <label>
          <span>Felhasználónév</span>
          <input v-model="login.username" type="email" />
        </label>
        <label>
          <span>Jelszó</span>
          <input v-model="login.password" type="password" />
        </label>
        <p class="forgot-pass">Elfelejtetted a jelszavad?</p>
        <button @click="performLogin()" type="button" class="submit">Bejelentkezés</button>
      </div>
      <div class="sub-cont">
        <div class="img">
          <div class="img__text m--up">
            <h2>Új vagy?</h2>
            <p>Regisztrálj, és csatlakozz nagyszerű közösségünkhöz.</p>
          </div>
          <div class="img__text m--in">
            <h2>Már közénk tartozol?</h2>
            <p>Ha van már felhasználód, csak jelentkezz be, már hiányoltunk!</p>
          </div>
          <div @click="toogleAuth()" class="img__btn">
            <span class="m--up">Regisztráció</span>
            <span class="m--in">Bejelentkezés</span>
          </div>
        </div>
        <div class="form sign-up">
          <br>
          <h2>Itt az idő, hogy otthon érezd magad,</h2>
          <br>
          <label>
            <span>Felhasználónév</span>
            <input v-model="register.username" type="text" />
          </label>
          <label>
            <span>Email</span>
            <input v-model="register.email" type="email" />
          </label>
          <label>
            <span>Jelszó</span>
            <input v-model="register.password" type="password" />
          </label>
          <button @click="performRegistration()" type="button" class="submit">Regisztráció</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import {AXIOS} from '../../../http-common'
  export default {
    data: function () {
      return {
        login: {
          username: '',
          password: ''
        },
        register: {
          username: '',
          email: '',
          password: ''
        },
        error: ''
      }
    },
    methods: {
      performLogin () {
        let self = this
        let creds = new URLSearchParams()
        creds.append('username', self.login.username)
        creds.append('password', self.login.password)
        this.$store.commit('LOGIN')
        AXIOS({
          method: 'post',
          url: 'http://localhost:8089/api/login',
          data: creds,
          config: { headers: { 'Content-type': 'application/x-www-form-urlencoded' } }
        }).then(function (response) {
          self.$store.dispatch('login', response).then(function () {
            self.$router.push('/fooldal')
            self.$notifications.notify(
              {
                message: 'Üdv újra köztünk! <br> Sikeresen bejelentkeztél!',
                icon: 'ti-heart',
                horizontalAlign: 'center',
                verticalAlign: 'top',
                type: 'success'
              })
          })
        }).catch(function (error) {
          console.log(error)
          if (error.response) {
            self.$notifications.notify(
              {
                message: 'Upsz, úgy nézki, hogy elütöttél valamit. <br> A bejelentkezés sikertelen volt :(',
                icon: 'ti-heart-broken',
                horizontalAlign: 'center',
                verticalAlign: 'top',
                type: 'danger'
              })
          }
        })
      },
      performRegistration () {
        let self = this
        let creds = new URLSearchParams()
        creds.append('username', self.register.username)
        creds.append('email', self.register.email)
        creds.append('password', self.register.password)
        AXIOS({
          method: 'post',
          url: 'http://localhost:8089/api/register',
          data: creds,
          config: { headers: { 'Content-type': 'application/x-www-form-urlencoded' } }
        }).then(function (response) {
          AXIOS({
            method: 'post',
            url: 'http://localhost:8089/api/login',
            data: creds,
            config: { headers: { 'Content-type': 'application/x-www-form-urlencoded' } }
          }).then(function (response2) {
            self.$store.dispatch('login', response2)
            self.$router.push('/')
            self.$notifications.notify(
              {
                message: 'Üdv közöttünk. <br> Sikeresen regisztráltál! <br> Automatikusan be is léptettünk a rendszerbe.',
                icon: 'ti-check',
                horizontalAlign: 'center',
                verticalAlign: 'top',
                type: 'success'
              })
          })
        }).catch(function (error) {
          console.log(error)
          if (error.response) {
            self.$notifications.notify(
              {
                message: 'Upsz, úgy nézki, hogy elütöttél valamit. <br> A regisztráció sikertelen volt :(',
                icon: 'ti-heart-broken',
                horizontalAlign: 'center',
                verticalAlign: 'top',
                type: 'danger'
              })
          }
        })
      },
      toogleAuth () {
        document.querySelector('.cont').classList.toggle('s--signup')
      }
    }
  }
</script>

<style scoped>
  .card {
    width: 900px;
    margin: 0 auto;
    top: 40px;
  }
  *, *:before, *:after {
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    margin: 0;
    padding: 0;
  }

  input, button {
    border: none;
    outline: none;
    background: none;
    font-family: 'Open Sans', Helvetica, Arial, sans-serif;
  }

  .tip {
    font-size: 20px;
    margin: 40px auto 50px;
    text-align: center;
  }

  .cont {
    overflow: hidden;
    position: relative;
    width: 900px;
    height: 550px;
    margin: 0 auto 100px;
    background: #fff;
  }

  .form {
    position: relative;
    width: 640px;
    height: 100%;
    -webkit-transition: -webkit-transform 1.2s ease-in-out;
    transition: -webkit-transform 1.2s ease-in-out;
    transition: transform 1.2s ease-in-out;
    transition: transform 1.2s ease-in-out, -webkit-transform 1.2s ease-in-out;
    padding: 50px 30px 0;
  }

  .sub-cont {
    overflow: hidden;
    position: absolute;
    left: 640px;
    top: 0;
    width: 900px;
    height: 100%;
    padding-left: 260px;
    background: #fff;
    -webkit-transition: -webkit-transform 1.2s ease-in-out;
    transition: -webkit-transform 1.2s ease-in-out;
    transition: transform 1.2s ease-in-out;
    transition: transform 1.2s ease-in-out, -webkit-transform 1.2s ease-in-out;
  }
  .cont.s--signup .sub-cont {
    -webkit-transform: translate3d(-640px, 0, 0);
    transform: translate3d(-640px, 0, 0);
  }

  button {
    display: block;
    margin: 0 auto;
    width: 260px;
    height: 36px;
    border-radius: 30px;
    color: #fff;
    font-size: 15px;
    cursor: pointer;
  }

  .img {
    overflow: hidden;
    z-index: 2;
    position: absolute;
    left: 0;
    top: 0;
    width: 260px;
    height: 100%;
    padding-top: 360px;
  }
  .img:before {
    content: '';
    position: absolute;
    right: 0;
    top: 0;
    width: 900px;
    height: 100%;
    background-image: url("https://s3-us-west-2.amazonaws.com/s.cdpn.io/142996/sections-3.jpg");
    background-size: cover;
    -webkit-transition: -webkit-transform 1.2s ease-in-out;
    transition: -webkit-transform 1.2s ease-in-out;
    transition: transform 1.2s ease-in-out;
    transition: transform 1.2s ease-in-out, -webkit-transform 1.2s ease-in-out;
  }
  .img:after {
    content: '';
    position: absolute;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    background: rgba(0, 0, 0, 0.6);
  }
  .cont.s--signup .img:before {
    -webkit-transform: translate3d(640px, 0, 0);
    transform: translate3d(640px, 0, 0);
  }
  .img__text {
    z-index: 2;
    position: absolute;
    left: 0;
    top: 50px;
    width: 100%;
    padding: 0 20px;
    text-align: center;
    color: #fff;
    -webkit-transition: -webkit-transform 1.2s ease-in-out;
    transition: -webkit-transform 1.2s ease-in-out;
    transition: transform 1.2s ease-in-out;
    transition: transform 1.2s ease-in-out, -webkit-transform 1.2s ease-in-out;
  }
  .img__text h2 {
    margin-bottom: 10px;
    font-weight: normal;
  }
  .img__text p {
    font-size: 14px;
    line-height: 1.5;
  }
  .cont.s--signup .img__text.m--up {
    -webkit-transform: translateX(520px);
    transform: translateX(520px);
  }
  .img__text.m--in {
    -webkit-transform: translateX(-520px);
    transform: translateX(-520px);
  }
  .cont.s--signup .img__text.m--in {
    -webkit-transform: translateX(0);
    transform: translateX(0);
  }
  .img__btn {
    overflow: hidden;
    z-index: 2;
    position: relative;
    width: 150px;
    height: 36px;
    margin: 0 auto;
    background: transparent;
    color: #fff;
    text-transform: uppercase;
    font-size: 15px;
    cursor: pointer;
  }
  .img__btn:after {
    content: '';
    z-index: 2;
    position: absolute;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    border: 2px solid #fff;
    border-radius: 30px;
  }
  .img__btn span {
    position: absolute;
    left: 0;
    top: 0;
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
    -webkit-box-pack: center;
    -ms-flex-pack: center;
    justify-content: center;
    -webkit-box-align: center;
    -ms-flex-align: center;
    align-items: center;
    width: 100%;
    height: 100%;
    -webkit-transition: -webkit-transform 1.2s;
    transition: -webkit-transform 1.2s;
    transition: transform 1.2s;
    transition: transform 1.2s, -webkit-transform 1.2s;
  }
  .img__btn span.m--in {
    -webkit-transform: translateY(-72px);
    transform: translateY(-72px);
  }
  .cont.s--signup .img__btn span.m--in {
    -webkit-transform: translateY(0);
    transform: translateY(0);
  }
  .cont.s--signup .img__btn span.m--up {
    -webkit-transform: translateY(72px);
    transform: translateY(72px);
  }

  h2 {
    width: 100%;
    font-size: 26px;
    text-align: center;
  }

  label {
    display: block;
    width: 260px;
    margin: 25px auto 0;
    text-align: center;
  }
  label span {
    font-size: 12px;
    color: #cfcfcf;
    text-transform: uppercase;
  }

  input {
    display: block;
    width: 100%;
    margin-top: 5px;
    padding-bottom: 5px;
    font-size: 16px;
    border-bottom: 1px solid rgba(0, 0, 0, 0.4);
    text-align: center;
  }

  .forgot-pass {
    margin-top: 15px;
    text-align: center;
    font-size: 12px;
    color: #cfcfcf;
  }

  .submit {
    margin-top: 40px;
    margin-bottom: 20px;
    background: #d4af7a;
    text-transform: uppercase;
  }

  .fb-btn {
    border: 2px solid #d3dae9;
    color: #8fa1c7;
  }
  .fb-btn span {
    font-weight: bold;
    color: #455a81;
  }

  .sign-in {
    -webkit-transition-timing-function: ease-out;
    transition-timing-function: ease-out;
  }
  .cont.s--signup .sign-in {
    -webkit-transition-timing-function: ease-in-out;
    transition-timing-function: ease-in-out;
    -webkit-transition-duration: 1.2s;
    transition-duration: 1.2s;
    -webkit-transform: translate3d(640px, 0, 0);
    transform: translate3d(640px, 0, 0);
  }

  .sign-up {
    -webkit-transform: translate3d(-900px, 0, 0);
    transform: translate3d(-900px, 0, 0);
  }
  .cont.s--signup .sign-up {
    -webkit-transform: translate3d(0, 0, 0);
    transform: translate3d(0, 0, 0);
  }

  .icon-link {
    position: absolute;
    left: 5px;
    bottom: 5px;
    width: 32px;
  }
  .icon-link img {
    width: 100%;
    vertical-align: top;
  }
  .icon-link--twitter {
    left: auto;
    right: 5px;
  }

</style>
