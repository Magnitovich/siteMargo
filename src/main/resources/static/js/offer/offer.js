$(document).ready(function() {
    $.datepicker.setDefaults( $.datepicker.regional[ "ru" ] );
    $( ".selector" ).datepicker({ dateFormat: 'yy-mm-dd' });
    //var date = $('#datepicker').datepicker({ dateFormat: 'dd-mm-yy' }).val();

    //$( ".selector" ).datepicker({
    //    showOn: "both"
    //});
//    var showOn = $( ".selector" ).datepicker( "option", "showOn" );
//
//// Setter
//    $( ".selector" ).datepicker( "option", "showOn", "both" );

    $('.pageSelected a').on('click', function (e) {
        var id = $(this).find('input[name=idCommodity]').val();
        var addPage = $(this).find('input[name=partBackEnd]').val();
        //alert("ID: "+id + "  page: "+addPage);
        if(addPage=="clothFabric" || addPage=="curtain" || addPage=="orderCurtain" ||
             addPage=="tulle" || addPage=="upholsteryFabric") {
            window.location.href ="buyCurtain?modelId="+id+"&part="+addPage;
            //window.location.href ="buyFinishProduct?modelId="+id+"&part="+addPage;
        }else if(addPage=="bedroom" || addPage=="cabinet" || addPage=="children" ||
             addPage=="guestroom" || addPage=="kitchen" || addPage=="lambr" || addPage=="curtFinish"
            || addPage=="tulleFinish") {
            window.location.href ="buyFinishProduct?modelId="+id+"&part="+addPage;
            //window.location.href ="buyFinishProduct?modelId="+id+"&part="+addPage;
        }else if(addPage=="band" || addPage=="fringe" || addPage=="luvers" ||
             addPage=="pickup" || addPage=="various" ) {
            window.location.href ="buyAccessoriesProduct?modelId="+id+"&part="+addPage;
            //window.location.href ="buyFinishProduct?modelId="+id+"&part="+addPage;
        }else if(addPage=="pillow" ) {
            window.location.href ="buyInteriorProduct?modelId="+id+"&part="+addPage;
            //window.location.href ="buyFinishProduct?modelId="+id+"&part="+addPage;
        }
    });

});
function deleteSelectedElements() {

    var selectedYachtForDelete = [];
    var i = 0;

    var arrayCheckboxClicked = $('input:checked')

    arrayCheckboxClicked.each(function(){
        var hasNextt = $(this);

        selectedYachtForDelete[i] = hasNextt[0].id;
        i++;
    });
    $.ajax({   //тип запроса
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        type:"POST", //это типа method
        data: JSON.stringify(selectedYachtForDelete), //грубо говоря это Серриализация
        url: '/delete/offer',
        success: function() {window.location.reload(true);}
    })
}

function sendInfoAboutOffer(){
    var q = $("#quantity").val();
    var offerQ = $("#quantityInOffer").val();
    var percent = $("#percent").val();

    //alert("Q: "+q+" offerQ: "+offerQ+", percent: "+percent)
}
//$( function() {
//    $( "#datepicker" ).datepicker();
//} );