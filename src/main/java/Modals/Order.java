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
public class Order {

    private int orderID;
    private int productID;
    private int userID;
    private int cartID;
    private String productName;
    private String size;
    private int quantity;
    private float totalPrice;
    private float totalProduct;
    private Date purchaseDate;
    private int monthRevenue;
    private double totalRevenue;
    private float productPrice;
    private String productIMG;
    private String orderStatus;
    private String productSizeAndName;

    public Order() {
    }

    public Order(int orderID, int userID, float totalPrice, Date purchaseDate, String orderStatus, String productName, String productIMG, String size) {
        this.orderID = orderID;
        this.userID = userID;
        this.totalPrice = totalPrice;
        this.purchaseDate = purchaseDate;
        this.orderStatus = orderStatus;
        this.productName = productName;
        this.productIMG = productIMG;
        this.size = size;
    }
    public Order(int orderID, float totalPrice, Date purchaseDate, String productSizeAndName, String productIMG) {
        this.orderID = orderID;
        this.productSizeAndName = productSizeAndName;
        this.totalPrice = totalPrice;
        this.purchaseDate = purchaseDate;
        this.productIMG = productIMG;
    }


    public Order(int orderID, int productID, String productName, float productPrice, String size, int quantity, float totalProduct, float totalPrice, Date purchaseDate, String orderStatus) {
        this.orderID = orderID;
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.size = size;
        this.quantity = quantity;
        this.totalProduct = totalProduct;
        this.totalPrice = totalPrice;
        this.purchaseDate = purchaseDate;
        this.orderStatus = orderStatus;
    }

    public Order(int orderID, int productID, int userID, int cartID, float totalPrice, Date purchaseDate, float productPrice) {
        this.orderID = orderID;
        this.productID = productID;
        this.userID = userID;
        this.cartID = cartID;
        this.totalPrice = totalPrice;
        this.purchaseDate = purchaseDate;
        this.productPrice = productPrice;
    }

    public Order(int productID, int userID, int cartID, float totalPrice, Date purchaseDate, float productPrice) {
        this.productID = productID;
        this.userID = userID;
        this.cartID = cartID;
        this.totalPrice = totalPrice;
        this.purchaseDate = purchaseDate;
        this.productPrice = productPrice;
    }

    public Order(int productID, float productPrice) {
        this.productID = productID;
        this.productPrice = productPrice;
    }

    public Order(int orderID, int userID, float totalPrice, Date purchaseDate, String orderStatus) {
        this.orderID = orderID;
        this.userID = userID;
        this.totalPrice = totalPrice;
        this.purchaseDate = purchaseDate;
        this.orderStatus = orderStatus;
    }

    public Order(int orderID, int userID, float totalPrice, Date purchaseDate) {
        this.orderID = orderID;
        this.userID = userID;
        this.totalPrice = totalPrice;
        this.purchaseDate = purchaseDate;
    }

    public Order(int monthRevenue, double totalRevenue) {
        this.monthRevenue = monthRevenue;
        this.totalRevenue = totalRevenue;
    }

    public Order(int userID, Float totalPrice, Date purchaseDate, String orderStatus) {
        this.userID = userID;
        this.totalPrice = totalPrice;
        this.purchaseDate = purchaseDate;
        this.orderStatus = orderStatus;
    }

     public String getProductSizeAndName() {
        return productSizeAndName;
    }

    public void setProductSizeAndName(String productSizeAndName) {
        this.productSizeAndName = productSizeAndName;
    }
    
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public int getMonthRevenue() {
        return monthRevenue;
    }

    public void setMonthRevenue(int monthRevenue) {
        this.monthRevenue = monthRevenue;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getTotalProduct() {
        return totalProduct;
    }

    public void setTotalProduct(float totalProduct) {
        this.totalProduct = totalProduct;
    }

    public String getProductIMG() {
        return productIMG;
    }

    public void setProductIMG(String productIMG) {
        this.productIMG = productIMG;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

}
