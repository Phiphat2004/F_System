/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modals;

/**
 *
 * @author Dell
 */
public class OrderDetail {
    private int orderID;
    private int productID;
    private String productName;
    private String productImg;
    private String size;
    private int quantity;
    private float productPrice;

    public OrderDetail() {
    }

    public OrderDetail(int orderID, int productID, float productPrice) {
        this.orderID = orderID;
        this.productID = productID;
        this.productPrice = productPrice;
    }

    public OrderDetail(int orderID, int productID, String size, int quantity, float productPrice) {
        this.orderID = orderID;
        this.productID = productID;
        this.size = size;
        this.quantity = quantity;
        this.productPrice = productPrice;
    }

    public OrderDetail(int orderID, int productID, String productName, String productImg, String size, int quantity, float productPrice) {
        this.orderID = orderID;
        this.productID = productID;
        this.productName = productName;
        this.productImg = productImg;
        this.size = size;
        this.quantity = quantity;
        this.productPrice = productPrice;
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

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }
    
    
    
}
