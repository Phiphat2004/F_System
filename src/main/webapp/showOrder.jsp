<%-- 
    Document   : showOrder
    Created on : Oct 18, 2023, 3:13:07 PM
    Author     : admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <link rel="stylesheet" href="style.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://cdn.datatables.net/1.13.7/css/jquery.dataTables.min.css">
        <style>
            .main-body{
                display: flex;
            }

            .navbar{
                background-color: black;
                color: white;
                text-decoration: none;
                font-size: xx-large;
                font-weight: bold;
            }

            .listcontent{
                font-size: x-large;
                margin-left: -30px;
                padding-top: 30px;
                width: 130px;
                height:1000px;
                background-color: black;
                color: white;
            }

            .listcontent:hover{
                font-size: x-large;
                width: 350px;
                height:2400px;
                background-color: black;
                color: white;
                transition: all 0.1s linear;
            }

            .listcontent:hover span{
                display: inline-block;
            }

            .listcontent span{
                display: none;
            }

            .listcontent li{
                padding: 10px;
                width: 300px;
                list-style-type: none;
            }

            .listcontent li:active{
                background-color: white;
                padding: 10px;
                width: 318px;
                list-style-type: none;
            }

            .listcontent li a{
                padding-left: 20px;
                text-decoration: none;
                color: white;
                width: 160px;
            }

            .listcontent li a:active{
                text-decoration: none;
                color: black;
                width: 160px;
            }

            .content{
                background-color: rgb(233, 233, 233);
                margin: 20px;
                border-radius: 20px;
                width: 100%;
                display: inline-block;
                justify-items: center;
                padding-left: 20px;
                padding-right: 20px;
                padding-top: 10px;
                font-size: large;
            }



            .paymentamount{
                display: flex;
            }

            .amount{
                display: flex;
            }

            .today-data{
                background-color: rgb(207, 179, 235);
                padding: 10px;
                margin: 20px;
                border-radius: 10px;
                width: 350px;
            }
            .today-data:hover{
                background-color: rgb(159, 109, 209);
            }

            .month-data{
                background-color: rgb(143, 215, 197);
                padding: 10px;
                margin: 20px;
                border-radius: 10px;
                width: 350px;
            }

            .month-data:hover{
                background-color: rgb(55, 189, 156);
            }
            .year-data{
                background-color: rgb(234, 145, 149);
                padding: 10px;
                margin: 20px;
                border-radius: 10px;
                width: 350px;
            }

            .year-data:hover{
                background-color: rgb(215, 104, 110);
            }

            .fa-dollar{
                background-color: rgb(17, 85, 167);
                color: white;
                font-size: 1.5rem;
                padding: 1.3rem;
                height: 60px;
                width: 60px;
                margin-left: 50px;
                text-align: center;
                border-radius: 50%;
            }

            .month-revenue{
                background-color: rgb(233, 233, 233);
                margin: 20px;
                border-radius: 20px;
                width: 100%;
                display: inline-block;
                justify-items: center;
                padding-left: 20px;
                padding-right: 20px;
                padding-top: 10px;
                font-size: large;
            }

            .finance{

            }



        </style>
    </head>
    <body class="bg-body-tertiary">
        <a href="" class="navbar ps-5">
            P-System
        </a>
        <div class="main-body">
            <nav>
                <ul class="listcontent">  
                    <li>
                        <a href="#"><i class="fa fa-home me-3"></i> 
                            <span>Dashboard</span>
                        </a>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-product-hunt me-3"></i> 
                            <span>Product</span>
                        </a>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-file me-3"></i>
                            <span>Order</span>
                        </a>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-user me-3"></i>
                            <span>Customer</span>
                        </a>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-sign-out me-3"></i>
                            <span>Logout</span>
                        </a>
                    </li>
                </ul>
            </nav>
            <div class="bg-body-tertiary body">
                <div class="content">
                    <h2>Payment amount</h2>
                    <div class="paymentamount pt-4">
                        <div class="today-data">
                            <h5>Sale today</h5>
                            <div class="amount">
                                <div class="ms-5">
                                    <label>Payment amount</label>
                                    <p>5.000.000vnđ</p>
                                </div>
                                <i class="fa fa-dollar"></i>
                            </div>
                        </div>   
                        <div class="month-data">
                            <h5>Sale this month</h5>
                            <div class="amount">
                                <div class="ms-5">
                                    <label>Payment amount</label>
                                    <p>5.000.000vnđ</p>
                                </div>
                                <i class="fa fa-dollar"></i>
                            </div>
                        </div> 
                        <div class="year-data">
                            <h5>Sale this year</h5>
                            <div class="amount">
                                <div class="ms-5">
                                    <label>Payment amount</label>
                                    <p>5.000.000vnđ</p>
                                </div>
                                <i class="fa fa-dollar"></i>
                            </div>
                        </div>  
                    </div>
                </div>
                <div class="month-revenue">
                    <h2>Finace data</h2>
                    <table id="example">
                        <thead>
                            <tr>
                                <th class="align-middle">NO</th>
                                <th class="align-middle">OrderID</th>
                                <th class="align-middle">Account</th>
                                <th class="align-middle">Date</th>
                                <th class="align-middle">Total</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="stt" value="0" />
                            <c:forEach items="${listOr}" var="o">
                                <c:set var="stt" value="${stt + 1}" />
                            <tr >
                                <td class="align-middle"><p>${stt}</p></td>
                                <td class="align-middle"><p>${o.orderID}</p></td>
                                <td class="align-middle"><p>${o.userID}</p></td>
                                <td class="align-middle"><p>${o.purchaseDate}</p></td>
                                <td class="align-middle"><p><fmt:formatNumber value="${o.totalPrice}" type="number" pattern="###,### vnđ" /></p></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>    
                            
        <script src="https://code.jquery.com/jquery-3.7.0.js"></script>
        <script src="https://cdn.datatables.net/1.13.7/js/jquery.dataTables.min.js"></script>
        <script>
            $(document).ready(function () {
                $('#example').DataTable();
            });
        </script>
        <script src="https://cdn.jsdelivr.net/npm/apexcharts"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    </body>
</html>
