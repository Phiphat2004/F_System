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
public class User {

    private int userID;
    private String userEmail;
    private String userPassword;
    private String userName;
    private Date userDOB;
    private String userPhone;
    private String userAddress;

    public User() {
    }

    public User(int userID, String userEmail, String userPassword, String userName, Date userDOB, String userPhone, String userAddress) {
        this.userID = userID;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userDOB = userDOB;
        this.userPhone = userPhone;
        this.userAddress = userAddress;
    }

    public User(String userEmail, String userPassword, String userName, Date userDOB, String userPhone, String userAddress) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userDOB = userDOB;
        this.userPhone = userPhone;
        this.userAddress = userAddress;
    }

    public User(int userID, String userEmail, String userName, Date userDOB, String userPhone, String userAddress) {
        this.userID = userID;
        this.userEmail = userEmail;
        this.userName = userName;
        this.userDOB = userDOB;
        this.userPhone = userPhone;
        this.userAddress = userAddress;
    }
    
     public User(int userID, String userName, Date userDOB, String userPhone, String userAddress) {
        this.userID = userID;
        this.userName = userName;
        this.userDOB = userDOB;
        this.userPhone = userPhone;
        this.userAddress = userAddress;
    }
    
    public User(int userID, String userPassword) {
        this.userID = userID;
        this.userPassword = userPassword;
    }
   
    
    public User(String userEmail, String userPassword) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getUserDOB() {
        return userDOB;
    }

    public void setUserDOB(Date userDOB) {
        this.userDOB = userDOB;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

}
