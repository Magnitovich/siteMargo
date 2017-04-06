$(function(){
    $("#buttonSaveEdit").click(function(event) {
        event.preventDefault();
        //valid вызывает метод валидации, проверяет есть ли ошибка или нет в вызваном id.
        if ($("#sendToChange").valid()) {
            sendEmail();
        }
    });
    $("#buttonSaveNewPassword").click(function(event) {
        event.preventDefault();
        var upperCase = new RegExp('[A-Z]');
        var lowerCase = new RegExp('[a-z]');
        var numbers = new RegExp('[0-9]');

        //valid вызывает метод валидации, проверяет есть ли ошибка или нет в вызваном id.
        var pass = $("#newPassword").val();
        $("#passErrorChange").hide();
        $("#passErrorNumbersChange").hide();
        //|| -или, && -и
        if (pass.length > 10 || pass.length<5) {
            $("#passErrorNumbersChange").show();

        } else {
            $("#passErrorChange").hide();
            $("#passErrorNumbersChange").hide();
            if (pass.match(upperCase) && pass.match(lowerCase) && pass.match(numbers)) {
                //alert("Pass: " + pass + " Length: " + pass.length);
                changePass();
            } else {$("#passErrorChange").show();}
        }

    //    if ($("#sendNewPassword").valid()) {
    //        changePass();
    //    }
    });
});
function changePass() {
    $.ajax({   //тип запроса
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        type: "POST", //это типа method

        url: '/newPassword?' + $("#sendNewPassword").serialize(),
        success: function (msg) {  //msg - показывает ответ с сервера

            //window.location.href = "http://dreams.cfapps.io/?successful";
            window.location.href = "/?successfulSentPass";
        }
    });
}
function sendEmail(){
    $("#showEmailError").hide();

    $.ajax({   //тип запроса
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        type:"POST", //это типа method

        url: '/emailToChange?' + $("#sendToChange").serialize(),
        success: function(msg){  //msg - показывает ответ с сервера

            //window.location.href = "http://dreams.cfapps.io/?successful";
            window.location.href = "/?successfulSentEmail";
        },
        error: function (xhr, ajaxOptions, thrownError) {

            //#errors это означ что мы обращаемся к нашему getElementById("errors")
            if (xhr.responseJSON.message === 'Email exists') {
                $("#showEmailError").show();
            }

            //логирование ошибки в консоль.
            console.log(xhr.responseJSON.message);

        }
    });
}