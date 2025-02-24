<%@page import="java.util.ArrayList" %>
<%@page import="Modals.Product" %>
<%@page import="Modals.Cart" %>
<%@page import="java.util.List" %>
<%@page import="java.util.List" %>
<%@page import="java.util.List" %>
<%@page import="DAOs.ProductDAO" %>
<%@page import="Modals.User" %>
<%@page import="java.sql.ResultSet" %>
<%@page import="DAOs.CartDAO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Checkout Form</title>

        <!-- Bootstrap CSS -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">

        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <style>
            body {
                padding-top: 56px; /* Adjusted for fixed-top navbar height */
            }

            /* Navbar */
            .navbar {
                box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1);
            }

            /* Main layout */
            main {
                padding-top: 4rem;
            }

            /* Checkout form card */
            .card {
                box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1);
            }

            /* Form input styling */
            .form-outline {
                position: relative;
                margin-bottom: 1.5rem;
            }

            .form-outline input {
                width: 100%;
                padding: 0.375rem 0.75rem;
                font-size: 1rem;
                line-height: 1.5;
                border: 1px solid #ced4da;
                border-radius: 0.25rem;
            }

            /* Cart sidebar */
            .list-group-item {
                justify-content: space-between;
            }

            /* Footer */
            footer {
                background-color: #607d8b;
                color: #fff;
                padding-top: 2rem;
            }

            /* Footer CTA buttons */
            .footer-cta {
                color: #fff;
                border-color: #fff;
            }

            /* Social media icons */
            .btn-floating {
                margin-right: 8px;
                margin-bottom: 8px;
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

        <main class="mt-5 pt-4">
            <div class="container">
                <!-- Heading -->
                <h2 class="my-5 text-center">Checkout form</h2>
                <!-- Grid row -->
                <div class="row">
                    <!-- Grid column -->
                    <div class="col-md-5 col-lg-4 order-md-last">
                        <h4 class="d-flex justify-content-between align-items-center mb-3">
                            <span class="text-primary">Your cart</span>
                        </h4>
                        <ul class="list-group mb-3">
                            <c:set var="total" value="0" />
                            <c:forEach items="${sessionScope.cartList}" var="cart">
                                <c:forEach items="${cart.items}" var="item">
                                    <li class="list-group-item d-flex justify-content-between lh-sm">
                                        <div>
                                            <h6 class="my-0">${item.product.productName} x ${cart.quantity}</h6>
                                            <small class="text-body-secondary">${item.product.productDis}</small>
                                        </div>
                                        <span class="text-body-secondary">
                                            <fmt:formatNumber type="number" pattern="#,###" value="${item.product.productPrice * cart.quantity}" />
                                            đ</span>
                                            <c:set var="total" value="${total + item.product.productPrice * cart.quantity}" />
                                    </li>
                                </c:forEach>
                            </c:forEach>
                            <li class="list-group-item d-flex justify-content-between">
                                <span>Tổng cộng</span>
                                <strong>
                                    <fmt:formatNumber type="number" pattern="#,###" value="${total}" />
                                    đ</strong>
                            </li>
                        </ul>
                    </div>
                    <!-- Grid column -->
                    <!-- Grid column -->
                    <div class="col-md-8 mb-4">
                        <!-- Card -->
                        <div class="card p-4">
                            <!-- Grid row -->
                            <form method="post" action="/P-System/checkout" >
                                <input type="hidden" name="total" value="${total}" 
                                       <div class="row mb-3">
                                <!-- Grid column -->
                                <div class="col-md-6 mb-2">
                                    <!-- First name -->
                                    <div class="form-outline">
                                        <input type="text" id="userName" name="userName" class="form-control" value="${sessionScope.userIn4.userName}" readonly/>
                                        <label class="form-label" for="userName">User name</label>
                                    </div>
                                </div>
                                <!-- Grid column -->
                                <!-- Grid row -->

                                <!-- Email -->
                                <p class="mb-0">
                                    Email
                                </p>
                                <div class="form-outline mb-4">
                                    <input type="email" class="form-control" name="userEmail" placeholder="youremail@example.com" value="${sessionScope.userIn4.userEmail}" readonly
                                           aria-label="youremail@example.com" aria-describedby="basic-addon1"/>
                                </div>
                                <!-- Phone Number -->
                                <p class="mb-0">
                                    Phone Number
                                </p>
                                <div class="form-outline mb-4">
                                    <input type="tel" class="form-control" name="userPhone" placeholder="012345789" aria-label="0123456789" value="${sessionScope.userIn4.userPhone}"
                                           aria-describedby="basic-addon1"/>
                                </div>
                                <!-- Address -->
                                <p class="mb-0">
                                    Address
                                </p>
                                <div class="form-outline mb-4">
                                    <input type="text" class="form-control" name="userAddress" placeholder="Address" value="${sessionScope.userIn4.userAddress}"
                                           aria-label="Apartment or suite" aria-describedby="basic-addon1"/>
                                </div>
                                <hr class="mb-4"/>
                                <button class="btn btn-primary" type="supmit">Continue to checkout</button>
                                <a class="btn btn-primary" href="/P-System/Cart/<%=session.getAttribute("id")%>">Quay lại</a>
                            </form>
                        </div>


                    </div>
                    <!-- Card -->
                </div>
                <!-- Grid column -->


            </div>

    </main>
    

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>

</html>