$(document).ready(function (){
    $('.admin-delete').on('click', function (){
        let paramsList = this.parentElement.getElementsByTagName('input')
        let row = this.parentElement.parentElement
        let command = paramsList[0].value
        let user_id = paramsList[1].value
        let data = {command:command, user_id:user_id}
        $.ajax({
            url:'controller',
            type: 'POST',
            data: data,
            success: function() {
                console.log(row)
                console.log(command)
                console.log(user_id)
            }
        });
    });
});