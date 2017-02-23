
$(document).ready(function() {

    var photo;
    var name;
    var describe;
    var quantity;
    var quantityInDB;
    var price;
    var quantityInDBFromJava;
    var accessToReturn;

    $("#clickToSummonsBuy").click(function (event) {
        //отмена привычных действий кнопки ссылки input(a)
        event.preventDefault();
        $('#goBuy').modal();

        photo = $("#photoId").val();
        name = $("#name").val();
        describe = $("#describeID").val();
        quantity = Number($("#numberOrder").val());
        quantityInDB = Number($("#quantityInDB").val());
        price = $("#priceID").val();
        quantityInDBFromJava = quantityInDB;
        var valueAfterOrder = (price * quantity).toFixed(2); //valueWhisky
        console.log("PHOTO: " + photo);
        $("#changePrice").html(price);

        $("input[type='number']").change(function () {
            $("#errorQuantityZERO").hide();
            $("#errorQuantity").hide();
            $("#errorSummQuantity").hide();
            if (Number($(this).val()) == 0) {
                $("#errorQuantityZERO").show();
                $("#errorQuantity").hide();
                $("#errorSummQuantity").hide();
            }
            else if (Number($(this).val()) > quantityInDB || $(this).val() < 0) {
                $("#errorQuantityZERO").hide();
                $("#errorQuantity").show();
                $("#errorSummQuantity").hide();
            }
            if (Number($(this).val()) > 0) {
                quantity = $(this).val();
                console.log("Number($(this).val()) >0" + quantity);
                var changePriceUI = (price * $(this).val()).toFixed(2);
                $("#changePrice").html(changePriceUI);
            } else {
                $("#changePrice").html(price);
            }
        });
    });
        //var nameUser = $("#someThingNew").val();
        //console.log(nameUser);

        var cartWhisky = new Array;
        if (localStorage.getItem("shoppingCart")) {
            cartWhisky = JSON.parse(localStorage.getItem("shoppingCart"));

        }

        var itemCart = function (photo, name, describe, quantity, price) {
            this.photo = photo;
            this.name = name;
            this.describe = describe;
            this.quantity = quantity;
            this.price = price;
        };

        $("#buyFromJS").click(function (event) {
            //отмена привычных действий кнопки ссылки input(a)
            event.preventDefault();
            toReturn();
        });
            function toReturn() {
                if (Number(quantity) <= quantityInDB && Number(quantity) > 0) {
                        if (localStorage.getItem("shoppingCart") === null) {
                            cartWhisky = new Array;
                            addItemToCart(photo, name, describe, quantity, price);
                            saveCart();
                            comeBack();
                        } else {
                            loadCart();

                            addItemToCart(photo, name, describe, quantity, price);
                            saveCart();
                            comeBack(); //возвращаемся на главную страницу без изменения кол-ва виски, т.к. оно в корзине.
                            //sendBuyWhiskyInJava();
                        }
                } else if(Number(quantity) ==0){
                    $("#errorQuantityZERO").show();
                    $("#errorQuantity").hide();
                    $("#errorSummQuantity").hide();
                }
                else if (Number(quantity) > quantityInDB || quantity < 0) {
                    $("#errorQuantityZERO").hide();
                    $("#errorQuantity").show();
                    $("#errorSummQuantity").hide();
                }
            }
//AddItemToCart(photo, name, describe, quantity, price)
        function addItemToCart(photo, name, describe, quantity, price) {
            for (var i in cartWhisky) {
                if (cartWhisky[i].name === name) {
                    cartArray = listCart();
                    var quantityInLocalStorage = cartWhisky[i].quantity;
                    var summQuantityOrder = Number(quantityInLocalStorage + quantity);

                    if (summQuantityOrder <= quantityInDBFromJava) {
                        quantity = cartWhisky[i].quantity + quantity;
                        console.log("quantity=Number(cartWhisky[i].quantity + quantity): " + quantity);
                        console.log("cartWhisky[i].quantity: " + cartWhisky[i].quantity);
                        var itemName = new itemCart(photo, name, describe, quantity, price);
                        removeAllItemsFromCart(name);
                        cartWhisky.push(itemName);
                        saveCart();
                        return;
                    } else {
                        $("#errorQuantity").hide();
                        $("#errorSummQuantity").show();
                        return toReturn();
                    }
                }

            }
            var item = new itemCart(photo, name, describe, quantity, price);
            cartWhisky.push(item);
            saveCart();
        }

//clear
        function clearCart() {
            cartWhisky = [];
            saveCart();
        }

//SaveCart()
        function saveCart() {
            //JSON.parse – читает объекты из строки в формате JSON.
            //JSON.stringify – превращает объекты в строку в формате JSON, используется, когда нужно из JavaScript передать данные по сети.

            localStorage.setItem("shoppingCart", JSON.stringify(cartWhisky));
        }

//loadCart()
        function loadCart() {
            cartWhisky = JSON.parse(localStorage.getItem("shoppingCart"));
            //var Yacht = JSON.parse(localStorage.getItem("shoppingCartYacht"));
            // cartWhisky = Whisky +Yacht;
        }

        function comeBack() {
            var k = photo.split("/");
            console.log(k[1] + "----K");
            if (k[1] === "curtain") {
                window.location.href = "curtainModels";
            } else if (k[1] === "tulle") {
                window.location.href = "tulleModel";
            } else if (k[1] === "cars") {
                window.location.href = "clothModel";
            } else if (k[1] === "orderCurtain") {
                window.location.href = "orderFabric";
            } else if (k[1] === "upholsteryFabric") {
                window.location.href = "upholsteryFabric";
            } else {
                window.location.href = "/";
            }
        }


        function cartOpen() {
            window.location.href = "allCart";
        }

//$(document).ready(function() {

//+++++++++++++++++++++++++++++++++++++
        //loadCart();
        var cartArray = listCart();
        var table = '';
        displayShow();

        function displayShow() {
            table = '';
            for (var r in cartArray) {
                table += '<tr><td width="50"><img src="' + cartArray[r].photo + '" width=35 height=80></td>\
            <td width="100">' + cartArray[r].name + '</td>\
            <td width="150">' + cartArray[r].describe + '</td>\
            <td width="100">\
            <table width="80"><td align="right"><button class="minus" data-name="' + cartArray[r].name + '">-</button></td>\
            <td align="center"> ' + cartArray[r].quantity + '</td>\
            <td align="left"><button class="plus" data-name="' + cartArray[r].name + '">+</button></td></tr></table>\
            </td>\
                +<td width="50" align="center">' + cartArray[r].price + '</td>\
                +<td width="80">$' + cartArray[r].total + '</td>\
                +<td width="50"><button class="deleteItem" data-name="' + cartArray[r].name + '">X</button></td></tr>';
            }
            $('#trash').html(table);
            $("#totalCart").html(totalPriceInCart());

        }

        $('#trash').on("click", ".minus", function (e) {
            var name = $(this).attr("data-name");
            removeOneItemsFromCart(name);
            cartArray = listCart();
            displayShow()
        });
        $('#trash').on("click", ".plus", function (e) {
            var name = $(this).attr("data-name");
            addOneItemsToCart(name);
            cartArray = listCart();
            displayShow()
        });
        $('#trash').on("click", ".deleteItem", function (e) {
            var name = $(this).attr("data-name");
            removeAllItemsFromCart(name);
            cartArray = listCart();
            displayShow()
        });

//***************************************************************************
        function listCart() {

            var cartCopy = new Array;
            for (var i in cartWhisky) {
                var item = cartWhisky[i];
                var itemCopy = {};
                for (var p in item) {
                    itemCopy[p] = item[p];
                }
                itemCopy.total = (item.price * item.quantity).toFixed(2);
                cartCopy.push(itemCopy);
            }
            return cartCopy;
        }

//RemoveItemsFromCart(name) //Remove only one
        function removeOneItemsFromCart(name) {
            for (var i in cartWhisky) {
                if (cartWhisky[i].name === name) {//"3" == 3 true, "3"===3 false - because "3" is a string
                    cartWhisky[i].quantity -= 1;
                    if (cartWhisky[i].quantity === 0) {
                        //Метод splice() изменяет содержимое массива, удаляя существующие элементы и/или добавляя новые.
                        cartWhisky.splice(i, 1);
                    }
                    break;
                }
            }
            saveCart();
            displayShow();
        }

//addItemsToCart
        function addOneItemsToCart(name) {
            for (var i in cartWhisky) {
                if (cartWhisky[i].name === name) {
                    cartWhisky[i].quantity++;
                    if (cartWhisky[i].quantity === 0) {
                        cartWhisky.splice(i, 1);
                    }
                    break;
                }
            }
            saveCart();
            displayShow();
        }

//RemoveAllItemsFromCart()  //Remove all
        function removeAllItemsFromCart(name) {
            for (var i in cartWhisky) {
                if (cartWhisky[i].name === name) {
                    cartWhisky.splice(i, 1);
                    break;
                }
            }
            saveCart();
            displayShow();
        }

//countCart() -->return total count Summary count all item in cart
        function countCart() {
            var totalCount = 0;
            for (var i in cartWhisky) {
                totalCount += cartWhisky[i].quantity;
            }
            return totalCount;
        }

//totalCart() --> return total cost Summary value price all item in cart
        function totalPriceInCart() {

            var totalPrice = 0;
            for (var i in cartWhisky) {
                totalPrice = cartWhisky[i].price * cartWhisky[i].quantity + totalPrice;
            }
            //toFixed() - кол-во знаков после запятой
            return totalPrice.toFixed(2);
        }

    });
//clearCart()
    function clearCart() {
        cartWhisky = [];
        saveCart();
    }

//SaveCart()
    function saveCart() {
        //JSON.parse – читает объекты из строки в формате JSON.
        //JSON.stringify – превращает объекты в строку в формате JSON, используется, когда нужно из JavaScript передать данные по сети.
        localStorage.setItem("shoppingCart", JSON.stringify(cartWhisky));
    }

    function sendToJavaBuy() {

        cartWhisky = JSON.parse(localStorage.getItem("shoppingCart"));
        var newArray = [];
        var i = 0;
        for (i in cartWhisky) {
            newArray[i] =
                cartWhisky[i].name + "_" + cartWhisky[i].quantity + "_" + cartWhisky[i].photo + "_"
                + cartWhisky[i].price;
            i++;
        }
        $.ajax({
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            type: "POST",
            url: '/buySuccessfulWhisky',
            data: JSON.stringify(newArray),

            success: function (msg) {
                clearCart(); //clear Local Storage
                window.location.href = "warehouseWhisky";
                //console.log(data)
            }
        });
    }

