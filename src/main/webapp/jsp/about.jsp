<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>About</title>
    <style><%@include file="/css/about.css"%></style>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Rubik:wght@400;500&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@500&family=Rubik:wght@400;500&display=swap" rel="stylesheet">
</head>
<fmt:setLocale value='<%=request.getSession().getAttribute("lang")%>'/>
<fmt:setBundle basename="lang" var="loc"/>
<fmt:message bundle="${loc}" key="lang.label.about" var="about"/>
<fmt:message bundle="${loc}" key="lang.label.information" var="info"/>
<fmt:message bundle="${loc}" key="lang.label.our_cafes" var="our_cafes"/>
<fmt:message bundle="${loc}" key="lang.label.best_restaurant" var="best_restaurant"/>
<fmt:message bundle="${loc}" key="lang.label.delivery" var="delivery"/>
<fmt:message bundle="${loc}" key="lang.label.contact_us" var="contact_us"/>
<fmt:message bundle="${loc}" key="lang.label.ask_question" var="ask_question"/>
<fmt:message bundle="${loc}" key="lang.label.your_question" var="your_question"/>
<fmt:message bundle="${loc}" key="lang.label.your_email" var="your_email"/>
<fmt:message bundle="${loc}" key="lang.label.delivery_rules" var="delivery_rules"/>
<fmt:message bundle="${loc}" key="lang.label.delivery_time_limits" var="delivery_time_limits"/>
<fmt:message bundle="${loc}" key="lang.label.free_order" var="free_order"/>
<fmt:message bundle="${loc}" key="lang.label.odd_money" var="odd_money"/>
<fmt:message bundle="${loc}" key="lang.label.dont_forget_give_feedback" var="dont_forget_give_feedback"/>
<fmt:message bundle="${loc}" key="lang.label.submit" var="submit"/>
<body>
    <c:choose>
        <c:when test="${sessionScope.user.getRole() eq 'USER'}">
            <%@ include file="/jsp/header/header-user.jsp"%>
        </c:when>
        <c:when test="${sessionScope.user.getRole() == null}">
            <%@ include file="/jsp/header/header.jsp"%>
        </c:when>
    </c:choose>
    <div class="about-container">
        <div class="about-header">
            <div class="about-title">
                <h1 class="about-title">
                    ${about}
                </h1>
            </div>
            <div class="about-body">
                <section class="about-section">
                    <div class="about-info">
                        <p class="about-info-text">${best_restaurant}</p>
                        <img class="about-info-image" src="${pageContext.request.contextPath}/images/restaurant.jpg">
                    </div>
                </section>
                <section class="about-section">
                    <div class="about-section-title-container">
                        <h3 class="about-section-title">${our_cafes}</h3>
                    </div>
                    <div class="about-section-body">
                        <c:forEach var="dish" items="${cafes_list}">
                            <div class="about-cafe">

                            </div>
                        </c:forEach>
                    </div>
                </section>
                <section class="about-section">
                    <div class="about-section-title-container">
                        <h3 class="about-section-title">${delivery}</h3>
                    </div>
                    <div class="about-section-body">
                        <div class="about-delivery">
                            <p class="delivery-rules-label">${delivery_rules}:</p>
                            <ul class="delivery-rules">
                                <li>
                                   <p class="delivery-rule">${delivery_time_limits}</p>
                                </li>
                                <li>
                                    <p class="delivery-rule">${free_order}</p>
                                </li>
                                <li>
                                    <p class="delivery-rule">${odd_money}</p>
                                </li>
                                <li>
                                    <p class="delivery-rule">${dont_forget_give_feedback}</p>
                                </li>
                            </ul>
                        </div>
                    </div>
                </section>
                <section class="about-section">
                    <div class="about-section-title-container">
                        <h3 class="about-section-title">${ask_question}</h3>
                    </div>
                    <div class="about-section-body">
                        <div class="about-ask-question">
                            <form>
                                <div>
                                    <label class="about-label" for="about-email">${your_email}</label>
                                    <input class="about-email" type="email" id = "about-email">
                                </div>
                                <div class="about-field-container">
                                    <label class="about-label" for="about-email">${your_question}</label>
                                    <textarea class="about-textarea"></textarea>
                                </div>
                                <button class="about-submit">${submit}</button>
                            </form>
                        </div>
                    </div>
                </section>

            </div>
        </div>
        <div></div>
    </div>
    <%@ include file="/jsp/footer/footer.jsp"%>
</body>
</html>
