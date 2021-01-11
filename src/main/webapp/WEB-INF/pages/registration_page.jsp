<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Registration</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <link rel="stylesheet" href="/resources/registrpage.css">

</head>
<body>

<nav class="navbar navbar-expand-lg text-light bg-dark bg-light">
    <div class="container-fluid">
        <span class="navbar-brand mb-0 h1">Super Store</span>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
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
                <div class="navbar-brand">Registration</div>
            </div>
        </div>
    </div>
</nav>

<div class="cont">
    <div class="col-lg-12 d-flex justify-content-around ">
        <div class="col-lg-8 p-4" style="background: #9ddbe3">
            <form:form method="POST" modelAttribute="registration_form" class="row g-2 col-md-12">
                <spring:bind path="firstName">
                    <div class="col-md-6 ${status.error ? 'has-error' : ''}">
                        <label for="firstname" class="form-label">First Name</label>
                        <form:input type="text" path="firstName" class="form-control" id="firstname"
                                    placeholder="Franck"/>
                    </div>
                </spring:bind>

                <spring:bind path="lastName">
                    <div class="col-md-6">
                        <label for="inputlname" class="form-label">Last Name</label>
                        <form:input type="text" path="lastName" class="form-control" id="inputlname"
                                    placeholder="Ocean"/>
                    </div>
                </spring:bind>

                <spring:bind path="birthday">
                    <div class="col-12">
                        <label for="inputbth" class="form-label">Birthdate</label>
                        <form:input type="date" path="birthday" class="form-control" id="inputbth"
                                    placeholder="25.06.1997"/>
                    </div>
                </spring:bind>

                <spring:bind path="email">
                    <div class="col-md-6">
                        <label for="inputemail" class="form-label">Email</label>
                        <form:input type="email" path="email" class="form-control" id="inputemail"
                                    placeholder="abcd@mail.com"/>
                    </div>
                </spring:bind>

                <spring:bind path="password">
                    <div class="col-md-6 ${status.error ? 'has-error' : ''}">
                        <label for="inputpass" class="form-label">Password</label>
                        <form:input type="password" path="password" class="form-control" id="inputpass"
                                    placeholder="Turn on your brain"/>
                    </div>
                </spring:bind>
                <hr>
                <spring:bind path="clientAddress.city">
                    <div class="col-md-6">
                        <label for="inputCity" class="form-label">City</label>
                        <form:input type="text" path="clientAddress.city" class="form-control" id="inputCity"
                                    placeholder="Saint-P"/>
                    </div>
                </spring:bind>

                <spring:bind path="clientAddress.country">
                    <div class="col-md-4">
                        <label for="inputState" class="form-label">Country</label>
                        <form:select id="inputState" path="clientAddress.country" class="form-select">
                            <option selected>Choose</option>
                            <option>Russia</option>
                            <option>USA</option>
                            <option>UK</option>
                            <option>Africa</option>
                        </form:select>
                    </div>
                </spring:bind>

                <spring:bind path="clientAddress.zip">
                    <div class="col-md-2">
                        <label for="inputZip" class="form-label">Zip</label>
                        <form:input type="text" path="clientAddress.zip" class="form-control" id="inputZip"
                                    placeholder="198062"/>
                    </div>
                </spring:bind>

                <spring:bind path="clientAddress.street">
                    <div class="col-md-6">
                        <label for="inputstreet" class="form-label">Street</label>
                        <form:input type="text" path="clientAddress.street" class="form-control" id="inputstreet"
                                    placeholder="Lenina,st "/>
                    </div>
                </spring:bind>

                <spring:bind path="clientAddress.house">
                    <div class="col-md-2">
                        <label for="inputhouse" class="form-label">House</label>
                        <form:input type="number" path="clientAddress.house" class="form-control" id="inputhouse"/>
                    </div>
                </spring:bind>

                <spring:bind path="clientAddress.apartment">
                    <div class="col-md-2">
                        <label for="inputkvr" class="form-label">Apartment</label>
                        <form:input type="number" path="clientAddress.apartment" class="form-control" id="inputkvr"/>
                    </div>
                </spring:bind>


                <div class="col-12">
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" id="gridCheck">
                        <label class="form-check-label" for="gridCheck">
                            Remain in the system
                        </label>
                    </div>
                </div>

                <div class="col-12">
                    <button type="submit" class="btn btn-primary">Sign in</button>
                </div>

            </form:form>
        </div>
    </div>
</div>

<script src="https://kit.fontawesome.com/5a393fd603.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
</body>
</html>