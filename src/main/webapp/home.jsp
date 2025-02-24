
<%-- 
    Document   : home.js
    Created on : Oct 16, 2023, 1:20:08 PM
    Author     : Dell
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.sql.ResultSet"%>
<%@page import="DAOs.ProductDAO"%>
<%@page import="java.nio.charset.StandardCharsets"%>
<%@page import="java.net.URLDecoder"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Trang chủ</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="style.css" />
        <style>
            #linkfooter{
                text-decoration: none;
                color: white;
            }

            .card-text > li{
                padding-top: 5px;
            }

            #payment > li{
                list-style-type: none;
            }

            #logoimg{
                border-radius: 10px;
            }
            .category a{
                text-decoration: none;
                color: black;
                font-size: large;
                font-weight: bold;
            }

            body{
                background-color: rgb(191, 191, 191);
            }

            #product div:hover{
                box-shadow: 5px 5px 5px gray;
            }

            .bannerimg{
                width: 1900px;
            }

            .page-item{
                padding: 5px;
            }

            #pagingitem a{
                color: rgb(137, 137, 137);
                border-radius: 100%;
                padding-left: 14px;
                padding-right: 14px;
            }

            #pagingitem a:hover{
                background-color: rgb(75, 75, 75);
                border: 1px solid black;
                color: white;
            }

            #pageprenext a{
                color: rgb(137, 137, 137);
                border-radius: 50px;
            }

            #pageprenext a:hover{
                background-color: rgb(75, 75, 75);
                border: 1px solid black;
                color: white;
            }

            #productDetail{
                width: 1900px;
            }

            #detailproductimg{
                width: 450px;
                height: 500px;
            }


            input[type="radio"]:checked + label.size-label{
                background-color: rgb(49, 49, 49);
                border: 1px solid rgb(0, 0, 0);
                color: white;
            }

            .quantity button{
                border: 1px solid rgb(137, 137, 137) ;
                color: rgb(106, 105, 105);
                border-radius: 5px;
                width: 40px;
                text-align: center;
                justify-content: center;

            }

            .quantity button:hover{
                background-color: rgb(147 117 80);
                border: 1px solid rgb(129, 102, 69);
                color: white;
            }

            #buttonThemvaogio{
                background-color: rgb(60, 60, 60);
                color: white;
            }

            #buttonThemvaogio:active{
                background-color: black;
            }

            #buttonMuangay{
                border: 1px solid;
            }

            #buttonMuangay:active{
                background-color: black;
                color: white;
            }

            #linkfooter{
                text-decoration: none;
                color: white;
            }

            .hethang{
                position: absolute;
            }
            .category {
                display: flex;
                align-items: center;
                justify-content: center;
                background-color: #fff; /* White background */
                color: #000; /* Black text */
                font-size: 25px;
                font-weight: bold;
                text-decoration: none;
                height: 80px; /* Adjust the height to your preference */
                border-radius: 8px; /* Add a subtle border radius */
                transition: background-color 0.3s ease, color 0.3s ease; /* Smooth transition on hover for background and text color */
            }

            .category:hover {
                background-color: #f0f0f0; /* Light gray background on hover */
            }
        </style>
    </head>

    <body>
        <%
            Cookie[] cookies = request.getCookies();
            if (session.getAttribute("acc") == null) {
                boolean flag = false;
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("user") && !cookie.getValue().equals("")) {
                            session.setAttribute("id", cookie.getValue());
                            flag = true;
                            break;
                        }
                    }
                }
                if (!flag) {
                    response.sendRedirect("/P-System/Login");
                }
            } else {
                boolean flag = false;
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("user") && !cookie.getValue().equals("")) {
                            session.setAttribute("id", cookie.getValue());
                            flag = true;
                            break;
                        }
                    }
                }
                if (!flag) {
                    response.sendRedirect("/P-System/Login");
                }
            }
        %>
        <!--Header-->
        <header>
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                <div class="container-fluid">
                    <a class="navbar-brand text-white fw-bold" style="font-size: xx-large;" href="/P-System/Home">P-System</a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                            data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                            aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <!--Search-->
                        <form class="d-flex container" role="search" aria-label="Search" action="Home?sort=${param['sort']}&search=${param['search']}">

                            <input class="form-control me-2" name="search" type="search" placeholder="Search" aria-label="Search">
                            <button class="btn btn-outline-light" type="submit">Search</button>
                        </form>
                        <ul class="navbar-nav d-sm-flex flex-sm-row justify-content-sm-center row me-5">
                            <%
                                if (session.getAttribute("id") != null) {

                            %>
                            <!--Cart-->
                            <li class="nav-item col-sm-2">
                                <a href="/P-System/Cart/<%=session.getAttribute("id")%>" class="nav-link
                                   active
                                   text-white">
                                    <svg xmlns="http://www.w3.org/2000/svg" height="1.5em" viewBox="0 0 576 512"
                                         style="fill: #ffffff;">
                                    <path
                                        d="M0 24C0 10.7 10.7 0 24 0H69.5c22 0 41.5 12.8 50.6 32h411c26.3 0 45.5 25 38.6 50.4l-41 152.3c-8.5 31.4-37 53.3-69.5 53.3H170.7l5.4 28.5c2.2 11.3 12.1 19.5 23.6 19.5H488c13.3 0 24 10.7 24 24s-10.7 24-24 24H199.7c-34.6 0-64.3-24.6-70.7-58.5L77.4 54.5c-.7-3.8-4-6.5-7.9-6.5H24C10.7 48 0 37.3 0 24zM128 464a48 48 0 1 1 96 0 48 48 0 1 1 -96 0zm336-48a48 48 0 1 1 0 96 48 48 0 1 1 0-96z" />
                                    </svg>
                                </a>
                            </li>
                            <!--History-->
                            <li class="nav-item col-sm-2">
                                <a href="/P-System/OrderHistory/<%=session.getAttribute("id")%>" class="nav-link
                                   active
                                   text-white">
                                    <svg xmlns="http://www.w3.org/2000/svg" height="1.5em" viewBox="0 0 512 512"
                                         style="fill: #ffffff;">
                                    <path
                                        d="M75 75L41 41C25.9 25.9 0 36.6 0 57.9V168c0 13.3 10.7 24 24 24H134.1c21.4 0 32.1-25.9 17-41l-30.8-30.8C155 85.5 203 64 256 64c106 0 192 86 192 192s-86 192-192 192c-40.8 0-78.6-12.7-109.7-34.4c-14.5-10.1-34.4-6.6-44.6 7.9s-6.6 34.4 7.9 44.6C151.2 495 201.7 512 256 512c141.4 0 256-114.6 256-256S397.4 0 256 0C185.3 0 121.3 28.7 75 75zm181 53c-13.3 0-24 10.7-24 24V256c0 6.4 2.5 12.5 7 17l72 72c9.4 9.4 24.6 9.4 33.9 0s9.4-24.6 0-33.9l-65-65V152c0-13.3-10.7-24-24-24z" />
                                    </svg>
                                </a>
                            </li>

                            <!--User Information-->
                            <li class="nav-item col-sm-2">
                                <a href="/P-System/User/Edit/<%=session.getAttribute("id")%>" class="nav-link
                                   active
                                   text-white">
                                    <svg xmlns="http://www.w3.org/2000/svg" height="1.5em" viewBox="0 0 448 512"
                                         style="fill: #ffffff;">
                                    <path
                                        d="M224 256A128 128 0 1 0 224 0a128 128 0 1 0 0 256zm-45.7 48C79.8 304 0 383.8 0 482.3C0 498.7 13.3 512 29.7 512H418.3c16.4 0 29.7-13.3 29.7-29.7C448 383.8 368.2 304 269.7 304H178.3z" />
                                    </svg>
                                </a>
                            </li>
                            <!--Logout-->
                            <li class="nav-item col-sm-2">
                                <a href="/P-System/Logout" class="nav-link
                                   active
                                   text-white">
                                    <svg xmlns="http://www.w3.org/2000/svg" height="1.5em" viewBox="0 0 512 512"
                                         style="fill: #ffffff;">
                                    <path
                                        d="M377.9 105.9L500.7 228.7c7.2 7.2 11.3 17.1 11.3 27.3s-4.1 20.1-11.3 27.3L377.9 406.1c-6.4 6.4-15 9.9-24 9.9c-18.7 0-33.9-15.2-33.9-33.9l0-62.1-128 0c-17.7 0-32-14.3-32-32l0-64c0-17.7 14.3-32 32-32l128 0 0-62.1c0-18.7 15.2-33.9 33.9-33.9c9 0 17.6 3.6 24 9.9zM160 96L96 96c-17.7 0-32 14.3-32 32l0 256c0 17.7 14.3 32 32 32l64 0c17.7 0 32 14.3 32 32s-14.3 32-32 32l-64 0c-53 0-96-43-96-96L0 128C0 75 43 32 96 32l64 0c17.7 0 32 14.3 32 32s-14.3 32-32 32z" />
                                    </svg>
                                </a>
                            </li>
                            <%
                            } else {
                            %>
                            <!--Login-->
                            <li class="nav-item col-sm-2">
                                <a href="/P-System/Login" class="nav-link
                                   active
                                   text-white">
                                    <svg xmlns="http://www.w3.org/2000/svg" height="1.5em" viewBox="0 0 512 512"
                                         style="fill: #ffffff;">
                                    <path d="M217.9 105.9L340.7 228.7c7.2 7.2 11.3 17.1 11.3 27.3s-4.1 20.1-11.3 27.3L217.9 406.1c-6.4 6.4-15 9.9-24 9.9c-18.7 0-33.9-15.2-33.9-33.9l0-62.1L32 320c-17.7 0-32-14.3-32-32l0-64c0-17.7 14.3-32 32-32l128 0 0-62.1c0-18.7 15.2-33.9 33.9-33.9c9 0 17.6 3.6 24 9.9zM352 416l64 0c17.7 0 32-14.3 32-32l0-256c0-17.7-14.3-32-32-32l-64 0c-17.7 0-32-14.3-32-32s14.3-32 32-32l64 0c53 0 96 43 96 96l0 256c0 53-43 96-96 96l-64 0c-17.7 0-32-14.3-32-32s14.3-32 32-32z"/>
                                    </svg>
                                </a>
                            </li>
                            <%
                                }
                            %>
                        </ul>
                    </div>
                </div>
            </nav>
        </header>
        <!--Body home page-->
        <div class="bg-body-secondary">

            <div id="carouselExampleDark" class="carousel carousel-dark slide">
                <div class="carousel-indicators">
                    <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="0" class="active"
                            aria-current="true" aria-label="Slide 1"></button>
                    <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="1"
                            aria-label="Slide 2"></button>
                    <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="2"
                            aria-label="Slide 3"></button>
                </div>
                <!--Banner-->
                <div id="banner">
                    <div class="carousel-inner">
                        <div class="carousel-item active" data-bs-interval="10000">
                            <img id="bannerimg" src="img/banner/banner1.jpg" class="d-block w-100" alt="banner1">
                            <div class="carousel-caption d-none d-md-block">
                            </div>
                        </div>
                        <div class="carousel-item" data-bs-interval="2000">
                            <img id="bannerimg" src="img/banner/banner2.jpg" class="d-block w-100" alt="banner2">
                            <div class="carousel-caption d-none d-md-block">
                            </div>
                        </div>
                        <div class="carousel-item">
                            <img id="bannerimg" src="img/banner/banner3.jpg" class="d-block w-100" alt="banner3">
                            <div class="carousel-caption d-none d-md-block">
                            </div>
                        </div>
                    </div>
                    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleDark"
                            data-bs-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Previous</span>
                    </button>
                    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleDark"
                            data-bs-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Next</span>
                    </button>
                </div>
            </div>
        </div>
        <div class="container mt-5 mb-5">
            <form action="Home?search=${param['search']}&sort=${param['sort']}&category=${param['category']}" method="get">
                <section class="bg-white p-5 p">
                    <div class="row m-2">
                        <a href="Home?search=${param['search']}&sort=${param['sort']}&category='m'" class="col-4 text-center p-3 category">hương thảo mộc</a>
                        <a href="Home?search=${param['search']}&sort=${param['sort']}&category='hoa'" class="col-4 text-center p-3 category">hoa cỏ</a>
                        <a href="Home?search=${param['search']}&sort=${param['sort']}&category='T'" class="col-4 text-center p-3 category">Trái Cây </a>
                    </div>
                    <div id="sort" class="row">
                        <div class="col-3">
                            <div class="form-floating row">
                                <div class="col-6">
                                    <div class="form-floating row">
                                        <select class="form-select form-select-sm" id="floatingSelectGrid" name="sort" onchange="this.form.submit()">
                                            <option selected value="0" ${param['sort'] == 0 ? "selected" : ""}>Lựa chọn</option>
                                            <option value="1" ${param['sort'] == 1 ? "selected" : ""}>Theo tên A-Z</option>
                                            <option value="2" ${param['sort'] == 2 ? "selected" : ""}>Giá tăng dần</option>
                                            <option value="3" ${param['sort'] == 3 ? "selected" : ""}>Giá giảm dần</option>
                                        </select>
                                        <label for="floatingSelectGrid">Sắp xếp</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <input type="hidden" name="category" value="${param['category']}">

                        <div class="row">
                            <c:forEach var="o" items="${plist}">
                                <a id="product" href="/P-System/Product/Detail/${o.productID}" class="m-5 col-sm-12 col-md-4 col-lg-2" style="text-decoration: none">
                                    <div class="card h-100" style="width: 13rem;">
                                        <img src="${o.productImg}" class="card-img " alt="product1">
                                        <div class="card-body">
                                            <h5 class="card-title">${o.productName}</h5>
                                            <p class="card-text">
                                                <span class="fw-bold rounded-2 text-white bg-danger p-1">Giá tiền:</span>
                                                <fmt:formatNumber pattern="###,###" value="${o.productPrice}"/> vnđ
                                            </p>
                                        </div>
                                    </div>
                                </a>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="flex-c-m flex-w w-full p-t-45">
                        <div style="display: flex; justify-content: center;">
                            <ul class="pagination">
                                <li class="page-item ${param['index'] == 1 ? 'disabled' : ''}">
                                    <a class="page-link" href="Home?index=${param['index'] - 1}&sort=${param['sort']}&category=${param['category']}">Previous</a>
                                </li>
                                <c:forEach var="i" begin="1" end="${numberPage}">
                                    <li class="${param['index'] == i ? 'page-item active' : 'page-item'}">
                                        <a href="Home?index=${i}&sort=${param['sort']}&category=${param['category']}" class="page-link">${i}</a>
                                    </li>
                                </c:forEach>
                                <li class="page-item ${param['index'] == numberPage ? 'disabled' : ''}">
                                    <a class="page-link" href="Home?index=${param['index'] + 1}&sort=${param['sort']}&category=${param['category']}">Next</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </section>
            </form>
        </div>
        <!--Footer-->
        <footer class="footer bg-dark">
            <div class="container">
                <div class="row">
                    <!--About P-System-->
                    <div class="col-lg-5 col-sm-4 card bg-dark border-0" style="color: white;">
                        <div class="card-body pt-5 pb-5">
                            <h5 class="card-title">Về P-System <br>
                                <span>Original and cheap</span>
                            </h5>
                            <p class="card-text"><svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 384 512"
                                                      style="fill: #ffffff;">
                                <path
                                    d="M215.7 499.2C267 435 384 279.4 384 192C384 86 298 0 192 0S0 86 0 192c0 87.4 117 243 168.3 307.2c12.3 15.3 35.1 15.3 47.4 0zM192 128a64 64 0 1 1 0 128 64 64 0 1 1 0-128z" />
                                </svg>
                                Số 203, Nguyễn Văn Cừ, TP.CT.
                            </p>
                            <p class="card-text">
                                <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 512 512"
                                     style="fill: #ffffff;">
                                <path
                                    d="M164.9 24.6c-7.7-18.6-28-28.5-47.4-23.2l-88 24C12.1 30.2 0 46 0 64C0 311.4 200.6 512 448 512c18 0 33.8-12.1 38.6-29.5l24-88c5.3-19.4-4.6-39.7-23.2-47.4l-96-40c-16.3-6.8-35.2-2.1-46.3 11.6L304.7 368C234.3 334.7 177.3 277.7 144 207.3L193.3 167c13.7-11.2 18.4-30 11.6-46.3l-40-96z" />
                                </svg>
                                0708330289
                            </p>
                            <p class="card-text">
                                <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 512 512"
                                     style="fill: #ffffff;">
                                <path
                                    d="M48 64C21.5 64 0 85.5 0 112c0 15.1 7.1 29.3 19.2 38.4L236.8 313.6c11.4 8.5 27 8.5 38.4 0L492.8 150.4c12.1-9.1 19.2-23.3 19.2-38.4c0-26.5-21.5-48-48-48H48zM0 176V384c0 35.3 28.7 64 64 64H448c35.3 0 64-28.7 64-64V176L294.4 339.2c-22.8 17.1-54 17.1-76.8 0L0 176z" />
                                </svg>
                                P-System@gmail.com
                            </p>
                        </div>
                    </div>
                    <!--Liên kết-->
                    <div class="col-lg-4 col-sm-2 bg-dark border-0" style="color: white;">
                        <div class="card-body pt-5 pb-5">
                            <h5 class="card-title">Liên kết
                            </h5>
                            <ul class="card-text">
                                <li>
                                    <a href="#" id="linkfooter">Trang chủ</a>
                                </li>
                                <li>
                                    <a href="#" id="linkfooter">Sản phẩm</a>
                                </li>
                                <li>
                                    <a href="#" id="linkfooter">Phụ kiện</a>
                                </li>
                                <li>
                                    <a href="#" id="linkfooter">Thương hiệu</a>
                                </li>
                                <li>
                                    <a href="#" id="linkfooter">Giới thiệu</a>
                                </li>
                                <li>
                                    <a href="#" id="linkfooter">P-System OUTLET</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <!--Chính sách-->
                    <div class="col-lg-3 col-sm-3 card bg-dark border-0" style="color: white;">
                        <div class="card-body pt-5 pb-5">
                            <h5 class="card-title">Chính sách
                            </h5>
                            <ul class="card-text">
                                <li>
                                    <a href="#" id="linkfooter">Sản phẩm bestseller</a>
                                </li>
                                <li>
                                    <a href="#" id="linkfooter">Sản phẩm khuyến mãi</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </footer>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
        <script src="quantity.js"></script>
        <%            String alertMess = (String) request.getAttribute("alertMess");
            if (alertMess != null && !alertMess.isEmpty()) {
        %>
        <script>
                                            alert("<%= alertMess%>");
        </script>
        <%

            }
        %>
    </body>
</html>