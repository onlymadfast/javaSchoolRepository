<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ page isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
          crossorigin="anonymous">

    <style>
        .main-wrapper {
            height: 80vh;
        }

        .section {
            height: 100%;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }
    </style>
</head>
<body>
<jsp:include page="navbar.jsp"/>
<div class="main-wrapper">
    <div class="section">
        <form method="post" action="${pageContext.request.contextPath}/springSecurity" class="row g-2 text-center">
            <h1 class="h1 mb-3 fw-normal">Please sign in</h1>
            <c:if test="${not empty error}">
                <h4 class="fw-normal">${error}</h4>
            </c:if>
            <div class="col-12">
                <label for="username" class="visually-hidden">Username</label>
                <input type="text" id="username" class="form-control" name="userName" placeholder="username"
                       required>
            </div>
            <div class="col-12">
                <label for="inputPassword" class="visually-hidden">Password</label>
                <input type="password" id="inputPassword" class="form-control" name="userPassword"
                       placeholder="password" data-flagcap="1" required>
            </div>
            <button class="w-100 btn btn-lg btn-primary" type="submit">Sign in</button>
            <h5>OR</h5>
            <a href="${pageContext.request.contextPath}/reg" class="w-100 btn btn-lg btn-dark" >Create new Account</a>

        </form>
    </div>
</div>
<jsp:include page="footer.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
</body>
</html>