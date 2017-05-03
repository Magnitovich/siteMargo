
$(function() {
    //$(function(){
    //    $.mask.definitions['~']='[+-]';
    //    $('#phone').mask('(999) 999-9999');
    //});
    $("#SignUpBtn").click(function(event) {
        //отмена привычных действий кнопки ссылки input(a)
        event.preventDefault();

        var upperCase = new RegExp('[A-Z]');
        var lowerCase = new RegExp('[a-z]');
        var numbers = new RegExp('[0-9]');
        //valid вызывает метод валидации, проверяет есть ли ошибка или нет в вызваном id.
        if ($("#signupSubmitFrm").valid()) {
            $.mask.definitions['~']='[+-]';
            $('#phone').mask('(999) 999-9999');

        //valid вызывает метод валидации, проверяет есть ли ошибка или нет в вызваном id.
        var pass = $("#signUpPassword").val();
        $("#passError").hide();
        $("#passErrorNumbers").hide();
        //|| -или, && -и
        if (pass.length > 10 || pass.length<5) {
            $("#passErrorNumbers").show();

        } else {
            $("#passError").hide();
            $("#passErrorNumbers").hide();
            if (pass.match(upperCase) && pass.match(lowerCase) && pass.match(numbers)) {

                    submitSignupShowError();

            } else {$("#passError").show();}
        }
        }

    });

    $("#NickName").change(function() {
        $("#NickName").valid()
    });

});

function AdminsRight() {

    window.location.href="administrationNotSleeps";
}

function submitSignupShowError() {

    $("#emailError").hide();
    $("#nickError").hide();

    $.ajax({   //тип запроса
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        type:"POST", //это типа method

        url: '/registrationPage?' + $("#signupSubmitFrm").serialize(),
        success: function(msg){  //msg - показывает ответ с сервера

            //window.location.href = "http://dreams.cfapps.io/?successful";
            window.location.href = "/?successful";
        },
        error: function (xhr, ajaxOptions, thrownError) {

            //#errors это означ что мы обращаемся к нашему getElementById("errors")
            if (xhr.responseJSON.message === 'Email exists') {
                $("#emailError").show();
                $("#nickError").hide();
            }
            if (xhr.responseJSON.message === 'NickName exists') {
                $("#emailError").hide();
                $("#nickError").show();
            }
            //логирование ошибки в консоль.
            console.log(xhr.responseJSON.message);

        }
    });
}