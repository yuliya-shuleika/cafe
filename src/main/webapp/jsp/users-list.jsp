<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<fmt:setLocale value='<%=request.getSession().getAttribute("lang")%>'/>
<fmt:setBundle basename="lang" var="loc"/>
<fmt:message bundle="${loc}" key="lang.label.block" var="block"/>
<fmt:message bundle="${loc}" key="lang.label.unblock" var="unblock"/>
<head>
    <title>Users</title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"
            type="text/javascript"></script>
    <script><%@include file="/js/users-list.js"%></script>
    <style><%@include file="/css/admin.css"%></style>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Rubik:wght@400;500&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@500&family=Rubik:wght@400;500&display=swap" rel="stylesheet">
</head>
<fmt:message bundle="${loc}" key="lang.label.number" var="number"/>
<fmt:message bundle="${loc}" key="lang.label.action" var="action"/>
<fmt:message bundle="${loc}" key="lang.label.user" var="user"/>
<fmt:message bundle="${loc}" key="lang.label.add" var="add"/>
<fmt:message bundle="${loc}" key="lang.label.edit" var="edit"/>
<fmt:message bundle="${loc}" key="lang.label.sort" var="sort"/>
<fmt:message bundle="${loc}" key="lang.label.by_email" var="by_email"/>
<fmt:message bundle="${loc}" key="lang.label.status" var="status"/>
<fmt:message bundle="${loc}" key="lang.label.role" var="role"/>
<fmt:message bundle="${loc}" key="lang.label.admin" var="admin"/>
<fmt:message bundle="${loc}" key="lang.label.role_user" var="role_user"/>
<fmt:message bundle="${loc}" key="lang.label.no_data_found" var="no_data_found"/>
<body>
    <%@ include file="/jsp/header/header-admin.jsp"%>
    <div class = "admin-container">
        <div class = "admin-header">
            <h2 class="admin-title">${users}</h2>
        </div>
        <div class="users-list-body">
            <div class="admin-manage">
                <div class="admin-manage-start">
                    <p class="admin-sort-label">${sort}</p>
                    <form action="controller" method="post" class="admin-sorting-form">
                        <input type="hidden" name="command" value="sort_users_by_email">
                        <button class="admin-sort-criteria" type="submit">${by_email}</button>
                    </form>
                </div>
                <div class="admin-manage-end">
                    <form action="users.do" method="post" class="admin-searching-form"
                          onsubmit="validateUserSearch(this); return false;">
                        <input type="hidden" name="command" value="search_user_by_email">
                        <input class="admin-search" type="search" id="search_user" name="user_email"
                               placeholder="Search...">
                    </form>
                </div>
            </div>
            <div class="admin-table">
                <table>
                    <thead>
                        <th>${number}</th>
                        <th>${user}</th>
                        <th>${role}</th>
                        <th class="user-status">${status}</th>
                        <th>${action}</th>
                    </thead>
                    <tbody>
                        <c:if test="${users_list != null && !users_list.isEmpty()}">
                            <c:forEach var="user" items="${users_list}" varStatus="loop">
                                <tr>
                                    <td>${loop.index + 1}</td>
                                    <td>${user.getEmail()}</td>
                                    <td class = "user-status">${user.getStatus()}</td>
                                    <td>
                                        <input type="hidden" name="status" value="${user.getRole()}">
                                        <select name="user_role" class="admin-select">
                                            <option class="admin-option" value="new">${role_user}</option>
                                            <option class="admin-option" value="approved">${admin}</option>
                                        </select>
                                    </td>
                                    <td>
                                        <input type="hidden" name="user_id" value="${user.getUserId()}">
                                        <c:if test="${user.status != 'BLOCKED'}">
                                            <a href="#" class="admin-delete">${block}</a>
                                        </c:if>
                                        <c:if test="${user.status == 'BLOCKED'}">
                                            <a href="#" class="admin-delete">${unblock}</a>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>
                    </tbody>
                </table>
                <c:if test="${users_list.isEmpty() || users_list == null}">
                    <p class="admin-not-found-label">${no_data_found}</p>
                </c:if>
            </div>
        </div>
    </div>
</body>
</html>
