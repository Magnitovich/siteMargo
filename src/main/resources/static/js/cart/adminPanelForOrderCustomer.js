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

    $(".ifChange input[type=checkbox]").change(function(){
       checkClass($(this));
    }).change();
    function checkClass(collaborate){
        var tr = collaborate.closest("tr");
        var receive = tr.find("input[type='checkbox'][name='receiveOrder']");
        var sent = tr.find("input[type='checkbox'][name='sentOrderToCustomer']");
        tr.removeClass("orderFull changeColorSentOrder changeColorReceiveOrder");
        if(receive.is(":checked")){
            if(sent.is(":checked")){
                tr.addClass("orderFull");
            } else {
                tr.addClass("changeColorReceiveOrder")
            }
        } else if(sent.is(":checked")){
            tr.addClass("changeColorSentOrder");
        }
    }
    $('.saveInfo').on('click', function (e) {
        var idToChange = $(this).find('input[name=saveBtn]').val();
        //receive checked or unchecked Checkbox, from selected <tr>
        var receiveCheckboxValue = $(this).closest("tr").find("input[type='checkbox'][name='receiveOrder']").prop('checked');
        var sentCheckboxValue = $(this).closest("tr").find("input[type='checkbox'][name='sentOrderToCustomer']").prop('checked');

        alert("idToChange: "+idToChange+" receiveCheckboxValue: "+receiveCheckboxValue+ " sentCheckboxValue: "+sentCheckboxValue);
    });
});
function sendCheckboxChange(){
    var idToChange = $(this).find('input[name=saveBtn]').val();
    var receiveCheckboxValue = $(this).find("input[type='checkbox'][name='receiveOrder']").prop('checked');
    var sentCheckboxValue = $(this).find("input[type='checkbox'][name='sentOrderToCustomer']").val();

    alert("idToChange: "+idToChange+" receiveCheckboxValue: "+receiveCheckboxValue+ " sentCheckboxValue: "+sentCheckboxValue);
}