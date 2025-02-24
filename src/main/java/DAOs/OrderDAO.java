/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Modals.Order;
import Modals.OrderDetail;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class OrderDAO {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public OrderDAO() {
        conn = DBContext.DBContext.getConnection();
    }

    public Order getOrderID(int userID) {
        String sql = "SELECT * FROM [Order] WHERE userID = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userID);
            rs = ps.executeQuery();
            if (rs.next()) {
                Order order = new Order();
                order = new Order(rs.getInt(1), rs.getInt(2), rs.getFloat(3), rs.getDate(5));
                return order;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    //addOrder
    public boolean addOrder(Order o) {
        //Add Order

        try {
            String sql = "insert into [Order] values (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, o.getUserID());
            ps.setDouble(2, o.getTotalPrice());
            ps.setDate(3, o.getPurchaseDate());
            ps.executeUpdate();
        } catch (Exception e) {
        }

        return false;
    }

    public boolean addOrderDetail(Order o) {
        //Add Order
        try {
            String sql1 = "insert into [OrderDetail] values (?,?)";
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps.setInt(1, o.getProductID());
            ps.setFloat(2, o.getProductPrice());
            ps1.executeUpdate();
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public boolean createOrderAndOrderDetails(Order order, List<OrderDetail> orderDetails) {
        try {
            String orderInsertQuery = "INSERT INTO [Order] (userID, totalPrice, purchaseDate, orderStatus) VALUES (?, ?, ?, ?)";
            PreparedStatement orderStatement = conn.prepareStatement(orderInsertQuery, Statement.RETURN_GENERATED_KEYS);
            orderStatement.setInt(1, order.getUserID());
            orderStatement.setDouble(2, order.getTotalPrice());
            orderStatement.setDate(3, order.getPurchaseDate());
            orderStatement.setString(4, order.getOrderStatus());

            int rowsAffected = orderStatement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = orderStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int generatedOrderID = generatedKeys.getInt(1); // Get the auto-generated orderID

                    String orderDetailInsertQuery = "INSERT INTO OrderDetail (orderID, productID, size, quantity, totalProduct) VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement orderDetailStatement = conn.prepareStatement(orderDetailInsertQuery);

                    // Insert into OrderDetail for each OrderDetail object
                    for (OrderDetail orderDetail : orderDetails) {
                        orderDetailStatement.setInt(1, generatedOrderID); // Use the generated orderID
                        orderDetailStatement.setInt(2, orderDetail.getProductID());
                        orderDetailStatement.setString(3, orderDetail.getSize());
                        orderDetailStatement.setInt(4, orderDetail.getQuantity());
                        orderDetailStatement.setFloat(5, orderDetail.getProductPrice());
                        orderDetailStatement.addBatch(); // Add to batch for execution
                    }
                    // Execute the batch
                    orderDetailStatement.executeBatch();
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception properly
        }
        return false;
    }

    //doanh thu theo thang
    public List<Order> monthlyRevenue() {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT MONTH(purchaseDate) AS month, SUM(O.totalPrice) AS revenue\n"
                + "FROM [Order] AS O\n"
                + "GROUP BY MONTH(O.purchaseDate)\n"
                + "ORDER BY MONTH(O.purchaseDate) DESC;";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Order(rs.getInt(1), rs.getDouble(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    // Doanh thu cho ngày hiện tại
    public double dailyRevenue() {
        double dailyRevenue = 0;
        String sql = "SELECT SUM(O.totalPrice) AS revenue\n"
                + "FROM [Order] AS O\n"
                + "WHERE CONVERT(DATE, O.purchaseDate) = CONVERT(DATE, GETDATE())\n"
                + "AND O.orderStatus <> N'Chờ xác nhận';";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                dailyRevenue += rs.getDouble("revenue");
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dailyRevenue;
    }

    // Doanh thu cho tháng hiện tại
    public double monthRevenue() {
        double monthlyRevenue = 0;
        String sql = "SELECT SUM(O.totalPrice) AS revenue\n"
                + "FROM [Order] AS O\n"
                + "WHERE MONTH(O.purchaseDate) = MONTH(GETDATE()) AND YEAR(O.purchaseDate) = YEAR(GETDATE())\n"
                + "AND O.orderStatus <> N'Chờ xác nhận';";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                monthlyRevenue += rs.getDouble("revenue");
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return monthlyRevenue;
    }

    // Doanh thu cho năm hiện tại
    public double yearlyRevenue() {
        double yearlyRevenue = 0;
        String sql = "SELECT SUM(O.totalPrice) AS revenue\n"
                + "FROM [Order] AS O\n"
                + "WHERE YEAR(O.purchaseDate) = YEAR(GETDATE())\n"
                + "AND O.orderStatus <> N'Chờ xác nhận';";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                yearlyRevenue += rs.getDouble("revenue");
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return yearlyRevenue;
    }

    public Order getOrderByID(int orderID) {
        String sql = "SELECT \n"
                + "    O.orderID,\n"
                + "    O.totalPrice,\n"
                + "    O.purchaseDate,\n"
                + "    STRING_AGG(P.productName + ' (' + OD.size + ')', ', ') AS AllProducts,\n"
                + "    STRING_AGG(P.productImg, ', ') AS AllProductImgs\n"
                + "FROM [Order] O \n"
                + "INNER JOIN OrderDetail OD ON O.orderID = OD.orderID \n"
                + "INNER JOIN Product P ON OD.productID = P.productID \n"
                + "WHERE O.orderID = ?\n"
                + "GROUP BY O.orderID, O.totalPrice, O.purchaseDate\n"
                + "ORDER BY O.orderID DESC;";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, orderID);
            rs = ps.executeQuery();

            if (rs.next()) {
                return new Order(
                        rs.getInt("orderID"),
                        rs.getFloat("totalPrice"),
                        rs.getDate("purchaseDate"),
                        rs.getString("AllProducts"),
                        rs.getString("AllProductImgs")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public List<Order> getAllOrder() {
        String sql = "SELECT * FROM [Order]";
        List<Order> o = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            int orderID = rs.getInt("orderID");
            int userID = rs.getInt("userID");
            float price = rs.getFloat("totalPrice");
            Date date = rs.getDate("purcaseDate");
            String status = rs.getString("orderStatus");
            Order order = new Order(orderID, userID, price, date, status);
            o.add(order);
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return o;
    }

    //getall
    public List<Order> getOrderAll() {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT *\n"
                + "FROM [Order]\n"
                + "ORDER BY orderID DESC;";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Order(rs.getInt(1),
                        rs.getInt(2),
                        rs.getFloat(3),
                        rs.getDate(4),
                        rs.getString(5)
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

//    //getall
//    public List<Order> getAllOrder() {
//        List<Order> list = new ArrayList<>();
//        String sql = "SELECT O.orderID, O.userID, O.productID, O.cartID, O.totalPrice, O.purchaseDate\n"
//                + "FROM [Order] AS O\n"
//                + "INNER JOIN Cart AS C ON O.cartID = C.cartID;";
//        try {
//            ps = conn.prepareStatement(sql);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                list.add(new Order(rs.getInt(1),
//                        rs.getInt(2),
//                        rs.getInt(3),
//                        rs.getInt(4),
//                        rs.getFloat(5),
//                        rs.getDate(6)));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return list;
//    }
    //Delete Order
    public int DeleteOrder(int id) {
        int kq = 0;
        String sql = "delete from [Order] where orderID = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            kq = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq;
    }

    //Delete OrderDetail
    public int DeleteOrderDetail(int id) {
        int kq = 0;
        String sql = "delete from [OrderDetail] where orderID = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            kq = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq;
    }

    //Get user order by ID
    public List<Order> showOrder(int userID) {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT [orderID], [userID], [totalPrice], [purchaseDate], [orderStatus] "
                + "FROM [Order] "
                + "WHERE [userID] = ? "
                + "ORDER BY [orderID] DESC";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userID);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Order(rs.getInt(1),
                        rs.getInt(2),
                        rs.getFloat(3),
                        rs.getDate(4),
                        rs.getString(5)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    //Get user order by ID
    public List<OrderDetail> showOrderDetail(int orderID) {
        List<OrderDetail> list = new ArrayList<>();
        String sql = "SELECT od.orderID, od.productID, od.size, od.quantity, od.totalProduct, p.productName, p.productImg\n"
                + "FROM OrderDetail od\n"
                + "JOIN Product p ON od.productID = p.productID\n"
                + "WHERE od.orderID = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, orderID);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new OrderDetail(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getFloat(5)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    //Get user order by ID
    public List<Order> showOrderHistory(int userID) {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT OD.orderID, OD.productID, P.productName, P.productPrice, OD.size, OD.quantity, OD.totalProduct, O.totalPrice, O.purchaseDate, o.orderStatus\n"
                + "FROM [OrderDetail] OD\n"
                + "JOIN [Order] O ON OD.orderID = O.orderID\n"
                + "JOIN [Product] P ON OD.productID = P.productID\n"
                + "WHERE O.userID = ?;";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userID);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Order(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getFloat(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getFloat(7),
                        rs.getFloat(8),
                        rs.getDate(9),
                        rs.getString(10)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    //get Order
    public int getOrderByAC(int id) {
        rs = null;
        int kq = 0;
        String sql = "select order_id from [Order] where account_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                kq = rs.getInt("order_id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq;
    }

    public Order getNewestOrder() {
        String sql = "SELECT TOP 1 O.*, P.productName , P.productImg, OD.size\n"
                + "FROM [Order] O \n"
                + "INNER JOIN OrderDetail OD ON O.orderID = OD.orderID \n"
                + "INNER JOIN Product P ON OD.productID = P.productID \n"
                + "ORDER BY O.orderID DESC";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Order(rs.getInt(1),
                        rs.getInt(2),
                        rs.getFloat(3),
                        rs.getDate(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // Get user orders by ID
    public List<Order> showOrderByID(int orderID) {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT * FROM [Order] WHERE orderID = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, orderID);
            rs = ps.executeQuery();

            while (rs.next()) {
                Order order = new Order(
                        rs.getInt("orderID"),
                        rs.getInt("userID"),
                        rs.getFloat("totalPrice"),
                        rs.getDate("purchaseDate"),
                        rs.getString("orderStatus")
                );
                list.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    // Update orderStatus by orderID
    public void updateOrderStatus(int orderID, String newOrderStatus) {
        String sql = "UPDATE [Order] SET orderStatus = ? WHERE orderID = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, newOrderStatus);
            ps.setInt(2, orderID);

            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Order status updated successfully.");
            } else {
                System.out.println("Failed to update order status.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
