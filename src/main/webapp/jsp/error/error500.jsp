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
<fmt:message bundle="${loc}" key="lang.label.error500" var="error500"/>
<body>
    <div class="error-container">
        <div class="error">
            <img class="cat-picture" src="${pageContext.request.contextPath}/images/sad_cat.png" alt="cats">
            <p class="error-info">500 - ${error500}</p>
        </div>
    </div>
</body>
</html>
