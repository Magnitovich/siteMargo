$(function(){
    $('.saveEdit').click(function (e) {
        var idCustomer = $("#orderCustomer").val();
        var idToChange = $(this).data('customer-id');
        var quantity = $('#quantity-' + idToChange).val();
        //'@Url.Action("../Home/MyActionResult")' + '?Page='+data+'&'+PostData;
        //window.location.href ="changeQuantityOrder?id="+id+"&quantity="+quantity;
        $.ajax({

            type: "POST",
            url: '/changeQuantityOrder',
            //data: JSON.stringify(send),
            data: {idOrder: idToChange, quantityChange: quantity},

            error: function(e) {
                console.log(e);
            },
            success: function () {
                window.location.href = "/orderFromCustomer?id="+idCustomer;
                //window.location.href = "/administrationNotSleeps";
            }
        });
    });
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
