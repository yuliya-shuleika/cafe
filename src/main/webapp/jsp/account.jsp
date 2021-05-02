<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="custom_tag" %>
<html>
<fmt:setLocale value='<%=request.getSession().getAttribute("lang")%>'/>
<fmt:setBundle basename="lang" var="loc"/>
<fmt:message bundle="${loc}" key="lang.label.fill_fields_correct" var="fill_fields_correct"/>
<head>
    <title>Account</title>
    <style><%@include file="/css/account.css"%></style>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script><%@include file="/js/account.js"%></script>
    <script><%@include file="/js/menu.js"%></script>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Rubik:wght@400;500&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@500&family=Rubik:wght@400;500&display=swap" rel="stylesheet">
</head>
<fmt:message bundle="${loc}" key="lang.label.username" var="username"/>
<fmt:message bundle="${loc}" key="lang.label.email" var="email"/>
<fmt:message bundle="${loc}" key="lang.label.edit" var="edit"/>
<fmt:message bundle="${loc}" key="lang.label.repeat" var="repeat"/>
<fmt:message bundle="${loc}" key="lang.label.my_orders" var="my_orders"/>
<fmt:message bundle="${loc}" key="lang.label.favorites" var="favotites"/>
<fmt:message bundle="${loc}" key="lang.label.extra" var="extra"/>
<fmt:message bundle="${loc}" key="lang.label.add_to_cart" var="add_to_cart"/>
<fmt:message bundle="${loc}" key="lang.label.city" var="city"/>
<fmt:message bundle="${loc}" key="lang.label.street" var="street"/>
<fmt:message bundle="${loc}" key="lang.label.house" var="house"/>
<fmt:message bundle="${loc}" key="lang.label.flat" var="flat"/>
<fmt:message bundle="${loc}" key="lang.label.entrance" var="entrance"/>
<fmt:message bundle="${loc}" key="lang.label.floor" var="floor"/>
<fmt:message bundle="${loc}" key="lang.label.add_to_cart" var="add_to_cart"/>
<fmt:message bundle="${loc}" key="lang.label.no_orders" var="no_orders"/>
<fmt:message bundle="${loc}" key="lang.label.no_favorites" var="no_favotites"/>
<fmt:message bundle="${loc}" key="lang.label.no_extra" var="no_extra"/>
<fmt:message bundle="${loc}" key="lang.label.edit" var="edit"/>
<fmt:message bundle="${loc}" key="lang.label.add" var="add"/>
<fmt:message bundle="${loc}" key="lang.label.avatar" var="avatar"/>
<fmt:message bundle="${loc}" key="lang.label.logout" var="logout"/>
<body>
    <%@ include file="/jsp/header/header-user.jsp"%>
    <%@ include file="/jsp/modal/edit-profile.jsp"%>
    <%@ include file="/jsp/modal/edit-user-address.jsp"%>
    <c:if test="${address_edit != null || edit_error_message != null}">
        <c:choose>
            <c:when test="${edit_error_message == 'edit_profile_error'}">
                <script>
                    let editProfile = document.getElementById('edit-profile')
                    editProfile.style.display = 'block'
                </script>
            </c:when>
            <c:when test="${edit_error_message == 'edit_address_error'}">
                <script>
                    let editAddress = document.getElementById('edit-user-address')
                    editAddress.style.display = 'block'
                </script>
            </c:when>
        </c:choose>
    </c:if>
    <c:if test="${add_dish_to_cart != null}">
        <script>
            $(document).ready(function () {
                let myOrders = document.getElementById('my-orders')
                let favorites = document.getElementById('favorites')
                let extra = document.getElementById('extra')
                let accountExtra = document.getElementById('account-extra')
                let accountFavorites = document.getElementById('account-favorites')
                let accountOrders = document.getElementById('account-orders')
                accountExtra.style.display = 'none'
                accountOrders.style.display = 'none'
                accountFavorites.style.display = 'flex'
                favorites.classList.add('is-active')
                if (myOrders.classList.contains('is-active')) {
                    myOrders.classList.remove('is-active')
                } else {
                    extra.classList.remove('is-active')
                }
            });
        </script>
    </c:if>
    <div class="account-container">
        <div class="profile">
            <div class="profile-image">
                <c:if test="${sessionScope.user.avatar == null}">
                    <img width="300px" height="300px" src="${pageContext.request.contextPath}/images/guest.jpg" alt="${avatar}">
                </c:if>
                <c:if test="${sessionScope.user.avatar != null}">
                    <img width="300px" height="300px" src="${pageContext.request.contextPath}${sessionScope.user.getAvatar()}" alt="${avatar}">
                </c:if>
            </div>
            <div class="profile-info">
                <p class="profile-label">${username}: ${sessionScope.user.getName()}</p>
                <p class="profile-label">${email}: ${sessionScope.user.getEmail()}</p>
                <div class="profile-buttons-container">
                    <button class="profile-edit">${edit}</button>
                    <form method="post" action="home.do">
                        <input type="hidden" name="command" value="logout">
                        <button class="profile-logout">${logout}</button>
                    </form>
                </div>
            </div>
        </div>
        <div class="account-manage">
            <div class="account-tabs">
                <button class="account-tab is-active" id="my-orders">${my_orders}</button>
                <button class="account-tab" id="favorites">${favotites}</button>
                <button class="account-tab" id="extra">${extra}</button>
            </div>
            <div class="account-orders" id="account-orders">
            <c:if test="${user_orders.isEmpty()}">
                <div class="account-empty">
                    <p class="account-empty-label">${no_orders}</p>
                </div>
            </c:if>
            <c:if test="${!user_orders.isEmpty()}">
                <c:forEach var="order" items="${user_orders}">
                    <div class="order-info">
                        <div class="order-start">
                            <p class="order-date"><fmt:formatDate value="${order.getDate()}" type = "date" /></p>
                            <p class="order-dishes">
                                <c:forEach var="dish" items="${order.getOrderedDishes()}">
                                    ${dish.getKey().getName()} x ${dish.getValue()}<br>
                                </c:forEach>
                            </p>
                        </div>
                        <div>
                            <div class="order-end">
                                <div class="order-price-container">
                                    <p class="order-price">${order.getTotal()}</p>
                                    <span class="total-currency">$</span>
                                </div>
                                <div class="order-repeat">
                                    <form action="payment.do" method="post">
                                        <input type="hidden" name="command" value="repeat_order">
                                        <input type="hidden" name="order_id" value="${order.getOrderId()}">
                                        <button class="account-action-button">${repeat}</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
            </div>
            <div class="account-favorites" id="account-favorites">
                <c:if test="${user_favorites.isEmpty()}">
                    <div class="account-empty">
                        <p class="account-empty-label">${no_favotites}</p>
                    </div>
                </c:if>
                <c:if test="${!user_favorites.isEmpty()}">
                    <c:forEach var="dish" items="${user_favorites}">
                        <div class="favorite-info">
                            <div class="favorite-info-start">
                                <img class="favorite-image" src="${pageContext.request.contextPath}${dish.getPictureName()}">
                                <div class="favorite-header">
                                    <div class="favorite-header">
                                        <h4 class="favorite-title">${dish.getName()}</h4>
                                    </div>
                                </div>
                            </div>
                            <div class="favorite-info-end">
                                <div class="favorite-price">
                                    <c:if test="${dish.getDiscountPercents() == 0}">
                                        <p class="favorite-price-value">
                                            <fmt:formatNumber maxFractionDigits="2" value="${dish.getPrice()}"/>
                                        </p>
                                    </c:if>
                                    <c:if test="${dish.getDiscountPercents() > 0}">
                                        <strike class="favorite-price-value">
                                            <fmt:formatNumber maxFractionDigits="2" value="${dish.getPrice()}"/>
                                        </strike>
                                    </c:if>
                                    <p class="favorite-price-value-discount">
                                        <c:if test="${dish.getDiscountPercents() > 0}">
                                            <ctg:countPrice price="${dish.getPrice()}" discount="${dish.getDiscountPercents()}"/>
                                        </c:if>
                                    </p>
                                    <span class="cart-item-price-currency">$</span>
                                </div>
                                <form action="account.do" method="post">
                                <input type="hidden" name="command" value="add_to_cart"/>
                                <input type="hidden" name="dish_id" value="${dish.getDishId()}"/>
                                <button class="account-action-button menu-item-button">${add_to_cart}</button>
                                </form>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
            </div>
            <div class="account-extra" id="account-extra">
                <c:if test="${user_address == null}">
                    <div class="account-empty">
                        <p class="account-empty-label">${no_extra}. </p>
                        <a class="account-empty-link" href="#">${add}</a>
                    </div>
                </c:if>
                <c:if test="${user_address != null}">
                    <div class="extra-container">
                        <p class="extra-info">${city}: <span class="extra-info-span">${user_address.getCity()}</span></p>
                        <p class="extra-info">${street}: <span class="extra-info-span">${user_address.getStreet()}</span></p>
                        <p class="extra-info">${house}: <span class="extra-info-span">${user_address.getHouse()}</span></p>
                        <p class="extra-info">${entrance}: <span class="extra-info-span">${user_address.getEntrance()}</span></p>
                        <p class="extra-info">${floor}: <span class="extra-info-span">${user_address.getFloor()}</span></p>
                        <p class="extra-info">${flat}: <span class="extra-info-span">${user_address.getFlat()}</span></p>
                        <a class="account-empty-link" href="#">${edit}</a>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</body>
</html>
