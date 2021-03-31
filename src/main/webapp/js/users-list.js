$(document).ready(function (){
    $('.admin-delete').on('click', function (){
        let paramsList = this.parentElement.getElementsByTagName('input')
        let status = this.parentElement.parentElement.getElementsByClassName('user-status')[0]
        let blockButton = this
        let command = paramsList[0].value
        let user_id = paramsList[1].value
        let data = {command:command, user_id:user_id}
        $.ajax({
            url:'controller',
            type: 'POST',
            data: data,
            success: function() {
                status.innerHTML ='BLOCKED'
                this.innerHTML = "${unblock}"
            }
        });
    });
});