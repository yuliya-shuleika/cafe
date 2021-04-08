var cart_item_ids = [];
$(document).ready(function (){
    changeCart();
    $('.menu-item-button').on('click', function (){
        let items_count_header = $('.header-items-count')[0]
        let cart_items_list = $('.cart-items-list')[0]
        let paramsList = this.parentElement.getElementsByTagName('input')
        let menu_item = this.closest('.menu-item')
        let dish_title = menu_item.getElementsByClassName('menu-item-title')[0].innerHTML
        let dish_picture = menu_item.getElementsByClassName('menu-item-picture')[0].getAttribute("src")
        let dish_description = menu_item.getElementsByClassName('menu-item-description')[0].innerHTML
        let dish_price = menu_item.getElementsByClassName('menu-price-value')[0].innerHTML
        let command = paramsList[0].value
        let dish_id = paramsList[1].value
        let data = {command:command, dish_id:dish_id}
        $.ajax({
            url:'controller',
            type: 'POST',
            data: data,
            success: function() {
                changeCart();
                let hasItem = false;
                for(let i = 0; i < cart_item_ids.length; i++){
                    if(cart_item_ids[i] == dish_id){
                        hasItem = true;
                        let cart_item_count = findCurrentItemCount(dish_id)
                        cart_item_count.innerHTML = Number.parseInt(cart_item_count.innerHTML) + 1
                    }
                }
                if(!hasItem){
                    cart_item_ids.push(dish_id)
                    if(cart_items_list !== undefined){
                        createCartItem(cart_items_list, dish_id, dish_picture, dish_title, dish_description, dish_price)
                    }
                }
                let items_count = items_count_header.innerHTML
                items_count_header.innerHTML= ++items_count
            }
        });
    });
    $('.menu-favorite').on('click', function (){
        let paramsList = this.parentElement.getElementsByTagName('input')
        let like = this
        let command = paramsList[0].value
        let dish_id = paramsList[1].value
        let data = {command:command, dish_id:dish_id}
        $.ajax({
            url:'controller',
            type: 'POST',
            data: data,
            success: function () {
                like.classList.remove('menu-favorite')
                like.classList.add('menu-favorite-added')
            }
        });
    });
    $('.menu-favorite-added').on('click', function (){
        let paramsList = this.parentElement.getElementsByTagName('input')
        let unlike = this
        let command = paramsList[0].value
        let dish_id = paramsList[1].value
        let data = {command:command, dish_id:dish_id}
        $.ajax({
            url:'controller',
            type: 'POST',
            data: data,
            success: function () {
                unlike.classList.remove('menu-favorite-added')
                unlike.classList.add('menu-favorite')
            }
        });
    });
});

function changeCart(){
    let items_count_header = $('.header-items-count')[0]
    let cart_items_container = $('.cart-items-container')[0]
    let cart_empty = $('.cart-empty')[0]
    if(items_count_header.innerHTML == '0'){
        cart_items_container.style.display = 'none'
        cart_empty.style.display = 'flex'
    }else{
        cart_items_container.style.display = 'flex'
        cart_items_container.style.flexDirection = 'column'
        cart_empty.style.display = 'none'
    }
}

function findCurrentItemCount(dish_id){
    let items = document.getElementsByName('cart_item_id')
    console.log(items)
    let cart_item
    for (let i = 0; i < items.length; i++){
        if(dish_id == items[i].getAttribute('value')){
            cart_item = items[i].closest('li')
        }
    }
    let item_count = cart_item.getElementsByClassName('cart-item-count-label')[0]
    return item_count;
}

