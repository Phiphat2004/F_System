/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAOs.AdminDAO;
import DAOs.StaffDAO;
import DAOs.UserDAO;
import Modals.Admin;
import Modals.Staff;
import Modals.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class LoginController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginController1</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginController1 at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        if (path.endsWith("/P-System/Login")) {
            request.getRequestDispatcher("/Register.jsp").forward(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("btnLogin") != null && request.getParameter("btnLogin").equals("login")) {
            String email = request.getParameter("email1");
            String pass = request.getParameter("password1");
            String role = request.getParameter("role");

            User user = new User(email, pass);
            Staff staff = new Staff(email, pass);
            Admin admin = new Admin(email, pass);
            UserDAO lgdao1 = new UserDAO();
            StaffDAO lgdao2 = new StaffDAO();
            AdminDAO lgdao3 = new AdminDAO();
            boolean ketqua1 = false;
            boolean ketqua2 = false;
            boolean ketqua3 = false;
            switch (role) {
                case "user":
                    User ac1 = lgdao1.GetUser(email);
                    try {
                        ketqua1 = lgdao1.Login(user);
                        ketqua2 = lgdao2.Login(staff);
                        ketqua3 = lgdao3.Login(admin);
                    } catch (SQLException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (ketqua1) {
                        HttpSession session = request.getSession();
                        session.setAttribute("acc", ac1);
                        Cookie c = new Cookie("user", String.valueOf(ac1.getUserID()));
                        Cookie cfull = new Cookie("nameUser", URLEncoder.encode(ac1.getUserName(), "UTF-8"));
                        c.setMaxAge(60 * 60 * 24);
                        cfull.setMaxAge(60 * 60 * 24);
                        response.addCookie(c);
                        response.addCookie(cfull);
                        response.sendRedirect("/P-System/Home");
                    } else {

                        if (ketqua2 || ketqua3) {
                            request.setAttribute("alertMess", "Lựa chọn sai quyền!");
                            request.getRequestDispatcher("Register.jsp").forward(request, response);
                        } else {
                            request.setAttribute("alertMess", "Đăng nhập thất bại, sai tài khoản hoặc mật khẩu!");
                            request.getRequestDispatcher("Register.jsp").forward(request, response);
                        }
                    }
                    break;
                case "staff":
                    Staff ac2 = lgdao2.GetStaff(email);
                    try {
                        ketqua2 = lgdao2.Login(staff);
                        ketqua1 = lgdao1.Login(user);
                        ketqua3 = lgdao3.Login(admin);
                    } catch (SQLException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (ketqua2) {
                        HttpSession session = request.getSession();
                        session.setAttribute("acc", ac2);
                        Cookie c = new Cookie("staff", String.valueOf(ac2.getStaffID()));
                        Cookie cfull = new Cookie("nameStaff", URLEncoder.encode(ac2.getStaffName(), "UTF-8"));
                        c.setMaxAge(60 * 60);
                        cfull.setMaxAge(60 * 60);
                        response.addCookie(c);
                        response.addCookie(cfull);
                        response.sendRedirect("/P-System/Manager/Dashboard");//manageProduct?type=view
                    } else {
                        if (ketqua1 || ketqua3) {
                            request.setAttribute("alertMess", "Lựa chọn sai quyền!");
                            request.getRequestDispatcher("Register.jsp").forward(request, response);
                        } else {
                            request.setAttribute("alertMess", "Đăng nhập thất bại, sai tài khoản hoặc mật khẩu!");
                            request.getRequestDispatcher("Register.jsp").forward(request, response);
                        }
                    }
                    break;
                case "admin":
                    Admin ac3 = lgdao3.GetAdmin(email);
                    try {
                        ketqua3 = lgdao3.Login(admin);
                        ketqua2 = lgdao2.Login(staff);
                        ketqua1 = lgdao1.Login(user);
                    } catch (SQLException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (ketqua3) {
                        HttpSession session = request.getSession();
                        session.setAttribute("acc", ac3);
                        Cookie c = new Cookie("admin", String.valueOf(ac3.getAdminID()));
                        Cookie cfull = new Cookie("nameAdmin", URLEncoder.encode(ac3.getAdminName(), "UTF-8"));
                        c.setMaxAge(60 * 60);
                        cfull.setMaxAge(60 * 60);
                        response.addCookie(c);
                        response.addCookie(cfull);
                        response.sendRedirect("/P-System/AdminManager/Dashboard");

                    } else {
                         if (ketqua1 || ketqua2) {
                            request.setAttribute("alertMess", "Lựa chọn sai quyền!");
                            request.getRequestDispatcher("Register.jsp").forward(request, response);
                        } else {
                            request.setAttribute("alertMess", "Đăng nhập thất bại, sai tài khoản hoặc mật khẩu!");
                            request.getRequestDispatcher("Register.jsp").forward(request, response);
                        }
                    }
                    break;
                default:
                    response.sendRedirect("/P-System/Login");
                    break;
            }

        }

        if (request.getParameter("btnRegis") != null) {
            String email = request.getParameter("email");
            String name = request.getParameter("username");
            String pass = request.getParameter("password");
            Date DOB = Date.valueOf(request.getParameter("birthday"));
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");

            User us = new User(email, pass, name, DOB, phone, address);
            UserDAO usdao = new UserDAO();
            User ac = usdao.GetUser(email);

            if (ac.getUserEmail() != null) {
                request.setAttribute("alertMess", "Email đã tồn tại, vui lòng sử dụng email khác");
                request.getRequestDispatcher("Register.jsp").forward(request, response);
            } else {
                int kq = 0;
                String alertMess = "";
                try {
                    kq = usdao.AddNew(us);
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (kq != 0) {
                    alertMess = "Đăng ký thành công, mời bạn đăng nhập!";
                    request.setAttribute("alertMess", alertMess);
                    request.getRequestDispatcher("Register.jsp").forward(request, response);
                } else {
                    alertMess = "Lỗi đăng ký, vui lòng thử lại!";
                    request.setAttribute("alertMess", alertMess);
                    request.getRequestDispatcher("Register.jsp").forward(request, response);
                }
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
