// function eventLoaderMenu() {
//     var previousEvent = $("#previous-event-btn");
//     var nextEvent = $("#next-event-btn");
//
//     previousEvent.click(function () {
//         var previousEventId = parseInt($(".event-id-text").val()) - 1;
//         changeEventDisplay(loadEventById(previousEventId));
//     });
//
//     nextEvent.click(function () {
//         var nextEventId = parseInt($(".event-id-text").val()) + 1;
//         changeEventDisplay(loadEventById(nextEventId));
//     });
//
//
//     function changeEventDisplay(event) {
//         debugger;
//         $(".event-id-text").val(event.id);
//         $(".event-title-text").text(event.title);
//         $(".event-name-text").text(event.name);
//         $(".event-address-text").text(event.address);
//         $(".event-date-text").text(event.date);
//         $(".event-description-text").text(event.description);
//         $(".event-price-text").text(event.price);
//         $(".event-locationByPublicTransport-text").text(event.locationByPublicTransport);
//         $(".event-locationByCar-text").text(event.locationByCar);
//         $(".event-address-text").text(event.address);
//     }
//
//
//     function loadEventById(id) {
//         var url = "/api/event/" + id;
//         $.ajax({
//             type : "GET",
//             url : window.location + "/api/event/" + id,
//             success: function(result){
//                 changeEventDisplay(result);
//             },
//             error : function(e) {
//                 console.log("ERROR: ", e);
//             }
//         });
//     }
// }

function authenticationButtons() {

    $('#login-form-link').click(function(e) {
        $("#login-form").delay(100).fadeIn(100);
        $("#register-form").fadeOut(100);
        $('#register-form-link').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
    });
    $('#register-form-link').click(function(e) {
        $("#register-form").delay(100).fadeIn(100);
        $("#login-form").fadeOut(100);
        $('#login-form-link').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
    });

}

function loadMenuBottomNav(eventId) {
    if(eventId > 0) {
        $("#prev-event-btn").attr("href", "/event/" + (eventId - 1));
    } else {
        $("#prev-event-btn").attr("href", "/event/0");
    }
    $("#next-event-btn").attr("href", "/event/" + (eventId + 1));
}

function eventMenu() {
    var eventInformationBtn = $("#event-information-btn");
    var eventSpeakersBtn = $("#event-speakers-btn");
    var eventTicketsBtn = $("#event-tickets-btn");
    var eventLocationBtn = $("#event-location-btn");
    var eventCommentsBtn = $("#event-comments-btn");

    var eventInformation = $("#event-information");
    var eventSpeakers = $("#event-speakers");
    var eventTickets = $("#event-tickets");
    var eventLocation = $("#event-location");
    var eventComments = $("#event-comments");

    var eventId = parseInt($(".event-id-text").val());

    loadMenuBottomNav(eventId);

    eventInformationBtn.click(function (e) {
        hideAllExcept("#event-information");
    });

    eventSpeakersBtn.click(function (e) {
        hideAllExcept("#event-speakers");
    });

    eventTicketsBtn.click(function (e) {
        hideAllExcept("#event-tickets");
    });

    eventLocationBtn.click(function (e) {
        hideAllExcept("#event-location");
    });

    eventCommentsBtn.click(function (e) {
        hideAllExcept("#event-comments");
    });

    function hideAllExcept(exception) {
        eventInformation.hide();
        eventSpeakers.hide();
        eventTickets.hide();
        eventLocation.hide();
        eventComments.hide();
        $(exception).show();
    }
}

$(function buttonClick() {

    $('#login-form-link').on( "click", function() {
        $("#login-form").delay(100).fadeIn(100);
        $("#register-form").fadeOut(100);
        $('#register-form-link').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
    });
    $('#register-form-link').on( "click", function() {
        $("#register-form").delay(100).fadeIn(100);
        $("#login-form").fadeOut(100);
        $('#login-form-link').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
    });

    $("#menu_toggle").click( function (e) {

    });

});



function main() {
    eventMenu();
    authenticationButtons();
    // loadGoogleMap();
}

$(document).ready(main);