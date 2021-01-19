<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Enter Customer Information</title>
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

<div class="page-title">Enter Customer Information</div>

<form:form method="POST" modelAttribute="customerForm" action="${pageContext.request.contextPath}/shoppingCartCustomer">

    <table>
        <tr>
            <td>FirstName *</td>
            <td><form:input path="firstName"/></td>
            <td><form:errors path="firstName" class="error-message"/></td>
        </tr>

        <tr>
            <td>LastName *</td>
            <td><form:input path="lastName" /></td>
            <td><form:errors path="lastName" class="error-message"/></td>
        </tr>

        <tr>
            <td>Birthday *</td>
            <td><form:input path="birthday"/></td>
            <td><form:errors path="birthday" class="error-message"/></td>
        </tr>

        <tr>
            <td>Email *</td>
            <td><form:input path="email" /></td>
            <td><form:errors path="email" class="error-message" /></td>
        </tr>

        <tr>
            <td>Country *</td>
            <td><form:input path="country" /></td>
            <td><form:errors path="country" class="error-message" /></td>
        </tr>

        <tr>
            <td>City *</td>
            <td><form:input path="city" /></td>
            <td><form:errors path="city" class="error-message" /></td>
        </tr>

        <tr>
            <td>Zip *</td>
            <td><form:input path="zip" /></td>
            <td><form:errors path="zip" class="error-message" /></td>
        </tr>

        <tr>
            <td>Street *</td>
            <td><form:input path="street" /></td>
            <td><form:errors path="street" class="error-message" /></td>
        </tr>

        <tr>
            <td>House *</td>
            <td><form:input path="house" /></td>
            <td><form:errors path="apartment" class="error-message" /></td>
        </tr>

        <tr>
            <td>Apartment *</td>
            <td><form:input path="apartment" /></td>
            <td><form:errors path="apartment" class="error-message" /></td>
        </tr>

        <tr>
            <td>&nbsp;</td>
            <td>
                <input type="submit" value="Submit" />
                <input type="reset" value="Reset" />
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