webpackJsonp([6],{"+nwc":function(t,e){},0:function(t,e,n){t.exports=n("nplA")},"3Puz":function(t,e){},JsEP:function(t,e){},KSNH:function(t,e){},N12Q:function(t,e){},R58i:function(t,e){},nplA:function(t,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var o=n("Dd8w"),i=n.n(o),a=(n("30VO"),n("/5sW")),r=n("7109");a.a.use(r.C,{components:{QLayout:r.n,QLayoutHeader:r.p,QLayoutDrawer:r.o,QPageContainer:r.t,QPage:r.s,QToolbar:r.y,QToolbarTitle:r.z,QBtn:r.c,QIcon:r.g,QList:r.q,QListHeader:r.r,QItem:r.i,QItemMain:r.j,QItemSide:r.l,QItemSeparator:r.k,QItemTile:r.m,QCard:r.d,QCollapsible:r.e,QTabs:r.x,QTab:r.v,QTabPane:r.w,QRouteTab:r.u,QTooltip:r.A,QField:r.f,QInput:r.h},directives:{Ripple:r.B,CloseOverlay:r.a},plugins:{Notify:r.b}});n("L4dm"),n("hAPP"),n("sOI+"),n("59Iv"),n("trlb"),n("3Puz"),n("N12Q");var l=function(){var t=this.$createElement,e=this._self._c||t;return e("div",{attrs:{id:"q-app"}},[e("router-view")],1)},s=[];l._withStripped=!0;var c=n("XyMi"),u=!1;var p=function(t){u||n("tHqv")},f=Object(c.a)({name:"App",created:function(){this.$store.dispatch("logInUserWithToken"),this.$store.dispatch("loadSiteSettings")}},l,s,!1,p,null,null);f.options.__file="src\\App.vue";var m=f.exports,g=n("/ocq"),h=[{path:"/",component:function(){return n.e(1).then(n.bind(null,"lfHO"))},children:[{path:"",component:function(){return n.e(0).then(n.bind(null,"2NXm"))}},{path:"profile",component:function(){return n.e(4).then(n.bind(null,"GEt+"))}},{path:"bejelentkezés",component:function(){return n.e(2).then(n.bind(null,"r8n2"))}}]},{path:"*",component:function(){return n.e(3).then(n.bind(null,"c5Mg"))}}];a.a.use(g.a);var d,S=new g.a({mode:"history",base:"/",scrollBehavior:function(){return{y:0}},routes:h}),b=n("//Fk"),v=n.n(b),I=n("bOdI"),T=n.n(I),O=n("NYxO"),Q=n("mtWM"),k=n.n(Q);a.a.use(O.a);var _=new O.a.Store({state:{isLoggedIn:!!localStorage.getItem("token"),loggedInUser:[],siteSettings:[]},mutations:(d={},T()(d,"TOOGLE_LEFT_BAR",function(t){t.siteSettings.leftBarOpen=!t.siteSettings.leftBarOpen}),T()(d,"TOGGLE_RIGHT_BAR",function(t){t.siteSettings.rightBarOpen=!t.siteSettings.rightBarOpen}),T()(d,"LOGIN_SUCCESS",function(t,e){t.isLoggedIn=!0,t.loggedInUser=e,t.rightBarOpen=!0,t.leftBarOpen=!0}),T()(d,"LOGOUT",function(t){t.isLoggedIn=!1}),T()(d,"SET_SITE_SETTINGS",function(t,e){t.siteSettings=e}),d),actions:{toggleLeftBar:function(t){(0,t.commit)("TOOGLE_LEFT_BAR")},toggleRightBar:function(t){(0,t.commit)("TOGGLE_RIGHT_BAR")},login:function(t,e){var n=t.commit;return new v.a(function(t,o){k.a.post("http://localhost:8089/api/login",e).then(function(e){localStorage.setItem("Bearer ",e.data[0].token),n("LOGIN_SUCCESS",e.data[1]),r.b.create({type:"positive",color:"positive",position:"bottom",timeout:2e3,message:"Sikeresen bejelentkeztél, üdv újra köztünk!"}),t()}).catch(function(t){console.log(t),r.b.create({type:"info",color:"info",position:"bottom",timeout:3e3,message:"Sajnos a bejelentkezés sikertelen volt, ellenőrizd le újra a felhasználóneved és jelszavad."}),o(t)})})},logInUserWithToken:function(t){var e=t.commit,n=localStorage.getItem("Bearer ");if(null!==n){var o=this;k.a.post("http://localhost:8089/api/me",n).then(function(t){e("LOGIN_SUCCESS",t.data)}).catch(function(t){console.log(t),r.b.create({type:"info",color:"info",position:"bottom",timeout:2e3,message:"A Tokened lejárt, kérlek lépj be újra az oldalra."}),o.$router.push("/bejelentkezés"),localStorage.removeItem("Bearer ")})}},logout:function(t){var e=t.commit;localStorage.removeItem("Bearer "),e("LOGOUT"),r.b.create({type:"info",color:"info",position:"bottom",timeout:3e3,message:"Sikeresen kijelentkeztél. Remélem azért még találkozunk!"}),this.$router.push("/bejelentkezés")},loadSiteSettings:function(t){var e=t.commit;k.a.get("http://localhost:8089/api/siteSettings").then(function(t){e("SET_SITE_SETTINGS",t.data)})}}});a.a.config.productionTip=!1;var L=i()({el:"#q-app",router:S,store:_},m),B=[];B.push(function(t){t.Vue.prototype.$axios=k.a}),B.forEach(function(t){return t({app:L,router:S,store:_,Vue:a.a})}),new a.a(L)},tHqv:function(t,e){},w9uc:function(t,e){}},[0]);