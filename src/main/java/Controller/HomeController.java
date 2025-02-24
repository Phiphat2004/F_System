/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAOs.ProductDAO;
import DAOs.UserDAO;
import Modals.Product;
import Modals.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dell
 */
public class HomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        if (path.endsWith("/P-System/")) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } else {
            if (path.endsWith("/P-System/Home")) {
                ProductDAO productDAO = new ProductDAO();
                String search = request.getParameter("search") == null ? "" : request.getParameter("search");
                String sort = request.getParameter("sort") == null ? "" : request.getParameter("sort");
                String category = request.getParameter("category") == null ? "" : request.getParameter("category");
                search = search.trim();

                int totalproduct = productDAO.getNumberProduct(search);
                int numberPage = (int) Math.ceil((double) totalproduct / 8); // 3 - 2 product/1 3/2=2 4/2=2 5/2=3
                int index;
                String currentPage = request.getParameter("index");
                if (currentPage == null) {
                    index = 1;
                } else {
                    index = Integer.parseInt(currentPage);
                }

                category = category.replaceAll("^'+|'+$", "").replace("O", "");

                List<Product> plist = productDAO.getProduct(search, index, sort, category);
                request.setAttribute("numberPage", numberPage);
                request.setAttribute("plist", plist);
                
                
               request.getRequestDispatcher("/home.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
