<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Menu</title>
    <style><%@include file="/css/reviews.css"%></style>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"
            type="text/javascript"></script>
    <script><%@include file="/js/reviews.js"%></script>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Rubik:wght@400;500&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@500&family=Rubik:wght@400;500&display=swap" rel="stylesheet">
</head>
<fmt:setLocale value='<%=request.getSession().getAttribute("lang")%>'/>
<fmt:setBundle basename="lang" var="loc"/>
<fmt:message bundle="${loc}" key="lang.label.give_feedback" var="give_feedback"/>
<fmt:message bundle="${loc}" key="lang.label.your_feedback" var="your_feedback"/>
<fmt:message bundle="${loc}" key="lang.label.feedback_header" var="feedback_header"/>
<fmt:message bundle="${loc}" key="lang.label.your_mark" var="your_mark"/>
<fmt:message bundle="${loc}" key="lang.label.submit" var="submit"/>
<body>
    <c:choose>
        <c:when test="${sessionScope.user.getRole() eq 'ADMIN'}">
            <%@ include file="/jsp/header/header-admin.jsp"%>
        </c:when>
        <c:when test="${sessionScope.user.getRole() eq 'USER'}">
            <%@ include file="/jsp/header/header-user.jsp"%>
        </c:when>
        <c:when test="${sessionScope.user.getRole() == null}">
            <%@ include file="/jsp/header/header.jsp"%>
        </c:when>
    </c:choose>
    <%@include file="/jsp/modal/give-feedback.jsp"%>
    <div class="reviews-container">
        <div class="reviews-header>">
            <h2 class="reviews-title">${reviews}</h2>
            <button class="give-feedback-button">${give_feedback}</button>
        </div>
        <ul class="reviews-list">
            <c:forEach var="review" items="${sessionScope.dishes_list}">
                <li class="reviews-list-item">
                    <div class="review-author">
                        <p class="review-author-name">name</p>
                        <img class="review-author-photo" src="${pageContext.request.contextPath}/images/guest.jpg">
                    </div>
                    <div class="review-content-container">
                        <div class="review-header">
                            <h4 class="review-header-title">!!!!!Title of my review!!!!!!!</h4>
                            <div class="review-rating">
                                <span class="fa fa-star"></span>
                                <span class="fa fa-star"></span>
                                <span class="fa fa-star"></span>
                                <span class="fa fa-star"></span>
                                <span class="fa fa-star"></span>
                            </div>
                        </div>
                        <div class="review-content">
                            <p>Lorem Ipsum is simply dummy text of the printing
                                and typesetting industry. Lorem Ipsum has been the industry's
                                standard dummy text ever since the 1500s, when an unknown printer took
                                a galley of type and scrambled it to make a type specimen book. It has
                                survived not only five centuries, but also the leap into electronic typesetting,
                                remaining essentially unchanged. It was popularised in the 1960s with the release of
                                Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing
                                software like Aldus PageMaker including versions of Lorem Ipsum.</p>
                        </div>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>
</body>
</html>
