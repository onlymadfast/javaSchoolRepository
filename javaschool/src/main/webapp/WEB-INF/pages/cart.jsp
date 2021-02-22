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
        <div class="card text-center">
            <h4 class="card-title">Items in my cart: </h4>
            <div class="card-body">
                Opps, there is no items...
                <br><br>
                <a href="${pageContext.request.contextPath}/store"
                   class="card-link">Show product list</a>
            </div>
        </div>
    </div>
</c:if>

<c:if test="${not empty cartForm and not empty cartForm.goodsDTOList}">
    <form:form method="post" modelAttribute="cartForm" action="${pageContext.request.contextPath}/shoppingCart">
        <div class="container-fluid p-2 div1">
            <c:forEach items="${cartForm.goodsDTOList}" var="cartLineInfo" varStatus="varStatus">
                <div class="card rounded-3" style="width: 18rem">
                    <img src="${cartLineInfo.image}"
                         class="card-img-top rounded-pill" alt="picture" style="height: 23rem;">
                    <div class="card-body">
                        <h5 class="card-title text-center">Name: ${cartLineInfo.itemName} || ID: ${cartLineInfo.id}</h5>
                    </div>
                    <ul class="list-group list-group-flush text-center">
                        <li class="list-group-item">Price: ${cartLineInfo.itemPrice}$</li>
                        <li class="list-group-item">Category: ${cartLineInfo.category.itemCategory}</li>
                        <li class="list-group-item">Size: ${cartLineInfo.itemSize.name()}</li>
                        <li class="list-group-item">Quantity:
<%--                            <form:input path="" />--%>
                        </li>


<%--                        TODO: отключючить quantity на время    --%>


                        <li class="list-group-item">
                            <span class="card-subtitle"> ${cartLineInfo.amount}$</span>
                        </li>
                    </ul>
                    <div class="card-body text-center">
                        <a class="nav-link" href="${pageContext.request.contextPath}/shoppingCartRemoveProduct?id=${cartLineInfo.id}">Delete</a>
                    </div>
                </div>
            </c:forEach>

            <div class="container p-2 col-6 text-center div1">
                <div class="card-body">
                    <input class="btn btn-outline-primary" type="submit" value="Update Quantity"/>
                    <a class="nav-link" href="${pageContext.request.contextPath}/shoppingCartCustomer">Next step</a>
                    <a class="nav-link" href="${pageContext.request.contextPath}/store">Store/Continue Buy</a>
                </div>
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