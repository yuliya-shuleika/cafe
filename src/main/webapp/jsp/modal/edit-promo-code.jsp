<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Promo</title>
    <style><%@include file="/css/edit.css"%></style>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Rubik:wght@400;500&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@500&family=Rubik:wght@400;500&display=swap" rel="stylesheet">
</head>
<fmt:setLocale value='<%=request.getSession().getAttribute("lang")%>'/>
<fmt:setBundle basename="lang" var="loc"/>
<fmt:message bundle="${loc}" key="lang.label.name" var="name"/>
<fmt:message bundle="${loc}" key="lang.label.discount_percents" var="discount_percents"/>
<fmt:message bundle="${loc}" key="lang.label.submit" var="submit"/>
<fmt:message bundle="${loc}" key="lang.label.discount" var="discount"/>
<body>
<div class="edit" id="edit-promo">
    <div class="edit-body">
        <div class="edit-content">
            <div class="add-promo">
                <form action="controller" method="post">
                    <div class="edit-header">
                        <h3 class="edit-title">${promo_code}</h3>
                        <a class="edit-close" id="edit-close">x</a>
                    </div>
                    <div class="edit-form">
                        <input type="hidden" name="command" value="edit_promo_code">
                        <input type="hidden" name="promo_code_id" value="${selected_promo_code.getPromoCodeId()}">
                        <div class="edit-general">
                            <span class="edit-label">${name}</span>
                            <input class="edit-general-input" type="text" placeholder="${name}" name="promo_code_name"
                                   required pattern="[A-Za-zА-Яа-яёЁ0-9_]{5,45}" value="${selected_promo_code.getName()}">
                        </div>
                        <div class="edit-general">
                            <span class="edit-label">${discount_percents}</span>
                            <input class="edit-general-input" type="text" placeholder="${discount}"
                                   required pattern="[1-9][0-9]?" name="promo_code_discount_percents"
                                   value="${selected_promo_code.getDiscountPercents()}">
                        </div>
                        <input type="hidden" name="promo_code_id" value="${selected_promo_code.getPromoCodeId()}">
                    </div>
                    <div class="edit-footer">
                        <button class="edit-submit" type="submit">${submit}</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>