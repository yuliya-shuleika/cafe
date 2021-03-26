<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cart</title>
    <style><%@include file="/css/login.css"%></style>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Rubik:wght@400;500&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@500&family=Rubik:wght@400;500&display=swap" rel="stylesheet">
</head>
<fmt:message bundle="${loc}" key="lang.label.login" var="login"/>
<fmt:message bundle="${loc}" key="lang.label.management" var="management"/>
<fmt:message bundle="${loc}" key="lang.label.register" var="register"/>
<fmt:message bundle="${loc}" key="lang.label.have_account" var="have_account"/>
<fmt:message bundle="${loc}" key="lang.label.password_mismatch" var="password_mismatch"/>
<body>
<div class="login" id="register">
    <div class="login-body">
        <div class="login-content">
            <div class="login-header">
                <h3 class="login-title">${register}</h3>
                <a class="login-close" href="#">x</a>
            </div>
            <div class="login-form-container">
                <form class="login-form" action="controller" method="post">
                    <input type="hidden" name="command" value="register">
                    <div class="login-form-field">
                        <input class="login-field-input" type="text" name="name" required pattern="[A-Za-zА-Яа-яёЁ]{3,25}">
                    </div>
                    <div class="login-form-field">
                        <input class="login-field-input" type="email" name="email" placeholder="Email" required>
                    </div>
                    <div class="login-form-field">
                        <input class="login-field-input" type="password" name="password" placeholder="Password"
                               id="password" required pattern="[A-Za-z0-9_]{5,20}">
                    </div>
                    <div class="login-form-field">
                        <input class="login-field-input" type="password" id="repeat_password" placeholder="Repeat password"
                               required pattern="[A-Za-z0-9_]{5,20}">
                        <p class="register-password-mismatch">${password_mismatch}</p>
                    </div>
                    <input class="login-form-submit" type="submit" value="Зарегистрироваться">
                </form>
            </div>
            <footer class="login-footer">
                <p class="login-footer-text">${have_account}
                    <a href="#login">${login}</a>
                </p>
            </footer>
        </div>
    </div>
</div>
</body>
</html>
