/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modals;

import java.sql.Date;

/**
 *
 * @author admin
 */
public class Import {
    private int importID;
    private int staffID;
    private String staffName;
    private String staffEmail;
    private String staffPhone;
    private Date importDate;

    public Import() {
    }

    public Import(int importID, int staffID, Date importDate) {
        this.importID = importID;
        this.staffID = staffID;
        this.importDate = importDate;
    }

    public Import(int importID, int staffID, String staffName, String staffEmail, String staffPhone, Date importDate) {
        this.importID = importID;
        this.staffID = staffID;
        this.staffName = staffName;
        this.staffEmail = staffEmail;
        this.staffPhone = staffPhone;
        this.importDate = importDate;
    }
    
    

    public int getImportID() {
        return importID;
    }

    public void setImportID(int importID) {
        this.importID = importID;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffEmail() {
        return staffEmail;
    }

    public void setStaffEmail(String staffEmail) {
        this.staffEmail = staffEmail;
    }

    public String getStaffPhone() {
        return staffPhone;
    }

    public void setStaffPhone(String staffPhone) {
        this.staffPhone = staffPhone;
    }

    
    
    @Override
    public String toString() {
        return "Import{" + "importID=" + importID + ", staffID=" + staffID + ", importDate=" + importDate + '}';
    }
            
}
