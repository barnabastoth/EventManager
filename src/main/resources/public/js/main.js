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
        .css("background-repeat", "no-repeat");
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
function changeMenuDisplay(menu) {
    console.log("asd");
    $(".menu-content").empty();
    $(".menu-content").append("<h1>"  + menu.menu["title"] + "</h1>")
        .append("<p> Helyszin:  " + menu.menu["description"] + "</p>");
    $(".menu").css("visibility", "visible");
}


function loadMenuContentByID(ID) {
    var url = "/menu/" + ID;
    $.get(url).done(function (data) {
        try {
            var menu = JSON.parse(data);
            changeMenuDisplay(menu);
        } catch(e) {
            console.log(e);
            loadLatestEvent();
        }
    });
}

function loadMenu() {
    $(".home").click(function (e) {
        loadMenuContentByID(1);
    });

    $(".documents").click(function (e) {
        loadMenuContentByID(2);
    });

    $(".forum").click(function (e) {
        loadMenuContentByID(3);
    });

    $(".contact").click(function (e) {
        loadMenuContentByID(4);
    });

    $(".about").click(function (e) {
        loadMenuContentByID(5);
    });

    $("#menu-close").click(function (e) {
        $(".menu").css("visibility", "hidden");
    });

}

function main() {
    loadLatestEvent();
    loadMenu();


    $("#login-btn").click(function (e) {
        $("#login-form").css("visibility", "visible");
    });


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