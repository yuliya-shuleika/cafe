$(document).ready(function (){
    $('.admin-add').click(function (){
        let addPromo = document.getElementById('add-promo')
        addPromo.style.display = 'block';
    });
    $('.edit-close').click(function (){
        let addPromo = document.getElementById('add-promo')
        if(addPromo.style.display == 'block') {
            let name = document.getElementById('add-promo-code-name')
            name.innerHTML = ""
            let discount = document.getElementById('add-promo-code-discount-percents')
            discount.innerHTML = ""
            addPromo.style.display = 'none';
        } else {
            let editPromo = document.getElementById('edit-promo')
            editPromo.style.display = 'none';
        }
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

function validatePromoCodeAddFields(form) {
    let isValid = true
    let name = document.getElementById('add-promo-code-name')
    let discount = document.getElementById('add-promo-code-discount-percents')
    let error = document.getElementById('add-promo-code-error-message')
    if (!validateName(name)){
        isValid = false
    }
    if(!validateDiscount(discount)){
        isValid = false;
    }
    if(!isValid){
        error.innerHTML = '${fill_fields_correct}'
    } else {
        form.submit()
    }
    return isValid
}

function validatePromoCodeEditFields(form) {
    let isValid = true
    let name = document.getElementById('edit-promo-code-name')
    let discount = document.getElementById('edit-promo-code-discount-percents')
    let error = document.getElementById('edit-promo-code-error-message')
    if (!validateName(name)){
        isValid = false
    }
    if(!validateDiscount(discount)){
        isValid = false;
    }
    if(!isValid) {
        error.innerHTML = '${fill_fields_correct}'
    } else {
        form.submit()
    }
    return isValid
}

function validateName(name) {
    let isValid
    const regExp = /^[A-Za-zА-Яа-яёЁ0-9_]{5,45}$/;
    if(regExp.test(name.value)){
        isValid = true
    } else {
        name.value = ""
        isValid = false
    }
    return isValid
}

function validateDiscount(discount) {
    let isValid
    const regExp = /^[1-9][0-9]?$/
    if(regExp.test(discount.value)){
        isValid = true
    } else {
        discount.value = ""
        isValid = false
    }
    return isValid
}

function validatePromoCodeSearch(form){
    let promoCodePart = document.getElementById('search-promo-code')
    const regExp = /^.{1,30}$/
    if(regExp.test(promoCodePart.value)){
        form.submit()
    } else {
        promoCodePart.value = ""
    }
}