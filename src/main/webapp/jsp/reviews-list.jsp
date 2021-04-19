<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
    <style><%@include file="/css/admin.css"%></style>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"
            type="text/javascript"></script>
    <script><%@include file="/js/reviews-list.js"%></script>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Rubik:wght@400;500&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@500&family=Rubik:wght@400;500&display=swap" rel="stylesheet">
</head>
<fmt:setLocale value='<%=request.getSession().getAttribute("lang")%>'/>
<fmt:setBundle basename="lang" var="loc"/>
<fmt:message bundle="${loc}" key="lang.label.number" var="number"/>
<fmt:message bundle="${loc}" key="lang.label.action" var="action"/>
<fmt:message bundle="${loc}" key="lang.label.review" var="review"/>
<fmt:message bundle="${loc}" key="lang.label.add" var="add"/>
<fmt:message bundle="${loc}" key="lang.label.edit" var="edit"/>
<fmt:message bundle="${loc}" key="lang.label.delete" var="delete"/>
<fmt:message bundle="${loc}" key="lang.label.sort" var="sort"/>
<fmt:message bundle="${loc}" key="lang.label.status" var="status"/>
<fmt:message bundle="${loc}" key="lang.label.by_header" var="by_header"/>
<fmt:message bundle="${loc}" key="lang.label.new_review" var="new_review"/>
<fmt:message bundle="${loc}" key="lang.label.approved" var="approved"/>
<fmt:message bundle="${loc}" key="lang.label.rejected" var="rejected"/>
<fmt:message bundle="${loc}" key="lang.label.watch" var="watch"/>
<fmt:message bundle="${loc}" key="lang.label.new_reviews" var="new_reviews"/>
<body>
<%@ include file="/jsp/header/header-admin.jsp"%>
<%@ include file="/jsp/modal/review-info.jsp"%>
<div class = "admin-container">
    <div class = "admin-header">
        <h2 class="admin-title">${reviews}</h2>
    </div>
    <div class="reviews-list-body">
        <div class="admin-manage">
            <div class="admin-manage-start">
                <p class="admin-sort-label">${sort}</p>
                <form action="controller" method="post" class="admin-sorting-form">
                    <input type="hidden" name="command" value="sort_reviews_by_header">
                    <button class="admin-sort-criteria" type="submit">${by_header}</button>
                </form>
                <form action="controller" method="post" class="admin-sorting-form">
                    <input type="hidden" name="command" value="show_new_reviews">
                    <button class="admin-sort-criteria" type="submit">${new_reviews}</button>
                </form>
            </div>
            <div class="admin-manage-end">
                <form action="controller" method="post" class="admin-searching-form">
                    <input type="hidden" name="command" value="search_review_by_header">
                    <input class="admin-search" type="search" id="search_review" name="review_header"
                           placeholder="Search..." required
                           minlength="1" maxlength="20">
                </form>
            </div>
        </div>
        <div class="admin-table">
            <table>
                <thead>
                <th>${number}</th>
                <th>${review}</th>
                <th>${status}</th>
                <th colspan="2">${action}</th>
                </thead>
                <tbody>
                <c:forEach var="review" items="${reviews_list}">
                    <tr>
                        <input type="hidden" name="review_id" value="${review.getReviewId()}">
                        <td>1</td>
                        <td>${review.getHeader()}</td>
                        <td>
                            <input type="hidden" name="status" value="${review.getStatus()}">
                            <select name="review_status" class="admin-select">
                                <option class="admin-option" value="new">${new_review}</option>
                                <option class="admin-status-option" value="approved">${approved}</option>
                                <option class="admin-option" value="rejected">${rejected}</option>
                            </select>
                        </td>
                        <td><a href="#" class="admin-edit">${watch}</a></td>
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
