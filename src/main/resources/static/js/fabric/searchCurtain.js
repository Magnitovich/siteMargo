function showSelectedSearch(){

    var price = $("#searchPrice").val();
    console.log(price);
    var position = [];
    var selectedColor = [];
    var i = 0;
    var arrayCheckboxChecked = $('#colorDiv input:checked');
    arrayCheckboxChecked.each(function(){
        var nextChecked = $(this).val();
        selectedColor[i] =$(this).val();
        position[i] = document.documentElement.innerHTML.indexOf(nextChecked);
        alert(position);
        i++;
    });
    console.log(selectedColor);

    var selectedPaint = [];
    var q = 0;
    $('#searchPaint input:checked').each(function(){

        selectedPaint[i] =$(this).val();
        alert($(this).val());
        q++;
    });
    console.log(selectedPaint);

}
