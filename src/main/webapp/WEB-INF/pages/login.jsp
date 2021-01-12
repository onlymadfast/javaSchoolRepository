<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Log in with you account</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <link rel="stylesheet" href="/resources/registrpage.css">
</head>
<body>

<nav class="navbar navbar-expand-lg text-light bg-dark bg-light">
    <div class="container-fluid">
        <span class="navbar-brand mb-0 h1">Super Store</span>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link text-light" aria-current="page" href="#">Back to Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-light" aria-current="page" href="#">Back to Store</a>
                </li>
            </ul>
            <div class="d-flex">
                <div class="navbar-brand">LOG IN</div>
            </div>
        </div>
    </div>
</nav>

<div class="cont">
    <div class="col-lg-12 d-flex justify-content-around">
        <div class="col-lg-8 p-4" style="background: #9ddbe3" >
            <form method="POST" action="/login" class="row g-2 col-md-12"> <%--spring security /login--%>

                <div class="col-md-6 ${error != null ? 'has-error' : ''}">
                    <label for="inputfname" class="form-label">First Name</label>
                    <input type="text" class="form-control" id="inputfname" placeholder="Enter your First Name">
                </div>

                <div class="col-md-6">
                    <label for="inputpass" class="form-label">Password</label>
                    <input type="password" class="form-control" id="inputpass" placeholder="Enter your password">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </div>

                <div class="col-12">
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" id="gridCheck">
                        <label class="form-check-label" for="gridCheck">Remain in the system</label>
                    </div>
                </div>

                <div class="col-md-6">
                    <button type="submit" class="btn btn-primary">Sign in</button>
                </div>

                <h4 class="col-md-6 btn btn-md btn-success">
                    <a class="btn" href="${contextPath}/registration_page">Create an account</a></h4>

            </form>
        </div>
    </div>
</div>

<script src="https://kit.fontawesome.com/5a393fd603.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
</body>
</html>
