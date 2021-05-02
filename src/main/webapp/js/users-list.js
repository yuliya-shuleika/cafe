const userRole = 0
const adminRole = 1
$(document).ready(function (){
    let selects = document.getElementsByClassName('admin-select')
    for (let i = 0; i < selects.length; i++) {
        let status = selects[i].parentElement.parentElement.getElementsByTagName('input')[0].value
        setSelect(selects[i], status)
    }
    $('.admin-delete').on('click', function (){
        let user_id = this.parentElement.getElementsByTagName('input')[0].value
        let status = this.parentElement.parentElement.getElementsByClassName('user-status')[0]
        let blockButton = this
        let command
        switch (status.innerHTML){
            case "BLOCKED":
                command = 'unblock_user'
                break
            case "OFFLINE":
            case "ONLINE":
                command = 'block_user'
                break
        }
        let data = {command:command, user_id:user_id}
        $.ajax({
            url: 'controller',
            type: 'POST',
            data: data,
            success: function () {
                if (command === 'block_user') {
                    status.innerHTML = 'BLOCKED'
                    blockButton.innerHTML = '${unblock}'
                } else {
                    status.innerHTML = 'OFFLINE'
                    blockButton.innerHTML = '${block}'
                }
            }
        });

    });
    $('.admin-select').on('change', function () {
        let select = this
        let command = 'change_user_role'
        let tableRow = select.parentElement.parentElement
        let user_id = tableRow.getElementsByTagName('input')[1].value
        let user_role = getRole(select.selectedIndex)
        let data = {command: command, user_role: user_role, user_id: user_id}
        $.ajax({
            url: 'controller',
            type: 'POST',
            data: data,
            success: function () {
                setSelect(select, status)
            }
        })
    });
    function setSelect(select, status){
        switch (status){
            case 'USER':
                select[userRole].selected = true
                break
            case 'ADMIN':
                select[adminRole].selected = true
        }
    }
    function getRole(selected){
        let result = ''
        switch (selected){
            case userRole:
                result = 'USER'
                break
            case adminRole:
                result = 'ADMIN'
        }
        return result
    }
});

function validateUserSearch(form){
    let emailPart = document.getElementById('search_user')
    const regExp = /^.{1,30}$/
    if(regExp.test(emailPart.value)){
        form.submit()
    } else {
        emailPart.value = ""
    }
}