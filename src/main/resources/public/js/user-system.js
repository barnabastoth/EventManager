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
    $(document.body).on("change", '#uploadProfileImg', function() {
        $("#uploadProfileImgForm").submit();
        $("#main_content").load("/api/profile/" + $("#loggedInUserId").val(), function () {
            pushState("/profile/" + $("#loggedInUserId").val());
            $.notify({
                icon: 'glyphicon glyphicon-ok',
                title: 'Siker!',
                message: 'Az új profilképed feltöltésre került.',
                type: 'success'
            });
        });
    });


}

function allUsersPage() {
    $(document.body).on("click", '#viewUserButton', function() {
        var id = $(this).parent().attr('action');
        $("#main_content").load("/api/profile/" + id, function () {
            pushState("/profile/" + id);
        });
    });
    $(document.body).on("click", '#editUserButton', function() {
        var id = $(this).parent().attr('action');
        $(".x_panel").load("/api/profile/" + id + "/edit", function () {
            pushState("/profile/" + id + "/edit");
        });
    });
    $(document.body).on("click", '#deactivateUserButton', function() {
        var id = $(this).parent().attr('action');
        $("#main_content").load("/api/profile/" + id + "/deactivate", function () {
            pushState("profile/" + id + "/edit");
            $.notify({
                icon: 'glyphicon glyphicon-ok',
                title: 'Siker!',
                message: 'A felhasználó mostantól bannolva van.',
                type: 'success'
            });
        });
    });
    $(document.body).on("click", '#activateUserButton', function() {
        var id = $(this).parent().attr('action');
        $("#main_content").load("/api/profile/" + id + "/activate", function () {
            pushState("/profile");
            $.notify({
                icon: 'glyphicon glyphicon-ok',
                title: 'Siker!',
                message: 'A felhasználó most már nincs bannolva.',
                type: 'success'
            });
        });
    });
    $(document.body).on("click", '#addAdminButton', function() {
        var id = $(this).parent().attr('action');
        $("#main_content").load("/api/profile/" + id + "/admin/add", function () {
            pushState("/profile");
            $.notify({
                icon: 'glyphicon glyphicon-ok',
                title: 'Siker!',
                message: 'A felhasználó megkapta az Admin jogosultságot.',
                type: 'success'
            });
        });
    });
    $(document.body).on("click", '#removeAdminButton', function() {
        var id = $(this).parent().attr('action');
        $("#main_content").load("/api/profile/" + id + "/admin/remove", function () {
            pushState("/profile");
            $.notify({
                icon: 'glyphicon glyphicon-ok',
                title: 'Siker!',
                message: 'A felhasználótól ellett véve az Admin jogosultság.',
                target: '_blank',
                type: 'success'
            });
        });
    });
}

function main() {
    userAuthModalHandling();
    userProfilePage();
    allUsersPage();
}



$(document).ready(main());