<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Home</title>
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
<div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel">
    <ol class="carousel-indicators">
        <li data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0"></li>
        <li data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1"></li>
        <li data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2" class="active"></li>
    </ol>
    <div class="carousel-inner">
        <div class="carousel-item">
            <img src="${pageContext.request.contextPath}/resources/images/c1.jpg" class="d-block w-100" alt="street1">
            <div class="carousel-caption d-none d-md-block">
                <figure class="fig">
                    <blockquote class="blockquote">
                        <p>
                            <mark>Dressing well is a profession, and whoever chose it does not have time for anything
                                else.
                            </mark>
                        </p>
                        <p>
                            <mark class="ca">- Heywood Brown</mark>
                        </p>
                    </blockquote>
                </figure>
            </div>
        </div>
        <div class="carousel-item">
            <img src="${pageContext.request.contextPath}/resources/images/c2.jpg" class="d-block w-100" alt="street2">
            <div class="carousel-caption d-none d-md-block">
                <figure class="fig">
                    <blockquote class="blockquote">
                        <p>
                            <mark>Everyone must be a work of art - or carry a work of art.</mark>
                        </p>
                        <p>
                            <mark class="ca">- Oscar Wilde</mark>
                        </p>
                    </blockquote>
                </figure>
            </div>
        </div>
        <div class="carousel-item active">
            <img src="${pageContext.request.contextPath}/resources/images/c3.jpg" class="d-block w-100" alt="street3">
            <div class="carousel-caption d-none d-md-block">
                <figure class="fig">
                    <blockquote class="blockquote">
                        <p>
                            <mark>Wear a red suit - and you will find yourself more than naked, become a pure object,
                                deprived of internal life.
                            </mark>
                        </p>
                        <p>
                            <mark class="ca">- Jean Baudriyard</mark>
                        </p>
                    </blockquote>
                </figure>
            </div>
        </div>
    </div>
    <a class="carousel-control-prev" href="#carouselExampleCaptions" role="button" data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
    </a>
    <a class="carousel-control-next" href="#carouselExampleCaptions" role="button" data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
    </a>
</div>

<div class="container-fluid div1">
    <div class="row d-flex justify-content-around p-2">
        <div class="card text-center" style="width: 25rem;">
            <img src="${pageContext.request.contextPath}/resources/images/street1.jpg" class="card-img-top p-2" alt="picture">
            <div class="card-body">
                <h5 class="card-title">Store</h5>
                <p class="card-text">You can choose for yourself anything, winter or summer, autumn or spring, here you
                    can find anything you need.</p>
                <a href="${pageContext.request.contextPath}/store" class="btn btn-dark">Go to store</a>
            </div>
        </div>
        <div class="card text-center" style="width: 25rem;">
            <img src="${pageContext.request.contextPath}/resources/images/street2.jpg" class="card-img-top p-2" alt="picture">
            <div class="card-body">
                <h5 class="card-title">TOP ITEMS</h5>
                <p class="card-text">
                    Here is the top products, see if something suits you.
                </p>
                <a href="${pageContext.request.contextPath}/top" class="btn btn-dark">Top items</a>
            </div>
        </div>
        <div class="card text-center" style="width: 25rem;">
            <img src="${pageContext.request.contextPath}/resources/images/street3.jpg" class="card-img-top p-2" alt="picture">
            <div class="card-body">
                <h5 class="card-title">Personal Account</h5>
                <p class="card-text">And here you can see edit your personal information, payment or delivery methods,
                    as well as view the current status of your order.</p>
                <security:authorize access="hasRole('ROLE_CUSTOMER')">
                <a href="${pageContext.request.contextPath}/accInfo" class="btn btn-dark">Check the personal account</a>
                </security:authorize>
                <c:if test="${pageContext.request.userPrincipal.name == null}">
                    <a href="${pageContext.request.contextPath}/login" class="btn btn-dark">Login</a>
                </c:if>
            </div>
        </div>
    </div>
    <br>
    <hr>
    <footer class="footer text-center">
        <p class="text-muted">SuperStore.SS</p>
    </footer>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
</body>
</html>