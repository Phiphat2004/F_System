/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAOs.CartDAO;
import DAOs.OrderDAO;
import DAOs.ProductDAO;
import DAOs.UserDAO;
import Modals.Cart;
import Modals.Order;
import Modals.OrderDetail;
import Modals.Product;
import Modals.User;
import Modals.Item;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author admin
 */
public class CheckOutController extends HttpServlet {

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
            out.println("<title>Servlet CheckOutController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckOutController at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("/pageOrder.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        User us = (User) session.getAttribute("userIn4");
        String userPhone = request.getParameter("userPhone");
        String userAddress = request.getParameter("userAddress");
        

        us.setUserPhone(userPhone);
        us.setUserAddress(userAddress);

        UserDAO dao = new UserDAO();
        dao.Update(us);
        int userID = us.getUserID();
        LocalDate ngayThangNamHienTai = LocalDate.now();
        Date purchaseDate = java.sql.Date.valueOf(ngayThangNamHienTai);
        Float total = Float.valueOf(request.getParameter("total"));
        String orderStatus = "Chờ xác nhận";

        OrderDAO odao = new OrderDAO();
        CartDAO cdao = new CartDAO();
        ProductDAO pdao = new ProductDAO();
        List<Cart> listcart = cdao.ShowinCart(userID);

        List<Product> pro = pdao.getAllProducts();

        Order order = new Order(userID, total, purchaseDate, orderStatus);
        List<OrderDetail> orderDetails = new ArrayList<>();

        for (Cart c : listcart) {
            for (Product p : pro) {
                if (c.getProductID() == p.getProductID()) {
                    float totalProduct = (float) (c.getQuantity() * p.getProductPrice());
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrderID(order.getOrderID());
                    orderDetail.setProductID(c.getProductID());
                    orderDetail.setSize(c.getSize());
                    orderDetail.setQuantity(c.getQuantity());
                    orderDetail.setProductPrice(totalProduct);
                    orderDetails.add(orderDetail);
                }
            }
        }
        boolean success = odao.createOrderAndOrderDetails(order, orderDetails);
        if (success) {
            for (Cart c : listcart) {
                    cdao.deleteWhenBuy(c.getCartID());
            }
            for (Cart c : listcart) {
                ProductDAO productDAO = new ProductDAO();
                Product product = productDAO.getProductById(c.getProductID());
                Item i = new Item();
                i.setProduct(product);
                i.setProductQuantity(c.getQuantity());
                i.setSize(c.getSize());
                i.setPrice(product.getProductPrice());

                List<Item> items = c.getItems() != null ? c.getItems() : new ArrayList<Item>();
                items.add(i);
                c.setItems(items);
            }
            session.setAttribute("cartList", listcart);
            request.setAttribute("thongBaoGioHang", "Đặt hàng thành công.");
            response.sendRedirect(request.getContextPath() + "/Home");
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
