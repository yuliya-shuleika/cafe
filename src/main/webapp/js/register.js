function validateRegisterForm(form){
    let name = document.getElementById('register-user-name')
    let email = document.getElementById('register-user-email')
    let password = document.getElementById('register-user-password')
    let passwordRepeat = document.getElementById('repeat_password')
    let error = document.getElementById('register-error-label')
    let isValid = validateRegisterFields(name, email, password, passwordRepeat)
    if (!isValid) {
        error.innerHTML = '${fill_fields_correct}'
    } else {
        form.submit()
    }
}

function validateLoginForm(form){
    let email = document.getElementById('login-user-email')
    let password = document.getElementById('login-user-password')
    let error = document.getElementById('login-user-error-message')
    let isValid = validateLoginFields(email, password)
    if (!isValid) {
        error.innerHTML = '${fill_fields_correct}'
    } else {
        form.submit()
    }
}

function validateLoginFields(email, password){
    let isValid = true
    if (!validateEmail(email)){
        isValid = false
    }
    if (!validatePassword(password)){
        isValid = false
    }
    return isValid
}

function validateRegisterFields(name, email, password, repeatPassword){
    let isValid = true
    if (!validateUserName(name)){
        isValid = false
    }
    if (!validateEmail(email)){
        isValid = false
    }
    if (!validatePassword(password)){
        isValid = false
    }
    if (!validatePassword(repeatPassword)){
        isValid = false
    }
    return isValid
}

function validateUserName(name){
    let isValid
    const regExp = /^[A-Za-zА-Яа-яёЁ]{3,25}$/
    if(regExp.test(name.value)){
        isValid = true
    } else {
        name.value = ""
        isValid = false
    }
    return isValid
}

function validateEmail(email){
    let isValid
    const regExp = /^[A-Za-z0-9_.]{2,22}@[a-z]{2,10}\.[a-z]{2,6}$/
    if(regExp.test(email.value)){
        isValid = true
    } else {
        email.value = ""
        isValid = false
    }
    return isValid
}

function validatePassword(password){
    let isValid
    const regExp = /^[A-Za-z0-9_]{5,20}$/
    if(regExp.test(password.value)){
        isValid = true
    } else {
        password.value = ""
        isValid = false
    }
    return isValid
}