function createCartItem(cart_items_list, dish_id, dish_picture, dish_title, dish_description, dish_price){
    let cart_item = document.createElement('li')
    cart_item.classList.add('cart-item')
    cart_items_list.appendChild(cart_item)
    let cart_item_id = document.createElement('input')
    cart_item_id.setAttribute("type", "hidden")
    cart_item_id.setAttribute("name", "cart_item_id")
    cart_item_id.setAttribute("value", dish_id)
    cart_item.appendChild(cart_item_id)
    //cart item info
    let cart_item_information = document.createElement("div")
    cart_item_information.classList.add("cart-item-information")
    cart_item.appendChild(cart_item_information)
    let cart_item_image = document.createElement("img")
    cart_item_image.setAttribute("src", dish_picture)
    cart_item_image.classList.add("cart-item-image")
    cart_item_information.appendChild(cart_item_image)
    let cart_item_title = document.createElement("div")
    cart_item_title.classList.add("cart-item-title")
    cart_item_information.appendChild(cart_item_title)
    let cart_item_name = document.createElement("h4")
    cart_item_name.innerHTML = dish_title
    cart_item_name.classList.add('cart-item-name')
    cart_item_title.appendChild(cart_item_name)
    let cart_item_description = document.createElement('p')
    cart_item_description.innerHTML = dish_description
    cart_item_description.classList.add('cart-item-description')
    cart_item_title.appendChild(cart_item_description)
    //cart manage
    let cart_item_manage = document.createElement("div")
    cart_item_manage.classList.add("cart-item-manage")
    cart_item.appendChild(cart_item_manage)
    let cart_item_price = document.createElement("div")
    cart_item_price.classList.add("cart-item-price")
    cart_item_manage.appendChild(cart_item_price)
    let cart_item_price_value = document.createElement('p')
    cart_item_price_value.innerHTML = dish_price
    cart_item_price_value.classList.add('cart-item-price-value')
    cart_item_price.appendChild(cart_item_price_value)
    let cart_item_price_currency = document.createElement('span')
    cart_item_price_currency.innerHTML = '$'
    cart_item_price_currency.classList.add('cart-item-price-currency')
    cart_item_price.appendChild(cart_item_price_currency)
    let cart_item_count = document.createElement("div")
    cart_item_count.classList.add("cart-item-count")
    cart_item_manage.appendChild(cart_item_count)
    let cart_item_count_inc = document.createElement("div")
    cart_item_count_inc.classList.add("cart-item-count-update")
    cart_item_count.appendChild(cart_item_count_inc)
    let add_command = document.createElement('input')
    add_command.setAttribute("type", "hidden")
    add_command.setAttribute("name", "command")
    add_command.setAttribute("value", 'add_to_cart')
    cart_item_count_inc.appendChild(add_command)
    let plus_button = document.createElement('span')
    plus_button.innerHTML = '+'
    plus_button.classList.add('plus-minus-button')
    cart_item_count_inc.appendChild(plus_button)
    let cart_item_count_label = document.createElement('p')
    cart_item_count_label.innerHTML = '1'
    cart_item_count_label.classList.add('cart-item-count-label')
    cart_item_count.appendChild(cart_item_count_label)
    let cart_item_count_dec = document.createElement("div")
    cart_item_count_dec.classList.add("cart-item-count-update")
    cart_item_count.appendChild(cart_item_count_dec)
    let delete_command = document.createElement('input')
    delete_command.setAttribute("type", "hidden")
    delete_command.setAttribute("name", "command")
    delete_command.setAttribute("value", 'delete_from_cart')
    cart_item_count_dec.appendChild(delete_command)
    let minus_button = document.createElement('span')
    minus_button.innerHTML = '-'
    minus_button.classList.add('plus-minus-button')
    cart_item_count_dec.appendChild(minus_button)
    let cart_item_delete = document.createElement("div")
    cart_item_delete.classList.add("cart-item-delete")
    cart_item_manage.appendChild(cart_item_delete)
    let delete_item_command = document.createElement('input')
    delete_item_command.setAttribute("type", "hidden")
    delete_item_command.setAttribute("name", "command")
    delete_item_command.setAttribute("value", 'delete_from_cart')
    cart_item_count_dec.appendChild(delete_command)
    let cart_item_delete_button = document.createElement("div")
    cart_item_delete_button.classList.add("cart-item-delete-button")
    cart_item_delete.appendChild(cart_item_delete_button)
    let cart_item_delete_item = document.createElement("i")
    cart_item_delete_item.classList.add("fa")
    cart_item_delete_item.classList.add("fa-trash")
    cart_item_delete_item.setAttribute('id', 'delete-item')
    cart_item_delete_button.appendChild(cart_item_delete_item)
}

