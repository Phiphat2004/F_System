/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Modals.Cart;
import Modals.Import;
import Modals.ImportDetail;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class ImportDAO {

    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public ImportDAO() {
        //conn = DBContext.DBContext.getConnection();
        conn = DBContext.DBContext.getConnection();
    }

    public void addNewImport(int staffID, int productID, String size, int quantity) {
        String importSQL = "INSERT INTO Import (staffID, importDate) VALUES (?, ?)";
        String importDetailSQL = "INSERT INTO ImportDetail (importID, productID, size, quantity) VALUES (?, ?, ?, ?)";

        try {
            // Bắt đầu giao dịch
            conn.setAutoCommit(false);

            // Thêm dữ liệu vào bảng Import
            try ( PreparedStatement importPS = conn.prepareStatement(importSQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
                importPS.setInt(1, staffID);
                importPS.setDate(2, new Date(System.currentTimeMillis()));

                int rowsAffected = importPS.executeUpdate();

                if (rowsAffected > 0) {
                    // Lấy importID được tạo ra từ bảng Import
                    try ( ResultSet generatedKeys = importPS.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int importID = generatedKeys.getInt(1);

                            // Thêm dữ liệu vào bảng ImportDetail
                            try ( PreparedStatement importDetailPS = conn.prepareStatement(importDetailSQL)) {
                                importDetailPS.setInt(1, importID);
                                importDetailPS.setInt(2, productID);
                                importDetailPS.setString(3, size);
                                importDetailPS.setInt(4, quantity);

                                int importDetailRowsAffected = importDetailPS.executeUpdate();

                                // Kết thúc giao dịch
                                if (importDetailRowsAffected > 0) {
                                    conn.commit();
                                } else {
                                    // Rollback giao dịch nếu không thành công
                                    conn.rollback();
                                }
                            }
                        } else {
                            // Xảy ra lỗi khi lấy importID
                            conn.rollback();
                        }
                    }
                } else {
                    // Rollback giao dịch nếu không thành công
                    conn.rollback();
                }
            }
        } catch (SQLException ex) {
            // Rollback giao dịch nếu có lỗi
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                // Khôi phục chế độ mặc định của AutoCommit
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Import> showImport() {
        List<Import> importList = new ArrayList<>();

        String sql = "SELECT I.importID, I.staffID, S.staffName, S.staffEmail, S.staffPhone, I.importDate \n"
                + "FROM Import I \n"
                + "JOIN Staff S ON I.staffID = S.staffID \n"
                + "ORDER BY I.importID DESC";

        try ( PreparedStatement preparedStatement = conn.prepareStatement(sql);  ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int importID = resultSet.getInt("importID");
                int staffID = resultSet.getInt("staffID");
                String staffName = resultSet.getString("staffName");
                String staffEmail = resultSet.getString("staffEmail");
                String staffPhone = resultSet.getString("staffPhone");
                java.sql.Date importDate = resultSet.getDate("importDate");

                Import importData = new Import(importID, staffID, staffName, staffEmail, staffPhone, importDate);
                importList.add(importData);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ImportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return importList;
    }

    public List<ImportDetail> showImportDetail(int id) {
        List<ImportDetail> importDetailProductList = new ArrayList<>();

        String sql = "SELECT ID.importID, P.productID, P.productName, P.productImg, ID.size, ID.quantity, P.productPrice "
                + "FROM ImportDetail ID "
                + "JOIN Product P ON ID.productID = P.productID "
                + "WHERE ID.importID = ?";

        try ( PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            try ( ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int importID = resultSet.getInt("importID");
                    int productID = resultSet.getInt("productID");
                    String productName = resultSet.getString("productName");
                    String productImg = resultSet.getString("productImg");
                    String size = resultSet.getString("size");
                    int quantity = resultSet.getInt("quantity");
                    float price = resultSet.getFloat("productPrice");
                    ImportDetail importDetailProduct = new ImportDetail(importID, productID, productName, productImg, size, quantity, price);
                    importDetailProductList.add(importDetailProduct);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ImportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return importDetailProductList;
    }

}
