/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAOs.UserDAO;
import Modals.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author Dell
 */
public class UserController extends HttpServlet {

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
            out.println("<title>Servlet UserController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserController at " + request.getContextPath() + "</h1>");
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
        if (path.startsWith("/P-System/User/Edit")) {
            String[] s = path.split("/");
            String id = s[s.length - 1];
            UserDAO dao = new UserDAO();
            User u = dao.GetUserId(id);

            if (u == null) {
                response.sendRedirect("/P-System");
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("thongtinnguoidung", u);
                request.getRequestDispatcher("/userInformation.jsp").forward(request, response);
            }
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
        if (request.getParameter("editbtn") != null && request.getParameter("editbtn").equals("edit")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("username");
            Date DOB = Date.valueOf(request.getParameter("birthday"));
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");

            User u = new User(id, name, DOB, phone, address);
            UserDAO dao = new UserDAO();
            int result = dao.Update(u);
            User getuser = dao.GetUserId(String.valueOf(id));
            if (result != 0) {
                HttpSession session = request.getSession();
                session.setAttribute("thongtinnguoidung", getuser);
                request.setAttribute("thongBaoCapNhat", "Chỉnh sửa thành công.");
                request.getRequestDispatcher("/userInformation.jsp").forward(request, response);
            } else {
                request.setAttribute("thongBaoCapNhat", "Chỉnh sửa không thành công, vui lòng thử lại.");
                request.getRequestDispatcher("/userInformation.jsp").forward(request, response);
            }
        }

        if (request.getParameter("changepassbtn") != null) {
            UserDAO dao = new UserDAO();
            
            int id = Integer.parseInt(request.getParameter("id"));
            String oldpass = request.getParameter("oldpass");
            String newpass = request.getParameter("newpass");
            String hassnewpass = hashPassword(oldpass, "MD5");
            String alertMess = "";
            User u1 = dao.GetUserId(String.valueOf(id));
            if (!hassnewpass.equalsIgnoreCase(u1.getUserPassword())) {
                alertMess = "Thay đổi thất bại, hãy sai mật khẩu hiện tại";
                request.setAttribute("alertMess", alertMess);
                request.setAttribute("display", "block");
                request.getRequestDispatcher("/userInformation.jsp").forward(request, response);
            } else {
                User u = new User(id, newpass);
                int result = dao.UpdatePassword(u);
                if (result != 0) {
                    alertMess = "Thay đổi thành công, mời bạn đăng nhập lại.";
                    request.setAttribute("alertMess", alertMess);
                    response.sendRedirect("/P-System/Login");
                } else {
                    alertMess = "Thay đổi thất bại, mời bạn thử lại!";
                    request.setAttribute("alertMess", alertMess);
                    request.getRequestDispatcher("/userInformation.jsp").forward(request, response);
                }
            }

        }
    }

    public String hashPassword(String password, String algorithm) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            byte[] hashedBytes = digest.digest(password.getBytes());

            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : hashedBytes) {
                stringBuilder.append(String.format("%02x", b));
            }

            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
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
