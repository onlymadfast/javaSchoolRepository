<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product List</title>
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
            <a href="${pageContext.request.contextPath}/accountInfo">${pageContext.request.userPrincipal.name}</a>
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

    <security:authorize access="hasAnyRole('ROLE_ADMINISTRATOR')">
        <a href="${pageContext.request.contextPath}/orderList">Order List</a>
    </security:authorize>

    <security:authorize access="hasRole('ROLE_ADMINISTRATOR')">
        <a href="${pageContext.request.contextPath}/product">Create Product</a>
    </security:authorize>
</div>

<fmt:setLocale value="en_US" scope="session"/>

<div class="page-title">Product List</div>

<div class="accordion" id="accordionExample">
    <div class="accordion-item">
        <h2 class="accordion-header" id="headingOne">
            <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne"
                    aria-expanded="true" aria-controls="collapseOne">
                All items
            </button>
        </h2>
        <div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne"
             data-bs-parent="#accordionExample">
            <div class="accordion-body">
                <c:forEach items="${paginationProducts.list}" var="prodInfo">
                    <div class="product-preview-container">
                        <ul>
                            <li><img class="product-image"
                                     src="${pageContext.request.contextPath}/productImage?code=${prodInfo.code}"
                                     alt="picture"/></li>
                            <li>Code: ${prodInfo.code}</li>
                            <li>Name: ${prodInfo.name}</li>
                            <li>Category: ${prodInfo.category}</li>
                            <li>Size: ${prodInfo.size}</li>
                            <li>Price: <fmt:formatNumber value="${prodInfo.price}" type="currency"/></li>
                            <li>
                                <a href="${pageContext.request.contextPath}/buyProduct?code=${prodInfo.code}">Buy Now</a>
                            </li>
                            <security:authorize access="hasRole('ROLE_ADMINISTRATOR')">
                                <li>
                                    <a style="color:red;"
                                       href="${pageContext.request.contextPath}/productEdit?code=${prodInfo.code}">Edit Product
                                    </a>
                                </li>
                            </security:authorize>
                        </ul>
                    </div>
                </c:forEach>

                <c:if test="${paginationProducts.totalPages > 1}">
                    <div class="page-navigator">
                        <c:forEach items="${paginationProducts.navigationPages}" var="page">
                            <c:if test="${page != -1 }">
                                <a href="productList?page=${page}" class="nav-item">${page}</a>
                            </c:if>
                            <c:if test="${page == -1 }">
                                <span class="nav-item"> ... </span>
                            </c:if>
                        </c:forEach>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
    <div class="accordion-item">
        <h2 class="accordion-header" id="headingTwo">
            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                    data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                SuperCategory1
            </button>
        </h2>
        <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo"
             data-bs-parent="#accordionExample">
            <div class="accordion-body">
                <c:forEach items="${paginationProductFilterOne.list}" var="prodInfo">
                    <div class="product-preview-container">
                        <ul>
                            <li><img class="product-image"
                                     src="${pageContext.request.contextPath}/productImage?code=${prodInfo.code}"
                                     alt="picture"/></li>
                            <li>Code: ${prodInfo.code}</li>
                            <li>Name: ${prodInfo.name}</li>
                            <li>Category: ${prodInfo.category}</li>
                            <li>Size: ${prodInfo.size}</li>
                            <li>Price: <fmt:formatNumber value="${prodInfo.price}" type="currency"/></li>
                            <li>
                                <a href="${pageContext.request.contextPath}/buyProduct?code=${prodInfo.code}">Buy Now</a>
                            </li>

                            <security:authorize access="hasRole('ROLE_ADMINISTRATOR')">
                                <li>
                                    <a style="color:red;"
                                       href="${pageContext.request.contextPath}/product?code=${prodInfo.code}">Edit
                                        Product</a>
                                </li>
                            </security:authorize>
                        </ul>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
    <div class="accordion-item">
        <h2 class="accordion-header" id="headingThree">
            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                    data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                SuperCategory2
            </button>
        </h2>
        <div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree"
             data-bs-parent="#accordionExample">
            <div class="accordion-body">
                <c:forEach items="${paginationProductFilterTwo.list}" var="prodInfo">
                    <div class="product-preview-container">
                        <ul>
                            <li><img class="product-image"
                                     src="${pageContext.request.contextPath}/productImage?code=${prodInfo.code}"
                                     alt="picture"/></li>
                            <li>Code: ${prodInfo.code}</li>
                            <li>Name: ${prodInfo.name}</li>
                            <li>Category: ${prodInfo.category}</li>
                            <li>Size: ${prodInfo.size}</li>
                            <li>Price: <fmt:formatNumber value="${prodInfo.price}" type="currency"/></li>
                            <li>
                                <a href="${pageContext.request.contextPath}/buyProduct?code=${prodInfo.code}">Buy Now</a>
                            </li>

                            <security:authorize access="hasRole('ROLE_ADMINISTRATOR')">
                                <li>
                                    <a style="color:red;"
                                       href="${pageContext.request.contextPath}/product?code=${prodInfo.code}">Edit
                                        Product</a>
                                </li>
                            </security:authorize>
                        </ul>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>

<br/>


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