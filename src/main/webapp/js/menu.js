$(document).ready(function (){
    $('.menu-item-button').click(function (){
        let paramsList = this.parentElement.getElementsByTagName('input')
        let command = paramsList[0].value
        let dish_id = paramsList[1].value
        let data = {command:command, dish_id:dish_id};
        $.ajax({
            url:'controller',
            type: 'POST',
            data: data,
            success: function(response) {
                $('.header-items-count').html(response)
            }
        });
    });
});
