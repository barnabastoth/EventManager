webpackJsonp([6],{"+nwc":function(e,t){},0:function(e,t,n){e.exports=n("nplA")},"3Puz":function(e,t){},JsEP:function(e,t){},KSNH:function(e,t){},N12Q:function(e,t){},R58i:function(e,t){},nplA:function(e,t,n){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var o=n("Dd8w"),i=n.n(o),a=(n("30VO"),n("/5sW")),r=n("7109");a.a.use(r.C,{components:{QLayout:r.n,QLayoutHeader:r.p,QLayoutDrawer:r.o,QPageContainer:r.t,QPage:r.s,QToolbar:r.y,QToolbarTitle:r.z,QBtn:r.c,QIcon:r.g,QList:r.q,QListHeader:r.r,QItem:r.i,QItemMain:r.j,QItemSide:r.l,QItemSeparator:r.k,QItemTile:r.m,QCard:r.d,QCollapsible:r.e,QTabs:r.x,QTab:r.v,QTabPane:r.w,QRouteTab:r.u,QTooltip:r.A,QField:r.f,QInput:r.h},directives:{Ripple:r.B,CloseOverlay:r.a},plugins:{Notify:r.b}});n("L4dm"),n("hAPP"),n("sOI+"),n("59Iv"),n("trlb"),n("3Puz"),n("N12Q");var s=n("mtWM"),l=n.n(s),c={name:"App",created:function(){l.a.defaults.baseURL="http://localhost:8089",this.$store.dispatch("logInUserWithToken"),this.$store.dispatch("loadSiteSettings")}},u=function(){var e=this.$createElement,t=this._self._c||e;return t("div",{attrs:{id:"q-app"}},[t("router-view")],1)},p=[];u._withStripped=!0;var f=n("XyMi"),m=!1;var g=function(e){m||n("tHqv")},d=Object(f.a)(c,u,p,!1,g,null,null);d.options.__file="src\\App.vue";var h=d.exports,S=n("/ocq"),b=[{path:"/",component:function(){return n.e(1).then(n.bind(null,"lfHO"))},children:[{path:"",component:function(){return n.e(0).then(n.bind(null,"2NXm"))}},{path:"profile",component:function(){return n.e(4).then(n.bind(null,"GEt+"))}},{path:"bejelentkezés",component:function(){return n.e(2).then(n.bind(null,"r8n2"))}}]},{path:"*",component:function(){return n.e(3).then(n.bind(null,"c5Mg"))}}];a.a.use(S.a);var v,I=new S.a({mode:"history",base:"/",scrollBehavior:function(){return{y:0}},routes:b}),T=n("//Fk"),O=n.n(T),Q=n("bOdI"),k=n.n(Q),L=n("NYxO");a.a.use(L.a);var _=new L.a.Store({state:{isLoggedIn:!!localStorage.getItem("token"),loggedInUser:[],siteSettings:[]},mutations:(v={},k()(v,"TOOGLE_LEFT_BAR",function(e){e.siteSettings.leftBarOpen=!e.siteSettings.leftBarOpen}),k()(v,"TOGGLE_RIGHT_BAR",function(e){e.siteSettings.rightBarOpen=!e.siteSettings.rightBarOpen}),k()(v,"LOGIN_SUCCESS",function(e,t){e.isLoggedIn=!0,e.loggedInUser=t,e.rightBarOpen=!0,e.leftBarOpen=!0}),k()(v,"LOGOUT",function(e){e.isLoggedIn=!1}),k()(v,"SET_SITE_SETTINGS",function(e,t){e.siteSettings=t}),v),actions:{toggleLeftBar:function(e){(0,e.commit)("TOOGLE_LEFT_BAR")},toggleRightBar:function(e){(0,e.commit)("TOGGLE_RIGHT_BAR")},login:function(e,t){var n=e.commit;return new O.a(function(e,o){l.a.post("/api/login",t).then(function(t){localStorage.setItem("Bearer ",t.data[0].token),n("LOGIN_SUCCESS",t.data[1]),r.b.create({type:"positive",color:"positive",position:"bottom",timeout:2e3,message:"Sikeresen bejelentkeztél, üdv újra köztünk!"}),e()}).catch(function(e){console.log(e),r.b.create({type:"info",color:"info",position:"bottom",timeout:3e3,message:"Sajnos a bejelentkezés sikertelen volt, ellenőrizd le újra a felhasználóneved és jelszavad."}),o(e)})})},logInUserWithToken:function(e){var t=e.commit,n=localStorage.getItem("Bearer ");if(null!==n){var o=this;l.a.post("/api/me",n).then(function(e){t("LOGIN_SUCCESS",e.data)}).catch(function(e){console.log(e),r.b.create({type:"info",color:"info",position:"bottom",timeout:2e3,message:"A Tokened lejárt, kérlek lépj be újra az oldalra."}),o.$router.push("/bejelentkezés"),localStorage.removeItem("Bearer ")})}},logout:function(e){var t=e.commit;localStorage.removeItem("Bearer "),t("LOGOUT"),r.b.create({type:"info",color:"info",position:"bottom",timeout:3e3,message:"Sikeresen kijelentkeztél. Remélem azért még találkozunk!"}),this.$router.push("/bejelentkezés")},loadSiteSettings:function(e){var t=e.commit;l.a.get("/api/siteSettings").then(function(e){t("SET_SITE_SETTINGS",e.data)})}}});a.a.config.productionTip=!1;var B=i()({el:"#q-app",router:I,store:_},h),E=[];E.push(function(e){e.Vue.prototype.$axios=l.a}),E.forEach(function(e){return e({app:B,router:I,store:_,Vue:a.a})}),new a.a(B)},tHqv:function(e,t){},w9uc:function(e,t){}},[0]);