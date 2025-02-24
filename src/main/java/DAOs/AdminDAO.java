/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Modals.Admin;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class AdminDAO {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public AdminDAO() {
        conn = DBContext.DBContext.getConnection();
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
    
    
    public Admin GetAdmin(String email) {
        Admin admin = new Admin();
        try {
            ps = conn.prepareStatement("select * from Admin where adminEmail=?");
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                admin = new Admin(rs.getInt("adminID"), rs.getString("adminEmail"), rs.getString("adminPassword"), rs.getString("adminName"),
                        rs.getDate("adminDayOfBirth"), rs.getString("adminPhone"), rs.getString("adminAddress"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return admin;
    }
    
    public boolean Login(Admin admin) throws SQLException {
        ResultSet rs = null;
        String sql = "Select * from Admin where adminEmail = ? and adminPassword=?";
        String password = admin.getAdminPassword();
        String hashPassword = hashPassword(password, "MD5");
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, admin.getAdminEmail());
            ps.setString(2, hashPassword);
            rs = ps.executeQuery();
        } catch (Exception e) {

        }

        return rs.next();
    }
}
