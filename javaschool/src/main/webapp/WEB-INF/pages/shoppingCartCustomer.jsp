<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ page isELIgnored = "false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Enter Customer Information</title>
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

<div class="page-title">Enter Customer Information</div>

<form:form method="POST" modelAttribute="customerForm" action="${pageContext.request.contextPath}/shoppingCartCustomer">
    <table>
        <tr>
            <td>FirstName *</td>
            <td><form:input path="customerFirstName"/></td>
            <td><form:errors path="customerFirstName" class="error-message"/></td>
        </tr>
        <tr>
            <td>LastName *</td>
            <td><form:input path="customerLastName" /></td>
            <td><form:errors path="customerLastName" class="error-message"/></td>
        </tr>
        <tr>
            <td>Birthday *</td>
            <td><form:input path="customerBirthday"/></td>
            <td><form:errors path="customerBirthday" class="error-message"/></td>
        </tr>
        <tr>
            <td>Email *</td>
            <td><form:input path="customerEmail" /></td>
            <td><form:errors path="customerEmail" class="error-message" /></td>
        </tr>
        <tr>
            <td>Country *</td>
            <td><form:input path="customerCountry" /></td>
            <td><form:errors path="customerCountry" class="error-message" /></td>
        </tr>
        <tr>
            <td>City *</td>
            <td><form:input path="customerCity" /></td>
            <td><form:errors path="customerCity" class="error-message" /></td>
        </tr>
        <tr>
            <td>Zip *</td>
            <td><form:input path="customerZip" /></td>
            <td><form:errors path="customerZip" class="error-message" /></td>
        </tr>
        <tr>
            <td>Street *</td>
            <td><form:input path="customerStreet" /></td>
            <td><form:errors path="customerStreet" class="error-message" /></td>
        </tr>
        <tr>
            <td>House *</td>
            <td><form:input path="customerHouse" /></td>
            <td><form:errors path="customerHouse" class="error-message" /></td>
        </tr>
        <tr>
            <td>Apartment *</td>
            <td><form:input path="customerApartment" /></td>
            <td><form:errors path="customerApartment" class="error-message" /></td>
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
<script src="https://kit.fontawesome.com/5a393fd603.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
</body>
</html>