$(document).ready(function (){
    $('#repeat_password').addEventListener('change', (event) => {
        let parent = $('#repeat_password').parentElement
        let password = parent.getElementsByTagName('input[password]')
    });
});