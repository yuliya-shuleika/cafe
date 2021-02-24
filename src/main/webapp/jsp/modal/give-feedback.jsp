<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cart</title>
    <style><%@include file="/css/give-feedback.css"%></style>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"
            type="text/javascript"></script>
    <script><%@include file="/js/login.js"%></script>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Rubik:wght@400;500&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@500&family=Rubik:wght@400;500&display=swap" rel="stylesheet">
</head>
<fmt:message bundle="${loc}" key="lang.label.login" var="login"/>
<fmt:message bundle="${loc}" key="lang.label.management" var="management"/>
<fmt:message bundle="${loc}" key="lang.label.register" var="register"/>
<fmt:message bundle="${loc}" key="lang.label.dont_have_account" var="dont_have_account"/>
<body>
<div class="feedback" id="feedback">
    <div class="feedback-body">
        <div class="feedback-content">
            <div class="give-feedback">
                <form>
                    <div class="feedback-header">
                        <h3 class="feedback-title">${login}</h3>
                        <a class="feedback-close" id="feedback-close">x</a>
                    </div>
                    <div class="feedback-body">
                        <div class="feedback-title">
                            <span class="feedback-label">${feedback_header}</span>
                            <input type="text" placeholder="Header" name="review_header">
                        </div>
                        <div class="feedback-mark">
                            <span class="feedback-label">${your_mark}</span>
                            <div class="feedback-rating">
                                <span class="fa fa-star"></span>
                                <span class="fa fa-star"></span>
                                <span class="fa fa-star"></span>
                                <span class="fa fa-star"></span>
                                <span class="fa fa-star"></span>
                            </div>
                        </div>
                        <div>
                            <span class="feedback-label">${your_feedback}</span>
                            <textarea></textarea>
                        </div>
                    </div>
                    <div class="feedback-footer">
                        <input type="file">
                        <button type="submit">${submit}</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>