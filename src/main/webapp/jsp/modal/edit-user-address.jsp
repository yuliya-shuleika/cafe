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
        <div class="edit-content edit-content-dark">
            <div class="add-promo">
                <form action="controller?command=edit_user_address" method="post">
                    <div class="edit-header">
                        <h3 class="edit-title edit-title-dark">${address}</h3>
                        <a class="edit-close edit-close-dark" id="edit-close">x</a>
                    </div>
                    <div class="edit-form">
                        <div class="edit-general">
                            <span class="edit-label edit-label-dark">${city}</span>
                            <c:choose>
                                <c:when test="${user_address != null && address_fields == null}">
                                    <input class="edit-general-input edit-general-input-dark" type="text" placeholder="${city}" name="city"
                                           value="${user_address.getCity()}">
                                </c:when>
                                <c:when test="${address_fields != null && address_fields.containsKey('city')}">
                                    <input class="edit-general-input edit-general-input-dark" type="text" placeholder="${city}" name="city"
                                           value="${address_fields.get('city')}">
                                </c:when>
                                <c:when test="${user_address == null || address_fields == null || !address_fields.containsKey('city')}">
                                    <input class="edit-general-input edit-general-input-dark" type="text" placeholder="${city}" name="city">
                                </c:when>
                            </c:choose>
                            <!--required pattern="[A-Za-zА-Яа-яёЁ\s]{5,45}"-->
                        </div>
                        <div class="edit-general">
                            <span class="edit-label edit-label-dark">${street}</span>
                            <c:choose>
                                <c:when test="${user_address != null && address_fields == null}">
                                    <input class="edit-general-input edit-general-input-dark" type="text" placeholder="${street}"
                                           name="street"
                                           value="${user_address.getStreet()}">
                                </c:when>
                                <c:when test="${address_fields != null && address_fields.containsKey('street')}">
                                    <input class="edit-general-input edit-general-input-dark" type="text" placeholder="${street}"
                                           name="street"
                                           value="${address_fields.get('street')}">
                                </c:when>
                                <c:when test="${user_address == null || address_fields == null || !address_fields.containsKey('street')}">
                                    <input class="edit-general-input edit-general-input-dark" type="text" placeholder="${street}"
                                           name="street">
                                </c:when>
                            </c:choose>
                            <!--required pattern="[A-Za-zА-Яа-я][a-zа-я]{1,30}"-->
                        </div>
                        <div class="edit-general">
                            <span class="edit-label edit-label-dark">${house}</span>
                            <c:choose>
                                <c:when test="${user_address != null && address_fields == null}">
                                    <input class="edit-general-input edit-general-input-dark" type="text" placeholder="${house}" name="house"
                                           value="${user_address.getHouse()}">
                                </c:when>
                                <c:when test="${address_fields != null && address_fields.containsKey('house')}">
                                    <input class="edit-general-input edit-general-input-dark" type="text" placeholder="${house}" name="house"
                                           value="${address_fields.get('house')}">
                                </c:when>
                                <c:when test="${user_address == null || address_fields == null || !address_fields.containsKey('house')}">
                                    <input class="edit-general-input edit-general-input-dark" type="text" placeholder="${house}" name="house">
                                </c:when>
                            </c:choose>
                            <!--required pattern="[1-9][0-9]{0,3}"-->
                        </div>
                        <div class="edit-general">
                            <span class="edit-label edit-label-dark">${entrance}</span>
                            <c:choose>
                                <c:when test="${user_address != null && address_fields == null}">
                                    <input class="edit-general-input edit-general-input-dark" type="text" placeholder="${entrance}" name="entrance"
                                           value="${user_address.getEntrance()}">
                                </c:when>
                                <c:when test="${address_fields != null && address_fields.containsKey('entrance')}">
                                    <input class="edit-general-input edit-general-input-dark" type="text" placeholder="${entrance}" name="entrance"
                                           value="${address_fields.get('entrance')}">
                                </c:when>
                                <c:when test="${user_address == null || address_fields == null || !address_fields.containsKey('entrance')}">
                                    <input class="edit-general-input edit-general-input-dark" type="text" placeholder="${entrance}" name="entrance">
                                </c:when>
                            </c:choose>
                            <!--required pattern="[1-9][0-9]{0,2}"-->
                        </div>
                        <div class="edit-general">
                            <span class="edit-label edit-label-dark">${floor}</span>
                            <c:choose>
                                <c:when test="${user_address != null && address_fields == null}">
                                    <input class="edit-general-input edit-general-input-dark" type="text" placeholder="${floor}" name="floor" value="${user_address.getFloor()}">
                                </c:when>
                                <c:when test="${address_fields != null && address_fields.containsKey('floor')}">
                                    <input class="edit-general-input edit-general-input-dark" type="text" placeholder="${floor}" name="floor" value="${address_fields.get('floor')}">
                                </c:when>
                                <c:when test="${user_address == null || address_fields == null || !address_fields.containsKey('floor')}">
                                    <input class="edit-general-input" type="text" placeholder="${floor}" name="floor">
                                </c:when>
                            </c:choose>
                            <!--required pattern="(-|[1-9])[0-9]{0,3}"-->
                        </div>
                        <div class="edit-general">
                            <span class="edit-label edit-label-dark">${flat}</span>
                            <c:choose>
                                <c:when test="${user_address != null && address_fields == null}">
                                    <input class="edit-general-input edit-general-input-dark" type="text" placeholder="${flat}" name="flat"
                                           value="${user_address.getFlat()}">
                                </c:when>
                                <c:when test="${address_fields != null && address_fields.containsKey('flat')}">
                                    <input class="edit-general-input edit-general-input-dark" type="text" placeholder="${flat}" name="flat"
                                           value="${address_fields.get('flat')}">
                                </c:when>
                                <c:when test="${user_address == null || address_fields == null || !address_fields.containsKey('flat')}">
                                    <input class="edit-general-input edit-general-input-dark" type="text" placeholder="${flat}" name="flat">
                                </c:when>
                            </c:choose>
                            <!--required pattern="[1-9][0-9]{0,5}"-->
                        </div>
                    </div>
                    <div class="edit-footer">
                        <c:if test="${edit_error_message != null}">
                            <p class="edit-error-message edit-error-message-dark">${fill_fields_correct}</p>
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

