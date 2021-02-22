<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ page isELIgnored = "false" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>AccountInformation</title>
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
    <h4>Your account information and opportunities: </h4>
    <br>
    <div class="card">
        <div class="card-body">
            <h5 class="mt-0">Lets try to buy something!</h5>
            <p>In this store you can shop for various reasons, one of the possible reasons is... you are missing
                something</p>
            <a href="${pageContext.request.contextPath}/store" class="card-link">Store</a>
        </div>
    </div>
    <br>
    <div class="card">
        <div class="card-body">
            <h5 class="mt-0">View order history / current order status</h5>
            <p>Here you can safely shop and track the status of the item in your personal account</p>
            <a href="#" class="card-link">View order status information</a>
        </div>
    </div>
    <br>
    <div class="card">
        <div class="card-body">
            <h5 class="mt-0">View/Edit profile</h5>
            <p>Here you can make a shift password, and other information fields</p>
            <a href="${pageContext.request.contextPath}/viewAccInfo" class="card-link">View/Edit your account information</a>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
</body>
</html>