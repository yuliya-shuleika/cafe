<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<fmt:setLocale value='<%=request.getSession().getAttribute("lang")%>'/>
<fmt:setBundle basename="lang" var="loc"/>
<fmt:message bundle="${loc}" key="lang.label.fill_fields_correct" var="fill_fields_correct"/>
<head>
    <title>Promo</title>
    <style><%@include file="/css/edit.css"%></style>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Rubik:wght@400;500&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@500&family=Rubik:wght@400;500&display=swap" rel="stylesheet">
</head>
<fmt:message bundle="${loc}" key="lang.label.name" var="name"/>
<fmt:message bundle="${loc}" key="lang.label.discount_percents" var="discount_percents"/>
<fmt:message bundle="${loc}" key="lang.label.submit" var="submit"/>
<fmt:message bundle="${loc}" key="lang.label.discount" var="discount"/>
<body>
<div class="edit" id="add-promo">
    <div class="edit-body">
        <div class="edit-content">
            <div class="add-promo">
                <form onsubmit="validatePromoCodeAddFields(this); return false;"
                      action="promo-codes.do" method="post">
                    <div class="edit-header">
                        <h3 class="edit-title">${promo_code}</h3>
                        <a class="edit-close" id="edit-close">x</a>
                    </div>
                    <div class="edit-form">
                        <input type="hidden" name="command" value="add_promo_code">
                        <div class="edit-general">
                            <span class="edit-label">${name}</span>
                            <c:choose>
                                <c:when test="${promo_code_fields != null && promo_code_fields.containsKey('promo_code_name')}">
                                    <input class="edit-general-input" type="text" placeholder="${name}" name="promo_code_name"
                                    value="${promo_code_fields.get('promo_code_name')}" id="add-promo-code-name">
                                </c:when>
                                <c:when test="${promo_code_fields == null || !promo_code_fields.containsKey('promo_code_name')}">
                                    <input class="edit-general-input" type="text" placeholder="${name}" name="promo_code_name"
                                           id="add-promo-code-name">
                                </c:when>
                            </c:choose>
                        </div>
                        <div class="edit-general">
                            <span class="edit-label">${discount_percents}</span>
                            <c:choose>
                                <c:when test="${promo_code_fields != null && promo_code_fields.containsKey('promo_code_discount_percents')}">
                                    <input class="edit-general-input" type="text" placeholder="${discount}"
                                           value="${promo_code_fields.get('promo_code_discount_percents')}"
                                           name="promo_code_discount_percents" id="add-promo-code-discount-percents">
                                </c:when>
                                <c:when test="${promo_code_fields == null || !promo_code_fields.containsKey('promo_code_discount_percents')}">
                                    <input class="edit-general-input" type="text" placeholder="${discount}"
                                           name="promo_code_discount_percents" id="add-promo-code-discount-percents">
                                </c:when>
                            </c:choose>
                        </div>
                    </div>
                    <div class="edit-footer">
                        <c:if test="${edit_error_message != null}">
                            <p class="edit-error-message" id="add-promo-code-error-message">${fill_fields_correct}</p>
                        </c:if>
                        <button class="edit-submit" type="submit">${submit}</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
