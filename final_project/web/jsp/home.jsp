<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
  <title>app</title>
  <style>
    <%@include file="/css/mainPage.css"%>
  </style>
</head>
<div class="center">
  <c:if test="${user!=null}" >
    <%@include file="user-header.jspf" %>
  </c:if>
  <c:if test="${user==null}" >
    <%@include file="guest-header.jspf" %>
  </c:if>
  <body>
  <h1>Choosing activities</h1>
  <div class="center">
    <h1>List of activities</h1>

    <table border="1">
      <tr>
        <td>ID</td>
        <td>Activity name</td>
        <td>Activity category</td>
        <td>Time spent</td>
      </tr>
      <c:forEach var="activity" items="${activities}">
        <tr>
          <td><c:out value="${activity.id}"/></td>
          <td><c:out value="${activity.name}"/></td>
          <td><c:out value="${activity.category}"/></td>
          <td><c:out value="${0}"/></td>
          <td>
            <a href="/user/editTime?activity_id=${activity.id}">Edit</a>
          </td>
        </tr>
      </c:forEach>
    </table>
  <%@include file="pagination.jsp"%>
</div>
</body>
</html>
