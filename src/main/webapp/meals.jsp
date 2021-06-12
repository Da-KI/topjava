<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<html lang="ru">
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<h2>
    <table>
        <tbody>
        <tr>
            <th>DATE</th>
            <th>Description</th>
            <th>Calories</th>
            <th colspan=2>Action</th>
        </tr>
        <c:set var="dateTimeFormatter" value="${dateTimeFormatter}"/>
        <c:forEach items="${meals}" var="meal">
            <c:set var="color" value="${meal.excess ? 'red' : 'green'}" />
            <tr style="color: ${color}">
            <td> ${meal.dateTime.format(dateTimeFormatter)} </td>
            <td> ${meal.description} </td>
            <td> ${meal.calories} </td>
            </tr> </c:forEach>
        </tbody>
    </table>
</h2>
</body>
</html>