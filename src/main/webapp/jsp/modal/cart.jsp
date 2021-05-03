 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
 <%@ taglib prefix="ctg" uri="custom_tag" %>
 <html>
<head>
    <title>Cart</title>
    <style><%@include file="/css/cart.css"%></style>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"
            type="text/javascript"></script>
    <script><%@include file="/js/cart.js"%></script>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Rubik:wght@400;500&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@500&family=Rubik:wght@400;500&display=swap" rel="stylesheet">
</head>
<fmt:message bundle="${loc}" key="lang.label.total" var="total"/>
<fmt:message bundle="${loc}" key="lang.label.cart" var="cart"/>
<fmt:message bundle="${loc}" key="lang.label.clear_cart" var="clear_cart"/>
<fmt:message bundle="${loc}" key="lang.label.checkout" var="checkout"/>
<fmt:message bundle="${loc}" key="lang.label.empty_cart" var="empty_cart"/>
<fmt:message bundle="${loc}" key="lang.label.from_menu" var="from_menu"/>
<body>
<div class="cart" id="cart">
    <div class="cart-body">
        <div class = "cart-content">
            <div class="cart-header">
                <h3 class="cart-title">${cart}</h3>
                <a class="cart-close" href="#">x</a>
            </div>
            <div class="cart-items-container">
            <div class="cart-items">
                <ul class="cart-items-list">
                    <c:forEach var="item" items="${sessionScope.cart_items}">
                        <li class="cart-item">
                            <input type="hidden" name="cart_item_id" value="${item.getKey().getDishId()}">
                            <div class="cart-item-information">
                                <img src="${pageContext.request.contextPath}${item.getKey().getPictureName()}"
                                     alt="${item.getKey().getName()}" class="cart-item-image">
                                <div class="cart-item-title">
                                    <h4 class="cart-item-name">${item.getKey().getName()}</h4>
                                    <p class="cart-item-description">${item.getKey().getDescription()}</p>
                                </div>
                            </div>
                            <div class="cart-item-manage">
                                <div class="cart-item-price">
                                    <p class="cart-item-price-value"><ctg:formatPrice price="${item.getKey().getPrice()}"/></p>
                                    <span class="cart-item-price-currency">$</span>
                                </div>
                                <div class="cart-item-count">
                                    <div class="cart-item-count-update">
                                        <input type="hidden" name ="command" value="add_to_cart">
                                        <span class="plus-minus-button">+</span>
                                    </div>
                                    <p class="cart-item-count-label">${item.getValue()}</p>
                                    <div class="cart-item-count-update">
                                        <input type="hidden" name ="command" value="delete_from_cart">
                                        <span class="plus-minus-button">-</span>
                                    </div>
                                </div>
                                <div class="cart-item-delete">
                                    <input type="hidden" name ="command" value="delete_from_cart">
                                    <div class="cart-item-delete-button">
                                        <i class="fa fa-trash" id="delete-item"></i>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <hr class="cart-items-delimiter">
            <div class="cart-footer">
                <div class="cart-items-total-price">
                    <p class="total-label">${total}</p>
                    <p class="cart-items-total" id="total-price">23.32</p>
                    <span class="total-currency">$</span>
                </div>
                <div class="cart-items-manage">
                    <form action="controller" method="post">
                        <input type="hidden" name="command" value="to_payment">
                        <button class="checkout" type="submit">${checkout}</button>
                    </form>
                </div>
            </div>
            </div>
            <div class="cart-empty">
                <p class="cart-empty-label">${empty_cart} </p>
                <a class="cart-empty-link" href="controller?command=to_menu">${from_menu}</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
