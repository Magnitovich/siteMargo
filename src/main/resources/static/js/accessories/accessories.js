
$(document).ready(function() {
    $('.pageSelected a').on('click', function (e) {
        var addPage = $("#selectPage").val();
        var id = $(this).find('input[name=id]').val();
        //alert("ID: "+id + "  photo: "+photo);
        window.location.href ="buyFinishProduct?modelId="+id+"&part="+addPage;
    })
});
    function addNewBedroom(){
    var addPage = $("#selectPage").val();
    window.location.href ="addInfoAboutNewFinishProduct?part="+addPage;}

    //function buyFinish() {
            //var photo = $(this).find('input[name=photoId]').val();
            //var id = $(this).find('input[type=hidden][name=pageId]').val();
            //var idProp = $(this).closest("tr").find('input[type=hidden][name=pageId]').prop();
        //console.log(idProp);
            //alert("ID: " + id + "IdProp"+ idProp);
        //var addPage = $("#selectPage").val();
        //var pageId = $("#pageId").val();
        //window.location.href ="buyFinishProduct?modelId="+pageId+"&part="+addPage;
    //}
function editInfoBedroom() {
    var addPage = $("#selectPage").val();

    var selectedForEdit = $('#mainDiv input:checked')
    if (selectedForEdit.length>1) {
        alert("You selected more one model")
    }else if (selectedForEdit.length==0) {
        alert("You don't selected anyone model")
    } else {
        window.location.href ="addInfoAboutNewFinishProduct?id="+selectedForEdit[0].id+"&part="+addPage;
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
        url: '/delete/finishProduct',
        success: function() {window.location.reload(true);}
    })
}