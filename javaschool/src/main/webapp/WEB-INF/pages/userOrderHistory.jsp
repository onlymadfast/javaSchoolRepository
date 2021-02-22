<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>User Order History</title>
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

<div class="container-fluid div1 text-center">
    <br>
    <h4>Enter you Order Number:</h4>
    <br>
    <form method="get" action="${pageContext.request.contextPath}/userHistory">
        <input type="text" name="orderNum"/>
        <input type="submit" value="Search"/>
    </form>
</div>

<c:choose>
    <c:when test="${not empty order}">
        <div class="container-fluid div1">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Order number</th>
                    <th scope="col">Date</th>
                    <th scope="col">HowPay/HowDeliver</th>
                    <th scope="col">StatusOrder</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <th scope="row"></th>
                    <td>${order.orderNum}</td>
                    <td>${order.orderDate}</td>
                    <td>${order.howPay}&nbsp;${order.howDeliver}</td>
                    <td>${order.statusOrder}</td>
                </tr>
                </tbody>
            </table>
            <br>

            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Name</th>
                    <th scope="col">Price/Quantity</th>
                    <th scope="col">Size</th>
                    <th scope="col">Category</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <c:forEach items="${order.goodsDTOList}" var="item">
                        <th scope="row"></th>
                        <td>${item.itemName}</td>
                        <td>${item.itemPrice}&nbsp;${item.itemQuantity}</td>
                        <td>${item.itemSize.name()}</td>
                        <td>${item.category.itemCategory}</td>
                    </c:forEach>
                </tr>
                </tbody>
            </table>

        </div>
    </c:when>
    <c:otherwise>
        <div class="container-fluid div1">
            <h4 class="text-center p-1 div1">Sorry, no order
                <a class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/store">STORE</a>
            </h4>
        </div>
    </c:otherwise>
</c:choose>

<jsp:include page="footer.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
</body>
</html>