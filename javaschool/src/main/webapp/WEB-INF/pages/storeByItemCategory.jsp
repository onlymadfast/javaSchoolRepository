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
    <title>Show</title>
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

<div class="container-fluid div1 p-1 text-center">
    <div class="container p-2">
        <h4>Select category: </h4>
        <c:forEach items="${listCategory}" var="cat">
            <c:choose>
                <c:when test="${cat.itemCategory != thisCategory}">
                    <a class="page-item btn btn-outline-dark" href="${pageContext.request.contextPath}/showResult/${cat.itemCategory}">${cat.itemCategory}</a>
                </c:when>
                <c:otherwise>
                    <button class="page-item btn btn-dark">${cat.itemCategory}</button>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <a class="page-item btn btn-primary" href="${pageContext.request.contextPath}/store">All items</a>
        <hr>
    </div>
</div>

<div class="container-fluid p-2 div1">
    <c:forEach items="${goodsByCat}" var="product">
        <div class="card rounded-3" style="width: 20rem">
            <img src="${product.image}"
                 class="card-img-top rounded-pill" alt="picture" style="height: 23rem;">
            <div class="card-body"><h5 class="card-title text-center">Name: ${product.itemName}</h5></div>
            <ul class="list-group list-group-flush text-center">
                <li class="list-group-item">Price : ${product.itemPrice}</li>
                <li class="list-group-item">Category: ${product.category.itemCategory}</li>
                <li class="list-group-item">Size: ${product.itemSize.name()}</li>
            </ul>
            <div class="card-body text-center">
                <c:choose>
                    <c:when test="${pageContext.request.userPrincipal.name != null}">
                        <a href="${pageContext.request.contextPath}/buyProduct?id=${product.id}"
                           class="card-link">Buy It</a>
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/login" class="card-link">Login</a> or
                        <a href="${pageContext.request.contextPath}/reg" class="card-link">Registration first</a>
                    </c:otherwise>
                </c:choose>
                <security:authorize access="hasRole('ROLE_ADMIN')">
                    <a href="${pageContext.request.contextPath}/updateProduct" class="card-link">Edit product</a>
                </security:authorize>
            </div>
        </div>
    </c:forEach>
</div>
<hr>
<jsp:include page="footer.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
</body>
</html>