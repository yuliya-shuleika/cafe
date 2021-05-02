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
    <script><%@include file="/js/promo-codes-list.js"%></script>
</head>
<fmt:message bundle="${loc}" key="lang.label.number" var="number"/>
<fmt:message bundle="${loc}" key="lang.label.action" var="action"/>
<fmt:message bundle="${loc}" key="lang.label.promo_code" var="promo_code"/>
<fmt:message bundle="${loc}" key="lang.label.add" var="add"/>
<fmt:message bundle="${loc}" key="lang.label.edit" var="edit"/>
<fmt:message bundle="${loc}" key="lang.label.delete" var="delete"/>
<fmt:message bundle="${loc}" key="lang.label.by_name" var="by_name"/>
<fmt:message bundle="${loc}" key="lang.label.sort" var="sort"/>
<fmt:message bundle="${loc}" key="lang.label.promo_add" var="promo_add"/>
<fmt:message bundle="${loc}" key="lang.label.no_data_found" var="no_data_found"/>
<body>
<%@include file="/jsp/header/header-admin.jsp"%>
<%@include file="/jsp/modal/add-promo-code.jsp"%>
<%@include file="/jsp/modal/edit-promo-code.jsp"%>
<c:if test="${selected_promo_code != null}">
    <script>
        let edit = document.getElementById('edit-promo')
        edit.style.display = 'block'
    </script>
</c:if>
<c:if test="${edit_error_message != null}">
    <c:choose>
        <c:when test="${edit_error_message == 'add_error'}">
            <script>
                let add = document.getElementById('add-promo')
                add.style.display = 'block'
            </script>
        </c:when>
        <c:when test="${edit_error_message == 'edit_error'}">
            <script>
                let edit = document.getElementById('edit-promo')
                edit.style.display = 'block'
            </script>
        </c:when>
    </c:choose>
</c:if>
<div class = "admin-container">
    <div class = "admin-header">
        <div class="admin-add-container">
            <button class="admin-add">${promo_add}</button>
        </div>
        <h2 class="admin-title">${promo_codes}</h2>
    </div>
    <div class="dishes-list-body">
        <div class="admin-manage">
            <div class="admin-manage-start">
                <p class="admin-sort-label">${sort}</p>
                <form action="controller" method="post" class="admin-sorting-form">
                    <input type="hidden" name="command" value="sort_promo_codes_by_name">
                    <button class="admin-sort-criteria" type="submit">${by_name}</button>
                </form>
            </div>
            <div class="admin-manage-end">
                <form action="promo-codes.do" method="post" class="admin-searching-form"
                      onsubmit="validatePromoCodeSearch(this); return false;">
                    <input type="hidden" name="command" value="search_promo_code_by_name_part">
                    <input class="admin-search" type="search" id="search_promo_code" name="promo_code_name_part"
                           placeholder="Search...">
                </form>
            </div>
        </div>
        <div class="admin-table">
            <table>
                <thead>
                <th>${number}</th>
                <th>${promo_code}</th>
                <th colspan="2">${action}</th>
                </thead>
                <tbody>
                    <c:if test="${promo_codes_list != null && !promo_codes_list.isEmpty()}">
                        <c:forEach var="promoCode" items="${promo_codes_list}" varStatus="loop">
                            <tr>
                                <td>${loop.index + 1}</td>
                                <td>${promoCode.getName()}</td>
                                <td>
                                    <a href="promo-codes.do?command=show_promo_code_edit&promo_code_id=${promoCode.getPromoCodeId()}"
                                        class="admin-edit">${edit}</a>
                                </td>
                                <td>
                                    <input type="hidden" name="command" value="delete_promo_code">
                                    <input type="hidden" name="promo_code_id" value="${promoCode.getPromoCodeId()}">
                                    <a href="#" class="admin-delete">${delete}</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </tbody>
            </table>
            <c:if test="${promo_codes_list.isEmpty() || promo_codes_list == null}">
                <p class="admin-not-found-label">${no_data_found}</p>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>