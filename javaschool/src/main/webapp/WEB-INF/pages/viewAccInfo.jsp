<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>View some info</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
          crossorigin="anonymous">
    <style>
        .div1 {
            background-color: #CCCCFF;
            padding-bottom: 50px;
        }
    </style>
</head>
<body>
<jsp:include page="navbar.jsp"/>
<div class="container-fluid div1 h-100">
    <br>
    <h3>User information: </h3>
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">First Name</th>
            <th scope="col">Last Name</th>
            <th scope="col">Birthday</th>
            <th scope="col">Email</th>
            <th scope="col">Edit info</th>
            <th scope="col">Change Password</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th scope="row"></th>
            <td>${user.userFirstName}</td>
            <td>${user.userLastName}</td>
            <td>${user.userBirthday}</td>
            <td>${user.userEmail}</td>
            <td>
                <form action="${pageContext.request.contextPath}/editAccInfo" method="get">
                    <input type="hidden" name="id" value="${user.id}">
                    <button class="btn btn-primary" type="submit">Edit information</button>
                </form>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/changePassword" class="btn btn-primary">Change Password</a>
            </td>
        </tr>
        </tbody>
    </table>
</div>

<c:if test="${userAddress !=null }">
    <div class="container-fluid div1 h-100">
        <br>
        <h3>User address: </h3>
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Country</th>
                <th scope="col">City</th>
                <th scope="col">Zip</th>
                <th scope="col">Street</th>
                <th scope="col">House</th>
                <th scope="col">Apartment</th>
                <th scope="col">Edit address</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th scope="row"></th>
                <td>${userAddress.userCountry}</td>
                <td>${userAddress.userCity}</td>
                <td>${userAddress.userZip}</td>
                <td>${userAddress.userStreet}</td>
                <td>${userAddress.userHouse}</td>
                <td>${userAddress.userApartment}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/editAddress" method="get">
                        <input type="hidden" name="username" value="${user.username}">
                        <button class="btn btn-primary" type="submit">Edit address</button>
                    </form>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</c:if>

<c:if test="${userAddress == null}">
    <h3>There is no information on the user address.
        Information will be updated when at least one purchase is made.</h3>
</c:if>

<jsp:include page="footer.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
</body>
</html>