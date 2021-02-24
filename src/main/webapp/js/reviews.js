$(document).ready(function (){
    $('.give-feedback-button').click(function (){
        let feedback = document.getElementById('feedback')
        feedback.style.display = 'block';
    });
    $('.feedback-close').click(function (){
        let feedback = document.getElementById('feedback')
        feedback.style.display = 'none';
    });
});