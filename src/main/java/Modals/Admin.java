/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modals;

import java.sql.Date;

/**
 *
 * @author Dell
 */
public class Admin {
    private int adminID;
    private String adminEmail;
    private String adminPassword;
    private String adminName;
    private Date adminDOB;
    private String adminPhone;
    private String adminAddress;

    public Admin() {
    }

    public Admin(int adminID, String adminEmail, String adminPassword, String adminName, Date adminDOB, String adminPhone, String adminAddress) {
        this.adminID = adminID;
        this.adminEmail = adminEmail;
        this.adminPassword = adminPassword;
        this.adminName = adminName;
        this.adminDOB = adminDOB;
        this.adminPhone = adminPhone;
        this.adminAddress = adminAddress;
    }
    
    public Admin(String adminEmail, String adminPassword) {
        this.adminEmail = adminEmail;
        this.adminPassword = adminPassword;
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public Date getAdminDOB() {
        return adminDOB;
    }

    public void setAdminDOB(Date adminDOB) {
        this.adminDOB = adminDOB;
    }

    public String getAdminPhone() {
        return adminPhone;
    }

    public void setAdminPhone(String adminPhone) {
        this.adminPhone = adminPhone;
    }

    public String getAdminAddress() {
        return adminAddress;
    }

    public void setAdminAddress(String adminAddress) {
        this.adminAddress = adminAddress;
    }
    
    
   
    
    
}
