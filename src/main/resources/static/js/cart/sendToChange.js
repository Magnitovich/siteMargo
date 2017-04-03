$(function(){
    $("#buttonSaveEdit").click(function(event) {
        //отмена привычных действий кнопки ссылки input(a)
        event.preventDefault();
        //var nameEdit = $(this).find('span[class=textColor]').val();
        var nameEdit = $("#spanName").text();
        var phoneEdit = $("#phoneCustomer").val();
        var addressEdit = $("#descriptionEdit").val();
        var emailEdit = $("#email").val();
        //alert("name: "+nameEdit+"\n"+"Phone: "+phoneEdit+"\n"+" Address: "+addressEdit+"\n"+" Email: "+emailEdit);
        $.ajax({
            type: "POST",
            url: '/changeUserInfo',
            data: {nameCustomer: nameEdit, phoneCustomer: phoneEdit,
                addressCustomer: addressEdit, emailCustomer:emailEdit},
            success:function()
            {window.location.href="/showAll"}
        });
    });

});
function finishRegistration(){
    window.location.href = "/showAll"
}
function changeDataUser(){

    //var nameCustomer = $(this).find('span[id=nickName]').val();
    var nameCustomer = $("#nameToSend").val();
    $.ajax({

        type: "POST",
        url: '/newInfoAboutCustomer/info',
        //data: JSON.stringify(send),
        data: {name: nameCustomer},

        success: function () {
            window.location.href = "/newInfoAboutCustomer";
            //window.location.href = "/administrationNotSleeps";
        },
        error: function(e) {
            console.log(e);
        }
    });

}

