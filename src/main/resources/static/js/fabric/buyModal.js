//$(document).ready(function() {
//    var photo;
//    var name;
//    var describe;
//    var quantity;
//    var quantityInDB;
//    var price;
//    var quantityInDBFromJava;
//
//
//    $("#clickToSummonsBuy").click(function (event) {
//        //отмена привычных действий кнопки ссылки input(a)
//        event.preventDefault();
//        $('#goBuy').modal();
//
//        photo = $("#photoId").val();
//        name = $("#name").val();
//        describe = $("#describeID").val();
//        quantity = Number($("#numberOrder").val());
//        quantityInDB = Number($("#quantityInDB").val());
//        price = $("#priceID").val();
//        quantityInDBFromJava = quantityInDB;
//        var valueAfterOrder = (price * quantity).toFixed(2); //valueWhisky
//        //$("#changePrice").html(valueWhisky);
//        console.log("valueWhisky " + valueAfterOrder);
//        console.log("PHOTO: " + photo);
//        console.log("NAME: " + name);
//        console.log("QUANTITYinDB: " + quantityInDB);
//        console.log("Quantity: " + quantity);
//        console.log("PRICE: " + price);
//        $("#changePrice").html(price);
//
//        $("input[type='number']").change(function(){
//            //alert($(this).val());
//        if(Number($(this).val()) ==0){
//            $("#errorQuantityZERO").show();
//            $("#errorQuantity").hide();
//            $("#errorSummQuantity").hide();
//        }
//        else if (Number($(this).val()) > quantityInDB || $(this).val() < 0) {
//            //alert("Error quantity");
//            $("#errorQuantity").show();
//            $("#errorSummQuantity").hide();
//        }
//        if (Number($(this).val()) >0) {
//            var k = (price * $(this).val()).toFixed(2)
//            $("#changePrice").html(k);
//        } else {
//
//            $("#changePrice").html(price);
//        }
//        })
//    });
//});