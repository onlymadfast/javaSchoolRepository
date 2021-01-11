<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>

    <title>CLIENTS</title>

    <link href="<c:url value="/resources/style.css"/>" rel="stylesheet" type="text/css"/>

</head>
<body>

<h1>Clients</h1>
<br>
<table>
    <caption>Clients</caption>
    <tr>
        <th>id</th>
        <th>firstName</th>
        <th>lastName</th>
        <th>birthday</th>
        <th>email</th>
        <th>password</th>
        <th>action</th>
    </tr>
    <c:forEach var="client"  items="${clientsList}">
        <tr>
            <td>${client.id}</td>
            <td>${client.firstName}</td>
            <td>${client.lastName}</td>
            <td>${client.birthday}</td>
            <td>${client.email}</td>
            <td>${client.password}</td>
            <td><a href="/edit/${client.id}">edit</a></td>
            <td><a href="/delete/${client.id}">delete</a></td>
        </tr>
    </c:forEach>
</table>

<h2>Add</h2>
<c:url value="/addPage" var="add"/>
<a href="${add}">Add new client</a>

</body>
</html>