<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Edit profile</title>
    <style><%@include file="/css/edit.css"%></style>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Rubik:wght@400;500&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@500&family=Rubik:wght@400;500&display=swap" rel="stylesheet">
</head>
<fmt:setLocale value='<%=request.getSession().getAttribute("lang")%>'/>
<fmt:setBundle basename="lang" var="loc"/>
<fmt:message bundle="${loc}" key="lang.label.address" var="address"/>
<fmt:message bundle="${loc}" key="lang.label.city" var="city"/>
<fmt:message bundle="${loc}" key="lang.label.street" var="street"/>
<fmt:message bundle="${loc}" key="lang.label.house" var="house"/>
<fmt:message bundle="${loc}" key="lang.label.entrance" var="entrance"/>
<fmt:message bundle="${loc}" key="lang.label.floor" var="floor"/>
<fmt:message bundle="${loc}" key="lang.label.flat" var="flat"/>
<fmt:message bundle="${loc}" key="lang.label.submit" var="submit"/>
<body>
<div class="edit" id="edit-user-address">
    <div class="edit-body">
        <div class="edit-content">
            <div class="add-promo">
                <form action="controller?command=edit_user_address" method="post">
                    <div class="edit-header">
                        <h3 class="edit-title">${address}</h3>
                        <a class="edit-close" id="edit-close">x</a>
                    </div>
                    <div class="edit-form">
                        <c:if test="${user_address != null}">
                        <div class="edit-general">
                            <span class="edit-label">${city}</span>
                            <input class="edit-general-input" type="text" placeholder="${city}" name="city"
                                   required pattern="[A-Za-zА-Яа-я][a-zа-я]{1,30}" value="${user_address.getCity()}">
                        </div>
                        <div class="edit-general">
                            <span class="edit-label">${street}</span>
                            <input class="edit-general-input" type="text" placeholder="${street}"
                                   required pattern="[A-Za-zА-Яа-я][a-zа-я]{1,30}" name="street"
                                   value="${user_address.getStreet()}">
                        </div>
                        <div class="edit-general">
                            <span class="edit-label">${house}</span>
                            <input class="edit-general-input" type="text" placeholder="${house}" name="house"
                                   required pattern="[1-9][0-9]{0,3}" value="${user_address.getHouse()}">
                        </div>
                        <div class="edit-general">
                            <span class="edit-label">${entrance}</span>
                            <input class="edit-general-input" type="text" placeholder="${entrance}"
                                   required pattern="[1-9][0-9]{0,2}" name="entrance"
                                   value="${user_address.getEntrance()}">
                        </div>
                        <div class="edit-general">
                            <span class="edit-label">${floor}</span>
                            <input class="edit-general-input" type="text" placeholder="${floor}" name="floor"
                                   required pattern="(-|[1-9])[0-9]{0,3}" value="${user_address.getFloor()}">
                        </div>
                        <div class="edit-general">
                            <span class="edit-label">${flat}</span>
                            <input class="edit-general-input" type="text" placeholder="${flat}"
                                   required pattern="[1-9][0-9]{0,5}" name="flat"
                                   value="${user_address.getFlat()}">
                        </div>
                        </c:if>
                        <c:if test="${user_address == null}">
                            <div class="edit-general">
                                <span class="edit-label">${city}</span>
                                <input class="edit-general-input" type="text" placeholder="${city}" name="city"
                                       required pattern="[A-Za-zА-Яа-я][a-zа-я]{1,30}">
                            </div>
                            <div class="edit-general">
                                <span class="edit-label">${street}</span>
                                <input class="edit-general-input" type="text" placeholder="${street}"
                                       required pattern="[A-Za-zА-Яа-я][a-zа-я]{1,30}" name="street">
                            </div>
                            <div class="edit-general">
                                <span class="edit-label">${house}</span>
                                <input class="edit-general-input" type="text" placeholder="${house}" name="house"
                                       required pattern="[1-9][0-9]{0,3}">
                            </div>
                            <div class="edit-general">
                                <span class="edit-label">${entrance}</span>
                                <input class="edit-general-input" type="text" placeholder="${entrance}"
                                       required pattern="[1-9][0-9]{0,2}" name="entrance">
                            </div>
                            <div class="edit-general">
                                <span class="edit-label">${floor}</span>
                                <input class="edit-general-input" type="text" placeholder="${floor}" name="floor"
                                       required pattern="(-|[1-9])[0-9]{0,3}">
                            </div>
                            <div class="edit-general">
                                <span class="edit-label">${flat}</span>
                                <input class="edit-general-input" type="text" placeholder="${flat}"
                                       required pattern="[1-9][0-9]{0,5}" name="flat">
                            </div>
                        </c:if>
                    </div>
                    <div class="edit-footer">
                        <button class="edit-submit" type="submit">${submit}</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>

