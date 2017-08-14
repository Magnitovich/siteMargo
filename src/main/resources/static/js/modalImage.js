$(document).ready(function() {
    $("#startModalImage").click(function (event) {
            event.preventDefault();
    $('#goImage').modal();

            showDivs(slideIndex);
}
    )}
);

var slideIndex = 1;


function plusDivs(n) {
    showDivs(slideIndex += n);
}

function showDivs(n) {
    var i;
    var x = document.getElementsByClassName("mySlides");
    if (n > x.length) {slideIndex = 1}
    if (n < 1) {slideIndex = x.length}
    for (i = 0; i < x.length; i++) {
        x[i].style.display = "none";
    }
    x[slideIndex-1].style.display = "block";
}
function closeModal(){
    $('#goImage').modal('hide');
}