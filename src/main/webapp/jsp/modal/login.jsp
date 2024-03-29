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
<fmt:message bundle="${loc}" key="lang.label.email" var="email"/>
<fmt:message bundle="${loc}" key="lang.label.password" var="password"/>
<fmt:message bundle="${loc}" key="lang.label.dont_have_account" var="dont_have_account"/>
<fmt:message bundle="${loc}" key="lang.label.wrong_password" var="wrong_password"/>
<fmt:message bundle="${loc}" key="lang.label.account_not_exist" var="account_not_exist"/>
<fmt:message bundle="${loc}" key="lang.label.added_to_blacklist" var="added_to_blacklist"/>
<fmt:message bundle="${loc}" key="lang.label.fill_fields_correct" var="fill_fields_correct"/>
<body>
    <div class="login" id="login">
        <div class="login-body">
            <div class="login-content">
                <div class="login-header">
                    <h3 class="login-title">${login}</h3>
                    <a class="login-close" href="#">x</a>
                </div>
                <div class="login-form-container">
                    <form class="login-form" action="login.do" method="post"
                          onsubmit="validateLoginForm(this); return false;">
                        <input type="hidden" name="command" value="login">
                        <div class="login-form-field">
                            <c:choose>
                                <c:when test="${login_fields != null && login_fields.containsKey('user_email')}">
                                    <input class="login-field-input" type="text" name = "user_email" id="login-user-email"
                                           placeholder="${email}" value="${login_fields.getValue('user_email')}">
                                </c:when>
                                <c:when test="${login_fields == null || !login_fields.containsKey('user_email')}">
                                    <input class="login-field-input" type="text" name = "user_email" id="login-user-email"
                                           placeholder="${email}">
                                </c:when>
                            </c:choose>
                        </div>
                        <div class="login-form-field">
                            <c:choose>
                                <c:when test="${login_fields != null && login_fields.containsKey('user_email')}">
                                    <input class="login-field-input" type="password" name = "user_password"
                                           placeholder="${password}" id="login-user-password"
                                           value="${login_fields.getValue('user_password')}">
                                </c:when>
                                <c:when test="${login_fields == null || !login_fields.containsKey('user_password')}">
                                    <input class="login-field-input" type="password" name = "user_password"
                                           placeholder="${password}" id="login-user-password">
                                </c:when>
                            </c:choose>
                        </div>
                        <p class="login-error-label" id="login-user-error-message">
                            <c:choose>
                                <c:when test="${login_error_message eq 'wrong_password'}">
                                    ${wrong_password}
                                </c:when>
                                <c:when test="${login_error_message eq 'account_not_exists'}">
                                    ${account_not_exist}
                                </c:when>
                                <c:when test="${login_error_message eq 'added_to_blacklist'}">
                                    ${added_to_blacklist}
                                </c:when>
                                <c:when test="${login_error_message eq 'fill_fields_correct'}">
                                    ${fill_fields_correct}
                                </c:when>
                            </c:choose>
                        </p>
                        <input class="login-form-submit" type="submit" value="${login}">
                    </form>
                </div>
                <footer class="login-footer">
                    <p class="login-footer-text">${dont_have_account}
                        <a href="#" id="to_register">${register}</a>
                    </p>
                </footer>
            </div>
        </div>
    </div>
</body>
</html>