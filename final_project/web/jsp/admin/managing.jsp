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
<h1>Add a new activity</h1>
<form action="/admin/addActivity" method="post">
    <input type="text" name="activity_name" placeholder="Name of the new activity">
    <input type="text" name="category" placeholder="Category of the new activity">
    <input type="submit" value="Submit">
</form>
<br>
<div class="center">
    <h1>List of activities</h1>

    <table border="1">
        <tr>
            <td>ID</td>
            <td>Activity name</td>
            <td>Activity category</td>
        </tr>
        <c:forEach var="activity" items="${activities}">
        <tr>
            <td><c:out value="${activity.id}"/></td>
            <td><c:out value="${activity.name}"/></td>
            <td><c:out value="${activity.category}"/></td>
            <td>
                <a href="/admin/deleteActivity?activity_id=${activity.id}">Delete</a>
            </td>
            <td>
                <a href="/admin/editActivity?activity_id=${activity.id}">Edit</a>
            </td>
        </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
