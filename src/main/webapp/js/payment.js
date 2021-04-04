$(document).ready(function () {
    let addressForm = document.getElementsByClassName('delivery-address')[0]
    let pickupInfo = document.getElementsByClassName('pickup-info')[0]
    let delivery = document.getElementById('delivery')
    let pickup = document.getElementById('pickup')
    $('#delivery').on('click', function () {
        pickupInfo.style.display = 'none'
        addressForm.style.display = 'flex'
        delivery.classList.add('is-active')
        pickup.classList.remove('is-active')
    });
    $('#pickup').on('click', function () {
        addressForm.style.display = 'none'
        pickupInfo.style.display = 'flex'
        delivery.classList.remove('is-active')
        pickup.classList.add('is-active')
    });
    let creditCardForm = document.getElementById('credit-card-form')
    let cash = document.getElementById('cash')
    let bankCard = document.getElementById('bank-card')
    let bankCardOnline = document.getElementById('bank-card-online')
    //let card_number_input = document.getElementById('card-number')
    //card_number_input.mask('9999 9999 9999 9999')
    //let expiration_date_input = document.getElementById('expiration-date')
    //expiration_date_input.mask('99/99')
    $('#cash').on('click', function () {
        creditCardForm.style.display = 'none'
        cash.classList.add('is-active')
        if(bankCard.classList.contains('is-active')) {
            bankCard.classList.remove('is-active')
        } else {
            bankCardOnline.classList.remove('is-active')
        }
    });
    $('#bank-card').on('click', function () {
        creditCardForm.style.display = 'none'
        if(cash.classList.contains('is-active')) {
            cash.classList.remove('is-active')
        } else {
            bankCardOnline.classList.remove('is-active')
        }
        bankCard.classList.add('is-active')
    });
    $('#bank-card-online').on('click', function () {
        creditCardForm.style.display = 'flex'
        if(cash.classList.contains('is-active')) {
            cash.classList.remove('is-active')
        } else {
            bankCard.classList.remove('is-active')
        }
        bankCardOnline.classList.add('is-active')
    });
});