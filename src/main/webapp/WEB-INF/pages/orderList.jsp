<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>--%>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>
<%--<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>--%>
<%--<%@ page isELIgnored = "false" %>--%>
<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <meta charset="UTF-8">--%>
<%--    <title>Product List</title>--%>
<%--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/style.css">--%>
<%--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"--%>
<%--          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">--%>
<%--</head>--%>
<%--<body>--%>

<%--<div class="header-container">--%>
<%--    <div class="site-name">Online Shop</div>--%>
<%--    <div class="header-bar">--%>
<%--        <c:if test="${pageContext.request.userPrincipal.name != null}">--%>
<%--            Hello--%>
<%--            <a href="${pageContext.request.contextPath}/accountInfo">${pageContext.request.userPrincipal.name} </a>--%>
<%--            &nbsp;|&nbsp;--%>
<%--            <a href="${pageContext.request.contextPath}/logout">Logout</a>--%>
<%--        </c:if>--%>

<%--        <c:if test="${pageContext.request.userPrincipal.name == null}">--%>
<%--            <a href="${pageContext.request.contextPath}/log_in">Login</a>--%>
<%--        </c:if>--%>
<%--    </div>--%>
<%--</div>--%>

<%--<div class="menu-container">--%>
<%--    <a href="${pageContext.request.contextPath}/home">Home</a>--%>
<%--    <a href="${pageContext.request.contextPath}/productList">Product List</a>--%>
<%--    <a href="${pageContext.request.contextPath}/shoppingCart">My Cart</a>--%>

<%--    <security:authorize  access="hasAnyRole('ROLE_ADMINISTRATOR')">--%>
<%--        <a href="${pageContext.request.contextPath}/orderList">Order List</a>--%>
<%--    </security:authorize>--%>

<%--    <security:authorize  access="hasRole('ROLE_ADMINISTRATOR')">--%>
<%--        <a href="${pageContext.request.contextPath}/product">Create Product</a>--%>
<%--    </security:authorize>--%>
<%--</div>--%>

<%--<fmt:setLocale value="en_US" scope="session"/>--%>

<%--<div class="page-title">Order List</div>--%>

<%--<div>Total Order: ${paginationResult.totalRecords}</div>--%>
<%--<div>Orders on this page: ${paginationResult.maxResult}</div>--%>

<%--<table border="1" style="width:100%">--%>
<%--    <tr>--%>
<%--        <th>Order Num</th>--%>
<%--        <th>Order Date</th>--%>
<%--        <th>Customer Name</th>--%>
<%--        <th>Customer Email</th>--%>
<%--        <th>Customer Country</th>--%>
<%--        <th>Customer City</th>--%>
<%--        <th>Customer Zip</th>--%>
<%--        <th>Customer Street</th>--%>
<%--        <th>Customer House</th>--%>
<%--        <th>Customer Apartment</th>--%>
<%--        <th>Amount</th>--%>
<%--        <th>View</th>--%>
<%--    </tr>--%>
<%--    <c:forEach items="${paginationResult.list}" var="orderInfo">--%>
<%--        <tr>--%>
<%--            <td>${orderInfo.orderNum}</td>--%>
<%--            <td>--%>
<%--                <fmt:formatDate value="${orderInfo.orderDate}" pattern="yyyy-MM-dd HH:mm:ss"/>--%>
<%--            </td>--%>
<%--            <td>${orderInfo.customerFirstName}</td>--%>
<%--            <td>${orderInfo.customerEmail}</td>--%>
<%--            <td>${orderInfo.customerCountry}</td>--%>
<%--            <td>${orderInfo.customerCity}</td>--%>
<%--            <td>${orderInfo.customerZip}</td>--%>
<%--            <td>${orderInfo.customerStreet}</td>--%>
<%--            <td>${orderInfo.customerHouse}</td>--%>
<%--            <td>${orderInfo.customerApartment}</td>--%>
<%--            <td style="color:red;">--%>
<%--                <fmt:formatNumber value="${orderInfo.amount}" type="currency"/>--%>
<%--            </td>--%>
<%--            <td>--%>
<%--                <a href="${pageContext.request.contextPath}/order?orderId=${orderInfo.id}">View</a>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--    </c:forEach>--%>
<%--</table>--%>

<%--<c:if test="${paginationResult.totalPages > 1}">--%>
<%--    <div class="page-navigator">--%>
<%--        <c:forEach items="${paginationResult.navigationPages}" var = "page">--%>
<%--            <c:if test="${page != -1 }">--%>
<%--                <a href="orderList?page=${page}" class="nav-item">${page}</a>--%>
<%--            </c:if>--%>
<%--            <c:if test="${page == -1 }">--%>
<%--                <span class="nav-item"> ... </span>--%>
<%--            </c:if>--%>
<%--        </c:forEach>--%>
<%--    </div>--%>
<%--</c:if>--%>

<%--<div class="footer-container">--%>
<%--    @HelloWorld--%>
<%--    <a href="#" target="_blank"> store.org </a>--%>
<%--    <br>--%>
<%--    See more <a>demo</a>--%>
<%--</div>--%>
<%--<script src="https://kit.fontawesome.com/5a393fd603.js" crossorigin="anonymous"></script>--%>
<%--<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"--%>
<%--        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"--%>
<%--        crossorigin="anonymous"></script>--%>
<%--</body>--%>
<%--</html>--%>