$(document).ready(function() {

    $(".ifChange input[type=checkbox]").change(function () {
        checkClass($(this));
    }).change();
    function checkClass(collaborate) {
        var tr = collaborate.closest("tr");
        var receive = tr.find("input[type='checkbox'][name='receiveOrder']");
        var sent = tr.find("input[type='checkbox'][name='sentOrderToCustomer']");
        tr.removeClass("orderFull changeColorSentOrder changeColorReceiveOrder");
        if (receive.is(":checked")) {
            if (sent.is(":checked")) {
                tr.addClass("orderFull");
            } else {
                tr.addClass("changeColorReceiveOrder")
            }
        } else if (sent.is(":checked")) {
            tr.addClass("changeColorSentOrder");
        }
    }

    $('.saveInfo').on('click', function (e) {
        var idToChange = $(this).find('input[name=saveBtn]').val();
        //receive checked or unchecked Checkbox, from selected <tr>
        var receiveCheckboxValue = $(this).closest("tr").find("input[type='checkbox'][name='receiveOrder']").prop('checked');
        var sentCheckboxValue = $(this).closest("tr").find("input[type='checkbox'][name='sentOrderToCustomer']").prop('checked');

        //alert("idToChange: "+idToChange+" receiveCheckboxValue: "+receiveCheckboxValue+ " sentCheckboxValue: "+sentCheckboxValue);
        $.ajax({
            type: "POST",
            url: '/saveChangeCheckbox',
            data: {idCustomer: idToChange, receiveCheckbox: receiveCheckboxValue, sentCheckbox: sentCheckboxValue},
            success:function() {window.location.reload(true);}
                //setTimeout(function(){
                //  window.location.reload(true)
                //},1000)
        });
    });

});