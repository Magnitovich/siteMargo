$(document).ready(function() {


//function sendIdToDeleteCustomerOrder(){
    $('.deleteDiv').on('click', function (e) {
        var deleteOrderFromCustomer = $(this).find('input[name=deleteBtn]').val();


        $.ajax({   //тип запроса
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            type: "POST",
            data: JSON.stringify(deleteOrderFromCustomer),
            url: '/delete/customerOrder',
            success: function (msg) {
                window.location.href = "/administrationNotSleeps"
            }
        });
    });
//}
    //$('.classEverything').on('click', function (e) {
    //    var deleteOrderFromCustomer = $(this).find('input[name=orderCustomer]').val();
    //
    //
    //    window.location.href ="orderFromCustomer?id="+deleteOrderFromCustomer;
    //});


    $('.deleteOrderCustomer').on('click', function (e) {
        var deleteOrderOneFromCustomer = $(this).find('input[name=deleteForOrderCustomer]').val();

        $.ajax({
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            type: "POST",
            data: JSON.stringify(deleteOrderOneFromCustomer),
            url: '/delete/oneOrderFromCustomer',
            success: function (msg) {
                window.location.href = "/administrationNotSleeps";
                //window.location.href = "/administrationNotSleeps"
            }
        });
    });
});
