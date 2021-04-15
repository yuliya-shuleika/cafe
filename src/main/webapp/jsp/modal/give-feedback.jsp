<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cart</title>
    <style><%@include file="/css/edit.css"%></style>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"
            type="text/javascript"></script>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Rubik:wght@400;500&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@500&family=Rubik:wght@400;500&display=swap" rel="stylesheet">
</head>
<fmt:setLocale value='<%=request.getSession().getAttribute("lang")%>'/>
<fmt:setBundle basename="lang" var="loc"/>
<fmt:message bundle="${loc}" key="lang.label.review" var="review"/>
<body>
<div class="edit" id="feedback">
    <div class="edit-body">
        <div class="edit-content">
            <div class="edit-container">
                <form action="controller" method="post">
                    <div class="edit-header">
                        <h3 class="edit-title">${review}</h3>
                        <a class="edit-close" id="edit-close">x</a>
                    </div>
                    <div class="edit-form">
                        <input type="hidden" name="command" value="give_feedback">
                        <div class="edit-general">
                            <span class="edit-label">${feedback_header}</span>
                            <input class="edit-general-input" type="text" placeholder="Header" name="review_header">
                        </div>
                        <div class="edit-mark">
                            <span class="edit-label">${your_mark}</span>
                            <div class="edit-rating">
                                <input type="hidden" name="review_rating" value="5">
                                <span class="fa fa-star edit-star" id="star_one"></span>
                                <span class="fa fa-star edit-star" id="star_two"></span>
                                <span class="fa fa-star edit-star" id="star_three"></span>
                                <span class="fa fa-star edit-star" id="star_four"></span>
                                <span class="fa fa-star edit-star" id="star_five"></span>
                            </div>
                        </div>
                        <div class="edit-text-container">
                            <span class="edit-label">${your_feedback}</span>
                            <textarea class="edit-text" name="review_text" maxlength="500"></textarea>
                        </div>
                    </div>
                    <div class="edit-footer">
                        <input class="edit-file" type="file">
                        <button class="edit-submit" type="submit">${submit}</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>