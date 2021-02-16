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
                        <a class="header-link" href="#">${reviews}</a>
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
                        <form class="header-form" method="post">
                            <input class="header-search" type="search" id="mySearch" name="q"
                                   placeholder="Search..." required
                                   minlength="1" maxlength="20">
                        </form>
                    </li>
                    <li class="header-icon">
                        <i class="fas fa-shopping-cart"> </i>
                        <a class="header-icon-link" href="controller?command=to_register">${cart}</a>
                    </li>
                    <li class="header-icon">
                        <i class="fas fa-user"> </i>
                        <a class="header-icon-link" href="controller?command=to_register">${account}</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</header>
</body>
</html>