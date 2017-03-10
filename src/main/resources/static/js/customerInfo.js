//$(document).ready(function() {

    function sendCustomerInfo() {

        var nameCustomerForSend = $("#NickNameCustomerForSend").val();
        var phoneCustomerForSend = $("#PhoneCustomerForSend").val();
        var emailCustomerForSend = $("#EmailCustomerForSend").val();
        var addressCustomerForSend = $("#descriptionCustomerForSend").val();

        console.log("Name: " + nameCustomerForSend + " Address: " + addressCustomerForSend + " Phone: " + phoneCustomerForSend
            + " Email: " + emailCustomerForSend);


        $.ajax({   //тип запроса
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            type: "POST", //это типа method
            data: {NickName:nameCustomerForSend, Phone:phoneCustomerForSend, Email:emailCustomerForSend,
                description:addressCustomerForSend},
            url: '/showAll/customerInfo',
            success: function (msg) {  //msg - показывает ответ с сервера
                window.location.href = "/showAll"
            }
        });
    }
//});