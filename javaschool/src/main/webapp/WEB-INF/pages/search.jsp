<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Search</title>
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

<div class="container-fluid p-2 text-center div1">
    <form action="${pageContext.request.contextPath}/search">
        <p>Searching by name: </p>
        <input type="text" name="keyword" value="${keyword}" required/>&nbsp;
        <input class="btn btn-outline-primary" type="submit" value="Search"/>&nbsp;
        <input class="btn btn-outline-primary" type="button" value="Clear" onclick="clearFilter()"/>
    </form>
</div>

<div class="container-fluid p-2 div1 d-flex justify-content-around">
    <c:forEach items="${search}" var="product">
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
                           class="btn btn-outline-primary">Buy It</a>&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/login" class="btn btn-outline-primary">Login</a>
                        &nbsp;
                        <a href="${pageContext.request.contextPath}/reg" class="btn btn-outline-primary">Registration</a>
                    </c:otherwise>
                </c:choose>
                <security:authorize access="hasRole('ROLE_ADMIN')">
                    <a href="${pageContext.request.contextPath}/updateProduct?id=${product.id}" class="btn btn-outline-dark">Edit</a>
                    &nbsp;
                    <a href="${pageContext.request.contextPath}/deleteProduct?id=${product.id}" class="btn btn-outline-dark">Delete</a>
                </security:authorize>
            </div>
        </div>
    </c:forEach>
</div>

<jsp:include page="footer.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>

<script type="text/javascript">
    function clearFilter(){
        window.location='/store';
    }
</script>
</body>
</html>