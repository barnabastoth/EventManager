function main() {
    $(document.body).on("click", "#homeButton", function() {
        $("#main_content").load("/api/event/latest");
        history.replaceState(null, null, '/');
    });
    $(document.body).on("click", "#aboutButton", function() {
        $("#main_content").load("/api/about");
        history.replaceState(null, null, 'about');
    });
    $(document.body).on("click", "#contactButton", function() {
        $("#main_content").load("/api/contact");
        history.replaceState(null, null, 'contact');
    });

}


$(document).ready(main());