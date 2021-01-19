<%@ page import="model.entity.Activity" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Managing</title>
</head>
<body>
<h1>Add a new user</h1>
<form action="/admin/addUser" method="post">
    <input type="text" name="username" placeholder="Name of the new user">
    <input type="text" name="password" placeholder="Password of the new user">
    <input type="text" name="role" placeholder="Role of the new user">
    <input type="submit" value="Submit">
</form>
<br>
<div class="center">
    <h1>List of users</h1>

    <table border="1">
        <tr>
            <td>ID</td>
            <td>Username</td>
            <td>Password</td>
            <td>Role</td>
        </tr>
        <c:forEach var="user" items="${users}">
        <tr>
            <td><c:out value="${user.id}"/></td>
            <td><c:out value="${user.username}"/></td>
            <td><c:out value="${user.passwd}"/></td>
            <td><c:out value="${user.role}"/></td>
            <td>
                <a href="/admin/deleteUser?user_id=${user.id}">Delete</a>
            </td>
            <td>
                <a href="/admin/editUser?user_id=${user.id}">Edit</a>
            </td>
        </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
