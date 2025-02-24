<%-- 
    Document   : Sidebar
    Created on : Oct 25, 2023, 2:58:55 PM
    Author     : kienb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="sidebar">
                <ul class="list-group category_block">
                    <li class="list-group-item ${mana == 0 ? 'active' : 'inactive'}">
                        <a href="/P-System/admin/liststaff">
                            <i class="fab fa-product-hunt fa-lg" style="color: #ffffff;"></i>
                            <span>Quản lý nhân viên</span> 
                        </a>
                    </li>
                    <li class="list-group-item  ${mana == 1 ? 'active' : 'inactive'}">
                        <a href="/P-System/Admin/listuser">
                            <i class="far fa-file-alt fa-lg"></i>
                            <span>Quản lý người dùng</span>
                        </a>
                    </li>
                                        <li class="list-group-item  ${logout != null ? 'active' : 'inactive'}">
                        <a href="/P-System/Logout">

                            <i class="fas fa-sign-out-alt fa-lg" style="color: #ffffff;"></i>
                            <span>Đăng xuất</span>
                        </a>
                    </li>
                    <!--For admin-->
                    <!-- <li class="list-group-item ${ac != null ? 'active' : 'inactive'}">
                        <a href="">
                            <i class="fas fa-table"></i>
                            <span>Quan Ly Tai Khoan</span>
                        </a>
                    </li> -->
                </ul>
            </div>
    </body>
</html>
