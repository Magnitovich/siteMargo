function buyYachtFromImg() {

    var selectedForEdit = $('input:hidden')


    window.location.href ="buyYachts?id="+selectedForEdit[0].id;

}

function addNewCurtain(){

    window.location.href ="addInfoAboutNewCurtain";
}

function editInfoCurtain() {

    var selectedForEdit = $('#mainDiv input:checked')

    if (selectedForEdit.length>1) {
        alert("You selected more one yacht")

    }else if (selectedForEdit.length==0) {
        alert("You don't selected anyone yacht")
    } else {

        window.location.href ="addInfoAboutNewCurtain?id="+selectedForEdit[0].id;
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
        url: '/deleteYacht/DELETE',
        success: function(msg){  //msg - показывает ответ с сервера
            window.location.href = "/curt"
        }
    })
}