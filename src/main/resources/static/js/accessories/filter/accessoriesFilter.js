$(document).ready(function() {

    function showSelectedElement(arr) {
        $('.color').hide();
        arr.forEach(function (e) {
            $("." + e.toString()).show();
        });
    }

    var firstValue = 0;
    var secondValue = 0;
    var arrayPrice = [];
    var arrayMax = [];
    var maxPrice;
    var minPrice;

    $("div[class='searchColor'] input").change(function () {
        firstValue = parseInt($('#filterPriceFirst').text());
        secondValue = parseInt($("#filterPriceSecond").text());
        //secondValue = $("[class=filterPriceSecond]").val();
        //console.log("firstValue "+firstValue+"secondValue "+secondValue);
        arrayMax = $("[class=priceSSS]");
        //console.log("arrayMax "+arrayMax.length);

        for (i = 0; i < arrayMax.length; i++) {
            arrayPrice[i] = arrayMax[i].innerHTML;
        }
        maxPrice = Math.max.apply(null, arrayPrice);
        minPrice = Math.min.apply(null, arrayPrice);

        //console.log("Max price: "+maxPrice+" Min price: "+minPrice);

        if ($("#filterColor input:checked").length == 0 && $("#filterPrice input:checked").length == 0) {
            $('.color').show();
        } else {
            var price = [];
            var color = [];

            if ($("#filterColor input:checked").length > 0) {
                $("#filterColor input:checked").each(function () {
                    color.push($(this).attr('value'));
                })
            }
            if ($("#filterPrice input:checked").length > 0) {
                var arr = [];
                $("#filterPrice input:checked").each(function () {
                    var s = $(this).attr('value'); //the same $(this).val();
                    for (i = 0; i < arrayMax.length; i++) {
                        if (s == "1") {
                            if (arrayPrice[i] > 0 && arrayPrice[i] <= firstValue) {
                                //concat - copy value from selected array, now we copy in arr all info from arrayPrice[i]
                                //which to be in the selected range
                                arr = arr.concat((arrayPrice[i]));
                            }
                        } else if (s == "2") {
                            if (arrayPrice[i] > firstValue && arrayPrice[i] <= secondValue) {
                                arr = arr.concat(arrayPrice[i]);
                            }
                        } else {
                            if (arrayPrice[i] > secondValue) {
                                arr = arr.concat(arrayPrice[i]);
                            }
                        }
                    }
                });
                console.log(arr);
                if (arr.length == 0){
                    $('.color').hide();
                } else {
                    arr.forEach(function (ele) {
                        //"Привет, мир".indexOf("Привет")    // вернет 0
                        //"Привет, мир".indexOf("Корова")    // вернет -1
                        //"Привет, мир".indexOf("мир")    // вернет 8
                        if (price.indexOf(ele) == -1)
                            price.push(ele);
                    });
                }
            }
            //console.log("number: "+price);

            if (color.length == 0 && price.length > 0) {
                $('.color').hide();
                price.forEach(function (e) {
                    $("." + e.replace(/(:|\.|\[|\]|,|=|@)/g, "\\$1")).show();
                });
            } else if (color.length > 0 && price.length == 0) {
                showSelectedElement(color)

            } else if (color.length > 0 && price.length > 0) {
                var temp = [];
                color.forEach(function (oe) {
                    price.forEach(function (ie) {
                        temp.push(oe + "." + ie.replace(/(:|\.|\[|\]|,|=|@)/g, "\\$1"));
                    });
                });
                showSelectedElement(temp);
            }
        }
    });
});