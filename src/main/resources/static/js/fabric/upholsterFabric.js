function buyYachtFromImg() {

    var selectedForEdit = $('input:hidden');


    window.location.href ="buyYachts?id="+selectedForEdit[0].id;

}

function addNewOrder(){

    window.location.href ="addInfoAboutNewUpholster";
}

function editInfoOrder() {

    var selectedForEdit = $('#mainDiv input:checked');

    if (selectedForEdit.length>1) {
        alert("You selected more one model")

    }else if (selectedForEdit.length==0) {
        alert("You don't selected anyone model")
    } else {

        window.location.href ="addInfoAboutNewUpholster?id="+selectedForEdit[0].id;
    }
}

function deleteSelectedElementsOrder() {

    var selectedYachtForDelete = [];
    var i = 0;

    var arrayCheckboxClicked = $('input:checked');

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
        url: '/deleteUpholster/DELETE',
        success: function(msg){  //msg - показывает ответ с сервера
            window.location.href = "/orderFabric"
        }
    })
}