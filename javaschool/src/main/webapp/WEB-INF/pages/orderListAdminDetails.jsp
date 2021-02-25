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
    <h2>Order Information by id:</h2>
    <ul class="list-group">
        <li class="list-group-item">Customer Name:
            ${orderInfo.userDto.userFirstName}&nbsp;${orderInfo.userDto.userLastName}
        </li>
        <li class="list-group-item">Email: ${orderInfo.userDto.userEmail}</li>
        <li class="list-group-item">Address:
            ${orderInfo.userAddressDTO.userCountry},${orderInfo.userAddressDTO.userCity},
            ${orderInfo.userAddressDTO.userZip},${orderInfo.userAddressDTO.userStreet},
            ${orderInfo.userAddressDTO.userHouse},${orderInfo.userAddressDTO.userApartment}
        </li>
        <li class="list-group-item">Total: ${orderInfo.amount}</li>
    </ul>
</div>

<div class="container-fluid div1">

    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Product id</th>
            <th scope="col">Product Name</th>
            <th scope="col">Quantity/Size</th>
            <th scope="col">Category</th>
            <th scope="col">Amount</th>   <%-- TODO: amount= price * quantity--%>
        </tr>
        </thead>
        <tbody>
        <tr>
            <c:forEach items="${orderInfo.goodsDTOList}" var="info">
                <th scope="row"></th>
                <td>${info.id}</td>
                <td>${info.itemName}</td>
                <td>${info.itemQuantity}&nbsp;${info.itemSize}</td>
                <td>${info.category.itemCategory}</td>
                <td>Amount - FIXME</td>
            </c:forEach>
        </tr>
        </tbody>
    </table>

</div>

<jsp:include page="footer.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
</body>
</html>