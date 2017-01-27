function showSelectedSearch(){

    var price = $("#searchPrice").val();
    console.log(price);

    var selectedColor = [];
    var i = 0;
    var arrayCheckboxChecked = $('input:checked');
    arrayCheckboxChecked.each(function(){
        var nextChecked = $(this);
        selectedColor[i] =$(this).val();
        alert($(this).val());
        i++;
    });
    console.log(selectedColor);
}
