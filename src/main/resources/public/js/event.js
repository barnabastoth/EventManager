
function loadMenuTopNav() {
    $(document.body).on("click", "#prev-event-btn", function () {
        var eventId = parseInt($(".event-id-text").val());
        if(eventId > 0) {
            $("#main_content").load('/api/event/' + parseInt($(".event-id-text").val() -1), function () {
                history.replaceState(null, null, '/event/' + parseInt($(".event-id-text").val()));
            });
        } else {
            $("#main_content").load("/api/event/0", function () {
                history.replaceState(null, null, '/event/' + parseInt($(".event-id-text").val()));
            });
        }
    });

    $(document.body).on("click", "#next-event-btn", function () {
        var eventId = parseInt($(".event-id-text").val());
        $("#main_content").load('/api/event/' + parseInt($(".event-id-text").val() + 1), function () {
            history.replaceState(null, null, '/event/' + parseInt($(".event-id-text").val()));
        });
    });

    $(document.body).on('click', "#newEventButton", function() {
        $("#main_content").load("/api/event/new", function () {
            history.replaceState(null, null, '/event/New');
        });
    });
    $(document.body).on('click', "#editEventButton", function() {
        var eventId = $(".event-id-text").val();
        $("#main_content").load("/api/event/" + eventId + "/edit", function () {
            history.replaceState(null, null, '/event/' + $(".event-id-text").val() + '/edit');
        });
    });
    $(document.body).on('click', "#openEventButton", function() {
        history.replaceState(null, null, '/event/' + $(".event-id-text").val());
    });
}

function eventMenu() {

    $(document.body).on('click', "#info", function() {
        hideAllExcept("#event-information");
    });

    $(document.body).on('click', "#speaker",  function() {
        hideAllExcept("#event-speakers");
    });

    $(document.body).on('click', "#tickets",  function() {
        hideAllExcept("#event-tickets");
    });

    $(document.body).on('click', "#location", function() {
        hideAllExcept("#event-location");
    });

    $(document.body).on('click', "#comments",  function() {
        hideAllExcept("#event-comments");
    });

    $(document.body).on('click', "#eventCommentButton",  function() {
        var comment = $("#addComment").val();
        $.ajax({
            url: "/api/event/" + parseInt($(".event-id-text").val()) + "/comment/new",
            type: "POST",
            data: JSON.stringify(comment),
            dataType: "json",
            contentType: "application/json"
        })
    });

    $(document.body).on('click', "#eventComments",  function() {
        $("#main_content").load('/api/event/' + $(".event-id-text").val());
    });

    $(document.body).on('click', "#attendEvent",  function() {
        $.ajax({
            url: "/api/event/" + parseInt($(".event-id-text").val()) + "/attend",
            type: "POST",
            dataType: "json",
            contentType: "application/json"
        });
        $("#main_content").load('/api/event/' + $(".event-id-text").val());
    });

    function hideAllExcept(exception) {
        $("#event-information").hide();
        $("#event-speakers").hide();
        $("#event-tickets").hide();
        $("#event-location").hide();
        $("#event-comments").hide();
        $(exception).show();
    }

    $(document.body).on("click", ".speakerProfile", function (e) {
        e.preventDefault();
        $("#main_content").load('/api/profile/' + $(".speakerProfile").parent().attr('action'), function () {
            history.replaceState(null, null, "/profile/" + $(".speakerProfile").parent().attr('action'));
        });
    });

}

function main() {
    eventMenu();
    loadMenuTopNav();
}

$(document).ready(main);