/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Modals.Staff;
import Modals.Staff;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class StaffDAO {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public StaffDAO() {
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

    public int AddNew(Staff s) throws SQLException {
        String sql = "insert into [Staff](staffEmail,staffPassword, staffName, staffDayOfBirth, staffPhone, staffAddress) values (?,?,?,?,?,?);";
        String password = s.getStaffPassword();
        String hashPassword = hashPassword(password, "MD5");
        int ketqua = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, s.getStaffEmail());
            ps.setString(2, hashPassword.toUpperCase());
            ps.setString(3, s.getStaffName());
            ps.setDate(4, s.getStaffDOB());
            ps.setString(5, s.getStaffPhone());
            ps.setString(6, s.getStaffAddress());
            ketqua = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ketqua;
    }

 
    
    public Staff GetStaff(String email) {

        try {
            ps = conn.prepareStatement("select * from Staff where staffEmail=?");
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                Staff staff = new Staff();
                staff = new Staff(rs.getInt("staffID"), rs.getString("staffEmail"), rs.getString("staffPassword"), rs.getString("staffName"),
                        rs.getDate("staffDayOfBirth"), rs.getString("staffPhone"), rs.getString("staffAddress"));
                return staff;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Staff GetStaffByID(String id) {

        try {
            ps = conn.prepareStatement("select * from Staff where staffID=?");
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Staff staff = new Staff();
                staff = new Staff(rs.getInt("staffID"), rs.getString("staffEmail"), rs.getString("staffPassword"), rs.getString("staffName"),
                        rs.getDate("staffDayOfBirth"), rs.getString("staffPhone"), rs.getString("staffAddress"));
                return staff;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<Staff> getStaffList(String email) {
        List<Staff> staffList = new ArrayList<>();

        try {
            ps = conn.prepareStatement("SELECT * FROM Staff WHERE staffEmail=?");
            ps.setString(1, email);
            rs = ps.executeQuery();

            while (rs.next()) {
                Staff staff = new Staff(
                        rs.getInt("staffID"),
                        rs.getString("staffEmail"),
                        rs.getString("staffPassword"),
                        rs.getString("staffName"),
                        rs.getDate("staffDayOfBirth"),
                        rs.getString("staffPhone"),
                        rs.getString("staffAddress")
                );

                staffList.add(staff);
            }

        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return staffList;
    }

    public boolean Login(Staff staff) throws SQLException {
        ResultSet rs = null;
        String sql = "Select * from Staff where staffEmail = ? and staffPassword=?";
        String password = staff.getStaffPassword();
        String hashPassword = hashPassword(password, "MD5");
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, staff.getStaffEmail());
            ps.setString(2, hashPassword);
            rs = ps.executeQuery();
        } catch (Exception e) {

        }

        return rs.next();
    }

    public List<Staff> getAllStaffs() {
        List<Staff> list = new ArrayList<>();
        try {
            ps = conn.prepareStatement("Select * from Staff");
            rs = ps.executeQuery();
            while (rs.next()) {
                Staff staff = new Staff(rs.getInt("staffID"), rs.getString("staffEmail"), rs.getString("staffPassword"), rs.getString("staffName"),
                        rs.getDate("staffDayOfBirth"), rs.getString("staffPhone"), rs.getString("staffAddress"));
                list.add(staff);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void deleteStaffID(int id) {
        try {
            ps = conn.prepareStatement("DELETE FROM [dbo].[Staff]\n"
                    + "      WHERE staffID = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addStaff(String email, String name, String phone, String address, Date date, String password) {
        try {
            ps = conn.prepareStatement("INSERT INTO [dbo].[Staff]\n"
                    + "           ([staffEmail]\n"
                    + "           ,[staffPassword]\n"
                    + "           ,[staffName]\n"
                    + "           ,[staffDayOfBirth]\n"
                    + "           ,[staffPhone]\n"
                    + "           ,[staffAddress])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?,?,?)");
            ps.setString(1, email);
            ps.setString(2, password);
            ps.setString(3, name);
            ps.setDate(4, date);
            ps.setString(5, phone);
            ps.setString(6, address);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateStaff( String name, String phone, String address, Date date, String id) {
        try {
            ps = conn.prepareStatement("UPDATE [dbo].[Staff]\n"
                    + "   SET [staffName] = ?\n"
                    + "      ,[staffDayOfBirth] = ?\n"
                    + "      ,[staffPhone] = ?\n"
                    + "      ,[staffAddress] = ?\n"
                    + " WHERE staffID = ?");
            ps.setString(1, name);
            ps.setDate(2, date);
            ps.setString(3, phone);
            ps.setString(4, address);
            ps.setInt(5, Integer.parseInt(id));
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
