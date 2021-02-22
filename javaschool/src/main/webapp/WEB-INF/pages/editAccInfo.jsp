<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored = "false" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Edit Account</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
          crossorigin="anonymous">
    <style>
        .div1{
            background-color: #CCCCFF;
            padding-bottom: 50px;
        }

        .main-wrapper{
            height: 70vh;
        }
    </style>
</head>
<body>
<jsp:include page="navbar.jsp"/>
<div class="container-fluid div1 h-100 d-flex justify-content-start">
    <div class="main-wrapper row col-6">
        <form:form class="form-horizontal m-3 row" modelAttribute="user"
                   action="${pageContext.request.contextPath}/editAccInfo" method="post">
            <h3>Edit account information:</h3>
            <form:hidden path="id"/>
            <div class="form-group">
                <label for="firstName" class="col-sm-2 control-label">First name</label>
                <div class="col-sm-10">
                    <form:input type="text" class="form-control" id="firstName" path="userFirstName" />
                </div>
            </div>
            <div class="form-group">
                <label for="lastName" class="col-sm-2 control-label">Last name</label>
                <div class="col-sm-10">
                    <form:input type="text" class="form-control" id="lastName" path="userLastName" />
                </div>
            </div>
            <div class="form-group">
                <label for="birthday" class="col-sm-2 control-label">Birthday</label>
                <div class="col-sm-10">
                    <form:input type="date" class="form-control" id="birthday" path="userBirthday" />
                </div>
            </div>
            <div class="form-group">
                <label for="email" class="col-sm-2 control-label">Email</label>
                <div class="col-sm-10">
                    <form:input type="email" class="form-control" id="email" path="userEmail" />
                </div>
            </div>
            <div class="form-group">
                <input class="btn btn-primary" type="submit" value="Edit">
                <a class="btn btn-dark" href="${pageContext.request.contextPath}/viewAccInfo">Back to information</a>
            </div>
        </form:form>
    </div>
</div>
<jsp:include page="footer.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
</body>
</html>