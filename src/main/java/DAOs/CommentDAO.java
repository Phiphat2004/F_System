/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import DBContext.DBContext;
import Modals.Comment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CommentDAO {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public List<Comment> getAllByProduct(int productID) {
        List<Comment> list = new ArrayList<>();
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement("Select * from Comment where productID = ? ORDER BY id DESC");
            ps.setInt(1, productID);
            rs = ps.executeQuery();
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setId(rs.getInt(1));
                comment.setProduct(new ProductDAO().GetProductId(productID));
                comment.setUser(new UserDAO().GetUserId(rs.getString(3)));
                comment.setContent(rs.getString(4));
                comment.setCreatedDate(rs.getDate(5));
                list.add(comment);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void insertComment(int userID, int productID, String content) {
        String sql = "INSERT INTO [dbo].[Comment]\n"
                + "           ([productID]\n"
                + "           ,[userID]\n"
                + "           ,[content]\n"
                + "           ,[createdDate])\n"
                + "     VALUES\n"
                + "           (?,?,?,GETDATE())";
        try {
            conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, productID);
            ps.setInt(2, userID);
            ps.setString(3, content);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
