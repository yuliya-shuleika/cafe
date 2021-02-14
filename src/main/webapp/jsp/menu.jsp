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
<fmt:setLocale value='<%=request.getSession().getAttribute("lang")%>'/>
<fmt:setBundle basename="lang" var="loc"/>
<fmt:message bundle="${loc}" key="lang.label.menu" var="menu"/>
<fmt:message bundle="${loc}" key="lang.label.sushi" var="sushi"/>
<fmt:message bundle="${loc}" key="lang.label.sushi_set" var="sushi_set"/>
<fmt:message bundle="${loc}" key="lang.label.soup" var="soup"/>
<fmt:message bundle="${loc}" key="lang.label.noodles" var="noodles"/>
<fmt:message bundle="${loc}" key="lang.label.add_to_cart" var="add_to_cart"/>
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
    <div class="menu-container">
        <header class="menu-header">
            <h2 class="menu-title">${menu}</h2>
        </header>
        <div class="menu-content">
            <ul class="menu-categories">
                <form method="post" action="controller">
                    <li class="menu-categories-item">
                        <input type="hidden" name="command" value="choose_category"/>
                        <input type="hidden" name="dish_category" value="sushi"/>
                        <button class="menu-categories-button">${sushi}</button>
                    </li>
                </form>
                <form method="post" action="controller">
                    <li class="menu-categories-item">
                        <input type="hidden" name="command" value="choose_category"/>
                        <input type="hidden" name="dish_category" value="sushi_set"/>
                        <button class="menu-categories-button">${sushi_set}</button>
                    </li>
                </form>
                <form method="post" action="controller">
                    <li class="menu-categories-item">
                        <input type="hidden" name="command" value="choose_category"/>
                        <input type="hidden" name="dish_category" value="soup"/>
                        <button class="menu-categories-button">${soup}</button>
                    </li>
                </form>
                <form method="post" action="controller">
                    <li class="menu-categories-item">
                        <input type="hidden" name="command" value="choose_category"/>
                        <input type="hidden" name="dish_category" value="noodles"/>
                        <button class="menu-categories-button">${noodles}</button>
                    </li>
                </form>
                </ul>
            <div class="menu-panel">
                <div class="menu-filters">
                </div>
                <div class="menu-items">
                    <c:forEach var="dish" items="${dishes_list}">
                    <div class="menu-item">
                        <img src="${pageContext.request.contextPath}${dish.getPictureName()}" alt="${dish.getName()}">
                        <div class="menu-item-content">
                            <h3 class="menu-item-title">${dish.getName()}</h3>
                            <p class = "menu-item-description">something</p>
                        </div>
                        <div class="menu-title-bottom">
                            <div class="menu-item-price">
                                <span class="menu-item-price-value">${dish.getPrice()}</span>
                            </div>
                            <c:choose>
                                <c:when test="${sessionScope.user.getRole() eq 'USER'}">
                                    <form method="post" action="controller">
                                        <button class="menu-item-button" type="submit">${add_to_cart}</button>
                                    </form>
                                </c:when>
                                <c:when test="${sessionScope.user.getRole() == null}">
                                    <form method="post" action="controller">
                                        <input type="hidden" name="command" value="add_to_guest_cart"/>
                                        <input type="hidden" name="dish_id" value="${dish.getDishId()}"/>
                                        <button class="menu-item-button" type="submit">${add_to_cart}</button>
                                    </form>
                                </c:when>
                            </c:choose>
                        </div>
                    </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
