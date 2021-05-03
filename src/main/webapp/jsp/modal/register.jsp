<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cart</title>
    <style><%@include file="/css/login.css"%></style>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Rubik:wght@400;500&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@500&family=Rubik:wght@400;500&display=swap" rel="stylesheet">
</head>
<fmt:message bundle="${loc}" key="lang.label.login" var="login"/>
<fmt:message bundle="${loc}" key="lang.label.management" var="management"/>
<fmt:message bundle="${loc}" key="lang.label.username" var="username"/>
<fmt:message bundle="${loc}" key="lang.label.email" var="email"/>
<fmt:message bundle="${loc}" key="lang.label.password" var="password"/>
<fmt:message bundle="${loc}" key="lang.label.repeat_password" var="repeat_password"/>
<fmt:message bundle="${loc}" key="lang.label.register" var="register"/>
<fmt:message bundle="${loc}" key="lang.label.have_account" var="have_account"/>
<fmt:message bundle="${loc}" key="lang.label.password_mismatch" var="password_mismatch"/>
<fmt:message bundle="${loc}" key="lang.label.account_already_exists" var="account_already_exists"/>
<body>
<div class="login" id="register">
    <div class="login-body">
        <div class="login-content">
            <div class="login-header">
                <h3 class="login-title">${register}</h3>
                <a class="login-close" href="#">x</a>
            </div>
            <div class="login-form-container">
                <form class="login-form" action="controller" method="post"
                      onsubmit="validateRegisterForm(this); return false;">
                    <input type="hidden" name="command" value="register">
                    <div class="login-form-field">
                        <c:choose>
                            <c:when test="${register_fields != null && register_fields.containsKey('user_name')}">
                                <input class="login-field-input" type="text" name="user_name"
                                       placeholder="${username}" id="register-user-name"
                                       value="${register_fields.getValue('user_name')}">
                            </c:when>
                            <c:when test="${register_fields == null || !register_fields.containsKey('user_name')}">
                                <input class="login-field-input" type="text" name="user_name"
                                       placeholder="${username}" id="register-user-name">
                            </c:when>
                        </c:choose>
                    </div>
                    <div class="login-form-field">
                        <c:choose>
                            <c:when test="${register_fields != null && register_fields.containsKey('user_email')}">
                                <input class="login-field-input" type="text" name = "user_email" id="register-user-email"
                                       placeholder="${email}" value="${register_fields.getValue('user_email')}">
                            </c:when>
                            <c:when test="${register_fields == null || !register_fields.containsKey('user_email')}">
                                <input class="login-field-input" type="text" name = "user_email"
                                       placeholder="${email}" id="register-user-email">
                            </c:when>
                        </c:choose>
                    </div>
                    <div class="login-form-field">
                        <c:choose>
                            <c:when test="${register_fields != null && register_fields.containsKey('user_email')}">
                                <input class="login-field-input" type="password" name = "user_password"
                                       placeholder="${password}" id="register-user-password"
                                       value="${register_fields.getValue('user_password')}">
                            </c:when>
                            <c:when test="${register_fields == null || !register_fields.containsKey('user_password')}">
                                <input class="login-field-input" type="password" name = "user_password"
                                       placeholder="${password}" id="register-user-password">
                            </c:when>
                        </c:choose>
                    </div>
                    <div class="login-form-field">
                        <input class="login-field-input" type="password"
                               id="repeat_password" placeholder="${repeat_password}">
                        <p class="login-error-label" id="register-error-label"></p>
                        <c:if test="${register_error_message != null}">
                            <script>
                                document.getElementById('register-error-label').innerText = "${account_already_exists}"
                            </script>
                        </c:if>
                    </div>
                    <input class="login-form-submit" id="register-button" type="submit" value="${register}">
                </form>
            </div>
            <footer class="login-footer">
                <p class="login-footer-text">${have_account}
                    <a id="to_login" href="#">${login}</a>
                </p>
            </footer>
        </div>
    </div>
</div>
<script>
    $(document).ready(function () {
        $('#repeat_password').on('input', function (){
            showDifference()
        });
        $('#register-user-password').on('input', function (){
            showDifference()
        });
    });
    function showDifference(){
        let password = document.getElementById('register-user-password').value
        let repeatPassword = document.getElementById('repeat_password').value
        let registerButton = document.getElementById('register-button')
        registerButton.disabled = true
        if (password.localeCompare(repeatPassword)){
            document.getElementById('register-error-label').innerText = "${password_mismatch}"
            registerButton.disabled = true
        } else {
            document.getElementById('register-error-label').innerText = ""
            registerButton.disabled = false
        }
    }
</script>
</body>
</html>
