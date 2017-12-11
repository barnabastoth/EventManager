function main() {
    debugger;
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




$(document).ready(main());