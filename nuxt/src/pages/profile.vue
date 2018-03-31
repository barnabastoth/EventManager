<template>
 <div>
   <q-page class="flex flex-center">
     asd
     <img alt="Quasar logo" src="~assets/quasar-logo-full.svg">
   </q-page>
 </div>
</template>

<script>
  // ==UserScript==
  // @name     Boostroyal Script
  // @version  1
  // @grant    none
  // ==/UserScript==

  // ==UserScript==
  // @name     br script
  // @version  2
  // @grant    none
  // ==/UserScript==
  function addJQuery(callback) {
    var script = document.createElement("script");
    script.setAttribute("src", "//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js");
    script.addEventListener('load', function() {
      var script = document.createElement("script");
      script.textContent = "window.jQ=jQuery.noConflict(true);(" + callback.toString() + ")();";
      document.body.appendChild(script);
    }, false);
    document.body.appendChild(script);
  }

  // the guts of this userscript
  function main() {
    // Note, jQ replaces $ to avoid conflicts.
    alert("There are " + jQ('a').length + " links on this page.");
  }

  // load jQuery and execute the main function
  addJQuery(main);

  alert("ASD");
  function main() {
    alert("I am running you fuck");
    var foundorder = parseorders();
    if(foundorder === false) {
      var boostwatcher = setInterval(function(){
        if($(".toast-message").text().length > 0) {
          location.hash = "reload";
          location.reload();
        } else {
          console.log("Sajnos meg nem jott be boost, probalkozok tovabb!")
        }
      }, 1000);
    }
    function parseorders() {
      if(foundorder === true) {
        window.clearinterval(boostwatcher);
      }

      let orders = $(".datatable-body").children().children()[1];
      for (let i = 0; i < 2; i  ) {
        let order = $(orders).children()[i];
        let orderid = $(order).children().children().children().children()[0];
        let orderpurchase = $(order).children().children().children().children()[1];
        let orderdetails = $(order).children().children().children().children()[2];
        let orderserver = $(order).children().children().children().children()[3];
        let orderbutton = $(order).children().children().children().children()[7];

        let orderidtext = $(orderid).text();
        let orderpurchasetext = $(orderpurchase).text();
        let orderdetailstext = $(orderdetails).text();
        let orderservertext = $(orderserver).text();

        let decision = evaluateorder(orderidtext, orderpurchasetext, orderdetailstext, orderservertext);
        if(decision === true) {
          $(orderbutton).children()[1].click();
          alert("Felvettem neked egy ordert, te dagadt fasz");
          foundorder = true;
          return true;
        } else {
          console.log("A boost ami bejott nem felelt meg a felteteleknek amit beallitottal.");
          return false;
        }
      }
    }

    function evaluateorder(orderidtext, orderpurchasetext, orderdetailstext, orderservertext) {
      console.log(orderpurchasetext);
      let orderdecision = true;
      let servers = ["EUW", "EUNE", "TR", "RUS"];
      let leaguestoaccept = ["Platinum", "Gold", "Tier"];
      let leaguestodecline = ["Bronze", "Diamond", "Master", "Challenger", "Silver"];
      let getduoqueue = false;
      let getonlytierboost = false;

      if(orderdecision === true) {
        for (let i = 0; i < servers.length; i++) {
          if(orderservertext.toUpperCase().includes(servers[i].toUpperCase())) {
            orderdecision = true;
            break;
          } else {
            orderdecision = false;
          }
        }
      }


      if(orderdecision === true) {
        for (let j = 0; j < leaguestoaccept.length; j++) {
          if(orderpurchasetext.toUpperCase().includes(leaguestoaccept[j].toUpperCase())) {
            orderdecision = true;
            break;
          } else {
            orderdecision = false;
          }
        }
      }


      if(orderdecision === true) {
        if(getduoqueue === true) {
          if(orderpurchasetext.toUpperCase().includes("DUOQUEUE")) {
            orderdecision = true;
          } else {
            orderdecision = false;
          }
        }
      }
    }

    if(orderdecision === true) {
      for (let j = 0; j < leaguestodecline.length; j++) {
        if(orderpurchasetext.includes(leaguestodecline[j])) {
          orderdecision = false;
          break;
        }
      }
      if(getonlytierboost === true) {
        orderdecision = orderpurchasetext.includes("Tier");
      }
      console.log("DWSADWQDQW" + orderdecision);
      return orderdecision;
    }

  }
  $(document).ready(main());
</script>
