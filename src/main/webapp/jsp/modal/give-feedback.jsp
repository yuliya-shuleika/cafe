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
            <div class="edit-content edit-content-dark">
                <div class="edit-container">
                    <form action="controller" method="post" onsubmit="return validateGiveFeedbackForm()">
                        <div class="edit-header">
                            <h3 class="edit-title edit-title-dark">${review}</h3>
                            <a class="edit-close edit-close-dark" id="edit-close">x</a>
                        </div>
                        <div class="edit-form">
                            <input type="hidden" name="command" value="give_feedback">
                            <div class="edit-general">
                                <span class="edit-label edit-label-dark">${feedback_header}</span>
                                <c:choose>
                                    <c:when test="${review_fields != null && review_fields.containsKey('review_header')}">
                                    <input class="edit-general-input edit-general-input-dark" type="text"
                                           placeholder="Header" value="${review_fields.get('review_header')}"
                                           id="add-review-header" name="review_header">
                                    </c:when>
                                    <c:when test="${review_fields == null|| !review_fields.containsKey('review_header')}">
                                        <input class="edit-general-input edit-general-input-dark" type="text"
                                               id="add-review-header" placeholder="Header" name="review_header">
                                    </c:when>
                                </c:choose>
                            </div>
                            <div class="edit-mark">
                                <span class="edit-label edit-label-dark">${your_mark}</span>
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
                                <span class="edit-label edit-label-dark">${your_feedback}</span>
                                <c:choose>
                                    <c:when test="${review_fields != null && review_fields.containsKey('review_text')}">
                                        <textarea class="edit-text edit-text-dark" id="add-review-text"
                                                  name="review_text">${review_fields.get('review_text')}</textarea>
                                    </c:when>
                                    <c:when test="${review_fields == null || !review_fields.containsKey('review_text')}">
                                        <textarea class="edit-text edit-text-dark" id="add-review-text"
                                                  name="review_text"></textarea>
                                    </c:when>
                                </c:choose>
                            </div>
                        </div>
                        <div class="edit-footer">
                            <c:if test="${edit_error_message != null}">
                                <p class="edit-error-message edit-error-message-dark"
                                   id="add-review-error-label">${fill_fields_correct}</p>
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