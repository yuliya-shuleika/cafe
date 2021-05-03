<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Header</title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"
            type="text/javascript"></script>
    <script><%@include file="/js/header.js"%></script>
    <style><%@include file="/css/header.css"%></style>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Rubik:wght@400;500&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@500&family=Rubik:wght@400;500&display=swap" rel="stylesheet">
</head>
<fmt:message bundle="${loc}" key="lang.label.home" var="home"/>
<fmt:message bundle="${loc}" key="lang.label.menu" var="menu"/>
<fmt:message bundle="${loc}" key="lang.label.reviews" var="reviews"/>
<fmt:message bundle="${loc}" key="lang.label.about" var="about"/>
<fmt:message bundle="${loc}" key="lang.label.cart" var="cart"/>
<fmt:message bundle="${loc}" key="lang.label.account" var="account"/>
<fmt:message bundle="${loc}" key="lang.label.login" var="login"/>
<fmt:message bundle="${loc}" key="lang.label.register" var="register"/>
<fmt:message bundle="${loc}" key="lang.label.translate" var="translate"/>
<fmt:message bundle="${loc}" key="lang.label.search" var="search"/>
<body>
<header class="header-page">
    <%@ include file="/jsp/modal/cart.jsp"%>
    <%@ include file="/jsp/modal/dish-info.jsp"%>
    <div class="container header-container">
        <div class="header-start">
            <nav class="header-nav">
                <ul class="header-ul">
                    <li class="header-li">
                        <a class="header-link" href="home.do?command=to_home">${home}</a>
                    </li>
                    <li class="header-li">
                        <a class="header-link" href="menu.do?command=to_menu">${menu}</a>
                    </li>
                    <li class="header-li">
                        <a class="header-link" href="reviews.do?command=to_reviews">${reviews}</a>
                    </li>
                    <li class="header-li">
                        <c:choose>
                            <c:when test="${sessionScope.lang eq 'ru'}">
                                <a class="header-link" href="controller?command=change_locale&language=en">${translate}</a>
                            </c:when>
                            <c:when test="${sessionScope.lang eq 'en'}">
                                <a class="header-link" href="controller?command=change_locale&language=ru">${translate}</a>
                            </c:when>
                        </c:choose>
                    </li>
                </ul>
            </nav>
        </div>
        <div class="header-end">
            <div class = "header-user">
                <ul class="header-ul">
                    <li>
                        <form class="header-form" method="post" action="controller"
                              onsubmit="validateUserDishSearch(this); return false;">
                            <input type="hidden" name="command" value="search_dish_by_name" />
                            <input class="header-search" type="search" id="user-search-dish" name="dish_name"
                                   placeholder="${search}">
                        </form>
                    </li>
                    <li class="header-icon">
                        <i class="fas fa-shopping-cart"> </i>
                        <c:choose>
                            <c:when test="${sessionScope.cart_items == null || sessionScope.cart_items.size() == 0}">
                                <a class="header-icon-link" href="#cart">${cart}(<span class="header-items-count">0</span>)</a>
                            </c:when>
                            <c:when test="${sessionScope.cart_items != null && sessionScope.cart_items.size() > 0}">
                                <a class="header-icon-link" href="#cart">${cart}(<span class="header-items-count">${sessionScope.cart_items_count}</span>)</a>
                            </c:when>
                        </c:choose>
                    </li>
                    <li class="header-icon">
                        <i class="fas fa-user"> </i>
                        <a class="header-icon-link" href="account.do?command=to_account">${account}</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</header>
</body>
</html>
