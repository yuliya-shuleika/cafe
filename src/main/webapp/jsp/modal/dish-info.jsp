<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %><html>
<head>
    <title>Dish info</title>
</head>
<fmt:setLocale value='<%=request.getSession().getAttribute("lang")%>'/>
<fmt:setBundle basename="lang" var="loc"/>
<fmt:message bundle="${loc}" key="lang.label.dish" var="dish"/>
<fmt:message bundle="${loc}" key="lang.label.name" var="name"/>
<fmt:message bundle="${loc}" key="lang.label.price" var="price"/>
<fmt:message bundle="${loc}" key="lang.label.category" var="category"/>
<fmt:message bundle="${loc}" key="lang.label.description" var="description"/>
<fmt:message bundle="${loc}" key="lang.label.discount" var="discount"/>
<fmt:message bundle="${loc}" key="lang.label.weight" var="weight"/>
<body>
<div class="edit" id="add-dish">
    <div class="edit-body">
        <div class="edit-content">
            <div class="add-promo">
                <div class="edit-header">
                    <h3 class="edit-title">${dish}</h3>
                    <a class="edit-close" id="edit-close">x</a>
                </div>
                <div class="edit-form">
                    <div class="edit-picture-container">
                        <img src="${pageContext.request.contextPath}" alt="dish">
                    </div>
                    <div class="edit-description-container">
                        <div class="edit-general">
                            <span class="edit-label">${name}</span>
                            <p class="edit-info" id="dish-info-name"></p>
                        </div>
                        <div class="edit-general">
                            <span class="edit-label">${category}</span>
                            <p class="edit-info" id="dish-info-category"></p>
                        </div>
                        <div class="edit-general">
                            <span class="edit-label">${price}</span>
                            <p class="edit-info" id="dish-info-price"></p>
                        </div>
                        <div class="edit-general">
                            <span class="edit-label">${discount}</span>
                            <p class="edit-info" id="review-info-discount"></p>
                        </div>
                        <div class="edit-general">
                            <span class="edit-label">${description}</span>
                            <p class="edit-info" id="review-info-description"></p>
                        </div>
                        <div class="edit-general">
                            <span class="edit-label">${weight}</span>
                            <p class="edit-info" id="review-info-weight"></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
