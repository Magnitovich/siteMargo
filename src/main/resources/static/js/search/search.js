
function toSelectedObject(){

    $('.pic a').on('click', function (e) {
        var photo = $(this).find('input[name=photoId]').val();
        var id = $(this).find('input[name=id]').val();
        alert("ID: "+id + "  photo: "+photo);
    })

}
