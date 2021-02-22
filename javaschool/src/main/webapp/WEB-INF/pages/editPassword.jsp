<%@ page isELIgnored = "false" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Edit Password</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
          crossorigin="anonymous">
    <style>

        .div1{
            background-color: #CCCCFF;
            padding-bottom: 50px;
        }

        .main-wrapper{
            height: 30vh;
        }

    </style>
</head>
<body>
<jsp:include page="navbar.jsp"/>
<div class="container-fluid div1 h-100 d-flex justify-content-start">
    <div class="main-wrapper row col-6">

        <form class="form-horizontal m-3 row" action="${pageContext.request.contextPath}/changePassword" method="post" id="form">
            <h4>Changing Password:</h4>
            <div class="form-group">
                <label for="userPassword" class="col-sm-2 control-label">Password</label>
                <div class="col-sm-10">
                    <input type="password" placeholder="Enter your new password" class="form-control" id="userPassword" name="userPassword">
                </div>
            </div>
            <div class="form-group">
                <button type="submit" value="Submit" class="btn btn-primary">Change</button>
            </div>
        </form>

    </div>
</div>
<jsp:include page="footer.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
</body>
</html>