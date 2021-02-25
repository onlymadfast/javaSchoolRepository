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
    <title>Document</title>
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
<div class="container-fluid div1">

    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">OrderNum</th>
            <th scope="col">Date</th>
            <th scope="col">Customer</th>
            <th scope="col">StatusPay/HowDeliver</th>
            <th scope="col">StatusOrder</th>
            <th scope="col">Amount</th>
            <th scope="col">View</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <c:forEach items="${orderInfo}" var="info">
                <th scope="row"></th>
                <td>${info.orderNum}</td>
                <td>${info.orderDate}</td>
                <td>${info.userDto.username}</td>
                <td>
                        ${info.statusPay.name()}&nbsp;${info.howDeliver.name()}
                </td>
                <td>${info.statusOrder.name()}</td>
                <td>${info.amount}</td>
                <td>
                    <a class="btn btn-dark"
                       href="${pageContext.request.contextPath}/viewOrderInfo?id=${info.id}">View</a>
                </td>
            </c:forEach>
        </tr>
        </tbody>
    </table>

</div>
<hr>
<div class="container-fluid div1">
    <h2 class="text-center"><a class="btn btn-outline-dark" href="${pageContext.request.contextPath}/changeStatus">Change Order Status</a></h2>
</div>
<hr>
<c:if test="${not empty totalPages}">
    <div class="container-fluid p-1 text-center div1">
        <div class="container table p-2">
            <hr>
            <h6 class="card-title">Total items: ${totalItems} --- Page ${currentPage} of ${totalPages}</h6>
            <c:forEach begin="1" end="${totalPages}" var="num">
                <c:choose>
                    <c:when test="${num !=currentPage}">
                        <a class="page-item btn btn-outline-info btn-sm" href="/orderList/${num}">${num}</a>
                    </c:when>
                    <c:otherwise>
                        <button class="page-item btn btn-outline-dark btn-sm ">${num}</button>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
    </div>
</c:if>

<jsp:include page="footer.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
</body>
</html>