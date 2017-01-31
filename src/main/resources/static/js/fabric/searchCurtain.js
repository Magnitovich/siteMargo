
$(function(){

    $('.container').mixItUp({


    });
});


//jQuery(document).ready(function() {
//    var fActive = '';
//
//    function filterColor(color) {
//        if (fActive != color) {
//          if($("#test007").equalTo(color)){
//              $("#test007").slideDown();
//          }
//            $('.test').filter(' ' + color).slideDown();
//            $('.test').filter(':not( ' + color + ')').slideUp();
//            fActive = color;
//        }
//    }
//
//    $('.orange').click(function () {
//        filterColor('Оранжевый');
//    });
//    $('.grey').click(function () {
//        filterColor('Серый');
//    });
//    $('.black').click(function () {
//        filterColor('Черный');
//    });
//});
//$('.f-all').click(function(){
//    $('div').slideDown();
//    fActive = 'all';
//});



function showSelectedSearch(){

    var main = [];

    var price = $("#searchPrice").val();
    console.log(price);
    var position = [];
    var selectedColor = [];
    var i = 0;
    var arrayCheckboxChecked = $('#colorDiv input:checked');
    arrayCheckboxChecked.each(function(){
        var nextChecked = $(this).val();
        selectedColor[i] =$(this).val();
        position[i] = document.documentElement.innerHTML.indexOf(nextChecked);
        alert(position);
        i++;
    });
    console.log(selectedColor);

    var selectedPaint = [];
    var q = 0;
    $('#searchPaint input:checked').each(function(){

        selectedPaint[i] =$(this).val();
        alert($(this).val());
        q++;
    });
    console.log(selectedPaint);

}
