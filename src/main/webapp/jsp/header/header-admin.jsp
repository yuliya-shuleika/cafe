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
<fmt:message bundle="${loc}" key="lang.label.users" var="users"/>
<fmt:message bundle="${loc}" key="lang.label.dishes" var="dishes"/>
<fmt:message bundle="${loc}" key="lang.label.orders" var="orders"/>
<body>
<header class="header-page">
    <div class="container header-container">
        <div class="header-start">
            <nav class="header-nav">
                <ul class="header-ul">
                    <li class="header-li">
                        <a class="header-link" href="controller?command=to_users">${users}</a>
                    </li>
                    <li class="header-li">
                        <a class="header-link" href="controller?command=to_dishes">${dishes}</a>
                    </li>
                    <li class="header-li">
                        <a class="header-link" href="controller?command=to_dishes">${orders}</a>
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
                    <li class="header-icon">
                        <a class="header-icon-link" href="controller?command=to_register">${logout}</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</header>
</body>
</html>