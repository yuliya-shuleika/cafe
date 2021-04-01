<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Account</title>

</head>
<fmt:setLocale value='<%=request.getSession().getAttribute("lang")%>'/>
<fmt:setBundle basename="lang" var="loc"/>
<fmt:message bundle="${loc}" key="lang.label.name" var="name"/>
<fmt:message bundle="${loc}" key="lang.label.email" var="email"/>
<fmt:message bundle="${loc}" key="lang.label.edit" var="edit"/>
<fmt:message bundle="${loc}" key="lang.label.repeat" var="repeat"/>
<fmt:message bundle="${loc}" key="lang.label.my_orders" var="my_orders"/>
<body>
    <%@ include file="/jsp/header/header-user.jsp"%>
    <div class="account-container">
        <div class="account-header">
            <h2 class="account-title"></h2>
        </div>
        <div class="account-body">
            <div class="account-image">
                <img src="${pageContext.request.contextPath}/images/guest.jpg" alt="user">
            </div>
            <div class="account-info">
                <p class="account-name">${name}: ${sessionScope.user.getName()}</p>
                <p class="account-email">${email}: ${sessionScope.user.getEmail()}</p>
                <button class="account-edit">${edit}</button>
            </div>
        </div>
        <div class="account-orders">
            <c:forEach var="order" items="${user_orders}">
                <div class="account-orders">
                    <div class="account-orders">
                        <h3>${my_orders}</h3>
                    </div>
                    <div class="order-info">
                        <p class="order-date"><fmt:formatDate value="${order.getDate()}" type = "date" /></p>
                        <p class="order-dishes">
                            <c:forEach var="dish" items="${order.getOrderedDishes()}">
                                ${dish.getKey().getName()} x ${dish.getValue()}<br>
                            </c:forEach>
                        </p>
                        <div class="order-price-container">
                            <p class="order-price">${order.getPrice()}</p>
                            <span class="total-currency">$</span>
                        </div>
                        <div class="order-repeat">
                            <button class="order-repeat-button">${repeat}</button>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</body>
</html>
