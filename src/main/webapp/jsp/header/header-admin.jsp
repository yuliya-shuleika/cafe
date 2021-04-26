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
<fmt:message bundle="${loc}" key="lang.label.reviews" var="reviews"/>
<fmt:message bundle="${loc}" key="lang.label.promo_codes" var="promo_codes"/>
<fmt:message bundle="${loc}" key="lang.label.logout" var="logout"/>
<fmt:message bundle="${loc}" key="lang.label.translate" var="translate"/>
<body>
<header class="header-page">
    <div class="container header-container">
        <div class="header-start">
            <nav class="header-nav">
                <ul class="header-ul">
                    <li class="header-li">
                        <a class="header-link" href="users.do?command=to_users_list">${users}</a>
                    </li>
                    <li class="header-li">
                        <a class="header-link" href="dishes.do?command=to_dishes_list">${dishes}</a>
                    </li>
                    <li class="header-li">
                        <a class="header-link" href="reviews.do?command=to_reviews_list">${reviews}</a>
                    </li>
                    <li class="header-li">
                        <a class="header-link" href="promo-codes.do?command=to_promo_codes_list">${promo_codes}</a>
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
                    <li class="header-icon">
                        <a class="header-icon-link" href="controller?command=logout">${logout}</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</header>
</body>
</html>