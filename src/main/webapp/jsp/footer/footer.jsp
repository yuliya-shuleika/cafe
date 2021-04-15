<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <style><%@include file="/css/footer.css"%></style>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Rubik:wght@400;500&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@500&family=Rubik:wght@400;500&display=swap" rel="stylesheet">
</head>
<fmt:setLocale value='<%=request.getSession().getAttribute("lang")%>'/>
<fmt:setBundle basename="lang" var="loc"/>
<fmt:message bundle="${loc}" key="lang.label.menu" var="menu"/>
<fmt:message bundle="${loc}" key="lang.label.follow_us" var="follow_us"/>
<fmt:message bundle="${loc}" key="lang.label.about" var="about"/>
<fmt:message bundle="${loc}" key="lang.label.discounts" var="discounts"/>
<fmt:message bundle="${loc}" key="lang.label.all" var="all"/>
<fmt:message bundle="${loc}" key="lang.label.new" var="new"/>
<fmt:message bundle="${loc}" key="lang.label.sushi_sets" var="sushi_sets"/>
<fmt:message bundle="${loc}" key="lang.label.new" var="new_dishes"/>
<fmt:message bundle="${loc}" key="lang.label.sushi_sets" var="sushi_sets"/>
<fmt:message bundle="${loc}" key="lang.label.information" var="information"/>
<fmt:message bundle="${loc}" key="lang.label.reviews" var="reviews"/>
<fmt:message bundle="${loc}" key="lang.label.home" var="home"/>
<body>
    <footer class="footer">
        <div class="footer-container">
            <div class="footer-row">
                <div class="footer-col">
                    <h4 class="footer-title">${menu}</h4>
                    <ul>
                        <li class="footer-item"><a class="footer-link" href="menu.do">${all}</a></li>
                        <li class="footer-item"><a class="footer-link" href="menu.do">${discounts}</a></li>
                        <li class="footer-item"><a class="footer-link" href="menu.do?command=show_new_dishes">${new_dishes}</a></li>
                        <li class="footer-item"><a class="footer-link" href="menu.do">${sushi_sets}</a></li>
                    </ul>
                </div>
                <div class="footer-col">
                    <h4 class="footer-title">${about}</h4>
                    <ul>
                        <li class="footer-item"><a class="footer-link" href="home.do?command=to_home">${home}</a></li>
                        <li class="footer-item"><a class="footer-link" href="about.do?command=to_about">${information}</a></li>
                        <li class="footer-item"><a class="footer-link" href="reviews.do?command=to_reviews">${reviews}</a></li>
                    </ul>
                </div>
                <div class="footer-social-links">
                    <h4 class="footer-title">${follow_us}</h4>
                    <a class="footer-social-link" href="#"><i class="fab fa-facebook-f"></i></a>
                    <a class="footer-social-link" href="#"><i class="fab fa-instagram"></i></a>
                    <a class="footer-social-link" href="#"><i class="fab fa-twitter"></i></a>
                </div>
            </div>
        </div>
    </footer>
</body>
</html>
