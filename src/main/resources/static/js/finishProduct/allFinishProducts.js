$(document).ready(function() {

    $('.bedroomSelected a').on('click', function (e) {
        var idBedroom = $(this).find('input[name=idBedroom]').val();
        //alert("ID: "+id + "  photo: "+photo);
        window.location.href = "buyFinishProduct?modelId=" + idBedroom + "&part=bedroom";
    });
    $('.cabinetSelected a').on('click', function (e) {
        var idCabinet = $(this).find('input[name=idCabinet]').val();
        //alert("ID: "+id + "  photo: "+photo);
        window.location.href = "buyFinishProduct?modelId=" + idCabinet + "&part=cabinet";
    });
    $('.childrenSelected a').on('click', function (e) {
        var id = $(this).find('input[name=idChildren]').val();
        //alert("ID: "+id + "  photo: "+photo);
        window.location.href = "buyFinishProduct?modelId=" + id + "&part=children";
    });
    $('.guestSelected a').on('click', function (e) {
        var id = $(this).find('input[name=idGuest]').val();
        //alert("ID: "+id + "  photo: "+photo);
        window.location.href = "buyFinishProduct?modelId=" + id + "&part=guestroom";
    });
    $('.kitchenSelected a').on('click', function (e) {
        var id = $(this).find('input[name=idKitchen]').val();
        //alert("ID: "+id + "  photo: "+photo);
        window.location.href = "buyFinishProduct?modelId=" + id + "&part=kitchen";
    });
    $('.lambrSelected a').on('click', function (e) {
        var id = $(this).find('input[name=idLambr]').val();
        //alert("ID: "+id + "  photo: "+photo);
        window.location.href = "buyFinishProduct?modelId=" + id + "&part=lambr";
    });
    $('.curtainSelected a').on('click', function (e) {
        var id = $(this).find('input[name=idCurtain]').val();
        //alert("ID: "+id + "  photo: "+photo);
        window.location.href = "buyFinishProduct?modelId=" + id + "&part=curtFinish";
    });
    $('.tulleSelected a').on('click', function (e) {
        var id = $(this).find('input[name=idTulle]').val();
        //alert("ID: "+id + "  photo: "+photo);
        window.location.href = "buyFinishProduct?modelId=" + id + "&part=tulleFinish";
    });

});