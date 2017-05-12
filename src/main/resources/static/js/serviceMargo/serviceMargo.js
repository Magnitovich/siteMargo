$(document).ready(function() {
    $('.pageSelected a').on('click', function (e) {
        var id = $(this).find('input[name=id]').val();
        //alert("ID: "+id + "  photo: "+photo);
        window.location.href ="buyServiceMargoProduct?modelId="+id;
    })
});
    function addNewBedroom(){
    window.location.href = "addInfoAboutNewServiceMargo"}

function editInfoBedroom() {

    var selectedForEdit = $('#mainDiv input:checked')
    if (selectedForEdit.length>1) {
        alert("You selected more one model")
    }else if (selectedForEdit.length==0) {
        alert("You don't selected anyone model")
    } else {
        window.location.href ="addInfoAboutNewServiceMargo?id="+selectedForEdit[0].id;
    }
}
function deleteSelectedElementsBedroom() {

    var selectedForDelete = [];
    var i = 0;

    var arrayCheckboxClicked = $('input:checked')

    arrayCheckboxClicked.each(function(){
        var hasNext = $(this);

        selectedForDelete[i] = hasNext[0].id;
        i++;
    });
    $.ajax({
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        type:"POST",
        data: JSON.stringify(selectedForDelete),
        url: '/delete/serviceMargo',
        success: function() {window.location.reload(true);}
    })
}