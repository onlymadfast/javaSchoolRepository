<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Registration</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
          crossorigin="anonymous">
</head>
<body>
<h1>Hello, lets go registration !!! </h1>
<br>
<div class="cont">
    <div class="col-lg-12 d-flex justify-content-around ">
        <div class="col-lg-8 p-4" style="background: #9ddbe3">
            <form:form action="${pageContext.request.contextPath}/reg" modelattribute="userForm" method="post" >
                <div class="mb-3">
                    <label for="exampleInputEmail1" class="form-label">User Name</label>
                    <input type="text" name="userName" class="form-control"
                                id="exampleInputEmail1"
                                />
                    <form:errors path="userName"/>
                        ${usernameError}
                </div>
                <div class="mb-3">
                    <label for="exampleInputPassword1" class="form-label">Password</label>
                    <input type="password" name="userPassword" class="form-control" id="exampleInputPassword1"/>
                </div>
                <div class="mb-3">
                    <label for="fname" class="form-label">First Name</label>
                    <input type="text" name="userFirstName" class="form-control" id="fname" />
                </div>
                <div class="mb-3">
                    <label for="lname" class="form-label">Last Name</label>
                    <input type="text" name="userLastName" class="form-control" id="lname" />
                </div>
                <div class="mb-3">
                    <label for="b" class="form-label">Birthday</label>
                    <input type="date" name="userBirthday" pattern="yyyy/MM/dd" class="form-control" id="b" />
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" name="userEmail" class="form-control" id="email" />
                </div>
                <button type="submit" class="btn btn-primary" value="Login">Submit</button>
            </form:form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
</body>
</html>