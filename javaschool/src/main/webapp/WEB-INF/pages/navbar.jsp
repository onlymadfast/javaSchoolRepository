<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ page isELIgnored="false" %>
<style>
    .nav-link:hover{
        background-color: black;
    }
</style>
<nav class="navbar navbar-expand-lg text-light bg-dark bg-light">
    <div class="container-fluid">

        <span class="navbar-brand mb-0 h1">SuperStore.SS</span>

        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <span class="navbar-brand mb-0 h1">Welcome | ${pageContext.request.userPrincipal.name}</span>
        </c:if>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <security:authorize access="hasAnyRole('ROLE_CUSTOMER')">
                        <a class="nav-link text-light" aria-current="page"
                           href="${pageContext.request.contextPath}/accInfo">Account</a>
                    </security:authorize>
                </li>
                <li class="nav-item">
                    <security:authorize access="hasAnyRole('ROLE_ADMIN')">
                        <a class="nav-link text-light" aria-current="page"
                           href="${pageContext.request.contextPath}/admin">FOR ADMIN</a>
                    </security:authorize>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-light" aria-current="page"
                       href="${pageContext.request.contextPath}/home">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-light" aria-current="page"
                       href="${pageContext.request.contextPath}/store">Store</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-light" aria-current="page"
                       href="${pageContext.request.contextPath}/shoppingCart">My Cart</a>
                </li>
            </ul>
            <c:if test="${pageContext.request.userPrincipal.name == null}">
                <li class="d-flex">
                    <a class="nav-link text-light" aria-current="page"
                       href="${pageContext.request.contextPath}/login">Login</a>
                </li>
            </c:if>
            <c:if test="${pageContext.request.userPrincipal.name != null}">
            <li class="d-flex">
                <a class="nav-link text-light" aria-current="page"
                   href="${pageContext.request.contextPath}/logout">Logout</a>
            </li>
            </c:if>
        </div>
    </div>
</nav>