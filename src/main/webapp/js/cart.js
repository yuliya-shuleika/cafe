let totalPrice
$(document).ready(function () {
    totalPrice = document.getElementById('total-price')
    changeCart()
    totalPrice.innerHTML = countTotalPrice()
    $('.cart-items').on('click', '.cart-item-delete', function () {
        let cart_item = this.closest(".cart-item")
        let params = cart_item.getElementsByTagName('input')
        let command
        let cart_item_id
        for (let i = 0; i < params.length; i++) {
            switch (params[i].name) {
                case 'cart_item_id':
                    cart_item_id = params[i].value
                    break;
                case 'command':
                    command = params[i].value
                    break;
            }
        }
        let items_count = cart_item.getElementsByClassName('cart-item-count-label')[0].innerHTML
        let data = {command: command, dish_id: cart_item_id, items_count: items_count};
        $.ajax({
            url: 'controller',
            type: 'POST',
            data: data,
            success: function () {
                let items_count_header = $('.header-items-count')[0]
                let items_count = items_count_header.innerHTML
                let cart_item_count = findCurrentItemCount(cart_item_id).innerHTML
                items_count = items_count - cart_item_count
                items_count_header.innerHTML = items_count
                if (items_count == 0) {
                    changeCart();
                    totalPrice.innerHTML = countTotalPrice()
                }
                cart_item.remove()
                removeFromCartIds(cart_item_id)
                totalPrice.innerHTML = countTotalPrice()
            }
        });
    });
    //update item count
    $('.cart-items').on('click', '.cart-item-count-update', function () {
        let cart_item = this.closest(".cart-item")
        let params = cart_item.getElementsByTagName('input')
        let parent = this.parentElement
        let command
        let dish_id
        for (let i = 0; i < params.length; i++) {
            if (params[i].name == 'cart_item_id') {
                dish_id = params[i].value
            }
        }
        command = this.getElementsByTagName('input')[0].value
        let items_count = 1
        let data = {command: command, dish_id: dish_id, items_count: items_count};
        $.ajax({
            url: 'controller',
            type: 'POST',
            data: data,
            success: function () {
                let itemCount = parent.getElementsByClassName('cart-item-count-label')[0]
                let itemCountVal = itemCount.innerHTML
                let items_count_header = $('.header-items-count')[0]
                let items_count = items_count_header.innerHTML
                if (command == 'delete_from_cart') {
                    items_count_header.innerHTML = --items_count
                    if (itemCountVal == 1) {
                        cart_item.remove()
                        removeFromCartIds(cart_item_ids)
                        changeCart()
                        totalPrice.innerHTML = countTotalPrice()
                    } else {
                        itemCountVal--
                        itemCount.innerHTML = itemCountVal
                    }
                } else {
                    items_count_header.innerHTML = ++items_count
                    if (itemCountVal == 0) {
                        changeCart();
                    }
                    itemCountVal++;
                    itemCount.innerHTML = itemCountVal
                }
                totalPrice.innerHTML = countTotalPrice()
            }
        });
    });
    $('#clean-cart').click(function () {
        let command = 'clean_cart'
        let data = {command: command};
        let cart_items = document.getElementsByClassName('cart-item')
        $.ajax({
            url: 'controller',
            type: 'POST',
            data: data,
            success: function () {
                console.log(cart_items.length)
                for (let i = 0; i < cart_items.length; i++) {
                    cart_items[i].remove()
                }
            }
        });
    })
});

function countTotalPrice() {
    let prices = document.getElementsByClassName('cart-item-price-value')
    let counts = document.getElementsByClassName('cart-item-count-label')
    let total = 0
    for (let i = 0; i < prices.length; i++) {
        total += Number.parseFloat(prices[i].innerHTML) * Number.parseFloat(counts[i].innerHTML)
    }
    return total.toFixed(2);
}

function removeFromCartIds(cart_item_id) {
    for (let i = 0; i < cart_item_ids.length; i++) {
        if (cart_item_id == cart_item_ids[i]) {
            cart_item_ids.splice(i, 1)
        }
    }
}

function changeCart() {
    let items_count_header = $('.header-items-count')[0]
    let cart_items_container = $('.cart-items-container')[0]
    let cart_empty = $('.cart-empty')[0]
    if (items_count_header.innerHTML == '0') {
        cart_items_container.style.display = 'none'
        cart_empty.style.display = 'flex'
    } else {
        cart_items_container.style.display = 'flex'
        cart_items_container.style.flexDirection = 'column'
        cart_empty.style.display = 'none'
    }
}

function findCurrentItemCount(dish_id) {
    let items = document.getElementsByName('cart_item_id')
    let cart_item
    for (let i = 0; i < items.length; i++) {
        if (dish_id == items[i].getAttribute('value')) {
            cart_item = items[i].closest('li')
        }
    }
    let item_count = cart_item.getElementsByClassName('cart-item-count-label')[0]
    return item_count;
}