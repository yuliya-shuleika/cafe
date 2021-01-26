<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Home</title>
    <style><%@include file="/css/login.css"%></style>

</head>
<fmt:setLocale value='<%=request.getSession().getAttribute("lang")%>'/>
<fmt:setBundle basename="lang" var="loc"/>
<fmt:message bundle="${loc}" key="lang.label.home" var="home"/>
<fmt:message bundle="${loc}" key="lang.label.login" var="login"/>
<fmt:message bundle="${loc}" key="lang.label.register" var="register"/>
<fmt:message bundle="${loc}" key="lang.label.welcome" var="welcome"/>
<fmt:message bundle="${loc}" key="lang.label.hello" var="hello"/>
<fmt:message bundle="${loc}" key="lang.label.logout" var="logout"/>
<fmt:message bundle="${loc}" key="lang.label.translate" var="translate"/>
<body>
<%@ include file="header.jsp"%>
<h3>${welcome}</h3>
<hr/>
${user}, ${hello}!
<hr/>
<a href="controller?command=logout">${logout}</a>
</body>
</html>
