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
<body>
<div class="edit" id="add-dish">
    <div class="edit-body">
        <div class="edit-content">
            <div class="add-promo">
                <form action="controller?command=add_dish_to_menu" method="post" enctype="multipart/form-data">
                    <div class="edit-header">
                        <h3 class="edit-title">${dish}</h3>
                        <a class="edit-close" id="edit-close">x</a>
                    </div>
                    <div class="edit-form">
                        <div class="edit-general">
                            <span class="edit-label">${name}</span>
                            <input class="edit-general-input" type="text" placeholder="${name}" name="dish_name"
                                   required pattern="[A-Za-zА-Яа-яёЁ\s]{5,45}">
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
                            <input class="edit-general-input" type="text" placeholder="${price}"
                                   required pattern="[1-9][0-9]{0,4}\.[0-9]?[1-9]" name="dish_price">
                        </div>
                        <div class="edit-general">
                            <span class="edit-label">${discount_percents}</span>
                            <input class="edit-general-input" type="text" placeholder="${discount}"
                                   required pattern="[0-9]{1,2}" name="dish_discount">
                        </div>
                        <div class="edit-general">
                            <span class="edit-label">${description}</span>
                            <textarea class="edit-text-short" name="dish_description" maxlength="300"></textarea>
                        </div>
                        <div class="edit-general">
                            <span class="edit-label">${weight}</span>
                            <input class="edit-general-input" type="text" placeholder="${weight}"
                                   required pattern="[1-9][0-9]{0,5}" name="dish_weight">
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
                        <button class="edit-submit" type="submit">${submit}</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
