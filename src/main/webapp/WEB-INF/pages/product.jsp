<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product</title>
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

    <security:authorize access="hasAnyRole('ROLE_ADMINISTRATOR')">
        <a href="${pageContext.request.contextPath}/orderList">Order List</a>
    </security:authorize>

    <security:authorize access="hasRole('ROLE_ADMINISTRATOR')">
        <a href="${pageContext.request.contextPath}/product">Create Product</a>
    </security:authorize>
</div>

<div class="page-title">Product</div>

<c:if test="${not empty message}">
    <div class="error-message">${message}</div>
</c:if>

<form:form modelAttribute="productForm" method="POST" enctype="multipart/form-data">
    <table style="text-align:left;">
        <tr>
            <td>Code *</td>
            <td style="color:red;">
                <c:choose>
                    <c:when test="${not empty productForm.code}">
                        ${productForm.code}
                    </c:when>
                    <c:when test="${empty productForm.code}">
                        <form:input path="code"/>
                    </c:when>
                </c:choose>
            </td>
            <td><form:errors path="code" class="error-message"/></td>
        </tr>

        <tr>
            <td>Name *</td>
            <td><form:input path="name"/></td>
            <td><form:errors path="name" class="error-message"/></td>
        </tr>

        <tr>
            <td>Price *</td>
            <td><form:input path="price"/></td>
            <td><form:errors path="price" class="error-message"/></td>
        </tr>

        <tr>
            <td>Category *</td>
            <td><form:input path="category"/></td>
            <td><form:errors path="category" class="error-message"/></td>
        </tr>

        <tr>
            <td>Size *</td>
            <td><form:input path="size"/></td>
            <td><form:errors path="size" class="error-message"/></td>
        </tr>

        <tr>
            <td>Image</td>
            <td>
                <img src="${pageContext.request.contextPath}/productImage?code=${productForm.code}" width="100"/></td>
            <td></td>
        </tr>
        <tr>
            <td>Upload Image</td>
            <td><form:input type="file" path="fileData"/></td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td>
                <input type="submit" value="Submit"/>
                <input type="reset" value="Reset"/>
            </td>
        </tr>
    </table>
</form:form>

<div class="footer-container">
    @HelloWorld
    <a href="#" target="_blank"> store.org </a>
    <br>
    See more <a>demo</a>
</div>

</body>
</html>