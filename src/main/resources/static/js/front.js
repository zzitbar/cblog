$(function () {
    $(".nav-cover").on("click", function(){
        if ($("body").hasClass("nav-opened")) {
            $("body").toggleClass("nav-opened nav-closed");
        }
    });
})
function navShow() {
    $("body").toggleClass("nav-opened nav-closed");
}