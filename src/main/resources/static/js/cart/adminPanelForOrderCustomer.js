$(document).ready(function() {

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


    $('.checkOrder').on('click', function (e) {
        var name = $(this).data('customer-id');
        window.location.href = "/orderFromCustomer?id="+name;

        //$.ajax({
        //
        //    type: "POST",
        //    data: {name: id},
        //
        //    error: function(e) {
        //                console.log(e);
        //            },
        //    success: function () {
        //        window.location.href = "/orderFromCustomer?id="+name;
        //    }
        //});
    });

    //$(".ifChange input[type=checkbox]").change(function () {
    //    checkClass($(this));
    //}).change();
    //function checkClass(collaborate) {
    //    var tr = collaborate.closest("tr");
    //    var receive = tr.find("input[type='checkbox'][name='receiveOrder']");
    //    var sent = tr.find("input[type='checkbox'][name='sentOrderToCustomer']");
    //    tr.removeClass("orderFull changeColorSentOrder changeColorReceiveOrder");
    //    if (receive.is(":checked")) {
    //        if (sent.is(":checked")) {
    //            tr.addClass("orderFull");
    //        } else {
    //            tr.addClass("changeColorReceiveOrder")
    //        }
    //    } else if (sent.is(":checked")) {
    //        tr.addClass("changeColorSentOrder");
    //    }
    //}
    //
    //$('.saveInfo').on('click', function (e) {
    //    var idToChange = $(this).find('input[name=saveBtn]').val();
    //    //receive checked or unchecked Checkbox, from selected <tr>
    //    var receiveCheckboxValue = $(this).closest("tr").find("input[type='checkbox'][name='receiveOrder']").prop('checked');
    //    var sentCheckboxValue = $(this).closest("tr").find("input[type='checkbox'][name='sentOrderToCustomer']").prop('checked');
    //
    //    //alert("idToChange: "+idToChange+" receiveCheckboxValue: "+receiveCheckboxValue+ " sentCheckboxValue: "+sentCheckboxValue);
    //    $.ajax({
    //        type: "POST",
    //        url: '/saveChangeCheckbox',
    //        data: {idCustomer: idToChange, receiveCheckbox: receiveCheckboxValue, sentCheckbox: sentCheckboxValue},
    //        success: window.location.reload(true)
    //    });
    //});



});