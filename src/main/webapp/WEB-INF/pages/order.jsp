<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ page isELIgnored = "false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product List</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
</head>
<body>

<div class="header-container">
    <div class="site-name">Online Shop</div>
    <div class="header-bar">
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            Hello
            <a href="${pageContext.request.contextPath}/accountInfo">
                    ${pageContext.request.userPrincipal.name} </a>
            &nbsp;|&nbsp;
            <a href="${pageContext.request.contextPath}/logout">Logout</a>
        </c:if>

        <c:if test="${pageContext.request.userPrincipal.name == null}">
            <a href="${pageContext.request.contextPath}/log_in">Login</a>
        </c:if>
    </div>
</div>

<div class="menu-container">
    <a href="${pageContext.request.contextPath}/home">Home</a>
    <a href="${pageContext.request.contextPath}/productList">Product List</a>
    <a href="${pageContext.request.contextPath}/shoppingCart">My Cart</a>

    <security:authorize  access="hasAnyRole('ROLE_ADMINISTRATOR')">
        <a href="${pageContext.request.contextPath}/orderList">Order List</a>
    </security:authorize>

    <security:authorize  access="hasRole('ROLE_ADMINISTRATOR')">
        <a href="${pageContext.request.contextPath}/product">Create Product</a>
    </security:authorize>
</div>

<fmt:setLocale value="en_US" scope="session"/>

<div class="page-title">Order Info</div>

<div class="customer-info-container">
    <h3>Customer Information:</h3>
    <ul>
        <li>Name: ${orderInfo.customerFirstName}</li>
        <li>Email: ${orderInfo.customerEmail}</li>
        <li>Country: ${orderInfo.customerCountry}</li>
        <li>City: ${orderInfo.customerCity}</li>
        <li>Zip: ${orderInfo.customerZip}</li>
        <li>Street: ${orderInfo.customerStreet}</li>
        <li>House: ${orderInfo.customerHouse}</li>
        <li>Apartment: ${orderInfo.customerApartment}</li>
    </ul>
    <h3>Order Summary:</h3>
    <ul>
        <li>Total:
            <span class="total">
           <fmt:formatNumber value="${orderInfo.amount}" type="currency"/>
           </span>
        </li>
    </ul>
</div>

<br/>

<table border="1" style="width:100%">
    <tr>
        <th>Product Code</th>
        <th>Product Name</th>
        <th>Quantity</th>
        <th>Price</th>
        <th>Amount</th>
    </tr>
    <c:forEach items="${orderInfo.details}" var="orderDetailInfo">
        <tr>
            <td>${orderDetailInfo.productCode}</td>
            <td>${orderDetailInfo.productName}</td>
            <td>${orderDetailInfo.quantity}</td>
            <td>
                <fmt:formatNumber value="${orderDetailInfo.price}" type="currency"/>
            </td>
            <td>
                <fmt:formatNumber value="${orderDetailInfo.amount}" type="currency"/>
            </td>
        </tr>
    </c:forEach>
</table>

<div class="footer-container">
    @HelloWorld
    <a href="#" target="_blank"> store.org </a>
    <br>
    See more <a>demo</a>
</div>
<script src="https://kit.fontawesome.com/5a393fd603.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
</body>
</html>