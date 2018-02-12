function userAuthModalHandling() {
    if($("#loginRequired").val() === "login") {
        $("#login").modal('show');
        pushState('login');
    } else if ($("#loginRequired").val() === "registration") {
        $("#register").modal('show');
        pushState('registration');
    }
}

function userProfilePage() {
    $(document.body).on("click", '#editProfileButton', function() {
        $(".x_panel").load("/api/profile/" + $("#profileId").val() + "/edit", function () {
            pushState("/profile/" + $("#loggedInUserId").val() + "/edit");
        });
    });
    $(document.body).on("click", '#uploadProfileImgButton', function() {
        $(".x_panel").load("/api/profile/" + $("#profileId").val() + "/uploadImg", function () {
            pushState("/profile/" + $("#profileId").val() + "/uploadImg");
        });
    });
    $(document.body).on("change", '#uploadProfileImg', function() {
        $("#uploadProfileImgForm").submit();
        $("#main_content").load("/api/profile/" + $("#loggedInUserId").val(), function () {
            pushState("/profile/" + $("#loggedInUserId").val());
        });
    });


}

function allUsersPage() {
    $(document.body).on("click", '#viewUserButton', function() {
        var id = $(this).parent().attr('action');
        $("#main_content").load("/api/profile/" + id, function () {
            pushState("/profile" + id);
        });
    });
    $(document.body).on("click", '#editUserButton', function() {
        var id = $(this).parent().attr('action');
        $(".x_panel").load("/api/profile/" + id + "/edit", function () {
            pushState("profile/" + id + "/edit");
        });
    });
    $(document.body).on("click", '#activateUserButton', function() {
        var id = $(this).parent().attr('action');
        $("#main_content").load("/api/profile/" + id + "/activate", function () {
            pushState("/profile");
        });
    });
    $(document.body).on("click", '#addAdminButton', function() {
        var id = $(this).parent().attr('action');
        $("#main_content").load("/api/profile/" + id + "/admin/add", function () {
            pushState("/profile");
        });
    });
    $(document.body).on("click", '#removeAdminButton', function() {
        var id = $(this).parent().attr('action');
        $("#main_content").load("/api/profile/" + id + "/admin/remove", function () {
            pushState("/profile");
        });
    });
}

function main() {
    userAuthModalHandling();
    userProfilePage();
    allUsersPage();

    // $(document.body).on( "click", '#login-form-link', function() {
    //     $("#login-form").delay(100).fadeIn(100);
    //     $("#register-form").fadeOut(100);
    //     $('#register-form-link').removeClass('active');
    //     $(this).addClass('active');
    //     e.preventDefault();
    //     history.replaceState(null, null, 'login');
    // });
    // $(document.body).on( "click", '#register-form-link', function() {
    //     $("#register-form").delay(100).fadeIn(100);
    //     $("#login-form").fadeOut(100);
    //     $('#login-form-link').removeClass('active');
    //     $(this).addClass('active');
    //     e.preventDefault();
    //     history.replaceState(null, null, 'registration');
    // });
}



$(document).ready(main());