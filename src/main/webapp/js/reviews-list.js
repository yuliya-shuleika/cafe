const statusNew = 0
const statusApproved = 1
const statusRejected = 2
$(document).ready(function (){
    let selects = document.getElementsByClassName('admin-select')
    for (let i = 0; i < selects.length; i++) {
        let status = selects[i].parentElement.parentElement.getElementsByTagName('input')[1].value
        console.log(status)
        setSelect(selects[i], status)
    }
    $('.admin-select').on('change', function () {
        let select = this
        let command = 'update_review_status'
        let tableRow = select.parentElement.parentElement
        let review_id = tableRow.getElementsByTagName('input')[0].value
        let status = getStatus(select.selectedIndex)
        let data = {command:command, review_status:status, review_id:review_id}
        $.ajax({
            url:'controller',
            type: 'POST',
            data: data,
            success: function () {
                setSelect(select, status)
            }
        });
    });
    $('.admin-delete').on('click', function () {
        let row = this.parentElement.parentElement
        let command = 'delete_review'
        let review_id = row.getElementsByTagName('input')[0].value
        let data = {command:command, review_id:review_id}
        $.ajax({
            url:'controller',
            type: 'POST',
            data: data,
            success: function () {
                row.remove()
            }
        });
    });
    $('.edit-close').on('click', function () {
        let info = document.getElementById('review-info')
        info.style.display = 'none'
    });
    function setSelect(select, status){
        switch (status){
            case 'NEW':
                select[statusNew].selected = true
                break
            case 'APPROVED':
                select[statusApproved].selected = true
                break
            case 'REJECTED':
                select[statusRejected].selected = true
        }
    }
    function getStatus(selected){
        let result = ''
        switch (selected){
            case statusNew:
                result = 'NEW'
                break
            case statusApproved:
                result = 'APPROVED'
                break
            case statusRejected:
                result = 'REJECTED'
        }
        return result
    }
});

function validateReviewSearch(form){
    let reviewPart = document.getElementById('search-review')
    const regExp = /^.{1,30}$/
    if(regExp.test(reviewPart.value)){
        form.submit()
    } else {
        reviewPart.value = ""
    }
}