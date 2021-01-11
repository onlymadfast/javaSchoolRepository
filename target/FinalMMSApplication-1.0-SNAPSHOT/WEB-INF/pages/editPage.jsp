<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:choose>
        <c:when test="${empty client.firstName}">
            <title>Add</title>
        </c:when>
        <c:otherwise>
            <title>Edit</title>
        </c:otherwise>
    </c:choose>
</head>
<body>

<c:url value="/addClient" var="addUrl"/>
<c:url value="/editClient" var="editUrl"/>

<form action="${empty client.firstName ? addUrl : editUrl}" name="client" method="POST">

    <c:if test="${!empty client.firstName}">
        <input type="hidden" name="id" value="${client.id}">
    </c:if>

    <p><input type="text" name="firstName" placeholder="firstName" value="${client.firstName}" maxlength="50" required></p>
    <p><input type="text" name="lastName" placeholder="lastName" value="${client.lastName}" maxlength="50" required></p>
    <p><input type="text" name="birthday" placeholder="birthday" value="${client.birthday}" required></p>
    <p><input type="email" name="email" placeholder="email" value="${client.email}" maxlength="50" required></p>
    <p><input type="password" name="password" placeholder="password" value="${client.password}" maxlength="50" required></p>

    <p>
        <c:set value="add" var="add"/>
        <c:set value="edit" var="edit"/>
        <input type="submit" value="${empty client.firstName ? add : edit}">
    </p>

</form>
</body>
</html>