<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Categories</title>
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
<div class="container-fluid div1">
    <div class="card p-2">
        <h4 class="card-title">Item Categories: </h4>
        <div class="card-body">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Item Category</th>
                    <th scope="col">Edit / Delete:</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${categories}" var="cat">
                <tr>
                    <th scope="row"></th>
                    <td>${cat.itemCategory}</td>
                    <td>
                        <a class="btn btn-outline-dark"
                           href="${pageContext.request.contextPath}/edit?name=${cat.itemCategory}">Edit
                            category</a>
                            &nbsp;
                        <a class="btn btn-outline-dark"
                           href="${pageContext.request.contextPath}/deleteCategory?name=${cat.id}">Delete
                            category</a>
                    </td>
                    </c:forEach>
                </tr>
                </tbody>
            </table>
        </div>
        <a class="btn btn-dark" href="${pageContext.request.contextPath}/newItemCategory">Create category</a>
    </div>
</div>
<jsp:include page="footer.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
</body>
</html>