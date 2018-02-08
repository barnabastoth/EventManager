function main() {
    if($("#loginRequired").val() === "login") {
        $("#login").modal('show');
        history.replaceState(null, null, 'login');
    } else if ($("#loginRequired").val() === "registration") {
        $("#register").modal('show');
        history.replaceState(null, null, 'registration');
    }

    $(document.body).on('click', ".profileButton", function() {
        $("#main_content").load("/api/profile/" + $("#loggedInUserId").val(), function () {
            history.replaceState(null, null, "/profile/" + $("#loggedInUserId").val());
        });
    });

    $(document.body).on("click", '#editProfileButton', function() {
        $(".x_panel").load("/api/profile/" + $("#profileId").val() + "/edit", function () {
            history.replaceState(null, null, "/profile/" + $("#loggedInUserId").val() + "/edit");
        });
    });

    $(document.body).on( "click", '#login-form-link', function() {
        $("#login-form").delay(100).fadeIn(100);
        $("#register-form").fadeOut(100);
        $('#register-form-link').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
        history.replaceState(null, null, 'login');
    });
    $(document.body).on( "click", '#register-form-link', function() {
        $("#register-form").delay(100).fadeIn(100);
        $("#login-form").fadeOut(100);
        $('#login-form-link').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
        history.replaceState(null, null, 'registration');
    });


    $(document.body).on("click", '#viewUserButton', function() {
        var id = $(this).parent().attr('action');
        $("#main_content").load("/api/profile/" + id, function () {
            history.replaceState(null, null, "/profile/" + id);
        });
    });
    $(document.body).on("click", '#editUserButton', function() {
        var id = $(this).parent().attr('action');
        $(".x_panel").load("/api/profile/" + id + "/edit", function () {
            history.replaceState(null, null, "/profile/" + id + "/edit");
        });
    });
    $(document.body).on("click", '#activateUserButton', function() {
        var id = $(this).parent().attr('action');
        $("#main_content").load("/api/profile/" + id + "/activate", function () {
            history.replaceState(null, null, 'profile/');
        });
    });
    $(document.body).on("click", '#addAdminButton', function() {
        var id = $(this).parent().attr('action');
        $("#main_content").load("/api/profile/" + id + "/admin/add", function () {
            history.replaceState(null, null, 'profile/');
        });
    });
    $(document.body).on("click", '#removeAdminButton', function() {
        var id = $(this).parent().attr('action');
        $("#main_content").load("/api/profile/" + id + "/admin/remove", function () {
            history.replaceState(null, null, 'profile/');
        });
    });


}



$(document).ready(main());