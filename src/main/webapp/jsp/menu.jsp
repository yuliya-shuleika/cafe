<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ctg" uri="custom_tag" %>
<html>
<fmt:setLocale value='<%=request.getSession().getAttribute("lang")%>'/>
<fmt:setBundle basename="lang" var="loc"/>
<fmt:message bundle="${loc}" key="lang.label.grams" var="grams"/>
<fmt:message bundle="${loc}" key="lang.label.sushi" var="sushi"/>
<fmt:message bundle="${loc}" key="lang.label.sushi_set" var="sushi_set"/>
<fmt:message bundle="${loc}" key="lang.label.soup" var="soup"/>
<fmt:message bundle="${loc}" key="lang.label.noodles" var="noodles"/>
<fmt:message bundle="${loc}" key="lang.label.menu" var="menu"/>
<head>
    <title>${menu}</title>
    <style><%@include file="/css/menu.css"%></style>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"
            type="text/javascript"></script>
    <script><%@include file="/js/menu.js"%></script>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Rubik:wght@400;500&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@500&family=Rubik:wght@400;500&display=swap" rel="stylesheet">
</head>
<fmt:message bundle="${loc}" key="lang.label.add_to_cart" var="add_to_cart"/>
<fmt:message bundle="${loc}" key="lang.label.sort" var="sort"/>
<fmt:message bundle="${loc}" key="lang.label.by_price" var="by_price"/>
<fmt:message bundle="${loc}" key="lang.label.discount_first" var="discount_first"/>
<fmt:message bundle="${loc}" key="lang.label.show_filters" var="show_filters"/>
<fmt:message bundle="${loc}" key="lang.label.dishes_not_found" var="dish_not_found"/>
<fmt:message bundle="${loc}" key="lang.label.by_popularity" var="by_popularity"/>
<body>
    <c:choose>
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
                    <div class="menu-items-sort">
                        <p class="menu-sort-label">${sort}</p>
                        <form action="menu.do" method="post" class="menu-sorting-form">
                            <input type="hidden" name="command" value="sort_by_price">
                            <button class="menu-sort-criteria" type="submit">${by_price}</button>
                        </form>
                        <form action="menu.do" method="post" class="menu-sorting-form">
                            <input type="hidden" name="command" value="show_discounts">
                            <button class="menu-sort-criteria" type="submit">${discount_first}</button>
                        </form>
                        <form action="menu.do" method="post" class="menu-sorting-form">
                            <input type="hidden" name="command" value="sort_dishes_by_popularity">
                            <button class="menu-sort-criteria" type="submit">${by_popularity}</button>
                        </form>
                        <p class="menu-not-found-label">
                            <c:if test="${dishes_not_found != null}">
                                ${dish_not_found}
                            </c:if>
                        </p>
                    </div>
                <div class="menu-items">
                    <c:if test="${dishes_list != null && !dishes_list.isEmpty()}">
                        <c:forEach var="dish" items="${dishes_list}">
                            <div class="menu-item">
                                <c:if test="${sessionScope.user.getRole() eq 'USER'}">
                                    <c:if test="${!user_favorites.contains(dish)}">
                                        <div class="add-to-favorites">
                                            <input type="hidden" name="command" value="add_dish_to_favorites">
                                            <input type="hidden" name="dish_id" value="${dish.getDishId()}">
                                            <i class="fa fa-heart menu-favorite"></i>
                                        </div>
                                    </c:if>
                                    <c:if test="${user_favorites.contains(dish)}">
                                        <div class="add-to-favorites">
                                            <input type="hidden" name="command" value="delete_from_favorites">
                                            <input type="hidden" name="dish_id" value="${dish.getDishId()}">
                                            <i class="fa fa-heart menu-favorite-added"></i>
                                        </div>
                                    </c:if>
                                </c:if>
                                <img src="${pageContext.request.contextPath}${dish.getPictureName()}"
                                     onclick="watchDish('${dish.getName()}', '${dish.getCategory()}', '${dish.getPrice()}',
                                             '${dish.getDiscountPercents()}', '${dish.getDescription()}', '${dish.getWeight()}',
                                             '${pageContext.request.contextPath}${dish.getPictureName()}')"
                                     class="menu-item-picture" alt="${dish.getName()}">
                                <div class="menu-item-content">
                                    <h3 class="menu-item-title">${dish.getName()}</h3>
                                    <p class = "menu-item-description">${dish.getDescription()}</p>
                                </div>
                                <div class="menu-item-bottom">
                                    <div class="menu-item-price">
                                        <c:if test="${dish.getDiscountPercents() == 0}">
                                            <p class="menu-price-value">
                                                <fmt:formatNumber maxFractionDigits="2" value="${dish.getPrice()}"/>
                                            </p>
                                        </c:if>
                                        <c:if test="${dish.getDiscountPercents() > 0}">
                                            <strike class="menu-price-value">
                                                <fmt:formatNumber maxFractionDigits="2" value="${dish.getPrice()}"/>
                                            </strike>
                                        </c:if>
                                        <p class="menu-price-value-discount">
                                            <c:if test="${dish.getDiscountPercents() > 0}">
                                                <ctg:countPrice price="${dish.getPrice()}" discount="${dish.getDiscountPercents()}"/>
                                            </c:if>
                                        </p>
                                        <span class="menu-item-price-currency">$</span>
                                    </div>
                                    <div id="add-item-form">
                                        <input type="hidden" name="command" value="add_to_cart"/>
                                        <input type="hidden" name="dish_id" value="${dish.getDishId()}"/>
                                        <button class="menu-item-button" type="button">${add_to_cart}</button>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
                    <c:if test="${dishes_list == null || dishes_list.isEmpty()}">
                       <div class="empty-dishes-list">
                           <p class="empty-dishes-list-label">
                               ${dish_not_found}
                           </p>
                       </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="/jsp/footer/footer.jsp"%>
</body>
</html>
