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
<fmt:message bundle="${loc}" key="lang.label.fill_fields_correct" var="fill_fields_correct"/>
<body>
<div class="edit" id="edit-profile">
    <div class="edit-body">
        <div class="edit-content edit-content-dark">
            <div class="add-promo">
                <form action="controller?command=edit_account&user_id=${sessionScope.user.userId}&user_avatar=${sessionScope.user.avatar}"
                      method="post" enctype="multipart/form-data" onsubmit="validateAccountEditForm(this); return false;">
                    <div class="edit-header">
                        <h3 class="edit-title edit-title-dark">${account}</h3>
                        <a class="edit-close edit-close-dark" id="edit-close">x</a>
                    </div>
                    <div class="edit-form">
                        <div class="edit-general">
                            <span class="edit-label-dark">${username}</span>
                            <c:choose>
                                <c:when test="${user_address != null}">
                                    <input class="edit-general-input edit-general-input-dark" type="text"  id="edit-user-name"
                                           placeholder="${username}" name="user_name" value="${sessionScope.user.name}">
                                </c:when>
                                <c:when test="${user_fields != null && user_fields.containsKey('user_name')}">
                                    <input class="edit-general-input edit-general-input-dark" type="text" id="edit-user-name"
                                           placeholder="${username}" name="user_name" value="${user_fields.get('user_name')}">
                                </c:when>
                                <c:when test="${user_fields == null || !user_fields.containsKey('user_name')}">
                                    <input class="edit-general-input edit-general-input-dark" type="text" id="edit-user-name"
                                           placeholder="${username}" name="user_name">
                                </c:when>
                            </c:choose>
                        </div>
                        <div class="edit-general">
                            <span class="edit-label-dark">${email}</span>
                            <c:choose>
                                <c:when test="${user_address != null}">
                                    <input class="edit-general-input edit-general-input-dark"
                                           type="text" placeholder="${email}"
                                           name="user_email" id="edit-user-email"
                                           value="${sessionScope.user.email}">
                                </c:when>
                                <c:when test="${user_fields != null && user_fields.containsKey('user_email')}">
                                    <input class="edit-general-input edit-general-input-dark"
                                           type="text" placeholder="${email}"
                                           name="user_email" id="edit-user-email"
                                           value="${user_fields.get('user_email')}">
                                </c:when>
                                <c:when test="${user_fields == null || !user_fields.containsKey('user_email')}">
                                    <input class="edit-general-input edit-general-input-dark"
                                           type="text" placeholder="${email}" id="edit-user-email"
                                           name="user_email">
                                </c:when>
                            </c:choose>
                        </div>
                        <div class="edit-general">
                            <span class="edit-label-dark">${photo}</span>
                            <input class="edit-file-input" type="file"
                                   name="user_avatar" id="add-user-photo">
                            <label for="add-user-photo" class="load-file-input load-file-input-dark">
                                <span class="load-file">${load_file}</span>
                                <i class="icon fa fa-upload"></i>
                            </label>
                        </div>
                    </div>
                    <div class="edit-footer">
                        <p class="edit-error-message edit-error-message-dark" id="edit-profile-error-message"></p>
                        <c:if test="${edit_error_message != null}">
                            <script>
                                let errorMessage = document.getElementById('edit-profile-error-message')
                                errorMessage.innerHTML = ${fill_fields_correct}
                            </script>
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
