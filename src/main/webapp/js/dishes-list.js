$(document).ready(function (){
    let name = document.getElementById('edit-dish-name')
    console.log(name)
    $('.admin-add').click(function (){
        let addDish = document.getElementById('add-dish')
        addDish.style.display = 'block';
    });
    $('.edit-close').click(function (){
        let addDish = document.getElementById('add-dish')
        if(addDish.style.display == 'block'){
            let name = document.getElementById('add-dish-name')
            name.innerHTML = ""
            let price = document.getElementById('add-dish-price')
            price.innerHTML = ""
            let discount = document.getElementById('add-dish-discount')
            discount.innerHTML = ""
            let description = document.getElementById('add-dish-description')
            description.innerHTML = ""
            let weight = document.getElementById('add-dish-weight')
            weight.innerHTML = ""
            addDish.style.display = 'none';
        } else {
            let editDish = document.getElementById('edit-dish')
            editDish.style.display = 'none';
        }
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

function validateDishAddFields(form) {
    let name = document.getElementById('add-dish-name')
    let price = document.getElementById('add-dish-price')
    let discount = document.getElementById('add-dish-discount')
    let description = document.getElementById('add-dish-description')
    let weight = document.getElementById('add-dish-weight')
    let error = document.getElementById('add-dish-error-message')
    let isValid = validateDishFields(name, price, discount, description, weight)
    if(!isValid){
        error.innerHTML = '${fill_fields_correct}'
    } else {
        form.submit()
    }
    return isValid
}

function validateDishEditFields(form) {
    let name = document.getElementById('edit-dish-name')
    let price = document.getElementById('edit-dish-price')
    let discount = document.getElementById('edit-dish-discount')
    let description = document.getElementById('edit-dish-description')
    let weight = document.getElementById('edit-dish-weight')
    let error = document.getElementById('edit-dish-error-message')
    let isValid = validateDishFields(name, price, discount, description, weight)
    if(!isValid){
        error.innerHTML = '${fill_fields_correct}'
    } else {
        form.submit()
    }
    return isValid
}

function validateDishFields(name, price, discount, description, weight){
    let isValid
    if (!validateName(name)){
        isValid = false
    }
    if (!validatePrice(price)){
        isValid = false
    }
    if (!validateDiscount(discount)){
        isValid = false
    }
    if (!validateDescription(description)){
        isValid = false
    }
    if (!validateWeight(weight)){
        isValid = false
    }
    return isValid
}

function validateName(name){
    let isValid
    const regExp = /^.{5,45}$/
    if(regExp.test(name.value)){
        isValid = true
    } else {
        name.value = ""
        isValid = false
    }
    return isValid
}

function validatePrice(price){
    let isValid
    const regExp = /^[1-9][0-9]{0,4}\.[0-9]?[1-9]$/
    if(regExp.test(price.value)){
        isValid = true
    } else {
        price.value = ""
        isValid = false
    }
    return isValid
}

function validateDiscount(discount){
    let isValid
    const regExp = /^[0-9]{1,2}$/
    if(regExp.test(discount.value)){
        isValid = true
    } else {
        discount.value = ""
        isValid = false
    }
    return isValid
}

function validateDescription(description){
    let isValid
    const regExp = /^.{5,300}$/
    if(regExp.test(description.value)){
        isValid = true
    } else {
        description.value = ""
        isValid = false
    }
    return isValid
}

function validateWeight(weight){
    let isValid
    const regExp = /^[1-9][0-9]{0,5}$/
    if(regExp.test(weight.value)){
        isValid = true
    } else {
        weight.value = ""
        isValid = false
    }
    return isValid
}

function validateDishSearch(form){
    let dishPart = document.getElementById('search_dish')
    const regExp = /^.{1,30}$/
    if(regExp.test(dishPart.value)){
        form.submit()
    } else {
        dishPart.value = ""
    }
}