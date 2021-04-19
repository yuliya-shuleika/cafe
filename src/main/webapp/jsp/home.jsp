<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Home</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style><%@include file="/css/home.css"%></style>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Rubik:wght@400;500&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@500&family=Rubik:wght@400;500&display=swap" rel="stylesheet">
</head>
<fmt:setLocale value='<%=request.getSession().getAttribute("lang")%>'/>
<fmt:setBundle basename="lang" var="loc"/>
<fmt:message bundle="${loc}" key="lang.label.delicious_sushi" var="delicious_sushi"/>
<fmt:message bundle="${loc}" key="lang.label.japanese_restaurant" var="japanese_restaurant"/>
<fmt:message bundle="${loc}" key="lang.label.menu" var="menu"/>
<body>
    <c:choose>
        <c:when test="${sessionScope.user.getRole() eq 'USER'}">
            <%@ include file="/jsp/header/header-user.jsp"%>
        </c:when>
        <c:when test="${sessionScope.user.getRole() == null}">
            <%@ include file="/jsp/header/header.jsp"%>
        </c:when>
    </c:choose>
    <section class="section-preview" style="background-image:url(<c:url value='/images/preview.jpg'/>)">
        <div class="section-preview-container">
            <div class="section-preview-info">${japanese_restaurant}</div>
            <h1 class="section-preview-title">${delicious_sushi}</h1>
            <div class="section-preview-btn-container">
                <form action="menu.do" method="post">
                    <input type="hidden" name="command" value="to_menu">
                    <button class="section-preview-btn">
                        ${menu}
                    </button>
                </form>
            </div>
        </div>
    </section>
    <%@ include file="/jsp/footer/footer.jsp"%>
</body>
</html>
