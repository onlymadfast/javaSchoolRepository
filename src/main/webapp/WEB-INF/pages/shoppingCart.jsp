<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Shopping Cart</title>
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

<div class="page-title">My Cart</div>

<c:if test="${empty cartForm or empty cartForm.cartLines}">
    <h2>There is no items in Cart</h2>
    <a href="${pageContext.request.contextPath}/productList">Show Product List</a>
</c:if>

<c:if test="${not empty cartForm and not empty cartForm.cartLines}">
    <form:form method="POST" modelAttribute="cartForm" action="${pageContext.request.contextPath}/shoppingCart">

        <c:forEach items="${cartForm.cartLines}" var="cartLineInfo" varStatus="varStatus">
            <div class="product-preview-container">
                <ul>
                    <li>
                        <img class="product-image" alt="pic" src="${pageContext.request.contextPath}/productImage?code=${cartLineInfo.productInfo.code}" />
                    </li>
                    <li>Code:${cartLineInfo.productInfo.code}<form:hidden path="cartLines" value="[${varStatus.index}].productInfo.code"/>
                    </li>
                    <li>Name: ${cartLineInfo.productInfo.name}
                    </li>
                    <li>Price:
                        <span class="price">
                            <fmt:formatNumber value="${cartLineInfo.productInfo.price}" type="currency"/>
                        </span>
                    </li>
                    <li>Category: ${cartLineInfo.productInfo.category}</li>
                    <li>Size: ${cartLineInfo.productInfo.size}</li>
                    <li>Quantity: <form:input path="cartLines" value="[${varStatus.index}].quantity" /></li>
                    <li>Subtotal:
                        <span class="subtotal">
                            <fmt:formatNumber value="${cartLineInfo.amount}" type="currency"/>
                         </span>
                    </li>
                    <li><a href="${pageContext.request.contextPath}/shoppingCartRemoveProduct?code=${cartLineInfo.productInfo.code}">Delete </a>
                    </li>
                </ul>
            </div>
        </c:forEach>

        <div style="clear: both"></div>
        <input class="button-update-sc" type="submit" value="Update Quantity" />
        <a class="navi-item" href="${pageContext.request.contextPath}/shoppingCartCustomer">Enter Customer Info</a>
        <a class="navi-item" href="${pageContext.request.contextPath}/productList">Continue Buy</a>
    </form:form>
</c:if>

<div class="footer-container">
    @HelloWorld
    <a href="#" target="_blank"> store.org </a>
    <br>
    See more <a>demo</a>
</div>

</body>
</html>