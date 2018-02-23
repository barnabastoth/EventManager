function main() {

    $(document.body).on("click", "#homeButton", function() {
        $("#main_content").load("/api/event/latest", function () {
            pushState('home');
        });
    });
    $(document.body).on("click", "#aboutButton", function() {
        $("#main_content").load("/api/about", function () {
            pushState('about');
        });
    });
    $(document.body).on("click", "#contactButton", function() {
        $("#main_content").load("/api/contact", function () {
            pushState('contact');
        });
    });
    $(document.body).on("click", "#eventButton", function() {
        $("#main_content").load("/api/event/", function () {
            pushState('event');
        });
    });

    $(document.body).on("click", "#allUsersButton", function() {
        $("#main_content").load("/api/profile", function () {
            pushState('profile');
        });
    });
    $(document.body).on('click', ".profileButton", function() {
        $("#main_content").load("/api/profile/" + $("#loggedInUserId").val(), function () {
            pushState("/profile/" + $("#loggedInUserId").val());
        });
    });
}

function pushState(url) {
    if(window.location.href.toString().split(window.location.host)[1] !== url) {
        history.pushState({}, document.title, url);
    }
}


$(document).ready(main());