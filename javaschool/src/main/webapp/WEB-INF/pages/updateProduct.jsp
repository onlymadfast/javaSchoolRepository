<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%@ page import="com.tsipadan.enumaration.ItemSize" %>
<c:set var="size" value="<%=ItemSize.values()%>"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Update Product</title>
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
<div class="container-fluid div1 h-100">
    <br>
    <div class="container center">
        <h4>Update product:</h4>
        <div class="card" style="width: 25rem;">
            <div class="card-body">
                <form:form modelAttribute="updateProduct" method="post" enctype="multipart/form-data">
                    <table>
                        <tr>
                            <td>ID:</td>
                            <td>${updateProduct.id}</td>
                            <td><form:hidden path="id"/></td>
                        </tr>
                        <tr>
                            <td>Name:</td>
                            <td><form:input path="itemName"/></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>Category:</td>
                            <td>
                                ${updateProduct.category.itemCategory}
                            </td>
                            <td>
                                <form:select path="category">
                                    <c:forEach items="${listOfCategory}" var="cat">
                                        <form:option value="${cat.id}">${cat.itemCategory}</form:option>
                                    </c:forEach>
                                </form:select>
                            </td>
                        </tr>
                        <tr>
                            <td>Size:</td>
                            <td>
                                <form:select path="itemSize">
                                    <c:forEach items="${size}" var="sizee">
                                        <form:option value="${sizee.name()}">${sizee.name()}</form:option>
                                    </c:forEach>
                                </form:select>
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>Price:</td>
                            <td><form:input path="itemPrice" value="${updateProduct.itemPrice}"/></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>Quantity:</td>
                            <td><form:input path="itemQuantity" value="${updateProduct.itemQuantity}"/></td>
                            <td></td>
                        </tr>
                        <tr>
                            <c:if test="${not empty updateProduct.image}">
                                <td>Image:</td>
                                <td><img src="${updateProduct.image}" alt="picture"/></td>
                                <td></td>
                            </c:if>
                        </tr>
                        <tr>
                            <td>Upload Image:</td>
                            <td><form:input type="file" path="image"/></td>
                            <td></td>
                        </tr>
                    </table>
                    <br>
                    <p class="card-text">
                        When updating the product, remember to update mind</p>
                    <form:button class="btn btn-primary" type="submit" value="Save">UPDATE</form:button>
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