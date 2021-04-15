$(document).ready(function (){
    const selectElement = document.querySelector('#review-status-select');
    selectElement.addEventListener('change', (event) => {
        let command = 'update_review_status'
        let status = selectElement.value
        console.log(selectElement.value)
        let tableRow = selectElement.parentElement.parentElement
        let review_id = tableRow.getElementsByTagName("input")[0].value
        console.log(review_id)
        let data = {command:command, review_status:status, review_id:review_id}
        $.ajax({
            url:'controller',
            type: 'POST',
            data: data,
        });
    });
});