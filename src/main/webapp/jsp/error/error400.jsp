<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Confirm order</title>
    <style><%@include file="/css/error.css"%></style>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Rubik:wght@400;500&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@500&family=Rubik:wght@400;500&display=swap" rel="stylesheet">
</head>
<fmt:setLocale value='<%=request.getSession().getAttribute("lang")%>'/>
<fmt:setBundle basename="lang" var="loc"/>
<fmt:message bundle="${loc}" key="lang.label.error400" var="error400"/>
<fmt:message bundle="${loc}" key="lang.label.to_home" var="to_home"/>
<fmt:message bundle="${loc}" key="lang.label.users_list" var="users_list"/>
<body>
<div class="error-container">
    <div class="error">
        <img class="cat-picture" src="${pageContext.request.contextPath}/images/sad_cat.png" alt="cats">
        <p class="error-info">401 - ${error400}</p>
        <form action="home.do" method="post">
            <c:choose>
                <c:when test="${sessionScope.user.getRole() eq 'ADMIN'}">
                    <input type="hidden" name="command" value="to_users_list">
                    <button class="error-button">${users_list}</button>
                </c:when>
                <c:when test="${sessionScope.user == null || sessionScope.user.getRole() eq 'USER'}">
                    <input type="hidden" name="command" value="to_home">
                    <button class="error-button">${to_home}</button>
                </c:when>
            </c:choose>
        </form>
    </div>
</div>
</body>
</html>
