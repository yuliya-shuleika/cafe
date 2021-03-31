$(document).ready(function (){
    $('.admin-add').click(function (){
        let feedback = document.getElementById('add-promo')
        feedback.style.display = 'block';
    });
    $('.edit-close').click(function (){
        let feedback = document.getElementById('add-promo')
        feedback.style.display = 'none';
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