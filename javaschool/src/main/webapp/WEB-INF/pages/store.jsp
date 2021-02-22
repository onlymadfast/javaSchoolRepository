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

<div class="container-fluid p-1 div1 text-center">
    <div class="container p-2">
        <h4>Filter by category: </h4>
        <c:forEach items="${listCategory}" var="cat">
            <a class="page-item btn btn-dark" href="${pageContext.request.contextPath}/showResult/${cat.itemCategory}">${cat.itemCategory}</a>
        </c:forEach>
        <hr>
    </div>
</div>

<div class="container-fluid p-2 div1">
    <c:forEach items="${allGoods}" var="product">
        <div class="card rounded-3" style="height: 28rem; width: 18rem;">
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
                    <a href="${pageContext.request.contextPath}/updateProduct?id=${product.id}" class="card-link">Edit product</a>
                </security:authorize>
            </div>
        </div>
    </c:forEach>
</div>
<div class="container-fluid p-1 text-center div1">
    <div class="container table p-2">
        <hr>
        <h6 class="card-title">Total items: ${totalItems} --- Page ${currentPage} of ${totalPages}</h6>
        <c:forEach begin="1" end="${totalPages}" var="num">
            <c:choose>
                <c:when test="${num !=currentPage}">
                    <a class="page-item btn btn-outline-info btn-sm" href="/store/${num}">${num}</a>
                </c:when>
                <c:otherwise>
                    <button class="page-item btn btn-outline-dark btn-sm ">${num}</button>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </div>
</div>

<jsp:include page="footer.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
</body>
</html>