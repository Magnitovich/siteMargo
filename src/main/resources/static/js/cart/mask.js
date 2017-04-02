$(function(){
    $.mask.definitions['~']='[+-]';
    $('#phone').mask('(999) 999-9999');
});
function finishRegistration(){
    window.location.href = "/showAll"
}