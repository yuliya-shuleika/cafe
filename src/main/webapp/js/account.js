$(document).ready(function () {
    let myOrders = document.getElementById('my-orders')
    let favorites = document.getElementById('favorites')
    let extra = document.getElementById('extra')
    let accountExtra = document.getElementById('account-extra')
    let accountFavorites = document.getElementById('account-favorites')
    let accountOrders = document.getElementById('account-orders')
    $('#my-orders').on('click', function () {
        accountExtra.style.display = 'none'
        accountFavorites.style.display = 'none'
        accountOrders.style.display = 'flex'
        myOrders.classList.add('is-active')
        if(favorites.classList.contains('is-active')) {
            favorites.classList.remove('is-active')
        } else {
            extra.classList.remove('is-active')
        }
    });
    $('#favorites').on('click', function () {
        accountExtra.style.display = 'none'
        accountOrders.style.display = 'none'
        accountFavorites.style.display = 'flex'
        favorites.classList.add('is-active')
        if(myOrders.classList.contains('is-active')) {
            myOrders.classList.remove('is-active')
        } else {
            extra.classList.remove('is-active')
        }
    });
    $('#extra').on('click', function () {
        accountFavorites.style.display = 'none'
        accountOrders.style.display = 'none'
        accountExtra.style.display = 'flex'
        extra.classList.add('is-active')
        if(myOrders.classList.contains('is-active')) {
            myOrders.classList.remove('is-active')
        } else {
            favorites.classList.remove('is-active')
        }
    });
    $('.profile-edit').on('click', function () {
        let editProfile = document.getElementById('edit-profile')
        editProfile.style.display = 'block';

    });
    $('.account-empty-link').on('click', function () {
        let editProfile = document.getElementById('edit-user-address')
        editProfile.style.display = 'block';

    });
    $('.edit-close').click(function (){
        let editProfile = document.getElementById('edit-profile')
        editProfile.style.display = 'none';
        let editAddress = document.getElementById('edit-user-address')
        editAddress.style.display = 'none';
    });
});

function validateUserAddressForm(form){
    let city = document.getElementById('edit-city')
    let street = document.getElementById('edit-street')
    let house = document.getElementById('edit-house')
    let entrance = document.getElementById('edit-entrance')
    let floor = document.getElementById('edit-house')
    let flat = document.getElementById('edit-floor')
    let error = document.getElementById('edit-flat')
    let isValid = validateAddressForm(city, street, house, entrance, floor, flat)
    if(!isValid) {
        error.innerHTML = '${fill_fields_correct}'
    } else {
        form.submit()
    }
}

function validateAccountEditForm(form){
    let name = document.getElementsByName('user_name')[0].value
    let email = document.getElementsByName('user_email')[0].value
    let error = document.getElementsByClassName('edit-error-message')[0]
    let isValid = validateUser(name, email)
    if (!isValid) {
        error.innerHTML = '${fill_fields_correct}'
    } else {
        form.submit()
    }
}

function validateUser(name, email){
    let isValid = true
    if (!validateName(name)){
        isValid = false
    }
    if (!validateEmail(email)){
        isValid = false
    }
    return isValid
}

function validateAddressForm(city, street, house, entrance, floor, flat){
    let isValid = true
    if (!validateCity(city)){
        isValid = false
    }
    if (!validateStreet(street)){
        isValid = false
    }
    if (!validateHouse(house)){
        isValid = false
    }
    if (!validateEntrance(entrance)){
        isValid = false
    }
    if (!validateFloor(floor)){
        isValid = false
    }
    if (!validateFlat(flat)){
        isValid = false
    }
    return isValid
}

function validateCity(city){
    let isValid
    const regExp = /^[A-Za-zА-Яа-яёЁ\s]{5,45}$/
    if(regExp.test(city.value)){
        isValid = true
    } else {
        city.value = ""
        isValid = false
    }
    return isValid
}

function validateStreet(street){
    let isValid
    const regExp = /^[A-Za-zА-Яа-я][a-zа-я]{1,30}$/
    if(regExp.test(street.value)){
        isValid = true
    } else {
        street.value = ""
        isValid = false
    }
    return isValid
}

function validateHouse(house){
    let isValid
    const regExp = /^[1-9][0-9]{0,3}$/
    if(regExp.test(house.value)){
        isValid = true
    } else {
        house.value = ""
        isValid = false
    }
    return isValid
}

function validateEntrance(entrance){
    let isValid
    const regExp = /^[1-9][0-9]{0,2}$/
    if(regExp.test(entrance.value)){
        isValid = true
    } else {
        entrance.value = ""
        isValid = false
    }
    return isValid
}

function validateFloor(floor){
    let isValid
    const regExp = /^(-|[1-9])[0-9]{0,3}$/
    if(regExp.test(floor.value)){
        isValid = true
    } else {
        floor.value = ""
        isValid = false
    }
    return isValid
}

function validateFlat(flat){
    let isValid
    const regExp = /^[1-9][0-9]{0,5}$/
    if(regExp.test(flat.value)){
        isValid = true
    } else {
        flat.value = ""
        isValid = false
    }
    return isValid
}