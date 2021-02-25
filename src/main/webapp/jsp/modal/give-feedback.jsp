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
                <form action="controller" method="post">
                    <div class="feedback-header">
                        <h3 class="feedback-title">${login}</h3>
                        <a class="feedback-close" id="feedback-close">x</a>
                    </div>
                    <div class="feedback-form">
                        <input type="hidden" name="command" value="give_feedback">
                        <div class="feedback-general">
                            <span class="feedback-label">${feedback_header}</span>
                            <input class="feedback-general-input" type="text" placeholder="Header" name="review_header">
                        </div>
                        <div class="feedback-mark">
                            <span class="feedback-label">${your_mark}</span>
                            <div class="feedback-rating">
                                <input type="hidden" name="review_rating" value="5">
                                <span class="fa fa-star feedback-star" id="star_one"></span>
                                <span class="fa fa-star feedback-star" id="star_two"></span>
                                <span class="fa fa-star feedback-star" id="star_three"></span>
                                <span class="fa fa-star feedback-star" id="star_four"></span>
                                <span class="fa fa-star feedback-star" id="star_five"></span>
                            </div>
                        </div>
                        <div class="feedback-text-container">
                            <span class="feedback-label">${your_feedback}</span>
                            <textarea class="feedback-text" name="review_text"></textarea>
                        </div>
                    </div>
                    <div class="feedback-footer">
                        <input class="feedback-file" type="file">
                        <button class="feedback-submit" type="submit">${submit}</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>