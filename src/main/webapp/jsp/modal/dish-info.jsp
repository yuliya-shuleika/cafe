<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %><html>
<head>
    <title>Dish info</title>
    <style><%@include file="/css/edit.css"%></style>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Rubik:wght@400;500&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@500&family=Rubik:wght@400;500&display=swap" rel="stylesheet">
</head>
<fmt:setLocale value='<%=request.getSession().getAttribute("lang")%>'/>
<fmt:setBundle basename="lang" var="loc"/>
<fmt:message bundle="${loc}" key="lang.label.dish" var="dish"/>
<fmt:message bundle="${loc}" key="lang.label.name" var="name"/>
<fmt:message bundle="${loc}" key="lang.label.price" var="price"/>
<fmt:message bundle="${loc}" key="lang.label.category" var="category"/>
<fmt:message bundle="${loc}" key="lang.label.ingredients" var="ingredients"/>
<fmt:message bundle="${loc}" key="lang.label.discount" var="discount"/>
<fmt:message bundle="${loc}" key="lang.label.weight_info" var="weight"/>
<body>
    <div class="edit" id="dish-info">
        <div class="edit-body">
            <div class="edit-content edit-content-dark edit-content-wide">
                <div class="add-promo">
                    <div class="edit-header">
                        <h3 class="edit-title edit-title-dark">${dish}</h3>
                        <a class="edit-close edit-close-dark" id="edit-close">x</a>
                    </div>
                    <div class="edit-info-container">
                        <div class="edit-picture-container">
                            <img src="${pageContext.request.contextPath}" alt="dish"
                                 id="dish-info-picture" class="edit-picture">
                        </div>
                        <div class="edit-description-container">
                            <div class="edit-general">
                                <span class="edit-info-label-dark">${name}:</span>
                                <p class="edit-info" id="dish-info-name"></p>
                            </div>
                            <div class="edit-general">
                                <span class="edit-info-label-dark">${category}:</span>
                                <p class="edit-info" id="dish-info-category"></p>
                            </div>
                            <div class="edit-general">
                                <span class="edit-info-label-dark">${price}:</span>
                                <p class="edit-info" id="dish-info-price"></p>
                            </div>
                            <div class="edit-general">
                                <span class="edit-info-label-dark">${ingredients}:</span>
                                <p class="edit-info" id="dish-info-description"></p>
                            </div>
                            <div class="edit-general">
                                <span class="edit-info-label-dark">${weight}:</span>
                                <p class="edit-info" id="dish-info-weight"></p>
                            </div>
                        </div>
                        <div class="edit-info-footer"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
