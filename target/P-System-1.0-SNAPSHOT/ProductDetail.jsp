<%-- 
    Document   : ProductDetail
    Created on : Oct 29, 2023, 12:33:33 AM
    Author     : Dell
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="Modals.User"%>
<%@page import="Modals.Comment"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="Modals.Size"%>
<%@page import="java.util.List"%>
<%@page import="Modals.Product"%>
<%@page import="DAOs.ProductDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Chi tiết sản phẩm</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="/P-System/style.css" />
        <style>
            .disabled-label {
                pointer-events: none;
                background-color: rgb(193,193,193);
            }

            #comment{
                border: none;
                background-color: rgb(60, 60, 60);
                color: white;
            }
            #comment:active{
                background-color: black;
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
//                if (!flag) {
//                    response.sendRedirect("/P-System/Login");
//                }
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
//                if (!flag) {
//                    response.sendRedirect("/P-System/Login");
//                }
            }
        %>
        <!--Header-->
        <header>
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                <div class="container-fluid">
                    <a class="navbar-brand text-white fw-bold" style="font-size: xx-large;" href="/P-System">P-System</a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                            data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                            aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <!--Search-->
                        <form class="d-flex container" role="search" aria-label="Search">
                            <input class="form-control me-2" type="hidden" placeholder="Search" aria-label="Search">
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



        <!--Modal Product Detail-->


        <%
            ProductDAO pdao = new ProductDAO();
            Product p = (Product) session.getAttribute("thongtinsanpham");
        %>

        <div class="container">
            <form action="Cart" method="post" class="bg-white p-5 m-5 rounded-3">
                <div class="mb-5">
                    <h1>Chi tiết sản phẩm</h1>
                </div>
                <div class="">
                    <div class="container-fluid">
                        <div class="row">

                            <img class="col-md-2 col-sm-3 rounded-5" id="detailproductimg"
                                 src="/P-System/<%= p.getProductImg()%>" alt="detailproductimg">
                            <div class="col-md-4 col-sm-3">
                                <section>
                                    <h1 class="fs-2 pt-1 pb-2"><%= p.getProductName()%></h1>
                                    <input type="hidden" name="proID" value="<%= p.getProductID()%>">
                                    <%
                                        if (session.getAttribute("id") != null) {

                                    %>
                                    <input type="hidden" name="userID" value ="<%=session.getAttribute("id")%>">

                                    <%
                                        }
                                    %>
                                </section>
                                <section>
                                    <label class="fs-5 pb-1 fw-bold">Giá tiền:</label>
                                    <p class="fs-4" style="color: rgb(216, 97, 50);">
                                        <%=  String.format("%,.0f", p.getProductPrice())%> vnđ
                                    </p>
                                </section>
                                <section class="pb-2">

                                    <label class="fs-5 pb-2 fw-bold">Size: </label>
                                    <br>
                                    <%
                                        List<Size> ls = pdao.getSize(p.getProductID());

    boolean ml75 = false;
    boolean ml90 = false;
    boolean ml100 = false;
    boolean ml120 = false;

    for (Size i : ls) {
        if (i.getSize().equalsIgnoreCase("75ml")) {
            ml75 = true;
        } else if (i.getSize().equalsIgnoreCase("90ml")) {
            ml90 = true;
        } else if (i.getSize().equalsIgnoreCase("100ml")) {
            ml100 = true;
        } else if (i.getSize().equalsIgnoreCase("120ml")) {
            ml120 = true;
        }
    }

    int ml75Qty = 0;
    int ml90Qty = 0;
    int ml100Qty = 0;
    int ml120Qty = 0;

    for (Size i : ls) {
        if (i.getSize().equalsIgnoreCase("75ml")) {
            ml75Qty = i.getQuantity();
        } else if (i.getSize().equalsIgnoreCase("90ml")) {
            ml90Qty = i.getQuantity();
        } else if (i.getSize().equalsIgnoreCase("100ml")) {
            ml100Qty = i.getQuantity();
        } else if (i.getSize().equalsIgnoreCase("120ml")) {
            ml120Qty = i.getQuantity();
        }
    }

                                        if (ml75) {
                                            if (ml75Qty != 0) {
                                    %>
                                    <input type="radio" class="btn-check" name="options-base" id="option1"
                                           autocomplete="off" checked value="75ml">
                                    <label class="size-label btn m-1 ps-4 pe-4 fs-4" for="option1">75ml</label>
                                    <%
                                    } else {
                                    %>
                                    <input type="radio" class="btn-check" name="options-base" id="option1"
                                           autocomplete="off" checked value="75ml">
                                    <label class="size-label btn m-1 ps-4 pe-4 fs-4 disabled-label" for="option1">75ml - Hết hàng</label>
                                    <%
                                            }
                                        }
                                        if (ml90) {
                                            if (ml90Qty != 0) {
                                    %>
                                    <input type="radio" class="btn-check" name="options-base" id="option2"
                                           autocomplete="off" value="90ml">
                                    <label class="size-label btn m-1 ps-4 pe-4 fs-4" for="option2">90ml</label>
                                    <%
                                    } else {
                                    %>
                                    <input type="radio" class="btn-check" name="options-base" id="option2"
                                           autocomplete="off" value="90ml">
                                    <label class="size-label btn m-1 ps-4 pe-4 fs-4 disabled-label" for="option2">90ml - Hết hàng</label>
                                    <%
                                            }
                                        }
                                        if (ml100) {
                                            if (ml100Qty != 0) {
                                    %>
                                    <input type="radio" class="btn-check" name="options-base" id="option3"
                                           autocomplete="off" value="100ml">
                                    <label class="size-label btn m-1 ps-4 pe-4 fs-4" for="option3">100ml</label>
                                    <%
                                    } else {
                                    %>
                                    <input type="radio" class="btn-check" name="options-base" id="option3"
                                           autocomplete="off" value="100ml">
                                    <label class="size-label btn m-1 ps-4 pe-4 fs-4 disabled-label" for="option3">100ml - Hết hàng</label>
                                    <%
                                            }
                                        }
                                        if (ml120) {
                                            if (ml120Qty != 0) {
                                    %>
                                    <input type="radio" class="btn-check" name="options-base" id="option4"
                                           autocomplete="off" value="120ml">
                                    <label class="size-label btn m-1 ps-4 pe-4 fs-4" for="option4">120ml</label>
                                    <%
                                    } else {
                                    %>
                                    <input type="radio" class="btn-check" name="options-base" id="option4"
                                           autocomplete="off" value="120ml">
                                    <label class="size-label btn m-1 ps-4 pe-4 fs-4 disabled-label" for="option4">120ml - Hết hàng</label>
                                    <%
                                            }
                                        }
                                    %>
                                </section>
                                <section>
                                    <div class="p-2 quantity">
                                        <label class="fs-5 pb-2 me-2 fw-bold">Số lượng: </label>
                                        <input type="number" name="quantity" id="quantity" value="1" min="1" max=""
                                               class="no-spinners fs-4 text-center w-25 rounded-3 ">
                                    </div>
                                </section>
                                <section>
                                    <div class="p-1">
                                        <label class="fs-5 pb-2 fw-bold">Mô tả:</label>
                                        <br>
                                        <p>
                                            <textarea class="form-control" style="width: 150%;" cols="30" readonly
                                                      rows="4"><%= p.getProductDis()%></textarea>
                                        </p>
                                    </div>
                                    <div class="">
                                        <%

                                            if (session.getAttribute("id") != null) {

                                        %>
                                        <button type="submit" name="addNew" class="btn p-2" id="buttonThemvaogio">+ Thêm vào giỏ hàng</button>
                                        <%                                        } else {
                                        %>
                                        <a href="/P-System/Login" class="fs-4" style="text-decoration: none; color: black;">
                                            Đăng nhập để mua hàng
                                        </a>
                                        <%
                                            }
                                        %>
                                    </div>
                                </section>
                            </div>

                            <div class="col-md-12 col-sm-12 container-fluid">
                                <%
                                    if (session.getAttribute("id") != null) {
                                %>
                                <form class="comment-form" action="Product" method="post">
                                    <input type="hidden" name="userID" value="<%= session.getAttribute("id") %>">
                                    <input type="hidden" name="productID" value="${sessionScope.thongtinsanpham.productID}">
                                    <div class="form-group" style="margin-top: 20px;">
                                        <textarea class="form-control" name="content" rows="5" placeholder="Viết bình luận của bạn vào đây..."></textarea>
                                    </div>
                                    <button type="submit" class="btn btn-primary  mt-3" id="comment" name="comment" style="margin-bottom: 10px;">Gửi</button>
                                </form>
                                <%                                    }
                                %>

                                <!-- Hiển thị 5 comment va xem them -->  <%
                                    List<Comment> lc = (List<Comment>) session.getAttribute("ListCmt");

                                    String display = "block";
                                    for (Comment i : lc) {
                                        User u = i.getUser();

                                %>
                                <div id="commentSection">

                                    <div class="comment" style="margin-bottom: 20px; margin-top: 10px; padding: 10px; border: 1px solid #eee; border-radius: 5px;">
                                        <div style="font-weight: bold; margin-bottom: 5px;">
                                            <span><%= u.getUserName()%> (<fmt:formatDate value="<%= i.getCreatedDate()%>" />)</span>
                                        </div>
                                        <div style="margin-bottom: 10px;">
                                            <p><%= i.getContent()%></p>
                                        </div>
                                    </div>

                                </div>
                                <%
                                    }
                                    if (lc.size() > 5) {
                                %>
                                <div class="text-center">
                                    <a id="loadMoreBtn" class="btn border-0 text-body-secondary fw-bold text-center">Xem thêm...</a>
                                    <a id="showLessBtn" style="display: none;" class="btn border-0 text-body-secondary fw-bold text-center">Thu gọn</a>
                                </div>
                                <%
                                    }
                                %>
                                <script>
                                    let visibleComments = document.querySelectorAll('.comment');

                                    let commentsToShow = 5;
                                    let loadMoreBtn = document.getElementById('loadMoreBtn');
                                    let showLessBtn = document.getElementById('showLessBtn');

                                    function toggleComments() {
                                        for (let i = 0; i < visibleComments.length; i++) {
                                            if (i < commentsToShow) {
                                                visibleComments[i].style.display = 'block';
                                            } else {
                                                visibleComments[i].style.display = 'none';
                                            }
                                        }
                                    }

                                    loadMoreBtn.addEventListener('click', function (event) {
                                        event.preventDefault();
                                        commentsToShow += 5;
                                        toggleComments();
                                        if (commentsToShow >= visibleComments.length) {
                                            loadMoreBtn.style.display = 'none';
                                            showLessBtn.style.display = 'block';
                                        }
                                    });

                                    showLessBtn.addEventListener('click', function (event) {
                                        event.preventDefault();
                                        commentsToShow = 5;
                                        toggleComments();
                                        loadMoreBtn.style.display = 'block';
                                        showLessBtn.style.display = 'none';
                                    });

                                    // Hiển thị chỉ 5 comment ban đầu
                                    toggleComments();
                                </script>
                            </div>
                        </div>
                    </div>
                </div>
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
                                Số 203,Nguyễn Văn Cừ , TP.CT.
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
        <%            String alertMess = (String) request.getAttribute("alrtMessdetailpro");
            if (alertMess
                    != null && !alertMess.isEmpty()) {
        %>
        <script>
                                    alert("<%= alertMess%>");
        </script>
        <%
            }
        %>
        <!-- Thêm mã script sau thẻ body -->
        <!-- Add this script block at the end of your HTML body -->
        <script>
            document.addEventListener('DOMContentLoaded', function () {
                // Get the quantity input element
                var quantityInput = document.getElementById('quantity');
                // Get the radio buttons
                var radioButtons = document.getElementsByName('options-base');
                // Add event listener to each radio button
                radioButtons.forEach(function (radio) {
                    radio.addEventListener('change', function () {
                        // Set the max attribute of the quantity input based on the selected radio
                        if (radio.value === '75ml') {
                            quantityInput.max = <%= ml75Qty%>;
                        } else if (radio.value === '90ml') {
                            quantityInput.max = <%= ml90Qty%>;
                        } else if (radio.value === '100ml') {
                            quantityInput.max = <%= ml75Qty%>;
                        } else if (radio.value === '120ml') {
                            quantityInput.max = <%= ml120Qty%>;
                        }
                    });
                });
            });
        </script>
    </body>
</html>
