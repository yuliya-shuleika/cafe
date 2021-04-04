<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Menu</title>
    <style><%@include file="/css/cart.css"%></style>
    <style><%@include file="/css/payment.css"%></style>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script><%@include file="/js/payment.js"%></script>
    <script><%@include file="/js/cart.js"%></script>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Rubik:wght@400;500&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@500&family=Rubik:wght@400;500&display=swap" rel="stylesheet">
</head>
<fmt:setLocale value='<%=request.getSession().getAttribute("lang")%>'/>
<fmt:setBundle basename="lang" var="loc"/>
<fmt:message bundle="${loc}" key="lang.label.checkout" var="checkout"/>
<fmt:message bundle="${loc}" key="lang.label.your_order" var="your_order"/>
<fmt:message bundle="${loc}" key="lang.label.sticks" var="sticks"/>
<fmt:message bundle="${loc}" key="lang.label.fork" var="fork"/>
<fmt:message bundle="${loc}" key="lang.label.ginger" var="ginger"/>
<fmt:message bundle="${loc}" key="lang.label.wasabi" var="wasabi"/>
<fmt:message bundle="${loc}" key="lang.label.soy_sauce" var="soy_sauce"/>
<fmt:message bundle="${loc}" key="lang.label.extra" var="extra"/>
<fmt:message bundle="${loc}" key="lang.label.delivery" var="delivery"/>
<fmt:message bundle="${loc}" key="lang.label.pickup" var="pickup"/>
<fmt:message bundle="${loc}" key="lang.label.city" var="city"/>
<fmt:message bundle="${loc}" key="lang.label.street" var="street"/>
<fmt:message bundle="${loc}" key="lang.label.house" var="house"/>
<fmt:message bundle="${loc}" key="lang.label.entrance" var="entrance"/>
<fmt:message bundle="${loc}" key="lang.label.floor" var="floor"/>
<fmt:message bundle="${loc}" key="lang.label.flat" var="flat"/>
<fmt:message bundle="${loc}" key="lang.label.delivery_time" var="delivery_time"/>
<fmt:message bundle="${loc}" key="lang.label.as_soon_as_possible" var="as_soon_as_possible"/>
<fmt:message bundle="${loc}" key="lang.label.certain_time" var="certain_time"/>
<fmt:message bundle="${loc}" key="lang.label.payment_method" var="payment_method"/>
<fmt:message bundle="${loc}" key="lang.label.cash" var="cash"/>
<fmt:message bundle="${loc}" key="lang.label.bank_card" var="bank_card"/>
<fmt:message bundle="${loc}" key="lang.label.payment_by_card_online" var="payment_by_card_online"/>
<fmt:message bundle="${loc}" key="lang.label.promo_code_optional" var="promo_code"/>
<fmt:message bundle="${loc}" key="lang.label.enter_promo_code" var="enter_promo_code"/>
<fmt:message bundle="${loc}" key="lang.label.your_comment" var="your_comment"/>
<fmt:message bundle="${loc}" key="lang.label.choose_cafe" var="choose_cafe"/>
<fmt:message bundle="${loc}" key="lang.label.card_owner_name" var="card_owner_name"/>
<fmt:message bundle="${loc}" key="lang.label.card_number" var="card_number"/>
<fmt:message bundle="${loc}" key="lang.label.expiration_date" var="expiration_date"/>
<fmt:message bundle="${loc}" key="lang.label.security_code" var="security_code"/>
<body>
    <%@ include file="/jsp/header/header-user.jsp"%>
    <div class="payment-container">
        <div class="payment-header">
            <h2 class="payment-title">${checkout}</h2>
        </div>
        <form action="controller" method="post">
        <input type="hidden" name="command" value="checkout">
        <div class="payment-body">
            <div class="payment-cart">
                <h4 class="payment-section-header">${your_order}</h4>
                <ul class="payment-cart-items">
                    <c:forEach var="item" items="${cart_items}">
                        <li class="payment-cart-item">
                            <input type="hidden" name="cart_item_id" value="${item.getKey().getDishId()}">
                            <div class="cart-item-information">
                                <img src="${pageContext.request.contextPath}${item.getKey().getPictureName()}"
                                     alt="${item.getKey().getName()}" class="cart-item-image">
                                <div class="cart-item-title">
                                    <h4 class="cart-item-name">${item.getKey().getName()}</h4>
                                    <p class="cart-item-description">something</p>
                                </div>
                            </div>
                            <div class="cart-item-manage">
                                <div class="cart-item-price">
                                    <p class="cart-item-price-value">${item.getKey().getPrice()}</p>
                                    <span class="cart-item-price-currency">$</span>
                                </div>
                                <div class="cart-item-count">
                                    <div class="cart-item-count-update">
                                        <input type="hidden" name ="command" value="add_to_guest_cart">
                                        <span class="plus-minus-button">+</span>
                                    </div>
                                    <p class="cart-item-count-label">${item.getValue()}</p>
                                    <div class="cart-item-count-update">
                                        <input type="hidden" name ="command" value="delete_from_guest_cart">
                                        <span class="plus-minus-button">-</span>
                                    </div>
                                </div>
                                <div class="cart-item-delete">
                                    <input type="hidden" name ="command" value="delete_from_guest_cart">
                                    <div class="cart-item-delete-button">
                                        <i class="fa fa-trash" id="delete-item"></i>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="payment-extra-items">
                <h4 class="payment-section-header">${extra}</h4>
                <ul class="extra-items">
                    <li class="extra-item">
                        <input type="checkbox" id="sticks"><label for="sticks" class="extra-item-title">${sticks}</label>
                    </li>
                    <li class="extra-item">
                        <input type="checkbox" id="fork"><label for="fork" class="extra-item-title">${fork}</label>
                    </li>
                    <li class="extra-item">
                        <input type="checkbox" id="ginger"><label for="ginger" class="extra-item-title">${ginger}</label>
                    </li>
                    <li class="extra-item">
                        <input type="checkbox" id="wasabi"><label for="wasabi" class="extra-item-title">${wasabi}</label>
                    </li>
                    <li class="extra-item">
                        <input type="checkbox" id="soy_sauce"><label for="soy_sauce" class="extra-item-title">${soy_sauce}</label>
                    </li>
                </ul>
            </div>
            <div class="payment-delivery">
                <input type="hidden" name="getting_type" value="delivery">
                <div class="choose">
                    <div id="delivery" class="choose-item is-active">${delivery}</div>
                    <div id="pickup" class="choose-item">${pickup}</div>
                </div>
                <div class="delivery-address">
                    <div class="delivery-address-house">
                        <div class="delivery-address-field">
                            <label class="delivery-address-label" for="city">${city}</label>
                            <input class="delivery-address-input-long" id="city" type="text"
                                   name="city" placeholder="Город" required>
                        </div>
                        <div class="delivery-address-field">
                            <label class="delivery-address-label" for="street">${street}</label>
                            <input class="delivery-address-input-long" id="street" type="text"
                                   name="street" placeholder="Улица" required>
                        </div>
                    </div>
                    <div class="delivery-address-house">
                        <div class="delivery-address-field">
                            <label class="delivery-address-label" for="house">${house}</label>
                            <input class="delivery-address-input-short" id="house" type="text"
                                   name="house" placeholder="Дом" required pattern="[1-9][0-9]{0,3}">
                        </div>
                        <div class="delivery-address-field">
                            <label class="delivery-address-label" for="entrance">${entrance}</label>
                            <input class="delivery-address-input-short" id="entrance" type="text"
                                   name="entrance" placeholder="Подъезд" pattern="[1-9][0-9]{0,2}">
                        </div>
                        <div class="delivery-address-field">
                            <label class="delivery-address-label" for="floor">${floor}</label>
                            <input class="delivery-address-input-short" id="floor" type="text"
                                   name="floor" placeholder="Этаж" pattern="(-|[1-9])[0-9]{0,3}">
                        </div>
                        <div class="delivery-address-field">
                            <label class="delivery-address-label" for="flat">${flat}</label>
                            <input class="delivery-address-input-short" id="flat" type="text"
                                   name="flat" placeholder="Квартира" pattern="[1-9][0-9]{0,5}">
                        </div>
                    </div>
                </div>
                <div class="pickup-info">
                    <input type="hidden" name="getting_type" value="pickup">
                    <p class="pickup-label">${choose_cafe}</p>
                    <select class="payment-select">
                        <c:forEach var="address" items="${cafe_addresses}">
                            <input type="hidden" name="address_id" value="${address.getAddressId()}">
                            <option>
                                ${address.getCity()}, ${address.getStreet()}, ${address.getHouse()}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="promo-code">
                    <h4 class="payment-section-header">${promo_code}</h4>
                    <div class="promo-code-field">
                        <label class="promo-code-label" for="promo-code">${enter_promo_code}</label>
                        <input class="promo-code-input" id="promo-code" type="text" placeholder="Promo code"
                        pattern="[A-Za-zА-Яа-яёЁ0-9_]{5,45}">
                    </div>
                </div>
                <div class="payment-method">
                    <h4 class="payment-section-header">${payment_method}</h4>
                    <div class="choose">
                        <div class="choose-item is-active" id="cash">
                            ${cash}
                            <input type="hidden" name="payment_type" value="cash">
                        </div>
                        <div class="choose-item" id="bank-card">
                            ${bank_card}
                            <input type="hidden" name="payment_type" value="bank_card">
                        </div>
                        <div class="choose-item" id="bank-card-online">
                            ${payment_by_card_online}
                            <input type="hidden" name="payment_type" value="bank_card_online">
                        </div>
                    </div>
                    <div class="credit-card-form" id="credit-card-form">
                        <div class="card-field">
                            <label class="card-label" for="security-code">${card_owner_name}</label>
                            <input id="name" maxlength="30" type="text" class="card-input-long">
                        </div>
                        <div class="card-field">
                            <label class="card-label" for="security-code">${card_number}</label>
                            <input id="card-number" type="text" pattern="[0-9]*" inputmode="numeric"
                                   class="card-input-long">
                        </div>
                        <div class="card-field">
                            <label class="card-label" for="expiration-date">${expiration_date}</label>
                            <input id="expiration-date" type="text" pattern="[0-9]*" inputmode="numeric"
                                   class="card-input-short">
                        </div>
                        <div class="card-field">
                            <label class="card-label" for="security-code">${security_code}</label>
                            <input id="security-code" type="text" pattern="[0-9]*" inputmode="numeric"
                                   class="card-input-short">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="payment-footer">
            <div class="comment-order">
                <h4 class="payment-section-header">${your_comment}</h4>
                <textarea class="comment-order-input" placeholder="Your comment..." name="comment"></textarea>
            </div>
            <div class="payment-confirm">
                <button class="payment-confirm-button" type="submit">${checkout}</button>
            </div>
        </div>
        </form>
    </div>
</body>
</html>