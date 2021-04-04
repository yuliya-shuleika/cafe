<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Confirm order</title>
    <style><%@include file="/css/payment.css"%></style>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Rubik:wght@400;500&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@500&family=Rubik:wght@400;500&display=swap" rel="stylesheet">
</head>
<fmt:setLocale value='<%=request.getSession().getAttribute("lang")%>'/>
<fmt:setBundle basename="lang" var="loc"/>
<fmt:message bundle="${loc}" key="lang.label.order_confirmation" var="order_confirmation"/>
<fmt:message bundle="${loc}" key="lang.label.to_home" var="to_home"/>
<body>
    <div class="order-confirm-container">
        <div class="order-confirm">
            <img class="cats-picture" src="${pageContext.request.contextPath}/images/cats.png" alt="cats">
            <p class="confirm-info">${order_confirmation}</p>
            <form action="home.do" method="post">
                <input type="hidden" name="command" value="to_home">
                <button class="payment-confirm-button">${to_home}</button>
            </form>
        </div>
    </div>
</body>
</html>
