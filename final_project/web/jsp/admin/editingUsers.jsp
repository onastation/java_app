
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="center">
    <h1>Edit user</h1>
    <form action="/admin/editUser" method="post">
        <input name="user_id" value="${user_id}" readonly>
        <input type="text" placeholder="New name" name="username">
        <input type="text" placeholder="New password" name="password">
        <input type="text" placeholder="New role" name="role">
        <input type="submit" value="Edit">
        </form>
</div>
</body>
</html>
