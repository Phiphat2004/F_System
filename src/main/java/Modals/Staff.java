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
public class Staff {

    private int staffID;
    private String staffEmail;
    private String staffPassword;
    private String staffName;
    private Date staffDOB;
    private String staffPhone;
    private String staffAddress;

    public Staff() {
    }

    public Staff(int staffID, String staffEmail, String staffPassword, String staffName, Date staffDOB, String staffPhone, String staffAddress) {
        this.staffID = staffID;
        this.staffEmail = staffEmail;
        this.staffPassword = staffPassword;
        this.staffName = staffName;
        this.staffDOB = staffDOB;
        this.staffPhone = staffPhone;
        this.staffAddress = staffAddress;
    }

    public Staff(String staffEmail, String staffPassword, String staffName, Date staffDOB, String staffPhone, String staffAddress) {
        this.staffEmail = staffEmail;
        this.staffPassword = staffPassword;
        this.staffName = staffName;
        this.staffDOB = staffDOB;
        this.staffPhone = staffPhone;
        this.staffAddress = staffAddress;
    }

    public Staff(String staffEmail, String staffPassword) {
        this.staffEmail = staffEmail;
        this.staffPassword = staffPassword;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public String getStaffEmail() {
        return staffEmail;
    }

    public void setStaffEmail(String staffEmail) {
        this.staffEmail = staffEmail;
    }

    public String getStaffPassword() {
        return staffPassword;
    }

    public void setStaffPassword(String staffPassword) {
        this.staffPassword = staffPassword;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public Date getStaffDOB() {
        return staffDOB;
    }

    public void setStaffDOB(Date staffDOB) {
        this.staffDOB = staffDOB;
    }

    public String getStaffPhone() {
        return staffPhone;
    }

    public void setStaffPhone(String staffPhone) {
        this.staffPhone = staffPhone;
    }

    public String getStaffAddress() {
        return staffAddress;
    }

    public void setStaffAddress(String staffAddress) {
        this.staffAddress = staffAddress;
    }

}
