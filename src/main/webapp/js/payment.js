$(document).ready(function (){
    let addressForm = document.getElementsByClassName('delivery-address')[0]
    let pickupInfo = document.getElementsByClassName('pickup-info')[0]
    let delivery = document.getElementById('delivery')
    let pickup = document.getElementById('pickup')
    $('#delivery').on('click', function (){
        pickupInfo.style.display = 'none'
        addressForm.style.display = 'flex'
        delivery.classList.add('is-active')
        pickup.classList.remove('is-active')
    });
    $('#pickup').on('click', function (){
        addressForm.style.display = 'none'
        pickupInfo.style.display = 'flex'
        delivery.classList.remove('is-active')
        pickup.classList.add('is-active')
    });
});