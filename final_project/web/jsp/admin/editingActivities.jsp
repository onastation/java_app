
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="center">
    <h1>Edit activity</h1>
    <form action="/admin/editActivity" method="post">
        <input name="activity_id" value="${activity_id}" readonly>
        <input type="text" placeholder="New name" name="activity_name">
        <input type="text" placeholder="New category" name="category">
        <input type="submit" value="Edit">
        </form>
</div>
</body>
</html>
