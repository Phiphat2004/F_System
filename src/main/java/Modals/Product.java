/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modals;

/**
 *
 * @author uchih
 */
public class Product {
    private int productID;
    private String productName;
    private double productPrice;
    private String productImg;
    private String productCategory;
    private String productDis;
    private String productSize;
    private int totalQuantity;

    public Product() {
    }

    public Product(int productID, String productName, double productPrice, int totalQuantity, String productImg, String productCategory, String productDis) {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.totalQuantity = totalQuantity;
        this.productImg = productImg;
        this.productCategory = productCategory;
        this.productDis = productDis;
    }

    public Product(int productID, String productName, double productPrice, String productImg, String productCategory, String productDis, String productSize, int totalQuantity) {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productImg = productImg;
        this.productCategory = productCategory;
        this.productDis = productDis;
        this.productSize = productSize;
        this.totalQuantity = totalQuantity;
    }
    
      public Product(int productID, String productName, double productPrice, String productImg, String productCategory, String productDis) {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productImg = productImg;
        this.productCategory = productCategory;
        this.productDis = productDis;
    }
    
     public Product(int productID, String productName, double productPrice, String productImg, String productCategory, String productDis, int totalQuantity) {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productImg = productImg;
        this.productCategory = productCategory;
        this.productDis = productDis;
        this.totalQuantity = totalQuantity;
    }

    
    
    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public void setProductDis(String productDis) {
        this.productDis = productDis;
    }

    public int getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public String getProductImg() {
        return productImg;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public String getProductDis() {
        return productDis;
    }
}