<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Create Product</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
          crossorigin="anonymous">
    <style>

        .div1 {
            background-color: #CCCCFF;
            padding-bottom: 50px;
        }

        .main-wrapper {
            height: 70vh;
        }

    </style>
</head>
<body>
<jsp:include page="navbar.jsp"/>
<div class="container-fluid div1 h-100">
    <br>
    <div class="container center">
        <h4>Create product:</h4>
        <c:if test="${not empty message}">
            <div class="error-message">${message}</div>
        </c:if>
        <div class="card" style="width: 25rem;">
            <div class="card-body">

                <form:form modelAttribute="createProduct" method="post" enctype="multipart/form-data">
                    <table>
                        <tr>
                            <td>ID:</td>
                            <td><input type="text" name="id"/></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>Name:</td>
                            <td><input type="text" name="itemName"/></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>Category:</td>
                            <td>
                                <form:select path="categories">
                                    <c:forEach items="${listOfCategory}" var="category">
                                        <form:option value="${category.name}">${category.name}</form:option>
                                    </c:forEach>
                                </form:select>
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>Size:</td>
                            <td>
                                <form:select path="sizeTable">
                                    <c:forEach items="${listOfSize}" var="size">
                                        <option value="${size.itemSize}">${size.itemSize}</option>
                                    </c:forEach>
                                </form:select>
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>Price:</td>
                            <td><input type="text" name="itemPrice"/></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>Quantity:</td>
                            <td><input type="text" name="itemQuantity"/></td>
                            <td></td>
                        </tr>
                        <tr>
                            <c:if test="${not empty createProduct.image}">
                            <td>Image:</td>
                            <td><img src="${createProduct.image}"/></td>
                            <td></td>
                            </c:if>
                        </tr>
                        <tr>
                            <td>Upload Image:</td>
                            <td><input type="file" name="image"/></td>
                            <td></td>
                        </tr>
                    </table>
                    <br>
                    <p class="card-text">
                        Create something requires a lot of effort, you don't need to calculate what
                        will be easy!</p>
                    <button class="btn btn-primary" type="submit" value="Submit">CREATE</button>
                </form:form>

            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
</body>
</html>