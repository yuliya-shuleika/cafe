<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Header</title>
    <style><%@include file="/css/navigation.css"%></style>
</head>
<body>
<div class="topnav">
    <a href="controller?command=to_home">${home}</a>
    <a href="controller?command=to_login">${login}</a>
    <a href="controller?command=to_register">${register}</a>
    <a href="controller?command=to_users_list">${users_list}</a>
    <a href="controller?command=change_locale">${translate}</a>
</div>
</body>
</html>
