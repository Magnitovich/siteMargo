$(document).ready(function() {

    $('.curtain a').on('click', function (e) {
        var idBedroom = $(this).find('input[name=idCurtain]').val();
        //alert("ID: "+id + "  photo: "+photo);
        window.location.href = "buyCurtain?modelId=" + idBedroom + "&part=curtain";
    });
    $('.clothFabric a').on('click', function (e) {
        var idBedroom = $(this).find('input[name=clothFabricId]').val();
        //alert("ID: "+id + "  photo: "+photo);
        window.location.href = "buyCurtain?modelId=" + idBedroom + "&part=clothFabric";
    });
    $('.orderCurtain a').on('click', function (e) {
        var idBedroom = $(this).find('input[name=orderCurtainId]').val();
        //alert("ID: "+id + "  photo: "+photo);
        window.location.href = "buyCurtain?modelId=" + idBedroom + "&part=orderCurtain";
    });
    $('.upholsteryFabric a').on('click', function (e) {
        var idBedroom = $(this).find('input[name=upholsteryFabricId]').val();
        //alert("ID: "+id + "  photo: "+photo);
        window.location.href = "buyCurtain?modelId=" + idBedroom + "&part=upholsteryFabric";
    });
    $('.tulle a').on('click', function (e) {
        var idBedroom = $(this).find('input[name=tulleId]').val();
        //alert("ID: "+id + "  photo: "+photo);
        window.location.href = "buyCurtain?modelId=" + idBedroom + "&part=tulle";
    });



});