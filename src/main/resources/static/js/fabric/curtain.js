$(document).ready(function() {
    $('.pageSelected a').on('click', function (e) {
        var addPage = $("#selectPage").val();
        var id = $(this).find('input[name=id]').val();
        //alert("ID: "+id + "  photo: "+photo);
        window.location.href ="buyCurtain?modelId="+id+"&part="+addPage;
    });

});
function addOffer() {
    var addPage = $("#selectPage").val();

    var selectedForEdit = $('#mainDiv input:checked')
    if (selectedForEdit.length>1) {
        alert("You selected more one model")
    }else if (selectedForEdit.length==0) {
        alert("You don't selected anyone model")
    } else {
        window.location.href ="addNewOffer?id="+selectedForEdit[0].id+"&part="+addPage;
    }
}

function addNewCurtain(){
    var addPage = $("#selectPage").val();
    window.location.href ="addInfoAboutNewCurtain?part="+addPage;}


function editInfoCurtain() {
    var addPage = $("#selectPage").val();
    var selectedForEdit = $('#mainDiv input:checked')

    if (selectedForEdit.length>1) {
        alert("You selected more one model")

    }else if (selectedForEdit.length==0) {
        alert("You don't selected anyone model")
    } else {

        window.location.href ="addInfoAboutNewCurtain?id="+selectedForEdit[0].id+"&part="+addPage;
    }
}

function deleteSelectedElementsCurtain() {

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
        url: '/delete/DELETE',
        success: function() {window.location.reload(true);}
    })
}