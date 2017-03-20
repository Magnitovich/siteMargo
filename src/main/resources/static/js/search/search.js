$(document).ready(function() {
    $("div[class='searchOnAllPages'] input").change(function () {
        var searchWord = $("#searchRequest").val();
        //console.log("WE are Searching: " + searchWord);

        $.ajax({   //тип запроса
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            type: "POST", //это типа method
            data: JSON.stringify(searchWord), //грубо говоря это Серриализация
            url: '/searchOnTheSite/question',
            success: function (msg) {  //msg - показывает ответ с сервера
                window.location.href = "/searchOnTheSite"
            }
        });
    });
});
//function toSelectedObject(){
//
//    $('.pic a').on('click', function (e) {
//        var photo = $(this).find('input[name=photoId]').val();
//        var id = $(this).find('input[name=id]').val();
//        alert("ID: "+id + "  photo: "+photo);
//    })
//
//}
