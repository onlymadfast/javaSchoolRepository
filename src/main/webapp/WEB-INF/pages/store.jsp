<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Store</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
          crossorigin="anonymous">
    <style>
        .div1 {
            background-color: #CCCCFF;
            padding-bottom: 50px;
            display: flex;
            justify-content: space-around;
        }
    </style>
</head>
<body>
<jsp:include page="navbar.jsp"/>
<div class="container-fluid p-2 div1">
    <c:forEach items="${allGoods}" var="product">
        <div class="card rounded-3" style="width: 20rem">
            <img src="${product.image}"
                 class="card-img-top rounded-pill" alt="picture" style="height: 23rem;">
            <div class="card-body"><h5 class="card-title text-center">Name: ${product.itemName}</h5></div>
            <ul class="list-group list-group-flush text-center">
                <li class="list-group-item">Price : ${product.itemPrice}</li>
                <li class="list-group-item">Category: ${product.itemCategoryDto}</li>
                <li class="list-group-item">Size: ${product.sizeTableDto}</li>
            </ul>
            <div class="card-body text-center">
                <a href="#" class="card-link">Buy It</a>
                <security:authorize access="hasRole('ROLE_ADMIN')">
                    <a href="#" class="card-link link-disabled">Edit product</a>
                </security:authorize>
            </div>
        </div>
    </c:forEach>
</div>
<jsp:include page="footer.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
</body>
</html>