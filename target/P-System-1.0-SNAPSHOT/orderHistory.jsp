


<%@page import="Modals.OrderDetail"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="Modals.Order"%>
<%@page import="java.util.List"%>
<%@page import="Modals.User"%>
<%@page import="DAOs.OrderDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>

        <title>Order History</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
        <script src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
        <script>
            $(document).ready(function () {
                $('#example').DataTable();
            });
        </script>
        <link rel="stylesheet" href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css">
        <style>
            body {
                background-color: #f8f9fa;
            }

            .navbar-brand {
                font-size: 1.5rem;
                font-weight: bold;
            }

            .navbar-toggler-icon {
                background-color: white;
            }

            .navbar-nav .nav-link {
                color: white;
            }

            .order-card {
                border: 1px solid #dfe1e5;
                border-radius: 10px;
                margin-bottom: 20px;
                padding: 20px;
                background-color: white;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }

            .order-info p {
                margin: 5px 0;
            }

            .order-status {
                font-weight: bold;
            }

            .order-details {
                padding-top: 20px;
            }
            

            .order-details h4 {
                margin-top: 10px;
            }

            .order-details p {
                margin: 5px 0;
            }

            .order-details hr {
                margin: 10px 0;
                border-top: 1px solid #dfe1e5;
            }

            .order-details .product-info {
                margin-top: 20px;
            }

            .order-details .product-info img {
                max-width: 100%;
                height: auto;
            }

            .order-details .product-info h4 {
                margin-bottom: 10px;
            }

            .order-details .row {
                align-items: center;
                
            }

            .order-details .col-md-3, .order-details .col-md-2 {
                margin-bottom: 10px;
            }

            .order-details .text-success, .order-details .text-warning, .order-details .text-primary {
                font-weight: bold;
            }
            .order-details .product-info img {
                max-width: 50%;
                height: auto;
                border-radius: 5px; /* Add border-radius for rounded corners */
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Add box-shadow for a subtle effect */
            }

            .order-details .product-info h4 {
                margin-bottom: 10px;
                color: #333; /* Adjust the color of the product name */
            }

            .order-details .product-info h4 {
                margin-bottom: 10px;
                color: #333; /* Adjust the color of the product name */
                font-size: 1.2rem; /* Increase font size */
                font-weight: bold; /* Set font weight to bold */
            }

            .order-details .product-info p {
                margin: 5px 0;
                font-size: 1rem; /* Adjust font size */
                color: #555; /* Adjust the color of other text */
            }
            .order-details .row.justify-content-end {
                margin-top: 10px; /* Thêm margin để tạo khoảng cách với các sản phẩm */
            }

            .order-details .col-md-2 p.card-text {
                font-size: 1.2rem; /* Đặt kích thước font lớn hơn */
                font-weight: bold; /* Chữ in đậm */
                color: #333; /* Màu chữ đậm hơn */
                margin-bottom: 0; /* Loại bỏ khoảng cách dưới của đoạn chữ */
                margin-right: 20px;
            }
            .order-details .row.justify-content-end {
                margin-top: 10px; /* Thêm margin để tạo khoảng cách với các sản phẩm */
            }

            .order-details .col-md-2 {
                text-align: right; /* Đặt text-align là right để phần Total nằm ở bên phải */
            }
            .order {
                border: 2px solid #dfe1e5; /* Thêm khung viền cho mỗi order */
                border-radius: 10px; /* Bo tròn góc của khung viền */
                padding: 20px; /* Thêm padding cho nội dung bên trong khung viền */
                background-color: #fafafa;
            }

            .cancel-order-btn {
                background-color: #dc3545; /* Màu nền của nút Hủy đơn */
                color: #fff; /* Màu chữ trắng */
                border: none; /* Loại bỏ đường viền */
                padding: 8px 16px; /* Tăng độ lớn của nút */
                border-radius: 5px; /* Bo tròn góc của nút */
                cursor: pointer;
                transition: background-color 0.3s; /* Hiệu ứng màu nền thay đổi */
            }

            .cancel-order-btn:hover {
                background-color: #bd2130; /* Màu nền khi di chuột qua nút */
            }

            .order-details .text-success {
                font-weight: bold;
                color: green; /* Change to the desired green color */
            }

            .order-details .text-warning {
                font-weight: bold;
                color: orange; /* Change to the desired orange color */
            }

            .order-details .text-primary {
                font-weight: bold;
                color: blue; /* Change to the desired blue color */
            }
            .order-details .text-success {
                font-weight: bold;
                color: green; /* Change to the desired green color */
            }

            .order-details .text-success {
                font-weight: bold;
                color: yellow; /* Change to the desired yellow color */
            }

            #linkfooter{
                text-decoration: none;
                color: white;
            }
            
            .detailsproduct{
                border: 2px solid #dfe1e5;
                background-color: #e0e0e0;
                padding-top: 10px;
                border-radius: 10px;
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
            
            User u = (User) session.getAttribute("thongtinnguoidung");

        %>
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
                        <form class="d-flex container " role="search" aria-label="Search">
                            <input class="form-control me-2" type="hidden" placeholder="Search" aria-label="Search">

                        </form>
                        <ul class="navbar-nav d-sm-flex flex-sm-row justify-content-sm-center row">
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
                                <a href="/P-System/orderHistory/<%=session.getAttribute("id")%>" class="nav-link
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
                        </ul>
                    </div>
                </div>
            </nav>
        </header>
        <!-- Body home page -->

        <div class="container mt-5">
            <div class="bg-white p-5 rounded-3">
                <h1 class="text-center mb-4">Order History</h1>
                <%
                    ArrayList<Order> lo = (ArrayList<Order>) request.getAttribute("orderHistory");
                    OrderDAO od = new OrderDAO();
                    if (lo.size() > 0) {
                        for (Order i : lo) {
                %>
                <div class="order mb-4 fs-5">
                    <div class="order-body">
                        <h5 class="card-body">ID: <%= i.getOrderID()%></h5>
                        <p class="card-text">Order Date: <%= i.getPurchaseDate()%></p>

                        <%
                            if ("Chờ xác nhận".equalsIgnoreCase(i.getOrderStatus())) {

                        %>
                        <p>Order Status: <span class="text-success fw-bold">Chờ xác nhận</span></p>
                        <form action="OrderHistory" method="post">
                            <input type="hidden" name="orderID" value="<%= i.getOrderID()%>">
                            <input type="hidden" name="productID" value="<%= i.getProductID()%>">
                            <input type="hidden" name="quantity" value="<%= i.getQuantity()%>">
                            <input type="hidden" name="userID" value="<%=session.getAttribute("id")%>">
                            <button type="submit" name="CancelOrder" class="cancel-order-btn">Hủy đơn</button>
                        </form>
                        <%                                    } else if ("Đang được giao".equalsIgnoreCase(i.getOrderStatus())) {
                        %>
                        <p>Order Status: <span class="text-warning fw-bold">Đang được giao</span></p>
                        <%    
                        } else {
                        %>
                        <p>Order Status: <span class="text-primary fw-bold">Giao hàng thành công</span></p>
                        <%        
                            }
                        %>

                    </div>
                    <div class="order-details fw-bold">

                        <%    
                            List<OrderDetail> lod = od.showOrderDetail(i.getOrderID());
                            for (OrderDetail j : lod) {

                        %>
                        <div class="product-info">
                            <div class="row detailsproduct">
                                <!-- Các cột dữ liệu -->
                                <div class="col-md-2">
                                    <img src="/P-System/<%= j.getProductImg()%>" alt="<%= j.getProductName()%> Image">
                                </div>
                                <div class="col-md-3">
                                    <p><%= j.getProductName()%></p>
                                </div>
                                <div class="col-md-2">
                                    <p><%= j.getSize()%><p>
                                </div>
                                <div class="col-md-2">
                                    <p>Quantity: <%= j.getQuantity()%></p>
                                </div>
                                <div class="col-md-2">
                                    <p>Price of products: <%= j.getProductPrice()%></p>
                                </div>
                            </div>
                        </div>
                        <%
                            }
                        %>
                        <div class="row justify-content-end">
                            <div class="col-md-2">
                                <p class="card-text">Total: <fmt:formatNumber value="<%= i.getTotalPrice()%>" type="number" pattern="###,###vnđ" /></p>
                            </div>
                        </div>
                    </div>
                </div>
                <%
                    }
                } else {
                %>
                <p class="text-center fs-4" style="color: Green;">Chưa có lịch sử giao hàng. Trở về <a href="/P-System/Home">Trang chủ</a></p>
                <%        
                    }
                %>
            </div>
        </div>
        <!<!-- Footer -->
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
    </body>
</html>
