function main() {
    if($("#loginRequired").text() === "login") {
        $("#login").modal('show');
        history.replaceState(null, null, 'login');
    } else if ($("#loginRequired").text() === "registration") {
        $("#register").modal('show');
        history.replaceState(null, null, 'registration');
    }

    $(".profileButton").click(function() {
        $("#main_content").load("/api/profile/" + $("#loggedInUserId").val());
        history.replaceState(null, null, "/profile/" + $("#loggedInUserId").val());
    });

    $(document.body).on("click", '#editProfileButton', function() {
        $(".x_panel").load("/api/profile/" + $("#profileId").val() + "/edit");
        history.replaceState(null, null, "/profile/" + $("#loggedInUserId").val() + "/edit");
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
}


$(document).ready(main());