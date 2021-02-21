$(document).ready(function (){
    //count price
    $('#total-price').html(countTotalPrice())
    //delete item from cart
    $('.cart-item-delete').click(function (){
        let cart_item = this.closest(".cart-item")
        let params = cart_item.getElementsByTagName('input')
        console.log(params)
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
        let data = {command:command, cart_item_id:cart_item_id};
        console.log(data)
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
});

function countTotalPrice(){
    let prices = document.getElementsByClassName('cart-item-price-value')
    let total = 0
    for (let i = 0; i < prices.length; i++) {
        total += Number.parseFloat(prices[i].innerHTML)
    }
    return total;
}
