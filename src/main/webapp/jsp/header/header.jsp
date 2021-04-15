<%@ page contentType="text/html;charset=UTF-8" language="java"%>
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
                            <a class="header-link" href="home.do?command=to_home">${home}</a>
                        </li>
                        <li class="header-li">
                            <a class="header-link" href="menu.do?command=to_menu">${menu}</a>
                        </li>
                        <li class="header-li">
                            <a class="header-link" href="reviews.do?command=to_reviews">${reviews}</a>
                        </li>
                        <li class="header-li">
                            <a class="header-link" href="about.do?command=to_about">${about}</a>
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
                                <input type="hidden" name="command" value="search_dish_by_name" />
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
                            <a class="header-link" id="open_login" href="#">${login}</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </header>
    <%@ include file="/jsp/modal/cart.jsp"%>
    <%@ include file="/jsp/modal/login.jsp"%>
    <%@ include file="/jsp/modal/register.jsp"%>
    <c:if test="${register_error_message != null}">
        <script>
            let login = document.getElementById('register')
            login.style.display = 'block'
        </script>
    </c:if>
    <c:if test="${login_error_message != null}">
        <script>
            let login = document.getElementById('login')
            login.style.display = 'block'
        </script>
    </c:if>
    <script>
        let login = document.getElementById('login')
        let register = document.getElementById('register')
        $('.login-close').click(function () {
            login.style.display = 'none';
            register.style.display = 'none';
        });
        $('#to_login').click(function () {
            login.style.display = 'block';
            register.style.display = 'none';
        });
        $('#to_register').click(function () {
            register.style.display = 'block';
            login.style.display = 'none';
        });
        $('#open_login').click(function () {
            login.style.display = 'block';
        });
    </script>
</body>
</html>
