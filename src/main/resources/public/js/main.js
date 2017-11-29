function changeEventDisplay(event) {
    $("#information").empty()
        .hide();

    $("#information").data("id", null)
        .data("id", event.event["ID"]);

    $("#information").append("<h1>"  + event.event["name"] + "</h1>")
        .append("<h3> Helyszin:  " + event.event["location"] + "</h3>")
        .append("<h3> Dátum:  " + event.event["date"] + "</h3>")
        .append("<h3> Időtartalom:  " + event.event["duration"] + "</h3>")
        .append("<h3> Jegy: <a href='" + event.event["ticketLink"] + "'>" + event.event["ticketLink"] + "</a></h3>")
        .append("<h4> Leirás:  " + event.event["description"] + "</h4>");
    // $("#information").append("<h3> Időtartalom:  " + event.event["speakers"] + "</h3>")

    $("body").css("background-image", "url(images/backgrounds/" + event.event["imgPath"] + ")")
        .css("background-size", "cover")
        .css("background-attachment", "fixed")
        .css("background-repeat", "no-repeat")
    $("#information").fadeIn();
}

function loadLatestEvent() {
    var url = "/latest-event";
    $.get(url).done(function (data) {
        var event = JSON.parse(data);
        changeEventDisplay(event);
    });
    return event;
}

function loadEventByID(ID) {
    var url = "/event/" + ID;
    $.get(url).done(function (data) {
        try {
            var event = JSON.parse(data);
            changeEventDisplay(event);
        } catch(e) {
            loadLatestEvent();
        }


    });
}

function loadMenu() {
    var isMenuOpen = false;
    $(".home").click(function (e) {
        if(isMenuOpen) {
            $(".menu-content").css("visibility", "hidden");
            isMenuOpen = false;
        } else {
            $(".menu-content").css("visibility", "visible");
            isMenuOpen = true;
        }
    });

    $(".documents").click(function (e) {
        if(isMenuOpen) {
            $(".menu-content").css("visibility", "hidden");
            isMenuOpen = false;
        } else {
            $(".menu-content").css("visibility", "visible");
            isMenuOpen = true;
        }
    });

    $(".forum").click(function (e) {
        if(isMenuOpen) {
            $(".menu-content").css("visibility", "hidden");
            isMenuOpen = false;
        } else {
            $(".menu-content").css("visibility", "visible");
            isMenuOpen = true;
        }
    });

    $(".contact").click(function (e) {
        if(isMenuOpen) {
            $(".menu-content").css("visibility", "hidden");
            isMenuOpen = false;
        } else {
            $(".menu-content").css("visibility", "visible");
            isMenuOpen = true;
        }
    });

    $(".about").click(function (e) {
        if(isMenuOpen) {
            $(".menu-content").css("visibility", "hidden");
            isMenuOpen = false;
        } else {
            $(".menu-content").css("visibility", "visible");
            isMenuOpen = true;
        }
    });

    $("#menu-close").click(function (e) {
       $(".menu-content").css("visibility", "hidden");
    });

}

function main() {
    loadLatestEvent();
    loadMenu();

    if (fullScreenApi.supportsFullScreen) {
        myButton.addEventListener('click', function() {
            fullScreenApi.requestFullScreen(someElement);
        }, true);
    }

    $("#arrow_left").click(function(e) {
        e.preventDefault();
        var prevEventID = $("#information").data("id");
        loadEventByID(prevEventID - 1);
    });

    $("#arrow_right").click(function(e) {
        e.preventDefault();
        var prevEventID = $("#information").data("id");
        loadEventByID(prevEventID + 1);

    });



}

$(document).ready(main());