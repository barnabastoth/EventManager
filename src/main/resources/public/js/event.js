
function loadMenuTopNav() {
    $(document.body).on("click", "#prev-event-btn", function () {
        var eventId = parseInt($(".event-id-text").val());
        if(eventId > 0) {
            $("#main_content").load('/api/event/' + parseInt($(".event-id-text").val() -1), function () {
                pushState('/event/' + $(".event-id-text").val());
            });
        } else {
            $("#main_content").load("/api/event/0", function () {
                pushState('/event/' + $(".event-id-text").val());
            });
        }
    });

    $(document.body).on("click", "#next-event-btn", function () {
        var eventId = parseInt($(".event-id-text").val());
        $("#main_content").load('/api/event/' + parseInt($(".event-id-text").val() + 1), function () {
            pushState('/event/' + $(".event-id-text").val());
        });
    });

    $(document.body).on('click', "#newEventButton", function() {
        $("#main_content").load("/api/event/new", function () {
            pushState("/event/new");
        });
    });
    $(document.body).on('click', "#editEventButton", function() {
        var eventId = $(".event-id-text").val();
        $("#main_content").load("/api/event/" + eventId + "/edit", function () {
            pushState('/event/' + $(".event-id-text").val() + '/edit');
        });
    });
    $(document.body).on('click', "#openEventButton", function() {
        pushState('/event/' + $(".event-id-text").val());
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

    $(document.body).on('click', "#sendEventComment",  function() {
        var comment = $("#addComment").val();
        $.ajax({
            url: "/api/event/" + parseInt($(".event-id-text").val()) + "/comment/new",
            type: "POST",
            data: JSON.stringify(comment),
            dataType: "json",
            contentType: "application/json"
        }).done(function () {
            $("#main_content").load('/api/event/' + $(".event-id-text").val());
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
            pushState("/profile/" + $(".speakerProfile").parent().attr('action'));
        });
    });

    $(function() {
        $('.material-card > .mc-btn-action').on('click', function () {
            var card = $(this).parent('.material-card');
            var icon = $(this).children('i');
            icon.addClass('fa-spin-fast');

            if (card.hasClass('mc-active')) {
                card.removeClass('mc-active');

                window.setTimeout(function() {
                    icon
                        .removeClass('fa-arrow-left')
                        .removeClass('fa-spin-fast')
                        .addClass('fa-bars');

                }, 800);
            } else {
                card.addClass('mc-active');

                window.setTimeout(function() {
                    icon
                        .removeClass('fa-bars')
                        .removeClass('fa-spin-fast')
                        .addClass('fa-arrow-left');

                }, 800);
            }
        });
    });

}

function allEventsPage() {
    $(document.body).on("click", '#viewEventButton', function() {
        var id = $(this).parent().attr('action');
        $("#main_content").load("/api/event/" + id, function () {
            pushState("/event/" + id);
        });
    });
    $(document.body).on("click", '#editEventButton', function() {
        var id = $(this).parent().attr('action');
        $("#main_content").load("/api/event/" + id + "/edit", function () {
            pushState("/event/" + id + "/edit");
        });
    });
    $(document.body).on("click", '#activateEventButton', function() {
        var id = $(this).parent().attr('action');
        $("#main_content").load("/api/event/" + id + "/activate", function () {
            pushState("/event");
        });
    });
    $(document.body).on("click", '#deactivateEventButton', function() {
        var id = $(this).parent().attr('action');
        $("#main_content").load("/api/event/" + id + "/deactivate", function () {
            pushState("/event");
        });
    });
}

function main() {
    eventMenu();
    loadMenuTopNav();
    allEventsPage();
}

$(document).ready(main);