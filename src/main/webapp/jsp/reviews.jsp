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
<fmt:message bundle="${loc}" key="lang.label.no_reviews" var="no_reviews"/>
<body>
    <c:choose>
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
            <c:if test="${reviews_list.isEmpty()}">
                <div class="reviews-empty">
                    <p class="reviews-empty-label">
                        ${no_reviews}
                    </p>
                </div>
            </c:if>
            <c:if test="${!reviews_list.isEmpty()}">
            <c:forEach var="review" items="${reviews_list}">
                <li class="reviews-list-item">
                    <div class="review-author">
                        <p class="review-author-name">name</p>
                        <img class="review-author-photo" src="${pageContext.request.contextPath}/images/guest.jpg">
                    </div>
                    <div class="review-content-container">
                        <div class="review-header">
                            <h4 class="review-header-title">${review.getHeader()}</h4>
                            <div class="review-rating">
                                <span id="review-stars-count" class="review-stars-count">${review.getRating()}</span>
                                <span class="fa fa-star"></span>
                                <span class="fa fa-star"></span>
                                <span class="fa fa-star"></span>
                                <span class="fa fa-star"></span>
                                <span class="fa fa-star"></span>
                            </div>
                        </div>
                        <div class="review-content">
                            <p>${review.getText()}</p>
                        </div>
                    </div>
                </li>
            </c:forEach>
            </c:if>
        </ul>
    </div>
    <%@ include file="/jsp/footer/footer.jsp"%>
</body>
</html>
