<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Header</title>
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
<body>
    <header class="header-page">
        <div class="container header-container">
            <div class="header-start">
                <nav class="header-nav">
                    <ul class="header-ul">
                        <li class="header-li">
                            <a class="header-link" href="controller?command=to_home">${home}</a>
                        </li>
                        <li class="header-li">
                            <a class="header-link" href="controller?command=to_menu">${menu}</a>
                        </li>
                        <li class="header-li">
                            <a class="header-link" href="controller?command=to_reviews">${reviews}</a>
                        </li>
                        <li class="header-li">
                            <a class="header-link" href="#">${about}</a>
                        </li>
                        <li class="header-li">
                            <a class="header-link" href="controller?command=change_locale">${translate}</a>
                        </li>
                    </ul>
                </nav>
            </div>
            <div class="header-end">
                <div class = "header-user">
                    <ul class="header-ul">
                        <li>
                            <form class="header-form" method="post" action="controller">
                                <input type="hidden" name="command" value="search_dish" />
                                <input class="header-search" type="search" id="mySearch" name="dish_name"
                                   placeholder="Search..." required
                                   minlength="1" maxlength="20">
                            </form>
                        </li>
                        <li class="header-icon">
                            <i class="fas fa-shopping-cart"> </i>
                            <c:if test="${sessionScope.cart_items == null || sessionScope.cart_items.size() == 0}">
                                <a class="header-icon-link" href="#cart">${cart}(<span class="header-items-count">0</span>)</a>
                            </c:if>
                            <c:if test="${sessionScope.cart_items != null && sessionScope.cart_items.size() > 0}">
                                <a class="header-icon-link" href="#cart">${cart}(<span class="header-items-count">${sessionScope.cart_items_count}</span>)</a>
                            </c:if>
                        </li>
                        <li class="header-li">
                            <a class="header-link" href="#login">${login}</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </header>
    <%@ include file="/jsp/modal/cart.jsp"%>
    <%@ include file="/jsp/modal/login.jsp"%>
    <%@ include file="/jsp/modal/register.jsp"%>
</body>
</html>
