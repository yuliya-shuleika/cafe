$(document).ready(function (){
    //count price
    $('#total-price').html(countTotalPrice())
    //delete item from cart
    $('.cart-item-delete').click(function (){
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
        let data = {command:command, dish_id:cart_item_id, items_count:items_count};
        $.ajax({
            url:'controller',
            type: 'POST',
            data: data,
            success: function(response) {
                cart_item.remove()
                $('.header-items-count').html(response)
                $('#total-price').html(countTotalPrice())
            }
        });
    });
    //update item count
    $('.cart-item-count-update').click(function (){
        let cart_item = this.closest(".cart-item")
        let params = cart_item.getElementsByTagName('input')
        let parent = this.parentElement
        let command
        let dish_id
        for (let i = 0; i < params.length; i++) {
            if(params[i].name == 'cart_item_id'){
               dish_id = params[i].value
            }
        }
        command = this.getElementsByTagName('input')[0].value
        let items_count = 1
        let data = {command:command, dish_id:dish_id, items_count:items_count};
        console.log(data)
        $.ajax({
            url:'controller',
            type: 'POST',
            data: data,
            success: function(response) {
                let itemCount = parent.getElementsByClassName('cart-item-count-label')[0]
                console.log(itemCount)
                let itemCountVal = itemCount.innerHTML
                if(command == 'delete_from_guest_cart') {
                    if (itemCountVal == 1) {
                        cart_item.remove()
                    } else {
                        itemCountVal--;
                        itemCount.innerHTML = itemCountVal
                    }
                }else {
                    itemCountVal++;
                    itemCount.innerHTML = itemCountVal
                }
                $('.header-items-count').html(response)
                $('#total-price').html(countTotalPrice())
            }
        });
    });
});

function countTotalPrice(){
    let prices = document.getElementsByClassName('cart-item-price-value')
    let total = 0
    for (let i = 0; i < prices.length; i++) {
        total += Number.parseFloat(prices[i].innerHTML)
    }
    return total.toFixed(2);
}
