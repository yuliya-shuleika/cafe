<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<fmt:setLocale value='<%=request.getSession().getAttribute("lang")%>'/>
<fmt:setBundle basename="lang" var="loc"/>
<fmt:message bundle="${loc}" key="lang.label.fill_fields_correct" var="fill_fields_correct"/>
<head>
    <title>Users</title>
    <style><%@include file="/css/admin.css"%></style>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Rubik:wght@400;500&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@500&family=Rubik:wght@400;500&display=swap" rel="stylesheet">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"
            type="text/javascript"></script>
    <script><%@include file="/js/dishes-list.js"%></script>
</head>
<fmt:message bundle="${loc}" key="lang.label.number" var="number"/>
<fmt:message bundle="${loc}" key="lang.label.action" var="action"/>
<fmt:message bundle="${loc}" key="lang.label.dish" var="dish"/>
<fmt:message bundle="${loc}" key="lang.label.add" var="add"/>
<fmt:message bundle="${loc}" key="lang.label.edit" var="edit"/>
<fmt:message bundle="${loc}" key="lang.label.delete" var="delete"/>
<fmt:message bundle="${loc}" key="lang.label.by_name" var="by_name"/>
<fmt:message bundle="${loc}" key="lang.label.sort" var="sort"/>
<fmt:message bundle="${loc}" key="lang.label.dish_add" var="dish_add"/>
<fmt:message bundle="${loc}" key="lang.label.no_data_found" var="no_data_found"/>
<body>
    <%@ include file="/jsp/header/header-admin.jsp"%>
    <%@ include file="/jsp/modal/add-dish.jsp"%>
    <%@ include file="/jsp/modal/edit-dish.jsp"%>
    <c:if test="${selected_dish != null}">
        <script>
            let edit = document.getElementById('edit-dish')
            edit.style.display = 'block'
        </script>
    </c:if>
    <c:if test="${edit_error_message != null}">
        <c:choose>
            <c:when test="${edit_error_message == 'add_error'}">
                <script>
                    let add = document.getElementById('add-dish')
                    add.style.display = 'block'
                </script>
            </c:when>
            <c:when test="${edit_error_message == 'edit_error'}">
                <script>
                    let edit = document.getElementById('edit-dish')
                    edit.style.display = 'block'
                </script>
            </c:when>
        </c:choose>
    </c:if>
    <div class = "admin-container">
        <div class = "admin-header">
            <div class="admin-add-container">
                <button class="admin-add">${dish_add}</button>
            </div>
            <h2 class="admin-title">${dishes}</h2>
        </div>
        <div class="dishes-list-body">
            <div class="admin-manage">
                <div class="admin-manage-start">
                    <p class="admin-sort-label">${sort}</p>
                    <form action="dishes.do" method="post" class="admin-sorting-form">
                        <input type="hidden" name="command" value="sort_dishes_by_name">
                        <button class="admin-sort-criteria" type="submit">${by_name}</button>
                    </form>
                </div>
                <div class="admin-manage-end">
                    <form action="dishes.do" method="post" class="admin-searching-form"
                          onsubmit="validateDishSearch(this); return false;">
                        <input type="hidden" name="command" value="search_dish_by_name">
                        <input class="admin-search" type="search" name="dish_name"
                               placeholder="Search..." id="search-dish">
                    </form>
                </div>
            </div>
            <div class="admin-table">
                <table>
                    <thead>
                    <th>${number}</th>
                    <th>${dish}</th>
                    <th colspan="2">${action}</th>
                    </thead>
                    <tbody>
                        <c:if test="${dishes_list != null && !dishes_list.isEmpty()}">
                            <c:forEach var="dish" items="${dishes_list}" varStatus="loop">
                                <tr>
                                    <td>${loop.index + 1}</td>
                                    <td>${dish.getName()}</td>
                                    <td>
                                        <a href="dishes-list.do?command=show_dish_edit&dish_id=${dish.getDishId()}"
                                                class="admin-edit">${edit}</a>
                                    </td>
                                    <td>
                                        <input type="hidden" name="command" value="delete_dish_from_menu">
                                        <input type="hidden" name="dish_id" value="${dish.getDishId()}">
                                        <a href="#" class="admin-delete">${delete}</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>
                    </tbody>
                </table>
                <c:if test="${dishes_list.isEmpty() || dishes_list == null}">
                    <p class="admin-not-found-label">${no_data_found}</p>
                </c:if>
            </div>
        </div>
    </div>
</body>
</html>
