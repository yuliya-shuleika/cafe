$(document).ready(function (){
    $('#repeat_password').addEventListener('change', (event) => {
        let parent = $('#repeat_password').parentElement
        let password = parent.getElementsByTagName('input[password]')
    });
});

function validateRegisterForm(){
    let isValid = true
    let name = document.getElementsByName('user_name')[0].value
    let email = document.getElementsByName('user_email')[0].value
    let password = document.getElementsByName('user_password')[0].value
    let error = document.getElementsByClassName('edit-error-message')[0]
    if (!validateName(name) || !validateEmail(email) ||
        !validatePassword(password)) {
        isValid = false
        error.innerHTML = '${fill_fields_correct}'
    }
    return isValid
}

function validateLoginForm(){
    let isValid = true
    let email = document.getElementsByName('user_email')[0].value
    let password = document.getElementsByName('user_password')[0].value
    let error = document.getElementsByClassName('edit-error-message')[0]
    if (!validateEmail(email) || !validatePassword(password)) {
        isValid = false
        error.innerHTML = '${fill_fields_correct}'
    }
    return isValid
}

function validateName(name){
    const regExp = /^[A-Za-zА-Яа-яёЁ]{3,25}$/;
    return regExp.test(name);
}

function validateEmail(email){
    const regExp = /^[A-Za-z0-9_.]{2,22}@[a-z]{2,10}\.[a-z]{2,6}$/;
    return regExp.test(email);
}

function validatePassword(password){
    const regExp = /^[A-Za-z0-9_]{5,20}$/;
    return regExp.test(password);
}
