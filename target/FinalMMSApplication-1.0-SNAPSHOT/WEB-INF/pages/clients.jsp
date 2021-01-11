<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

</head>
<body>
<table class="table">
    <thead>
    <tr>
        <th scope="col">id</th>
        <th scope="col">fname</th>
        <th scope="col">lname</th>
        <th scope="col">birthday</th>
        <th scope="col">email</th>
        <th scope="col">password</th>
        <th scope="col">country</th>
        <th scope="col">city</th>
        <th scope="col">zip</th>
        <th scope="col">street</th>
        <th scope="col">house</th>
        <th scope="col">apartment</th>
        <th scope="col">client_id</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="client"  items="${clientsList}">
        <tr>
            <td><c:out value="${client.id}"/></td>
            <td><c:out value="${client.firstName}"/></td>
            <td><c:out value="${client.lastName}"/></td>
            <td><c:out value="${client.birthday}"/></td>
            <td><c:out value="${client.email}"/></td>
            <td><c:out value="${client.password}"/></td>
            <td><c:out value="${client.clientAddress.country}"/></td>
            <td><c:out value="${client.clientAddress.city}"/></td>
            <td><c:out value="${client.clientAddress.zip}"/></td>
            <td><c:out value="${client.clientAddress.street}"/></td>
            <td><c:out value="${client.clientAddress.house}"/></td>
            <td><c:out value="${client.clientAddress.apartment}"/></td>
            <td><c:out value="${client.clientAddress.client_id}"/></td>
<%--            <td><a href="/edit/${client.id}">edit</a></td>--%>
<%--            <td><a href="/delete/${client.id}">delete</a></td>--%>
        </tr>
    </c:forEach>
    </tbody>
</table>



<script src="https://kit.fontawesome.com/5a393fd603.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
</body>
</html>