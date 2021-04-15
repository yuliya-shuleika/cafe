$(document).ready(function (){
    $('.admin-add').click(function (){
        let addPromo = document.getElementById('add-promo')
        addPromo.style.display = 'block';
    });
    $('.edit-close').click(function (){
        let addPromo = document.getElementById('add-promo')
        addPromo.style.display = 'none';
        let editPromo = document.getElementById('edit-promo')
        editPromo.style.display = 'none';
    });
    $('.admin-delete').on('click', function (){
        let paramsList = this.parentElement.getElementsByTagName('input')
        let row = this.parentElement.parentElement
        let command = paramsList[0].value
        let promo_code_id = paramsList[1].value
        let data = {command:command, promo_code_id:promo_code_id}
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