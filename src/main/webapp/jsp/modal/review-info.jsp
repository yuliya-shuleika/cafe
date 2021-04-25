<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Review info</title>
    <style><%@include file="/css/edit.css"%></style>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Rubik:wght@400;500&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@500&family=Rubik:wght@400;500&display=swap" rel="stylesheet">
</head>
<fmt:setLocale value='<%=request.getSession().getAttribute("lang")%>'/>
<fmt:setBundle basename="lang" var="loc"/>
<fmt:message bundle="${loc}" key="lang.label.review" var="review"/>
<fmt:message bundle="${loc}" key="lang.label.header" var="review_header"/>
<fmt:message bundle="${loc}" key="lang.label.text" var="text"/>
<fmt:message bundle="${loc}" key="lang.label.rating" var="rating"/>
<body>
<div class="edit" id="review-info">
    <div class="edit-body">
        <div class="edit-content">
            <div class="add-promo">
                <div class="edit-header">
                    <h3 class="edit-title">${review}</h3>
                    <a class="edit-close">x</a>
                </div>
                <div class="edit-form">
                    <div class="edit-general">
                        <span class="edit-label">${review_header}</span>
                        <p class="edit-info" id="review-info-header"></p>
                    </div>
                    <div class="edit-general">
                        <span class="edit-label">${rating}</span>
                        <p class="edit-info" id="review-info-rating"></p>
                    </div>
                    <div class="edit-general">
                        <span class="edit-label">${text}</span>
                        <p class="edit-info" id="review-info-text"></p>
                    </div>
                </div>
                <div class="edit-info-footer"></div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
