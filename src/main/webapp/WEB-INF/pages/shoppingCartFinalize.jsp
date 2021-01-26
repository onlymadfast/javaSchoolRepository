<%--<jsp:useBean id="lastOrderedCart" scope="request" type="com.tsipadan.mmsapplication.model.CartInfo"/>--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ page isELIgnored = "false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Shopping Cart Finalize</title>
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

<div class="page-title">Finalize</div>

<div class="container">
    <h3>Thank you for Order</h3>
    Your order number is: ${lastOrderedCart.orderNum}
</div>

<div class="footer-container">
    @HelloWorld
    <a href="#" target="_blank"> store.org </a>
    <br>
    See more <a>demo</a>
</div>

</body>
</html>