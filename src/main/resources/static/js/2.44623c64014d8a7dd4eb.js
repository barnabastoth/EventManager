webpackJsonp([2],{VzDZ:function(n,e,r){var t=r("nhay");"string"==typeof t&&(t=[[n.i,t,""]]),t.locals&&(n.exports=t.locals);(0,r("rjj0").default)("2b22fdea",t,!1,{})},nhay:function(n,e,r){(n.exports=r("FZ+f")(!1)).push([n.i,"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n",""])},r8n2:function(n,e,r){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var t=r("mtWM"),o=r.n(t),s={data:function(){return{login:{username:"",password:"",errors:{}},register:{username:"",email:"",password:"",errors:{}}}},methods:{clearErrors:function(){this.$data.login.errors=["asd"],this.$data.register.errors=["asd"]},performLogin:function(){var n=this;this.$store.dispatch("login",{username:this.$data.login.username,password:this.$data.login.password}).then(function(){n.$router.push("/")}).catch(function(){n.$router.push("/bejelentkezés")})},performRegistration:function(){var n=this;o.a.post("http://localhost:8089/api/register",this.$data.register).then(function(e){n.login.username=n.register.username,n.login.password=n.register.password,n.login.errors={},n.performLogin()}).catch(function(e){n.register.errors=e.response.data})},toogleAuth:function(){document.querySelector(".cont").classList.toggle("s--signup")}}},a=function(){var n=this,e=n.$createElement,r=n._self._c||e;return r("q-layout",[r("q-page-container",[r("q-page",[r("div",{staticClass:"absolute-center",staticStyle:{width:"700px","max-width":"90vw"}},[r("div",{staticClass:"main-color shadow-1 row inline flex-center text-white",class:"bg-black"}),n._v(" "),r("q-input",{attrs:{"float-label":"Felhasználónév",inverted:"",color:"primary",type:"email",before:[{icon:"fa-address-card",handler:function(){}}]},model:{value:n.login.username,callback:function(e){n.$set(n.login,"username",e)},expression:"login.username"}}),n._v(" "),r("br"),n._v(" "),r("q-input",{attrs:{"float-label":"Jelszó",inverted:"",color:"primary",type:"password",before:[{icon:"fa-key"}],after:[{icon:"done",condition:n.login.password.length>=5,handler:function(){}}]},model:{value:n.login.password,callback:function(e){n.$set(n.login,"password",e)},expression:"login.password"}}),n._v(" "),r("br"),n._v(" "),r("q-btn",{attrs:{color:"primary","icon-right":"fa-sign-in-alt",label:"Bejelentkezés"},on:{click:function(e){n.performLogin()}}})],1)])],1)],1)},i=[];a._withStripped=!0;var l=r("XyMi"),c=!1;var u=function(n){c||r("VzDZ")},p=Object(l.a)(s,a,i,!1,u,null,null);p.options.__file="src\\pages\\auth.vue";e.default=p.exports}});