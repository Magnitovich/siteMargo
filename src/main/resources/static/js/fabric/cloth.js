function buyYachtFromImg() {

    var selectedForEdit = $('input:hidden')


    window.location.href ="buyYachts?id="+selectedForEdit[0].id;

}

function addNewCloth(){

    window.location.href ="addInfoAboutNewCloth";
}

function editInfoCloth() {

    var selectedForEdit = $('#mainDiv input:checked')

    if (selectedForEdit.length>1) {
        alert("You selected more one model")

    }else if (selectedForEdit.length==0) {
        alert("You don't selected anyone model")
    } else {

        window.location.href ="addInfoAboutNewCloth?id="+selectedForEdit[0].id;
    }
}

function deleteSelectedElementsCloth() {

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
        url: '/deleteCloth/DELETE',
        success: function(msg){  //msg - показывает ответ с сервера
            window.location.href = "/clothModel"
        }
    })
}