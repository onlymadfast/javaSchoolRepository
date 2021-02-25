<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Shopping cart</title>
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

<c:if test="${empty cartForm or empty cartForm.goodsDTOList}">
    <div class="container-fluid p-2 div1">
        <div class="card text-center p-3">
            <h4 class="card-title">Items in my cart: </h4>
            <div class="card-body">
                <p>NO ITEMS</p>
                <a href="${pageContext.request.contextPath}/store"
                   class="btn btn-outline-primary">Show product list</a>
            </div>
        </div>
    </div>
</c:if>

<c:if test="${not empty cartForm and not empty cartForm.goodsDTOList}">
    <form:form action="shoppingCart" method="post" modelAttribute="cartForm">
        <div class="container-fluid p-2 div1">
            <c:forEach items="${cartForm.goodsDTOList}" var="goodsDTO" varStatus="varStatus">
                <div class="card rounded-3" style="width: 18rem">
                    <img src="${goodsDTO.image}"
                         class="card-img-top rounded-pill" alt="picture" style="height: 23rem;">
                    <div class="card-body">
                        <h5 class="card-title text-center">Name: ${goodsDTO.itemName}</h5>
                    </div>
                    <ul class="list-group list-group-flush text-center">
                        <li class="list-group-item">Price: ${goodsDTO.itemPrice}$</li>
                        <li class="list-group-item">Category: ${goodsDTO.category.itemCategory}</li>
                        <li class="list-group-item">Size: ${goodsDTO.itemSize.name()}</li>
                        <li class="list-group-item">Quantity:
                            <input name="${varStatus.index}" />
                        </li>

                        <li class="list-group-item">
                            <c:set value="${goodsDTO.itemPrice * goodsDTO.itemQuantity}" var="amount"/>
                            <span class="card-subtitle"> ${amount}$</span>
                        </li>
                    </ul>
                    <div class="card-body text-center">
                        <a class="btn btn-outline-primary"
                           href="${pageContext.request.contextPath}/shoppingCartRemoveProduct?id=${goodsDTO.id}">Delete</a>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div class="container-fluid p-2 text-center div1">
            <div class="card-body">
                <input class="btn btn-outline-primary" type="submit" value="Update Quantity"/>
                <a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/shoppingCartCustomer">Next step</a>
                <a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/store">Store/Continue Buy</a>
            </div>
        </div>
    </form:form>
</c:if>

<jsp:include page="footer.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
</body>
</html>