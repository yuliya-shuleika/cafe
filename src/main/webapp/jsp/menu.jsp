<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Menu</title>
    <style><%@include file="/css/menu.css"%></style>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Rubik:wght@400;500&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@500&family=Rubik:wght@400;500&display=swap" rel="stylesheet">
</head>
<fmt:message bundle="${loc}" key="lang.label.menu" var="menu"/>
<body>
    <c:choose>
        <c:when test="${sessionScope.user.getRole() eq 'ADMIN'}">
            <%@ include file="/jsp/header/header-admin.jsp"%>
        </c:when>
        <c:when test="${sessionScope.user.getRole() eq 'USER'}">
            <%@ include file="/jsp/header/header-user.jsp"%>
        </c:when>
        <c:when test="${sessionScope.user.getRole() == null}">
            <%@ include file="/jsp/header/header.jsp"%>
        </c:when>
    </c:choose>
    <div class="container">
        <header class="menu-title">
            <h2>${menu}</h2>
        </header>
        <nav>

        </nav>
    </div>
</body>
</html>
