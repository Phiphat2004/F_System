/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Modals.Product;
import Modals.Size;
import Modals.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author uchih
 */
public class ProductDAO {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public ProductDAO() {
        con = DBContext.DBContext.getConnection();
    }

            public Product GetProductId(int id) {
        Product p = new Product();
        try {
            ps = con.prepareStatement("select * from [Product] where productID=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                p = new Product(rs.getInt("productID"), rs.getString("productName"), rs.getDouble("productPrice"), rs.getInt("productQuantity"),
                        rs.getString("productImg"), rs.getString("productCategory"), rs.getString("productDis"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }
            
    public Product getProductById(int id) {
        Product p = null;
        try ( PreparedStatement ps = con.prepareStatement("SELECT P.*, S.productSize, S.productQuantity "
                + "FROM [Product] P "
                + "LEFT JOIN [Size] S ON P.productID = S.productID "
                + "WHERE P.productID = ?")) {
            ps.setInt(1, id);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    p = new Product(
                            rs.getInt("productID"),
                            rs.getString("productName"),
                            rs.getDouble("productPrice"),
                            rs.getString("productImg"),
                            rs.getString("productCategory"),
                            rs.getString("productDis"),
                            rs.getString("productSize"), // Thêm thông tin từ bảng Size
                            rs.getInt("productQuantity") // Thêm thông tin từ bảng Size
                    );
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    public void updateQuantity(int quan, int proID) {
        try {
            ps = con.prepareStatement("UPDATE [Product] SET productQuantity=? where productID=?");
            ps.setInt(1, quan);
            ps.setInt(2, proID);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Product> getProductForGuest() {
        ArrayList<Product> products = new ArrayList<>();

        try {
            String query = "SELECT * FROM Product";
            try ( PreparedStatement preparedStatement = con.prepareStatement(query);  ResultSet rs = preparedStatement.executeQuery()) {

                while (rs.next()) {
                    Product product = new Product(
                            rs.getInt("productID"),
                            rs.getString("productName"),
                            rs.getFloat("productPrice"),
                            rs.getString("productImg"),
                            rs.getString("productCategory"),
                            rs.getString("productDis"));
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log or handle the exception appropriately
        }

        return products;
    }

    public ArrayList<Product> getProduct(String search, int index, String sort, String category) {
        String sortby = "";
        switch (sort) {
            case "1":
                sortby = "order by p.productName asc";
                break;
            case "2":
                sortby = "order by p.productPrice asc";
                break;
            case "3":
                sortby = "order by p.productPrice desc";
                break;
            default:
                sortby = "order by p.productName desc";
                break;

        }
        ArrayList<Product> list = new ArrayList<>();
        String sql = "  select * from [Product] p where p.productName like ? and [productCategory] like ? "
                + sortby
                + "  OFFSET ? ROWS FETCH NEXT 8  ROWS ONLY"; // 6 0-2,2-4,4-6
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + search + "%"); // Short   %h%  zzzhqwoeoqw hqoweoqwe qưuioeqioweioh
            ps.setString(2, "%" + category + "%");
            ps.setInt(3, (index - 1) * 8);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int productID = resultSet.getInt("productID");
                String productName = resultSet.getString("productName");
                double productPrice = resultSet.getDouble("productPrice");
                String productImg = resultSet.getString("productImg");
                String productCategory = resultSet.getString("productCategory");
                String productDescription = resultSet.getString("productDis");
                Product product = new Product(productID, productName, productPrice, productImg, productCategory, productDescription);
                list.add(product);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();

        try (
                 Statement statement = con.createStatement()) {
            String query = "SELECT p.productID, p.productName, p.productPrice, p.productImg, p.productCategory, p.productDis, COALESCE(SUM(s.productQuantity), 0) AS totalQuantity\n"
                    + "FROM Product p\n"
                    + "LEFT JOIN Size s ON p.productID = s.productID\n"
                    + "GROUP BY p.productID, p.productName, p.productPrice, p.productImg, p.productCategory, p.productDis;";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int productID = resultSet.getInt("productID");
                String productName = resultSet.getString("productName");
                double productPrice = resultSet.getDouble("productPrice");
                String productImg = resultSet.getString("productImg");
                String productMaterial = resultSet.getString("productCategory");
                String productType = resultSet.getString("productDis");
                int totalQuantity = resultSet.getInt("totalQuantity");
                Product product = new Product(productID, productName, productPrice, productImg, productMaterial, productType, totalQuantity);
                products.add(product);

            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any potential database errors here
        }

        return products;
    }

    public Product getAll() {
        Product p = null;
        try (
                 Statement statement = con.createStatement()) {
            String query = "SELECT * FROM Product";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int productID = resultSet.getInt("productID");
                String productName = resultSet.getString("productName");
                double productPrice = resultSet.getDouble("productPrice");
                int productQuantity = resultSet.getInt("productQuantity");
                String productImg = resultSet.getString("productImg");
                String productCategory = resultSet.getString("productCategory");
                String productDis = resultSet.getString("productDis");
                p = new Product(productID, productName, productPrice, productQuantity, productImg, productCategory, productDis);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any potential database errors here
        }

        return p;
    }

    public List<Product> searchProduct(String name, String typeSort) {
        String sql = "select * from Product where userId like '%?%' ";
        List<Product> products = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, name);
            String sqlSort = "";
            switch (typeSort) {
                case "asc":
                    sql += "order by asc";
                    break;
                case "desc":
                    sql += "order by desc";
                    break;
                default:
                    sql = "select * from Product where userId like '%?%' ";
                    break;
            }

            rs = ps.executeQuery();
            while (rs.next()) {
                int productID = rs.getInt("productID");
                String productName = rs.getString("productName");
                double productPrice = rs.getDouble("productPrice");
                int productQuantity = rs.getInt("productQuantity");
                String productImg = rs.getString("productImg");
                String productMaterial = rs.getString("productCategory");
                String productType = rs.getString("productDis");
                Product product = new Product(productID, productName, productPrice, productQuantity, productImg, productMaterial, productType);
                products.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int getNumberProduct(String search) {
        ArrayList<Product> list = new ArrayList<>();
        String sql = "  select count(*) from Product p where p.productName like ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public ResultSet getAllProduct() {
        String sql = "select*from Product";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void updateSize(int productID, String size, int newQuantity) {
        String sql = "UPDATE Size SET productQuantity = ? WHERE productID = ? AND productSize = ?";

        try ( PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            // Set parameters for the PreparedStatement
            preparedStatement.setInt(1, newQuantity);
            preparedStatement.setInt(2, productID);
            preparedStatement.setString(3, size);

            // Execute the SQL statement
            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Size updated successfully.");
            } else {
                System.out.println("Failed to update size. Size not found or quantity unchanged.");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQLException appropriately
        }
    }

    public void addSize(String productID, String size, String quantity) {
        String sql = "INSERT INTO Size (productID, productSize, productQuantity) VALUES (?, ?, ?)";

        try ( PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            // Set parameters for the PreparedStatement
            preparedStatement.setString(1, productID);
            preparedStatement.setString(2, size);
            preparedStatement.setString(3, quantity);

            // Execute the SQL statement
            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Size added successfully.");
            } else {
                System.out.println("Failed to add size.");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQLException appropriately
        }
    }

    public List<Size> getSize(int productID) {
        List<Size> sizes = new ArrayList<>();
        String sql = "SELECT * FROM Size WHERE productID = ?";

        try ( PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            // Set parameters for the PreparedStatement
            preparedStatement.setInt(1, productID);

            // Execute the SQL statement
            try ( ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    // Create a Size object and add it to the list
                    Size size = new Size(
                            resultSet.getInt("productID"),
                            resultSet.getString("productSize"),
                            resultSet.getInt("productQuantity")
                    );
                    sizes.add(size);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQLException appropriately
        }

        return sizes;
    }

    public void addProductAndImport(int staffID, String productName, String productPrice, String size, int productQuantity, String productImg, String productDiscription, String productCategory, int importQuantity) {
        String productSql = "INSERT INTO [dbo].[Product] ([productName], [productPrice], [productImg], [productCategory], [productDis]) VALUES (?, ?, ?, ?, ?)";
        String sizeSql = "INSERT INTO [dbo].[Size] ([productID], [productSize], [productQuantity]) VALUES (?, ?, ?)";
        String importSQL = "INSERT INTO Import (staffID, importDate) VALUES (?, ?)";
        String importDetailSQL = "INSERT INTO ImportDetail (importID, productID, size, quantity) VALUES (?, ?, ?, ?)";

        try {
            // Bắt đầu giao dịch
            con.setAutoCommit(false);

            // Thêm dữ liệu vào bảng Product
            try ( PreparedStatement productStatement = con.prepareStatement(productSql, Statement.RETURN_GENERATED_KEYS)) {
                productStatement.setString(1, productName);
                productStatement.setString(2, productPrice);
                productStatement.setString(3, productImg);
                productStatement.setString(4, productDiscription);
                productStatement.setString(5, productCategory);

                int rowsInsertedProduct = productStatement.executeUpdate();

                if (rowsInsertedProduct > 0) {
                    // Lấy productID được tạo ra từ bảng Product
                    try ( ResultSet generatedKeys = productStatement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int productID = generatedKeys.getInt(1);

                            // Thêm dữ liệu vào bảng Size
                            try ( PreparedStatement sizeStatement = con.prepareStatement(sizeSql)) {
                                sizeStatement.setInt(1, productID);
                                sizeStatement.setString(2, size);
                                sizeStatement.setInt(3, productQuantity);

                                int rowsInsertedSize = sizeStatement.executeUpdate();

                                if (rowsInsertedSize > 0) {
                                    // Thêm dữ liệu vào bảng Import
                                    try ( PreparedStatement importPS = con.prepareStatement(importSQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
                                        importPS.setInt(1, staffID);
                                        importPS.setDate(2, new Date(System.currentTimeMillis()));

                                        int rowsAffected = importPS.executeUpdate();

                                        if (rowsAffected > 0) {
                                            // Lấy importID được tạo ra từ bảng Import
                                            try ( ResultSet importGeneratedKeys = importPS.getGeneratedKeys()) {
                                                if (importGeneratedKeys.next()) {
                                                    int importID = importGeneratedKeys.getInt(1);

                                                    // Thêm dữ liệu vào bảng ImportDetail
                                                    try ( PreparedStatement importDetailPS = con.prepareStatement(importDetailSQL)) {
                                                        importDetailPS.setInt(1, importID);
                                                        importDetailPS.setInt(2, productID);
                                                        importDetailPS.setString(3, size);
                                                        importDetailPS.setInt(4, importQuantity);

                                                        int importDetailRowsAffected = importDetailPS.executeUpdate();

                                                        // Kết thúc giao dịch
                                                        if (importDetailRowsAffected > 0) {
                                                            con.commit();
                                                            System.out.println("Thêm sản phẩm và import thành công.");
                                                        } else {
                                                            // Rollback giao dịch nếu không thành công
                                                            con.rollback();
                                                            System.out.println("Không thể thêm dữ liệu vào bảng ImportDetail.");
                                                        }
                                                    }
                                                } else {
                                                    // Xảy ra lỗi khi lấy importID
                                                    con.rollback();
                                                    System.out.println("Xảy ra lỗi khi lấy importID.");
                                                }
                                            }
                                        } else {
                                            // Rollback giao dịch nếu không thành công
                                            con.rollback();
                                            System.out.println("Không thể thêm dữ liệu vào bảng Import.");
                                        }
                                    }
                                } else {
                                    // Rollback giao dịch nếu không thành công
                                    con.rollback();
                                    System.out.println("Không thể thêm dữ liệu vào bảng Size.");
                                }
                            }
                        } else {
                            // Xảy ra lỗi khi lấy productID
                            con.rollback();
                            System.out.println("Xảy ra lỗi khi lấy productID.");
                        }
                    }
                } else {
                    // Rollback giao dịch nếu không thành công
                    con.rollback();
                    System.out.println("Không thể thêm dữ liệu vào bảng Product.");
                }
            }
        } catch (SQLException ex) {
            // Rollback giao dịch nếu có lỗi
            try {
                con.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ex.printStackTrace();
        } finally {
            try {
                // Khôi phục chế độ mặc định của AutoCommit
                con.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void addProduct(String productName, String productPrice, String size, int productQuantity, String productImg, String productDiscription, String productCategory) {
        String productSql = "INSERT INTO [dbo].[Product] ([productName], [productPrice], [productImg], [productCategory], [productDis]) VALUES (?, ?, ?, ?, ?)";
        String sizeSql = "INSERT INTO [dbo].[Size] ([productID], [productSize], [productQuantity]) VALUES (?, ?, ?)";

        try {
            // Insert into Product table
            PreparedStatement productStatement = con.prepareStatement(productSql, Statement.RETURN_GENERATED_KEYS);

            productStatement.setString(1, productName);
            productStatement.setString(2, productPrice);
            productStatement.setString(3, productImg);
            productStatement.setString(4, productDiscription);
            productStatement.setString(5, productCategory);

            int rowsInsertedProduct = productStatement.executeUpdate();

            if (rowsInsertedProduct > 0) {
                // Retrieve the generated productID
                ResultSet generatedKeys = productStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int productID = generatedKeys.getInt(1);

                    // Insert into Size table
                    PreparedStatement sizeStatement = con.prepareStatement(sizeSql);
                    sizeStatement.setInt(1, productID);
                    sizeStatement.setString(2, size);
                    sizeStatement.setInt(3, productQuantity);

                    int rowsInsertedSize = sizeStatement.executeUpdate();

                    if (rowsInsertedSize > 0) {
                        System.out.println("Thêm sản phẩm thành công.");
                    } else {
                        System.out.println("Không thể thêm sản phẩm vào bảng Size.");
                    }
                }
            } else {
                System.out.println("Không thể thêm sản phẩm vào bảng Product.");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQLException appropriately
        }
    }

    public void updateProduct(String productName, String productPrice, String productImg, String productDis, String productCategory, String id) {
        String sql = "UPDATE Product\n"
                + "SET productName = ?,\n"
                + "    productPrice = ?,\n"
                + "    productImg = ?,\n"
                + "    productDis = ?,\n"
                + "    productCategory = ?\n"
                + "WHERE productID = ?";

        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, productName);
            preparedStatement.setString(2, productPrice);
            preparedStatement.setString(3, productImg);
            preparedStatement.setString(4, productDis);
            preparedStatement.setString(5, productCategory);
            preparedStatement.setString(6, id);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Cập nhật sản phẩm thành công!");
            } else {
                System.out.println("Không có sản phẩm nào được cập nhật.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý bất kỳ lỗi cơ sở dữ liệu nào ở đây
        }
    }

    public void deleteProduct(String productId) {
        try {
            // Xóa các bản ghi từ bảng Size liên quan đến sản phẩm
            String deleteSizeSql = "DELETE FROM Size WHERE productID = ?";
            try ( PreparedStatement deleteSizeStatement = con.prepareStatement(deleteSizeSql)) {
                deleteSizeStatement.setString(1, productId);
                deleteSizeStatement.executeUpdate();
            }

            // Xóa sản phẩm từ bảng Product
            String deleteProductSql = "DELETE FROM Product WHERE productID = ?";
            try ( PreparedStatement deleteProductStatement = con.prepareStatement(deleteProductSql)) {
                deleteProductStatement.setString(1, productId);

                int rowsDeleted = deleteProductStatement.executeUpdate();

                if (rowsDeleted > 0) {
                    System.out.println("Xóa sản phẩm thành công!");
                } else {
                    System.out.println("Không có sản phẩm nào bị xóa.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý bất kỳ lỗi cơ sở dữ liệu nào ở đây
        }
    }

    public void updateSizeQuantity(int productID, String size, int newQuantity) {
        String sql = "UPDATE Size SET productQuantity = ? WHERE productID = ? AND productSize = ?";

        try ( PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setInt(1, newQuantity);
            preparedStatement.setInt(2, productID);
            preparedStatement.setString(3, size);

            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Size quantity updated successfully.");
            } else {
                System.out.println("Failed to update size quantity. Size not found or quantity unchanged.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQLException appropriately
        }
    }

}
