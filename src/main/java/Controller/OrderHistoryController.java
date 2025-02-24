/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import DAOs.OrderDAO;
import DAOs.ProductDAO;
import DAOs.UserDAO;
import Modals.Order;
import Modals.OrderDetail;
import Modals.Product;
import Modals.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author admin
 */
public class OrderHistoryController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet OrderHistoryController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderHistoryController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String path = request.getRequestURI();
        if (path.startsWith("/P-System/OrderHistory/")) {
            String[] s = path.split("/");
            String id = s[s.length - 1];
            UserDAO dao = new UserDAO();
            User u = dao.GetUserId(id);
            HttpSession session = request.getSession();
            if (u != null) {
                OrderDAO ordao = new OrderDAO();
                List<Order> orderHistory = ordao.showOrder(Integer.parseInt(id));
                session.setAttribute("userIn4", u);
                request.setAttribute("orderHistory", orderHistory);
                request.getRequestDispatcher("/orderHistory.jsp").forward(request, response);
            }
        }
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        if(request.getParameter("CancelOrder")!=null){
            int orderID = Integer.parseInt(request.getParameter("orderID"));
            int productID = Integer.parseInt(request.getParameter("productID"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            int userID = Integer.parseInt(request.getParameter("userID"));
            
            OrderDAO odao = new OrderDAO();
            ProductDAO pdao = new ProductDAO();
            int kq = 0;
            
            List<OrderDetail> lo = odao.showOrderDetail(orderID);
            for(OrderDetail i: lo){
                int kq1 = odao.DeleteOrderDetail(orderID);
            }
            
            kq = odao.DeleteOrder(orderID);
            response.sendRedirect("/P-System/OrderHistory/" + userID);
//             for (Product p : pro) {
//                    if (c.getProductID() == p.getProductID()) {
//                        int quantity = p.getProductQuantity() - c.getQuantity();
//                        pdao.updateQuantity(quantity, p.getProductID());
//                    }
//                    }
            
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
