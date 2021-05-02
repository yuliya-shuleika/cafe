<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Dish</title>
    <style><%@include file="/css/edit.css"%></style>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Rubik:wght@400;500&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@500&family=Rubik:wght@400;500&display=swap" rel="stylesheet">
</head>
<fmt:setLocale value='<%=request.getSession().getAttribute("lang")%>'/>
<fmt:setBundle basename="lang" var="loc"/>
<fmt:message bundle="${loc}" key="lang.label.dish" var="dish"/>
<fmt:message bundle="${loc}" key="lang.label.name" var="name"/>
<fmt:message bundle="${loc}" key="lang.label.category" var="category"/>
<fmt:message bundle="${loc}" key="lang.label.price" var="price"/>
<fmt:message bundle="${loc}" key="lang.label.discount_percents" var="discount_percents"/>
<fmt:message bundle="${loc}" key="lang.label.photo" var="photo"/>
<fmt:message bundle="${loc}" key="lang.label.submit" var="submit"/>
<fmt:message bundle="${loc}" key="lang.label.discount" var="discount"/>
<fmt:message bundle="${loc}" key="lang.label.sushi" var="sushi"/>
<fmt:message bundle="${loc}" key="lang.label.sushi_set" var="sushi_set"/>
<fmt:message bundle="${loc}" key="lang.label.soup" var="soup"/>
<fmt:message bundle="${loc}" key="lang.label.noodles" var="noodles"/>
<fmt:message bundle="${loc}" key="lang.label.weight" var="weight"/>
<fmt:message bundle="${loc}" key="lang.label.description" var="description"/>
<fmt:message bundle="${loc}" key="lang.label.fill_fields_correct" var="fill_fields_correct"/>
<body>
    <div class="edit" id="add-dish">
        <div class="edit-body">
            <div class="edit-content">
                <div class="add-promo">
                    <form action="controller?command=add_dish_to_menu" method="post"
                          enctype="multipart/form-data" onsubmit="validateDishAddFields(this); return false;">
                        <div class="edit-header">
                            <h3 class="edit-title">${dish}</h3>
                            <a class="edit-close" id="edit-close">x</a>
                        </div>
                        <div class="edit-form">
                            <div class="edit-general">
                                <span class="edit-label">${name}</span>
                                <c:choose>
                                    <c:when test="${dish_fields != null && dish_fields.containsKey('dish_name')}">
                                        <input class="edit-general-input" type="text" placeholder="${name}" name="dish_name"
                                               value="${dish_fields.get('dish_name')}" id="add-dish-name">
                                    </c:when>
                                    <c:when test="${dish_fields == null || !dish_fields.containsKey('dish_name')}">
                                        <input class="edit-general-input" type="text" placeholder="${name}"
                                               name="dish_name" id="add-dish-name">
                                    </c:when>
                                </c:choose>
                            </div>
                            <div class="edit-general">
                                <span class="edit-label">${category}</span>
                                <select class="edit-select-input" name="dish_category" required>
                                    <option value="sushi">${sushi}</option>
                                    <option value="sushi_set">${sushi_set}</option>
                                    <option value="soup">${soup}</option>
                                    <option value="noodles">${noodles}</option>
                                </select>
                            </div>
                            <div class="edit-general">
                                <span class="edit-label">${price}</span>
                                <c:choose>
                                    <c:when test="${dish_fields != null && dish_fields.containsKey('dish_price')}">
                                        <input class="edit-general-input" type="text" placeholder="${price}"
                                               name="dish_price" id="add-dish-price"
                                               value="${dish_fields.get('dish_price')}">
                                    </c:when>
                                    <c:when test="${dish_fields == null || !dish_fields.containsKey('dish_price')}">
                                        <input class="edit-general-input" type="text" placeholder="${price}"
                                                name="dish_price" id="add-dish-price">
                                    </c:when>
                                </c:choose>
                            </div>
                            <div class="edit-general">
                                <span class="edit-label">${discount_percents}</span>
                                <c:choose>
                                    <c:when test="${dish_fields != null && dish_fields.containsKey('dish_discount')}">
                                        <input class="edit-general-input" type="text" placeholder="${discount}"
                                               name="dish_discount" id="add-dish-discount"
                                               value="${dish_fields.get('dish_discount')}">
                                    </c:when>
                                    <c:when test="${dish_fields == null || !dish_fields.containsKey('dish_discount')}">
                                        <input class="edit-general-input" type="text" placeholder="${discount}"
                                               name="dish_discount" id="add-dish-discount">
                                    </c:when>
                                </c:choose>
                            </div>
                            <div class="edit-general">
                                <span class="edit-label">${description}</span>
                                <c:choose>
                                    <c:when test="${dish_fields != null && dish_fields.containsKey('dish_description')}">
                                        <textarea class="edit-text-short" id="add-dish-description"
                                                  name="dish_description">${dish_fields.get('dish_description')}</textarea>
                                    </c:when>
                                    <c:when test="${dish_fields == null || !dish_fields.containsKey('dish_description')}">
                                        <textarea class="edit-text-short"
                                                  name="dish_description" id="add-dish-description"></textarea>
                                    </c:when>
                                </c:choose>
                            </div>
                            <div class="edit-general">
                                <span class="edit-label">${weight}</span>
                                <c:choose>
                                    <c:when test="${dish_fields != null && dish_fields.containsKey('dish_weight')}">
                                        <input class="edit-general-input" type="text" placeholder="${weight}"
                                               name="dish_weight" id="add-dish-weight"
                                               value="${dish_fields.get('dish_weight')}">
                                    </c:when>
                                    <c:when test="${dish_fields == null || !dish_fields.containsKey('dish_weight')}">
                                        <input class="edit-general-input" type="text" placeholder="${weight}"
                                               name="dish_weight" id="add-dish-weight">
                                    </c:when>
                                </c:choose>
                            </div>
                            <div class="edit-general">
                                <span class="edit-label">${photo}</span>
                                <input class="edit-file-input" type="file" required
                                       name="dish_picture_name" id="add-dish-photo">
                                <label for="add-dish-photo" class="load-file-input">
                                    <span class="load-file">Загрузить файл</span>
                                    <i class="icon fa fa-upload"></i>
                                </label>
                            </div>
                        </div>
                        <div class="edit-footer">
                            <c:if test="${edit_error_message != null}">
                                <p class="edit-error-message" id="add-dish-error-message">${fill_fields_correct}</p>
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
