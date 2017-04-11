$(document).ready(function() {
    changePageAndSize();
});

function changePageAndSize() {
    $('#pageSizeSelect').change(function(evt) {
        window.location.replace("/administrationNotSleeps?pageSize=" + this.value + "&page=1");
    });
}