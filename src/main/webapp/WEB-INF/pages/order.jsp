<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product List</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/style.css">
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
    <a href="${pageContext.request.contextPath}/">Home</a>
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
        <li>Name: ${orderInfo.customerName}</li>
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

<jsp:useBean id="paginationResult" scope="request" type="com.tsipadan.mmsapplication.model.PaginationResult"/>
<c:if test="${paginationResult.totalPages > 1}">
    <div class="page-navigator">
        <c:forEach items="${paginationResult.navigationPages}" var ="page">
            <c:if test="${page != -1 }">
                <a href="orderList?page=${page}" class="nav-item">${page}</a>
            </c:if>
            <c:if test="${page == -1 }">
                <span class="nav-item"> ... </span>
            </c:if>
        </c:forEach>
    </div>
</c:if>

<div class="footer-container">
    @HelloWorld
    <a href="#" target="_blank"> store.org </a>
    <br>
    See more <a>demo</a>
</div>

</body>
</html>