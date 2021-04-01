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
    let credit_card_form = document.getElementById('credit-card-form')
    let cash = document.getElementById('cash')
    let bank_card = document.getElementById('bank-card')
    let bank_card_online = document.getElementById('bank-card-online')
    let card_number_input = document.getElementById('card-number')
    card_number_input.mask('9999 9999 9999 9999')
    let expiration_date_input = document.getElementById('expiration-date')
    expiration_date_input.mask('99/99')
    $('#cash').on('click', function () {
        credit_card_form.style.display = 'none'
        cash.classList.add('is-active')
        if(bank_card.classList.contains('is-active')) {
            bank_card.classList.remove('is-active')
        } else {
            bank_card.classList.remove('is-active')
        }
    });
    $('#bank-card').on('click', function () {
        credit_card_form.style.display = 'none'
        if(cash.classList.contains('is-active')) {
            cash.classList.remove('is-active')
        } else {
            bank_card_online.classList.remove('is-active')
        }
        bank_card.classList.add('is-active')
    });
    $('#bank-card-online').on('click', function () {
        credit_card_form.style.display = 'flex'
        if(cash.classList.contains('is-active')) {
            cash.classList.remove('is-active')
        } else {
            bank_card.classList.remove('is-active')
        }
        bank_card_online.classList.add('is-active')
    });

});