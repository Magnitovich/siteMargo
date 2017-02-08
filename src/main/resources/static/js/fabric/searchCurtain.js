$(document).ready(function() {
    var arrayPrice = [];
    var arrayMax = [];
    var maxPrice;
    var minPrice;
   $("div[id='filterPrice'] input").change(function(){


       arrayMax = $("[class^=priceSSS]");
       for (i=0; i<arrayMax.length; i++){
           arrayPrice[i] = arrayMax[i].innerHTML;
       }
       console.log(arrayPrice)
       maxPrice = Math.max.apply(null, arrayPrice);
       minPrice =  Math.min.apply(null, arrayPrice);

   });
    $("div[class='searchColor'] input").change(function () {


        if($("#filterColor input:checked").length == 0 && $("#searchPaint input:checked").length == 0 &&
                 $("#filterPrice input:checked").length == 0 ){

            $('.color').show();

        }else if($("#filterColor input:checked").length == 0 && $("#searchPaint input:checked").length > 0 &&
                         $("#filterPrice input:checked").length == 0 ){

            $('.color').show();
            $("#searchPaint input:not(:checked)").each(function() {
                $('.' + $(this).attr('value')).hide();
            });
        }else if($("#filterColor input:checked").length > 0 && $("#searchPaint input:checked").length == 0 &&
                        $("#filterPrice input:checked").length == 0 ){

            $('.color').show();
            selectedMoreThreeCheckboxColor();
            $("#filterColor input:not(:checked)").each(function() {
                //console.log(this,$(this).attr('value'),$('.'+$(this).attr('value')))
                $('.' + $(this).attr('value')).hide();
            });
        }else if($("#filterColor input:checked").length == 0 && $("#searchPaint input:checked").length == 0 &&
            $("#filterPrice input:checked").length > 0) {

            $('.color').show();
            //switch ()
            //$("#filterPrice input:not(:checked)").each(function() {
            //    //console.log(this,$(this).attr('value'),$('.'+$(this).attr('value')))
            //    $('.' + $(this).attr('value')).hide();

            console.log("max: "+ maxPrice);
            console.log("min: "+ minPrice);
            //});
        price();

        }else{
            selectedMoreThreeCheckboxColor();
            $('.color').show();

            $("#searchPaint input:not(:checked)").each(function() {
                var notCheckedPaint = $(this).val();
                $('.' + notCheckedPaint).hide();
            });

            $("#filterColor input:not(:checked)").each(function() {
                //console.log(this,$(this).attr('value'),$('.'+$(this).attr('value')))
                $('.' + $(this).attr('value')).hide();
            });

        }
    });
    function selectedMoreThreeCheckboxColor(){
            if($("#filterColor input[type=checkbox]:checked").length >= 3 ){
                $('#filterColor input[type=checkbox]:not(:checked)').attr('disabled', "disabled");
            } else{
                $('#filterColor input[type=checkbox]:disabled').removeAttr('disabled');
            }
    }

    function price() {
        var that = this;
        //var firstIntervalStart = 0;
        //var firstIntervalEnd = 0;
        //var secondIntervalStart = 0;
        //var secondIntervalEnd = 0;

        hideAllSpans();

        var oneChecked = $("#price01").is(':checked');
        var twoChecked = $("#price02").is(':checked');
        var threeChecked = $("#price03").is(':checked');

        //if (oneChecked) {
        //    firstIntervalStart = minPrice;
        //    firstIntervalEnd = 800;
        //    if (twoChecked) {
        //        firstIntervalEnd = 1600;
        //        if (threeChecked) {
        //            firstIntervalEnd = maxPrice;
        //        }
        //    }
        //    else if (threeChecked) {
        //        secondIntervalStart = 1600;
        //        secondIntervalEnd = maxPrice;
        //    }
        //}
        //else if (twoChecked) {
        //    firstIntervalStart = 800;
        //    firstIntervalEnd = 1600;
        //    if (threeChecked) {
        //        firstIntervalEnd = maxPrice;
        //    }
        //}
        //else if (threeChecked) {
        //    firstIntervalStart = 1600;
        //    firstIntervalEnd = maxPrice;
        //}
        //
        //for (var i = firstIntervalStart; i < firstIntervalEnd; i++) {
        //    $($(".priceSSS")[i]).show();
        //}
        if (oneChecked){
            for (i=0; i<arrayMax.length; i++){

            }
        }

        if (secondIntervalStart != 0) {
            for (var i = secondIntervalStart; i < secondIntervalEnd; i++) {
                $($(".priceSSS")[i]).show();
            }
        }


        function hideAllSpans() {
            $(".priceSSS").hide();
        }
    }
});