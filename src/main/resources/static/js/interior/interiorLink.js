$(document).ready(function() {
    $('.pageSelected a').on('click', function (e) {
        var id = $(this).find('input[name=id]').val();
        //alert("ID: "+id + "  photo: "+photo);
        window.location.href ="buyInteriorProduct?modelId="+id;
    })
});
    function addNewBedroom(){
    window.location.href = "addInfoAboutNewInteriorProduct"}

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
        window.location.href ="addInfoAboutNewInteriorProduct?id="+selectedForEdit[0].id;
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
        url: '/delete/interior',
        success: function() {window.location.reload(true);}
    })
}