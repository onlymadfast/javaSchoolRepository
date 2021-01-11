<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>JSP-TEMPLATE</title>
    <link rel="shortcut icon" href="/resources/images/hom.png" type="image/x-icon">
    <link rel="stylesheet" href="/resources/homeStyle.css">
</head>
<body>
<header class="header">

    <div class="header_inner">
        <nav class="nav">
            <a class="nav_link" href="#">Store</a>
            <a class="nav_link" href="#">Clothes</a>
            <a class="nav_link" href="#">Sale</a>
            <a class="nav_link" href="#">About us</a>
        </nav>


        <div class="welcome">Welcome %username%</div>

        <nav class="nav">

            <div class="nav_img">
                <img class="right_nav_img" src="/resources/images/percacc.png" alt="">
                <a class="nav_link" href="#">Personal Account</a>
            </div>

            <div class="nav_img">
                <img class="right_nav_img"
                     src="/resources/images/like.png" alt="">
                <div><a class="nav_link" href="#">What I liked</a></div>
            </div>

            <div class="nav_img">
                <img class="right_nav_img"
                     src="/resources/images/basket.png" alt="">
                <div><a class="nav_link" href="#">Basket</a></div>
            </div>
        </nav>
    </div>
</header> <!-- close header -->

<!---->

<div class="intro">
    <div class="container">

    </div>
</div>

<!---->

<section class="section">
    <div class="container">

        <div class="section_header">
            <h1 class="section_title">Story about us</h1>
            <div class="section_text">
                <p>Hi everyone! You are on my clothing website,
                    I hope you find something new and incredibly exciting
                    for yourself here. For example,
                    clothes for every day and just for the evening.</p>
            </div>
        </div>

        <div class="about">

            <div class="about_item">
                <div class="about_image">
                    <img src="/resources/images/street1.jpg" alt="">
                </div>
                <div class="about_text">Store</div>
            </div>

            <div class="about_item">
                <div class="about_image">
                    <img src="/resources/images/street2.jpg" alt="">
                </div>
                <div class="about_text">Sale</div>
            </div>

            <div class="about_item">
                <div class="about_image">
                    <img src="/resources/images/street3.jpg" alt="">
                </div>
                <div class="about_text">Personal Account</div>
            </div>

        </div> <!--close div pictures-->
    </div>
</section> <!--close section with pictures-->

<div class="section_header2">
    <h1 class="section_title"></h1>

    <div class="social">
        <a class="social_item" href="#" target="_blank">
            <i class="fab fa-instagram"></i></a>
        <a class="social_item" href="#" target="_blank">
            <i class="fab fa-vk"></i></a>
    </div>

</div>
<script src="https://kit.fontawesome.com/5a393fd603.js" crossorigin="anonymous"></script>
<!-- <script src="script.js"></script> -->
</body>
</html>