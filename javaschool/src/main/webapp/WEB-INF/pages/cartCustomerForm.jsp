<%@ page import="com.tsipadan.enumaration.HowPay" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<c:set var="howPay" value="<%=HowPay.values()%>"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
          crossorigin="anonymous">
    <style>
        .div1 {
            background-color: #CCCCFF;
            padding-bottom: 50px;
            display: flex;
            justify-content: space-around;
        }
    </style>
</head>
<body>
<jsp:include page="navbar.jsp"/>
<div class="container-fluid p-3 div1">
    <div class="card p-2 col-6">
        <div class="card-body">
            <h4 class="card-title">Enter your information: </h4>
            <hr>
            <form:form action="shoppingCartCustomer" method="post" modelAttribute="customerForm">
                <table class="table">
                    <tr>
                        <td>Country:</td>
                        <td><input name="userCountry" value="${userAddress.userCountry}" required/></td>
                            <%--                        <td><form:input path="userCountry"/></td>--%>
                    </tr>
                    <tr>
                        <td>City:</td>
                        <td><input name="userCity" value="${userAddress.userCity}" required/></td>
                            <%--                        <td><form:input path="userCity"/></td>--%>
                    </tr>
                    <tr>
                        <td>ZIP:</td>
                        <td><input name="userZip" value="${userAddress.userZip}" required/></td>
                            <%--                        <td><form:input path="userZip"/></td>--%>
                    </tr>
                    <tr>
                        <td>Street:</td>
                        <td><input name="userStreet" value="${userAddress.userStreet}" required/></td>
                            <%--                        <td><form:input path="userStreet"/></td>--%>
                    </tr>
                    <tr>
                        <td>House:</td>
                        <td><input name="userHouse" value="${userAddress.userHouse}" required/></td>
<%--                                                    <td><form:input path="userHouse" required="requied"/></td>--%>
                    </tr>
                    <tr>
                        <td>Apartment:</td>
                        <td><input name="userApartment" value="${userAddress.userApartment}" required/></td>
                            <%--                        <td><form:input path="userApartment"/></td>--%>
                    </tr>
                </table>
                <input type="submit" class="btn btn-primary" value="Submit"/>
            </form:form>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
</body>
</html>