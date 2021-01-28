<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Header</title>
    <style><%@include file="/css/navigation.css"%></style>
</head>
<fmt:setLocale value='<%=request.getSession().getAttribute("lang")%>'/>
<fmt:setBundle basename="lang" var="loc"/>
<fmt:message bundle="${loc}" key="lang.label.home" var="home"/>
<fmt:message bundle="${loc}" key="lang.label.login" var="login"/>
<fmt:message bundle="${loc}" key="lang.label.register" var="register"/>
<body>
<nav class="topnav">
    <a href="controller?command=to_home">${home}</a>
    <a href="controller?command=to_login">${login}</a>
    <a href="controller?command=to_register">${register}</a>
    <a href="controller?command=translate">${translate}</a>
</nav>
</body>
</html>
