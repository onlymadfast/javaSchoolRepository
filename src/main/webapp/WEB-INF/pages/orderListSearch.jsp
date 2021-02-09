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
            <a href="${pageContext.request.contextPath}/accountInfo">${pageContext.request.userPrincipal.name} </a>
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

<div class="page-title">Order List</div>

<div>Total Order: ${paginationResult.totalRecords}</div>
<br>

<%--<input type="text" id="myInput" onkeyup="myFunction()" placeholder="Search for order number..">--%>

<table id="myTable">
    <tr class="header">
        <th onclick="sortTable(0)" class="linkMy">Order Num</th>
        <th>Order Date</th>
        <th>Customer Name</th>
        <th>Customer Email</th>
        <th>Customer Country</th>
        <th>Customer City</th>
        <th>Customer Zip</th>
        <th>Customer Street</th>
        <th>Customer House</th>
        <th>Customer Apartment</th>
        <th>Amount</th>
        <th>View</th>
    </tr>
    <c:forEach items="${paginationResult.list}" var="orderInfo">
        <tr>
            <td>${orderInfo.orderNum}</td>
            <td>
                <fmt:formatDate value="${orderInfo.orderDate}" pattern="yyyy-MM-dd HH:mm"/>
            </td>
            <td>${orderInfo.customerFirstName}</td>
            <td>${orderInfo.customerEmail}</td>
            <td>${orderInfo.customerCountry}</td>
            <td>${orderInfo.customerCity}</td>
            <td>${orderInfo.customerZip}</td>
            <td>${orderInfo.customerStreet}</td>
            <td>${orderInfo.customerHouse}</td>
            <td>${orderInfo.customerApartment}</td>
            <td style="color:red;">
                <fmt:formatNumber value="${orderInfo.amount}" type="currency"/>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/order?orderId=${orderInfo.id}">View</a>
            </td>
        </tr>
    </c:forEach>

</table>

<c:if test="${paginationResult.totalPages > 1}">
    <div class="page-navigator">
        <c:forEach items="${paginationResult.navigationPages}" var = "page">
            <c:if test="${page != -1 }">
                <a href="orderList?page=${page}" class="nav-item">${page}</a>
            </c:if>
            <c:if test="${page == -1 }">
                <span class="nav-item"> ... </span>
            </c:if>
        </c:forEach>
    </div>
</c:if>

<div class="footer-container">
    @HelloWorld
    <a href="#" target="_blank"> store.org </a>
    <br>
    See more <a>demo</a>
</div>
<script>
    function sortTable(n) {
        let table, rows, switching, i, x, y, shouldSwitch, dir, switchCount = 0;
        table = document.getElementById("myTable");
        switching = true;
        // Set the sorting direction to ascending:
        dir = "asc";
        /* Make a loop that will continue until
        no switching has been done: */
        while (switching) {
            // Start by saying: no switching is done:
            switching = false;
            rows = table.rows;
            /* Loop through all table rows (except the
            first, which contains table headers): */
            for (i = 1; i < (rows.length - 1); i++) {
                // Start by saying there should be no switching:
                shouldSwitch = false;
                /* Get the two elements you want to compare,
                one from current row and one from the next: */
                x = rows[i].getElementsByTagName("TD")[n];
                y = rows[i + 1].getElementsByTagName("TD")[n];
                /* Check if the two rows should switch place,
                based on the direction, asc or desc: */
                if (dir === "asc") {
                    if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                        // If so, mark as a switch and break the loop:
                        shouldSwitch = true;
                        break;
                    }
                } else if (dir === "desc") {
                    if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
                        // If so, mark as a switch and break the loop:
                        shouldSwitch = true;
                        break;
                    }
                }
            }
            if (shouldSwitch) {
                /* If a switch has been marked, make the switch
                and mark that a switch has been done: */
                rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                switching = true;
                // Each time a switch is done, increase this count by 1:
                switchCount ++;
            } else {
                /* If no switching has been done AND the direction is "asc",
                set the direction to "desc" and run the while loop again. */
                if (switchCount === 0 && dir === "asc") {
                    dir = "desc";
                    switching = true;
                }
            }
        }
    }
</script>
<%--<script>--%>
<%--    function sortTable() {--%>
<%--        let table, rows, switching, i, x, y, shouldSwitch;--%>
<%--        table = document.getElementById("myTable");--%>
<%--        switching = true;--%>
<%--        /* Make a loop that will continue until--%>
<%--        no switching has been done: */--%>
<%--        while (switching) {--%>
<%--// Start by saying: no switching is done:--%>
<%--            switching = false;--%>
<%--            rows = table.rows;--%>
<%--            /* Loop through all table rows (except the--%>
<%--            first, which contains table headers): */--%>
<%--            for (i = 1; i < (rows.length - 1); i++) {--%>
<%--// Start by saying there should be no switching:--%>
<%--                shouldSwitch = false;--%>
<%--                /* Get the two elements you want to compare,--%>
<%--                one from current row and one from the next: */--%>
<%--                x = rows[i].getElementsByTagName("TD")[0];--%>
<%--                y = rows[i + 1].getElementsByTagName("TD")[0];--%>
<%--// Check if the two rows should switch place:--%>
<%--                if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {--%>
<%--// If so, mark as a switch and break the loop:--%>
<%--                    shouldSwitch = true;--%>
<%--                    break;--%>
<%--                }--%>
<%--            }--%>
<%--            if (shouldSwitch) {--%>
<%--                /* If a switch has been marked, make the switch--%>
<%--                and mark that a switch has been done: */--%>
<%--                rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);--%>
<%--                switching = true;--%>
<%--            }--%>
<%--        }--%>
<%--    }--%>
<%--</script>--%>

<script src="https://kit.fontawesome.com/5a393fd603.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
</body>
</html>