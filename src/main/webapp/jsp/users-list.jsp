<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
    <style><%@include file="/css/admin.css"%></style>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"
            type="text/javascript"></script>
    <script><%@include file="/js/menu.js"%></script>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Rubik:wght@400;500&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@500&family=Rubik:wght@400;500&display=swap" rel="stylesheet">
</head>
<fmt:message bundle="${loc}" key="lang.label.number" var="number"/>
<fmt:message bundle="${loc}" key="lang.label.action" var="action"/>
<fmt:message bundle="${loc}" key="lang.label.user" var="user"/>
<fmt:message bundle="${loc}" key="lang.label.add" var="add"/>
<fmt:message bundle="${loc}" key="lang.label.edit" var="edit"/>
<fmt:message bundle="${loc}" key="lang.label.ban" var="ban"/>
<body>
    <%@ include file="/jsp/header/header-admin.jsp"%>
    <div class = "admin-container">
        <div class = "admin-header">
            <h2 class="admin-title">${users}</h2>
        </div>
        <div class="users-list=body">
            <div class="users-list-add"></div>
            <div class="users-list-manage">
                <table>
                    <thead>
                        <th>${number}</th>
                        <th>${user}</th>
                        <th colspan="3">${action}</th>
                    </thead>
                    <tbody>
                        <c:forEach var="user" items="${users_list}">
                            <tr>
                                <td>1</td>
                                <td>${user.getEmail()}</td>
                                <td><a href="#" class="admin-add">${add}</a></td>
                                <td><a href="#" class="admin-edit">${edit}</a></td>
                                <td><a href="#" class="admin-delete">${ban}</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>
