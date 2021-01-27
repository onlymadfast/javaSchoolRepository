<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ page isELIgnored = "false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>History List</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/style.css">
</head>
<body>

<div class="header-container">
    <div class="site-name">Online Shop</div>
    <div class="header-bar">
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            Hello
            <a href="${pageContext.request.contextPath}/accountInfo">${pageContext.request.userPrincipal.name} </a>
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

    <security:authorize  access="hasRole('ROLE_CUSTOMER')">
        <a href="${pageContext.request.contextPath}/orderHistoryList">Order History</a>
    </security:authorize>
</div>

<div class="page-title">Order Details And History</div>

<%--<form method="GET" modelattribute="" action="${pageContext.request.contextPath}/">--%>
    <h3>Enter you Order Number *
        <form method="get" action="${pageContext.request.contextPath}/orderHistoryList">
        <input type="text" name="keyword" />
        <input type="submit" value="Search" />
        </form>
    </h3>
<%--</form>--%>

<c:forEach items="${paginationResult.list}" var="orderInfo">
    <div><a href="${pageContext.request.contextPath}/order?orderId=${orderInfo.id}">View</a></div>
</c:forEach>

<div class="customer-info-container">
    <c:forEach items="${result}" var="orderInfo">
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
    </c:forEach>
</div>


<div class="footer-container">
    @HelloWorld
    <a href="#" target="_blank"> store.org </a>
    <br>
    See more <a>demo</a>
</div>
</body>
</html>