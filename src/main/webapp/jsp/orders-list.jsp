<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
    <style><%@include file="/css/admin.css"%></style>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Rubik:wght@400;500&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@500&family=Rubik:wght@400;500&display=swap" rel="stylesheet">
</head>
<fmt:setLocale value='<%=request.getSession().getAttribute("lang")%>'/>
<fmt:setBundle basename="lang" var="loc"/>
<fmt:message bundle="${loc}" key="lang.label.number" var="number"/>
<fmt:message bundle="${loc}" key="lang.label.action" var="action"/>
<fmt:message bundle="${loc}" key="lang.label.order" var="order"/>
<fmt:message bundle="${loc}" key="lang.label.add" var="add"/>
<fmt:message bundle="${loc}" key="lang.label.edit" var="edit"/>
<fmt:message bundle="${loc}" key="lang.label.delete" var="delete"/>
<fmt:message bundle="${loc}" key="lang.label.sort" var="sort"/>
<fmt:message bundle="${loc}" key="lang.label.by_date" var="by_date"/>
<body>
<%@ include file="/jsp/header/header-admin.jsp"%>
<div class = "admin-container">
    <div class = "admin-header">
        <h2 class="admin-title">${orders}</h2>
    </div>
    <div class="orders-list-body">
        <div class="admin-manage">
            <div class="admin-manage-start">
                <p class="admin-sort-label">${sort}</p>
                <form action="controller" method="post" class="admin-sorting-form">
                    <input type="hidden" name="command" value="sort_by_email">
                    <button class="admin-sort-criteria" type="submit">${by_date}</button>
                </form>
            </div>
            <div class="admin-manage-end">
                <form action="controller" method="post" class="admin-searching-form">
                    <input type="hidden" name="command" value="search_user">
                    <input class="admin-search" type="search" id="search_user" name="dish_name"
                           placeholder="Search..." required
                           minlength="1" maxlength="20">
                </form>
            </div>
        </div>
        <div class="users-list-manage">
            <table>
                <thead>
                <th>${number}</th>
                <th>${order}</th>
                <th colspan="2">${action}</th>
                </thead>
                <tbody>
                <c:forEach var="user" items="${orders_list}">
                    <tr>
                        <td>1</td>
                        <td>${user.getDate()}</td>
                        <td><a href="#" class="admin-edit">${edit}</a></td>
                        <td><a href="#" class="admin-delete">${delete}</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
