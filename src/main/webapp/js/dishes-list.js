$(document).ready(function (){
    $('.admin-add').click(function (){
        let addDish = document.getElementById('add-dish')
        addDish.style.display = 'block';
    });
    $('.edit-close').click(function (){
        let addDish = document.getElementById('add-dish')
        addDish.style.display = 'none';
    });
    $('.admin-delete').on('click', function (){
        let paramsList = this.parentElement.getElementsByTagName('input')
        let row = this.parentElement.parentElement
        let command = paramsList[0].value
        let dish_id = paramsList[1].value
        let data = {command:command, dish_id:dish_id}
        $.ajax({
            url:'controller',
            type: 'POST',
            data: data,
            success: function() {
                row.remove()
            }
        });
    });
});