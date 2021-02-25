<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Confirmation</title>
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
<div class="container-fluid p-3 div1">
    <div class="card p-2 col-6">
        <div class="card-body">
            <h4 class="card-title">Additional Information: </h4>
            <table class="table">
                <tr>
                    <td>Country: ${myCart.userAddressDTO.userCountry}</td>
                </tr>
                <tr>
                    <td>City: ${myCart.userAddressDTO.userCity}</td>
                </tr>
                <tr>
                    <td>ZIP: ${myCart.userAddressDTO.userZip}</td>
                </tr>
                <tr>
                    <td>Street: ${myCart.userAddressDTO.userStreet}</td>
                </tr>
                <tr>
                    <td>House: ${myCart.userAddressDTO.userHouse}</td>
                </tr>
                <tr>
                    <td>Apartment: ${myCart.userAddressDTO.userApartment}</td>
                </tr>
            </table>

            <h4 class="card-title">Cart Summary: </h4>
            <ul class="list-group list-group-flush">
                <li class="list-group-item">Quantity: ${myCart.quantityTotal}</li>
                <li class="list-group-item">Total: ${myCart.amountTotal}$</li>
            </ul>

        </div>
    </div>
</div>

<form:form method="post" action="shoppingCartConfirmation">
    <div class="container-fluid p-3 div1">
        <div class="card p-2 col-6">
            <hr>
            <h5 class="card-title">How Pay: </h5>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="howPay" value="CASH" id="cash">
                <label class="form-check-label" for="cash">Cash</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="howPay" value="CARD" id="card" checked>
                <label class="form-check-label" for="card">Card</label>
            </div>

            <h5 class="card-title">How Deliver: </h5>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="howDeliver" value="HOME" id="home" checked>
                <label class="form-check-label" for="home">Home</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" value="" id="point" disabled>
                <label class="form-check-label" for="point">Point of Delivery</label>
            </div>
            <hr>
            <a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/shoppingCart">Edit Cart</a>
            <hr>
            <a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/shoppingCartCustomer">Edit Information</a>
            <hr>
            <input type="submit" class="btn btn-primary" value="Confirm and send"/>
        </div>
    </div>
</form:form>

<div class="container-fluid p-3 div1">
    <c:forEach items="${myCart.goodsDTOList}" var="cartLineInfo">
        <div class="card rounded-3 p-2" style="height: 20rem">
            <img src="${cartLineInfo.image}" class="card-img-top rounded-pill" alt="picture" style="height: 23rem;">
            <div class="card-body">
                <h5 class="card-title text-center">Name: ${cartLineInfo.itemName}</h5>
            </div>
            <ul class="list-group list-group-flush text-center">
                <li class="list-group-item">Price: <fmt:formatNumber value="${cartLineInfo.itemPrice}" type="currency"/></li>
                <li class="list-group-item">Category: ${cartLineInfo.category.itemCategory}</li>
                <li class="list-group-item">Size: ${cartLineInfo.itemSize.name()}</li>
                <li class="list-group-item">Quantity: ${cartLineInfo.itemQuantity}</li>
                <li class="list-group-item">
                    <span class="card-subtitle">
                        <c:set value="${cartLineInfo.itemPrice * cartLineInfo.itemQuantity}" var="amount"/>
                        Subtotal: ${amount}$
                    </span>
                </li>
            </ul>
        </div>
    </c:forEach>
</div>
<jsp:include page="footer.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
</body>
</html>