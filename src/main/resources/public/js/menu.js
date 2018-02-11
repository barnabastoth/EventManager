function main() {
    $(document.body).on("click", "#homeButton", function() {
        $("#main_content").load("/api/event/latest", function () {
            history.replaceState(null, null, '/');
        });
    });
    $(document.body).on("click", "#aboutButton", function() {
        $("#main_content").load("/api/about", function () {
            history.replaceState(null, null, 'about');
        });
    });
    $(document.body).on("click", "#contactButton", function() {
        $("#main_content").load("/api/contact", function () {
            history.replaceState(null, null, 'contact');
        });
    });
    $(document.body).on("click", "#eventButton", function() {
        alert(window.location.href.toString().split(window.location.host)[1]);
        $("#main_content").load("/api/event/", function () {
            history.replaceState(null, null, 'event');
        });
    });

    $(document.body).on("click", "#allUsersButton", function() {
        $("#main_content").load("/api/profile", function () {
            history.pushState(null, null, 'profile/');
        });
    });
    $(document.body).on('click', ".profileButton", function() {
        $("#main_content").load("/api/profile/" + $("#loggedInUserId").val(), function () {
            history.replaceState(null, null, "/profile/" + $("#loggedInUserId").val());
        });
    });

}


$(document).ready(main());