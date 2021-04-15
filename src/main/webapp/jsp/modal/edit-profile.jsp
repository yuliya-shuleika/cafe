<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Edit profile</title>
    <style><%@include file="/css/edit.css"%></style>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Rubik:wght@400;500&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@500&family=Rubik:wght@400;500&display=swap" rel="stylesheet">
</head>
<fmt:setLocale value='<%=request.getSession().getAttribute("lang")%>'/>
<fmt:setBundle basename="lang" var="loc"/>
<fmt:message bundle="${loc}" key="lang.label.email" var="email"/>
<fmt:message bundle="${loc}" key="lang.label.username" var="username"/>
<fmt:message bundle="${loc}" key="lang.label.account" var="account"/>
<fmt:message bundle="${loc}" key="lang.label.photo" var="photo"/>
<fmt:message bundle="${loc}" key="lang.label.load_file" var="load_file"/>
<fmt:message bundle="${loc}" key="lang.label.submit" var="submit"/>
<body>
<div class="edit" id="edit-profile">
    <div class="edit-body">
        <div class="edit-content">
            <div class="add-promo">
                <form action="controller?command=edit_account&user_id=${sessionScope.user.userId}&user_avatar=${sessionScope.user.avatar}"
                      method="post" enctype="multipart/form-data">
                    <div class="edit-header">
                        <h3 class="edit-title">${account}</h3>
                        <a class="edit-close" id="edit-close">x</a>
                    </div>
                    <div class="edit-form">
                        <div class="edit-general">
                            <span class="edit-label">${username}</span>
                            <input class="edit-general-input" type="text" placeholder="${username}" name="user_name"
                                   required pattern="[A-Za-zА-Яа-яёЁ]{3,25}" value="${sessionScope.user.name}">
                        </div>
                        <div class="edit-general">
                            <span class="edit-label">${email}</span>
                            <input class="edit-general-input" type="email" placeholder="${email}"
                                   required name="user_email"
                                   value="${sessionScope.user.email}">
                        </div>
                        <div class="edit-general">
                            <span class="edit-label">${photo}</span>
                            <input class="edit-file-input" type="file"
                                   name="user_avatar" id="add-dish-photo">
                            <label for="add-dish-photo" class="load-file-input">
                                <span class="load-file">${load_file}</span>
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
