<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Home</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style><%@include file="/css/main.css"%></style>-+
    <style><%@include file="/css/bootstrap.css"%></style>
</head>
<fmt:setLocale value='<%=request.getSession().getAttribute("lang")%>'/>
<fmt:setBundle basename="lang" var="loc"/>
<fmt:message bundle="${loc}" key="lang.label.home" var="home"/>
<fmt:message bundle="${loc}" key="lang.label.login" var="login"/>
<fmt:message bundle="${loc}" key="lang.label.register" var="register"/>
<fmt:message bundle="${loc}" key="lang.label.users_list" var="users_list"/>
<fmt:message bundle="${loc}" key="lang.label.management" var="management"/>
<fmt:message bundle="${loc}" key="lang.label.welcome" var="welcome"/>
<fmt:message bundle="${loc}" key="lang.label.hello" var="hello"/>
<fmt:message bundle="${loc}" key="lang.label.logout" var="logout"/>
<fmt:message bundle="${loc}" key="lang.label.translate" var="translate"/>
<fmt:message bundle="${loc}" key="lang.label.add_to_cart" var="add_to_cart"/>
<fmt:message bundle="${loc}" key="lang.label.sushi" var="sushi"/>
<fmt:message bundle="${loc}" key="lang.label.sushi_set" var="sushi_set"/>
<fmt:message bundle="${loc}" key="lang.label.soup" var="soup"/>
<fmt:message bundle="${loc}" key="lang.label.noodles" var="noodles"/>
<fmt:message bundle="${loc}" key="lang.label.choose" var="choose"/>
<body>
    <c:choose>
        <c:when test="${sessionScope.user.getRole() eq 'ADMIN'}">
            <%@ include file="header-admin.jsp"%>
        </c:when>
        <c:when test="${sessionScope.user.getRole() eq 'USER'}">
            <%@ include file="header.jsp"%>
        </c:when>
        <c:when test="${sessionScope.user.getRole() == null}">
            <%@ include file="header.jsp"%>
        </c:when>
    </c:choose>
    <main>
        <div class="team" id="team">
        <div class="container">
            <div class="default-heading">
                <h2>Menu</h2>
            </div>
            <div class="filters">
                <form method="post" action="controller">
                    <input type="hidden" name="command" value="choose_category" />
                    <select name="categories">
                        <option value="choose a category">${choose}</option>
                        <option value="sushi">${sushi}</option>
                        <option value="sushi_set">${sushi_set}</option>
                        <option value="soup">${soup}</option>
                        <option value="noodles">${noodles}</option>
                    </select>
                    <div class="filters_button">
                        <input type="submit" value="confirm">
                    </div>
                </form>
            </div>
            <c:forEach var="dish" items="${dishes_list}">
                <div class="col-md-3 col-sm-3">
                    <div class="member">
                        <img class="img-responsive" src="${pageContext.request.contextPath}${dish.getPictureName()}" alt="Team Member" />
                        <h3>${dish.getName()}</h3>
                        <span class="dig">${dish.getPrice()}</span>
                        <a href="cart/id25125">${add_to_cart}</a>
                    </div>
            </div>
            </c:forEach>
        </div>
        </div>
    </main>
</body>
</html>
