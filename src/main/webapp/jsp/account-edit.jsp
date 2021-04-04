<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Edit account</title>
    <style><%@include file="/css/account.css"%></style>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script><%@include file="/js/account-edit.js"%></script>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Rubik:wght@400;500&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@500&family=Rubik:wght@400;500&display=swap" rel="stylesheet">
</head>
<fmt:setLocale value='<%=request.getSession().getAttribute("lang")%>'/>
<fmt:setBundle basename="lang" var="loc"/>
<fmt:message bundle="${loc}" key="lang.label.username" var="username"/>
<fmt:message bundle="${loc}" key="lang.label.email" var="email"/>
<fmt:message bundle="${loc}" key="lang.label.submit" var="submit"/>
<fmt:message bundle="${loc}" key="lang.label.address" var="address"/>
<fmt:message bundle="${loc}" key="lang.label.address_extra" var="address_extra"/>
<fmt:message bundle="${loc}" key="lang.label.city" var="city"/>
<fmt:message bundle="${loc}" key="lang.label.street" var="street"/>
<fmt:message bundle="${loc}" key="lang.label.house" var="house"/>
<fmt:message bundle="${loc}" key="lang.label.entrance" var="entrance"/>
<fmt:message bundle="${loc}" key="lang.label.floor" var="floor"/>
<fmt:message bundle="${loc}" key="lang.label.flat" var="flat"/>
<fmt:message bundle="${loc}" key="lang.label.general" var="general"/>
<body>
<%@ include file="/jsp/header/header-user.jsp"%>
    <div class="account-edit-container">
        <div class="account-edit">
        <div class="account-edit-general">
            <h3 class="account-general-title">${general}</h3>
            <div class = "account-general-fields">
                <div class="avatar-edit">
                    <input type="file" class="account-file-input">
                    <img src="${pageContext.request.contextPath}/images/guest.jpg" alt="avatar" class="account-edit-image">
                </div>
                <div class="general-fields">
                    <div class="account-field">
                        <label class="account-general-label" for="username-edit">${username}</label>
                        <input class="account-general-input" type="text"
                            name="username" id="username-edit" value="${sessionScope.user.getName()}">
                    </div>
                    <div class="account-field">
                        <label class="account-general-label" for="email-edit">${email}</label>
                        <input class="account-general-input" type="text"
                            name="username" id="email-edit" value="${sessionScope.user.getEmail()}">
                    </div>
                </div>
            </div>
        </div>
        </div>
        <div class="account-edit-extra">
            <div class="account-edit-header">
               <h3 class="account-general-title">${address}</h3>
            </div>
            <div>
                <div class="account-field">
                    <label class="account-general-label" for="city-edit">${city}</label>
                    <input class="account-general-input" type="text" name="city" id="city-edit">
                </div>
                <div class="account-field">
                    <label class="account-general-label" for="street-edit">${street}</label>
                    <input class="account-general-input" type="text" name="street" id="street-edit">
                </div>
                <div class="account-field">
                    <label class="account-general-label" for="house-edit">${house}</label>
                    <input class="account-general-input" type="text" name="house" id="house-edit">
                </div>
                <div class="account-field">
                    <label class="account-general-label" for="entrance-edit">${entrance}</label>
                    <input class="account-general-input" type="text" name="entrance" id="entrance-edit">
                </div>
                <div class="account-field">
                    <label class="account-general-label" for="floor-edit">${floor}</label>
                    <input class="account-general-input" type="text" name="floor" id="floor-edit">
                </div>
                <div class="account-field">
                    <label class="account-general-label" for="flat-edit">${email}</label>
                    <input class="account-general-input" type="text" name="flat" id="flat-edit">
                </div>
            </div>
            <c:if test="${user_address != null}">
                <script>
                    $(document).ready(function () {
                        let cityInput = document.getElementById('city-edit')
                        cityInput.value = '${user_address.getCity()}'
                        let streetInput = document.getElementById('street-edit')
                        streetInput.value = '${user_address.getStreet()}'
                        let houseInput = document.getElementById('house-edit')
                        houseInput.value = '${user_address.getHouse()}'
                        let entranceInput = document.getElementById('entrance-edit')
                        entranceInput.value = '${user_address.getEntrance()}'
                        let floorInput = document.getElementById('floor-edit')
                        floorInput.value = '${user_address.getEntrance()}'
                        let flatInput = document.getElementById('flat-edit')
                        flatInput.value = '${user_address.getFlat()}'
                    });
                </script>
            </c:if>
        </div>
        <div class="account-edit-footer">
            <button class="account-edit-submit">${submit}</button>
        </div>
    </div>
    </div>
</body>
</html>
