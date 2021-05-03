const deliveryType = 'delivery'
const pickupType = 'pickup'
$(document).ready(function () {
    let addressForm = document.getElementsByClassName('delivery-address')[0]
    let pickupInfo = document.getElementsByClassName('pickup-info')[0]
    let orderGettingType = document.getElementById('order-getting-type')
    let delivery = document.getElementById('delivery')
    let pickup = document.getElementById('pickup')
    $('#delivery').on('click', function () {
        pickupInfo.style.display = 'none'
        addressForm.style.display = 'flex'
        delivery.classList.add('is-active')
        pickup.classList.remove('is-active')
        orderGettingType.value = deliveryType
    });
    $('#pickup').on('click', function () {
        addressForm.style.display = 'none'
        pickupInfo.style.display = 'flex'
        delivery.classList.remove('is-active')
        pickup.classList.add('is-active')
        orderGettingType.value = pickupType
    });
    let cash = document.getElementById('cash')
    let bankCard = document.getElementById('bank-card')
    $('#cash').on('click', function () {
        cash.classList.add('is-active')
        bankCard.classList.remove('is-active')
    });
    $('#bank-card').on('click', function () {
        cash.classList.remove('is-active')
        bankCard.classList.add('is-active')
    });
});

