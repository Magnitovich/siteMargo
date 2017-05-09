
$(document).ready(function() {
    $('.pageSelected a').on('click', function (e) {
        var addPage = $("#selectPage").val();
        var id = $(this).find('input[name=id]').val();
        //alert("ID: "+id + "  photo: "+photo);
        window.location.href ="buyAccessoriesProduct?modelId="+id+"&part="+addPage;
    })
});
    function addNewAccessories(){
    var addPage = $("#selectPage").val();
    window.location.href ="addInfoAboutNewAccessories?part="+addPage;}

function editInfoAccessories() {
    var addPage = $("#selectPage").val();

    var selectedForEdit = $('#mainDiv input:checked')
    if (selectedForEdit.length>1) {
        alert("You selected more one model")
    }else if (selectedForEdit.length==0) {
        alert("You don't selected anyone model")
    } else {
        window.location.href ="addInfoAboutNewAccessories?id="+selectedForEdit[0].id+"&part="+addPage;
    }
}

function deleteSelectedElementsAccessories() {

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
        url: '/delete/accessories',
        success: function() {window.location.reload(true);}
    })
}