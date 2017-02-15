
$(document).ready(function() {

    function showSelectedElement(arr){
        console.log("start function showSelectedElement"+arr);
        $('.color').hide();
        arr.forEach(function(e){
            console.log(" arr.forEach(function(e) ."+e.toString());
            $("."+e.toString()).show();
        });
    }
    var arrayPrice = [];
    var arrayMax = [];
    var maxPrice;
    var minPrice;
    var color = [];
    var paint = [];
    var structure = [];
    var price = [];

    $("div[class='searchColor'] input").change(function () {

        arrayMax = $("[class^=priceSSS]");
        for (i=0; i<arrayMax.length; i++){
            arrayPrice[i] =arrayMax[i].innerHTML;
        }
        //console.log("Price: "+arrayPrice);
        maxPrice = Math.max.apply(null, arrayPrice);
        minPrice =  Math.min.apply(null, arrayPrice);


        if($("#filterColor input:checked").length == 0 && $("#filterPrice input:checked").length == 0 &&
            $("#searchPaint input:checked").length == 0
            && $("#filterStructure input:checked").length == 0
        ){
            $('.color').show();
        }else{
            var color = ["Белый","Бежевый","Кремовый","Золотой", "Желтый","Оранжевый","Персиковый","Терракотовый",
                "Кофейный", "Коричневый","Розовый","Красный", "Бордовый","Салатовый","Зеленый",
                "Голубой", "Синий", "Фиолетовый", "Серый", "Черный"];
            var paint = ["Абстрактный","Геометрический","Классический", "Однотонный","Полоска","Растительный"];
            var structure = ["Лен","Нити","Двухслойная", "Жатая","Жаккард","Сатин", "Тафта", "Двухсторонняя","Бархат",
                "Покрывальная"];
            var price = [];

            if($("#filterColor input:not(:checked)").length > 0){
                $("#filterColor input:not(:checked)").each(function(){
                    //удаляем 1 элемент, splice служит для добавления/удаления элем из массива
                    //in this code we remove 1 elements. Splice(obj,1)
                    color.splice(color.indexOf($(this).attr('value')),1);
                })
            }
            console.log("color: "+color);

            if($("#searchPaint input:not(:checked)").length > 0){
                $("#searchPaint input:not(:checked)").each(function(){
                    paint.splice(paint.indexOf($(this).attr('value')),1);
                })
            }
            console.log("paint: "+paint);

            if($("#filterPrice input:checked").length > 0){
                var arr = [];
                $("#filterPrice input:checked").each(function(){
                    var s = $(this).attr('value'); //the same $(this).val();
                    for (i=0; i<arrayMax.length; i++) {
                    if(s == "1"){
                        if (arrayPrice[i] > 0 && arrayPrice[i] <= 800) {
                            //concat - copy value from selected array, now we copy in arr all info from arrayPrice[i]
                            //which to be in the selected range
                         arr = arr.concat((arrayPrice[i]));
                        }
                    }else if(s == "2") {
                        if (arrayPrice[i] > 800 && arrayPrice[i] <= 1600) {
                            arr = arr.concat(arrayPrice[i]);
                        }
                        } else {
                            if (arrayPrice[i] > 1600) {
                                arr = arr.concat(arrayPrice[i]);
                            }
                        }
                    }
                    });
                console.log("Before Arr.forEach: "+arr);
                arr.forEach(function(ele){
                    if(price.indexOf(ele) == -1)
                        price.push(ele);
                });
            }
            console.log("number: "+price);

            if(color.length == 0 && paint.length == 0 && price.length > 0){
                $('.color').hide();
                price.forEach(function(e){

                    //console.log(e.toString().split(".").join("-"),$("."+e.toString().split(".").join("-")))
                    $("."+e.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1" )).show();
                });
                //showSelectedElement(price)
            }else if(color.length == 0 && paint.length > 0 && price.length == 0){

                showSelectedElement(paint)
            }else if(color.length > 0 && paint.length == 0 && price.length == 0){

                showSelectedElement(color)
            }else if(color.length > 0 && paint.length > 0 && price.length == 0){
                var temp = [];
                color.forEach(function(oe){
                    paint.forEach(function(ie){
                        temp.push(oe +"."+ie);
                    });
                });
                showSelectedElement(temp);
            }else if(color.length == 0 && paint.length > 0 && price.length > 0){
                var temp = [];
                paint.forEach(function(oe){
                    price.forEach(function(ie){
                        temp.push(oe +"."+ie.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1" ));
                    });
                });
                showSelectedElement(temp);
            }else if(color.length > 0 && paint.length == 0 && price.length > 0){
                var temp = [];
                color.forEach(function(oe){
                    price.forEach(function(ie){
                        temp.push(oe +"."+ie.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1" ));
                    });
                });
                showSelectedElement(temp);
            }else{
                var temp = [];
                color.forEach(function(oe){
                    price.forEach(function(ie){
                        paint.forEach(function(iie){
                            temp.push(oe +"."+ie.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1" ) + "." + iie);
                        });
                    });
                });
                showSelectedElement(temp);
            }
        }
    });
});


//$(document).ready(function() {
//    var arrayNumber = [];
//    var arrayMax = [];
//
//    $("div[class='searchSelectedNumber'] input").change(function () {
//        arrayMax = $("[class^=number]");
//        for (i=0; i<arrayMax.length; i++){
//            arrayNumber[i] = arrayMax[i].innerHTML;
//        }
//        var firstIntervalStart = 0;
//        var firstIntervalEnd = 0;
//        var secondIntervalStart = 0;
//        var secondIntervalEnd = 0;
//        if (  $("#searchNumber input:checked").length == 0){
//            $('.color').show();
//        } else {
//            var oneChecked = $("#number01").is(':checked');
//            var twoChecked = $("#number02").is(':checked');
//
//            if (oneChecked) {
//                firstIntervalStart = 0;
//                firstIntervalEnd = 30;
//                if (twoChecked) {
//                    firstIntervalEnd = 40;
//                }
//            }
//            else if (twoChecked) {
//                firstIntervalStart = 20;
//                firstIntervalEnd = 40;
//            }
//            for (i = 0; i < arrayMax.length; i++) {
//                if (arrayNumber[i] > firstIntervalStart && arrayNumber[i] <= firstIntervalEnd) {
//                    $($(".color")[i]).show();
//                } else {
//                    $($(".color")[i]).hide();
//                }
//            }
//            if (secondIntervalStart != 0) {
//                for (i = 0; i < arrayMax.length; i++) {
//                    if (arrayNumber[i] > secondIntervalStart && arrayNumber[i] <= secondIntervalEnd) {
//                        console.log("secondIntervalStart < arrayPrice[i] <= secondIntervalEnd " + $($(".color")[i]));
//                        $($(".color")[i]).show();
//                    } else {
//                        $($(".color")[i]).hide();
//                    }
//                }
//            }
//        }
//    });
//    $("div[class='searchColor'] input").change(function () {
//
//        if($("#filterColor input:checked").length == 0 && $("#searchShape input:checked").length == 0
//          ){
//            $('.color').show();
//        }else if($("#filterColor input:checked").length == 0 && $("#searchShape input:checked").length > 0){
//            $('.color').show();
//            $("#searchShape input:not(:checked)").each(function() {
//                var k = $(this).val();
//                $('.' + k).hide();
//            });
//        }else if($("#filterColor input:checked").length > 0 && $("#searchShape input:checked").length == 0){
//            $('.color').show();
//            $("#filterColor input:not(:checked)").each(function() {
//
//                $('.' + $(this).attr('value')).hide();
//            });
//        }else{
//            $('.color').show();
//
//            $("#searchShape input:not(:checked)").each(function() {
//                $('.' + $(this).attr('value')).hide();
//            });
//
//            $("#filterColor input:not(:checked)").each(function() {
//                $('.' + $(this).attr('value')).hide();
//            });
//        }
//    });
//});
