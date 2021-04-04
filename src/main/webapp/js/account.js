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
});