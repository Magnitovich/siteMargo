
function addNewBedroom(){
    var addPage = $("#selectPage").val();
    window.location.href ="addInfoAboutNewFinishProduct?part="+addPage;}
function addNewCabinet(){ window.location.href ="addInfoAboutNewFinishProduct?part=cabinet";}
function addNewChildrenroom(){ window.location.href ="addInfoAboutNewFinishProduct?part=children";}
function addNewGuestroom(){ window.location.href ="addInfoAboutNewFinishProduct?part=guestroom";}
function addNewKitchen(){ window.location.href ="addInfoAboutNewFinishProduct?part=kitchen";}
function addNewLambrequin(){ window.location.href ="addInfoAboutNewFinishProduct?part=lambr";}
function addNewCurtainFinish(){ window.location.href ="addInfoAboutNewFinishProduct?part=curtFinish";}
function addNewTulleFinish(){ window.location.href ="addInfoAboutNewFinishProduct?part=tulleFinish";}
//function addNewCabinet(){ window.location.href ="addInfoAboutNewCabinet";}
//function addNewChildrenroom(){ window.location.href ="addInfoAboutNewChildrenroom";}
//function addNewGuestroom(){ window.location.href ="addInfoAboutNewGuestroom";}
//function addNewKitchen(){ window.location.href ="addInfoAboutNewKitchen";}
//function addNewLambrequin(){ window.location.href ="addInfoAboutNewLambrequin";}
//function addNewCurtainFinish(){ window.location.href ="addInfoAboutNewCurtainFinish";}
//function addNewTulleFinish(){ window.location.href ="addInfoAboutNewTulleFinish";}

function editInfoBedroom() {

    var selectedForEdit = $('#mainDiv input:checked')
    if (selectedForEdit.length>1) {
        alert("You selected more one model")
    }else if (selectedForEdit.length==0) {
        alert("You don't selected anyone model")
    } else {
        window.location.href ="addInfoAboutNewFinishProduct?id="+selectedForEdit[0].id+"&part=bedroom";
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
        url: '/delete/DELETE',
        success: function(msg){
            window.location.href = "/curtainModels"
        }
    })
}