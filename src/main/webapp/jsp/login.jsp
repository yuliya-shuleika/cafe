<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login</title>
    <style><%@include file="/css/login.css"%></style>
</head>
<fmt:setLocale value='<%=request.getSession().getAttribute("lang")%>'/>
<fmt:setBundle basename="lang" var="loc"/>
<fmt:message bundle="${loc}" key="lang.label.home" var="home"/>
<fmt:message bundle="${loc}" key="lang.label.login" var="login"/>
<fmt:message bundle="${loc}" key="lang.label.management" var="management"/>
<fmt:message bundle="${loc}" key="lang.label.register" var="register"/>
<fmt:message bundle="${loc}" key="lang.label.users_list" var="users_list"/>
<fmt:message bundle="${loc}" key="lang.label.translate" var="translate"/>
<body class="align">
    <c:choose>
        <c:when test="${sessionScope.user.getRole() eq 'ADMIN'}">
            <%@ include file="header-admin.jsp"%>
        </c:when>
        <c:when test="${sessionScope.user.getRole() eq 'USER'}">
            <%@ include file="header.jsp"%>
        </c:when>
        <c:when test="${sessionScope.user.getRole() == null}">
            <%@ include file="header.jsp"%>
        </c:when>
    </c:choose>
<div class="grid">
    <form method="post" class="form login" action="controller">
        <input type="hidden" name="command" value="login" />
        <header class="login__header">
            <h3 class="login__title">${login}</h3>
        </header>
        <div class="login__body">
            <div class="form__field">
                <input type="email" name = "email" placeholder="Email" required>
            </div>
            <div class="form__field">
                <input type="password" name = "password" placeholder="Password" required pattern="[A-Za-z0-9_]{5,20}">
            </div>
        </div>
        <footer class="login__footer">
            <input type="submit" value="Login">
            <p><a href="controller?command=to_register">${register}</a></p>
        </footer>
    </form>
    <p>${error}</p>
</div>
</body>
</html>