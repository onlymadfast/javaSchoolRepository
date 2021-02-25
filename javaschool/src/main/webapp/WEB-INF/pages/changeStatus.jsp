<%@ page import="com.tsipadan.enumaration.StatusOrder" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<c:set var="status" value="<%=StatusOrder.values()%>"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Change Status</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
          crossorigin="anonymous">
    <style>
        .div1 {
            background-color: #CCCCFF;
            padding-bottom: 50px;
        }
    </style>
</head>
<body>
<jsp:include page="navbar.jsp"/>

<div class="container-fluid div1 p-3">
    <c:forEach items="${allOrder}" var="order">
        <p>
            Order: ${order.orderNum}
        </p>
        <p>
            Status: ${order.statusOrder.name()} &nbsp;

            <form:form action="changeStatus" method="post" modelAttribute="allOrder">

                <form:select path="">

                    <c:forEach items="${status}" var="statuss">
                        <form:option value="${statuss.name()}">${statuss.name()}</form:option>
                    </c:forEach>

                </form:select>

            </form:form>
        </p>
    </c:forEach>
</div>

<jsp:include page="footer.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
</body>
</html>