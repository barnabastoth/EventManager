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

    $(document.body).on('click', "#alertbtn", function () {
        $.notify({
            // options
            icon: 'glyphicon glyphicon-warning-sign',
            title: 'Bootstrap notify',
            message: 'Turning standard Bootstrap alerts into "notify" like notifications',
            url: 'https://github.com/mouse0270/bootstrap-notify',
            target: '_blank'
        },{
            // settings
            element: 'body',
            position: null,
            type: "info",
            allow_dismiss: true,
            newest_on_top: false,
            showProgressbar: false,
            placement: {
                from: "top",
                align: "right"
            },
            offset: 20,
            spacing: 10,
            z_index: 1031,
            delay: 5000,
            timer: 1000,
            url_target: '_blank',
            mouse_over: null,
            animate: {
                enter: 'animated fadeInDown',
                exit: 'animated fadeOutUp'
            },
            onShow: null,
            onShown: null,
            onClose: null,
            onClosed: null,
            icon_type: 'class',
            template: '<div data-notify="container" class="col-xs-11 col-sm-3 alert alert-{0}" role="alert">' +
            '<button type="button" aria-hidden="true" class="close" data-notify="dismiss">×</button>' +
            '<span data-notify="icon"></span> ' +
            '<span data-notify="title">{1}</span> ' +
            '<span data-notify="message">{2}</span>' +
            '<div class="progress" data-notify="progressbar">' +
            '<div class="progress-bar progress-bar-{0}" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%;"></div>' +
            '</div>' +
            '<a href="{3}" target="{4}" data-notify="url"></a>' +
            '</div>'
        });
    })

}

function pushState(url) {
    if(window.location.href.toString().split(window.location.host)[1] !== url) {
        history.pushState({}, document.title, url);
    }
}


$(document).ready(main());