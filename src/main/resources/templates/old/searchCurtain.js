$(document).ready(function() {
    var arrayPrice = [];
    var arrayMax = [];
    var maxPrice;
    var minPrice;
   //$("div[id='filterPrice'] input").change(function(){

   //});
    $("div[class='searchColor'] input").change(function () {
        arrayMax = $("[class^=priceSSS]");
        for (i=0; i<arrayMax.length; i++){
            arrayPrice[i] = arrayMax[i].innerHTML;
        }
        console.log(arrayPrice)
        maxPrice = Math.max.apply(null, arrayPrice);
        minPrice =  Math.min.apply(null, arrayPrice);

        if($("#filterColor input:checked").length == 0 && $("#searchPaint input:checked").length == 0 &&
                 $("#filterPrice input:checked").length == 0  && $("#filterStructure input:checked").length == 0
            && $("#filterHeight input:checked").length == 0){

            $('.color').show();

        }else if($("#filterColor input:checked").length == 0 && $("#searchPaint input:checked").length > 0 &&
                $("#filterPrice input:checked").length == 0 && $("#filterStructure input:checked").length == 0
            && $("#filterHeight input:checked").length == 0){

            $('.color').show();
            $("#searchPaint input:not(:checked)").each(function() {
                $('.' + $(this).attr('value')).hide();
            });
        }else if($("#filterColor input:checked").length > 0 && $("#searchPaint input:checked").length == 0 &&
            $("#filterPrice input:checked").length == 0 && $("#filterStructure input:checked").length == 0
            && $("#filterHeight input:checked").length == 0){

            $('.color').show();
            selectedMoreThreeCheckboxColor();
            $("#filterColor input:not(:checked)").each(function() {
                //console.log(this,$(this).attr('value'),$('.'+$(this).attr('value')))
                $('.' + $(this).attr('value')).hide();
            });
        }else if($("#filterColor input:checked").length == 0 && $("#searchPaint input:checked").length == 0 &&
            $("#filterPrice input:checked").length == 0 && $("#filterStructure input:checked").length > 0
            && $("#filterHeight input:checked").length == 0){
            $('.color').show();

            $("#filterStructure input:not(:checked)").each(function() {
                var selectedStr = $(this).val();
                $('.' + selectedStr).hide();
            });}
        else if($("#filterColor input:checked").length == 0 && $("#searchPaint input:checked").length == 0 &&
            $("#filterPrice input:checked").length == 0 && $("#filterStructure input:checked").length == 0
            && $("#filterHeight input:checked").length > 0){

            $('.height').show();

            $("#filterHeight input:not(:checked)").each(function() {
                var selectedStr = $(this).val();
                $('.' + selectedStr.text).hide();
            });
        }else if($("#filterColor input:checked").length == 0 && $("#searchPaint input:checked").length == 0
                && $("#filterPrice input:checked").length > 0  && $("#filterStructure input:checked").length == 0
            && $("#filterHeight input:checked").length == 0){

            $('.color').show();
            price();

        }else{
            selectedMoreThreeCheckboxColor();

            if( $("#filterPrice input:checked").length > 0){
                $('.color').show();
            } else {$('.color').show();}

            $("#searchPaint input:not(:checked)").each(function() {
                if ($("#searchPaint input:checked").length == 0){

                } else {
                    checkOnIf();
                    var notCheckedPaint = $(this).val();
                    //$(this).attr('value')
                    $('.' + notCheckedPaint).hide();
                }
            });

            $("#filterColor input:not(:checked)").each(function() {
                if ($("#filterColor input:checked").length == 0){

                } else {
                    checkOnIf();
                    var notCheckedColor = $(this).val();
                    //console.log(this,$(this).attr('value'),$('.'+$(this).attr('value')))

                    $('.' + notCheckedColor).hide();
                }
            });
            $("#filterHeight input:not(:checked)").each(function() {
                if ($("#filterHeight input:checked").length == 0){

                } else {
                    checkOnIf();
                    var notCheckedColor = $(this).val();
                    //console.log(this,$(this).attr('value'),$('.'+$(this).attr('value')))

                    $('.height' + notCheckedColor).hide();
                }
            });

            $("#filterStructure input:not(:checked)").each(function() {
                if ($("#filterStructure input:checked").length == 0){

                } else {
                    checkOnIf();
                    var notCheckedColor = $(this).val();

                    $('.' + notCheckedColor).hide();
                }
            });
        }
    });
    //IF MORE 3 CHECKBOX-CHECKED DOING DISABLE OTHER CHECKBOX
    function selectedMoreThreeCheckboxColor(){
            if($("#filterColor input[type=checkbox]:checked").length >= 3 ){
                $('#filterColor input[type=checkbox]:not(:checked)').attr('disabled', "disabled");
            } else{
                $('#filterColor input[type=checkbox]:disabled').removeAttr('disabled');
            }
    }

    function price() {
        var that = $(this);
        var firstIntervalStart = 0;
        var firstIntervalEnd = 0;
        var secondIntervalStart = 0;
        var secondIntervalEnd = 0;

        var oneChecked = $("#price01").is(':checked');
        var twoChecked = $("#price02").is(':checked');
        var threeChecked = $("#price03").is(':checked');

        if (oneChecked) {
            firstIntervalStart = 0;
            firstIntervalEnd = 800;
            if (twoChecked) {
                firstIntervalEnd = 1600;
                if (threeChecked) {
                    firstIntervalEnd = maxPrice;
                }
            }
            else if (threeChecked) {
                secondIntervalStart = 1600;
                secondIntervalEnd = maxPrice;
            }
        }
        else if (twoChecked) {
            firstIntervalStart = 800;
            firstIntervalEnd = 1600;
            if (threeChecked) {
                firstIntervalEnd = maxPrice;
            }
        }
        else if (threeChecked) {
            firstIntervalStart = 1600;
            firstIntervalEnd = maxPrice;
        }

            for (i=0; i<arrayMax.length; i++) {
                if (arrayPrice[i] > firstIntervalStart && arrayPrice[i] <= firstIntervalEnd) {
                    $($(".color")[i]).show();
                } else { $($(".color")[i]).hide();}
            }
        if (secondIntervalStart != 0) {
            for (i = 0; i < arrayMax.length; i++) {
                if (arrayPrice[i] > secondIntervalStart && arrayPrice[i] <= secondIntervalEnd) {
                    console.log("secondIntervalStart < arrayPrice[i] <= secondIntervalEnd " + $($(".color")[i]));
                    $($(".color")[i]).show();
                } else { $($(".color")[i]).hide();}
            }
        }
    }
    function checkOnIf(){
        if( $("#filterPrice input:checked").length > 0){
            price();
        }
    }
});