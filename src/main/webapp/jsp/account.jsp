<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Account</title>
    <style><%@include file="/css/account.css"%></style>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script><%@include file="/js/account.js"%></script>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Rubik:wght@400;500&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@500&family=Rubik:wght@400;500&display=swap" rel="stylesheet">
</head>
<fmt:setLocale value='<%=request.getSession().getAttribute("lang")%>'/>
<fmt:setBundle basename="lang" var="loc"/>
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
<body>
    <%@ include file="/jsp/header/header-user.jsp"%>
    <div class="account-container">
        <div class="profile">
            <div class="profile-image">
                <img src="${pageContext.request.contextPath}/images/guest.jpg" alt="user">
            </div>
            <div class="profile-info">
                <p class="profile-label">${username}: ${sessionScope.user.getName()}</p>
                <p class="profile-label">${email}: ${sessionScope.user.getEmail()}</p>
                <form action="profile-edit.do" method="post">
                    <input type="hidden" name="command" value="to_account_edit">
                    <button class="profile-edit">${edit}</button>
                </form>
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
                                    <button class="account-action-button">${repeat}</button>
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
                                    <p class="favorite-description">${dish.getDescription()}</p>
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
                                        <fmt:formatNumber maxFractionDigits="2" value="${dish.getPrice() * dish.getDiscountPercents() / 100}"/>
                                    </c:if>
                                </p>
                                <span class="cart-item-price-currency">$</span>
                            </div>
                            <button class="account-action-button" type="button">${add_to_cart}</button>
                        </div>
                    </div>
                </c:forEach>
                </c:if>
            </div>
            <div class="account-extra" id="account-extra">
                <c:if test="${user_address == null}">
                    <div class="account-empty">
                        <p class="account-empty-label">${no_extra}</p>
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
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</body>
</html>
