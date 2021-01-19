<jsp:useBean id="myCart" scope="request" type="com.tsipadan.mmsapplication.model.CustomerInfo"/>
<jsp:useBean id="myCart2" scope="request" type="com.tsipadan.mmsapplication.model.CartInfo"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Shopping Cart Confirmation</title>
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

<div class="page-title">Confirmation</div>

<div class="customer-info-container">
    <h3>Customer Information:</h3>
    <ul>
        <li>FirstName: ${myCart.firstName}</li>
        <li>LastName: ${myCart.lastName}</li>
        <li>Birthday: ${myCart.birthday}</li>
        <li>Email: ${myCart.email}</li>
        <li>Country: ${myCart.country}</li>
        <li>City: ${myCart.city}</li>
        <li>Street: ${myCart.street}</li>
        <li>House: ${myCart.house}</li>
        <li>Apartment: ${myCart.apartment}</li>
    </ul>
    <h3>Cart Summary:</h3>
    <ul>
        <li>Quantity: ${myCart2.quantityTotal}</li>
        <li>Total:
            <span class="total">
            <fmt:formatNumber value="${myCart2.amountTotal}" type="currency"/>
            </span>
        </li>
    </ul>
</div>

<form method="POST" action="${pageContext.request.contextPath}/shoppingCartConfirmation">

    <a class="navi-item" href="${pageContext.request.contextPath}/shoppingCart">Edit Cart</a>

    <a class="navi-item" href="${pageContext.request.contextPath}/shoppingCartCustomer">Edit Customer Info</a>

    <input type="submit" value="Send" class="button-send-sc"/>
</form>

<div class="container">
    <c:forEach items="${myCart2.cartLines}" var="cartLineInfo">
        <div class="product-preview-container">
            <ul>
                <li>
                    <img class="product-image" src="${pageContext.request.contextPath}/productImage?code=${cartLineInfo.productInfo.code}"/>
                </li>
                <li>
                    Code: ${cartLineInfo.productInfo.code} <input type="hidden" name="code" value="${cartLineInfo.productInfo.code}"/>
                </li>
                <li>
                    Name: ${cartLineInfo.productInfo.name}
                </li>
                <li>
                    Price: <span class="price"><fmt:formatNumber value="${cartLineInfo.productInfo.price}" type="currency"/></span>
                </li>
                <li>
                    Category: ${cartLineInfo.productInfo.category}
                </li>
                <li>
                    Size: ${cartLineInfo.productInfo.size}
                </li>
                <li>
                    Quantity: ${cartLineInfo.quantity}
                </li>
                <li>
                    Subtotal: <span class="subtotal"><fmt:formatNumber value="${cartLineInfo.amount}" type="currency"/></span>
                </li>
            </ul>
        </div>
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