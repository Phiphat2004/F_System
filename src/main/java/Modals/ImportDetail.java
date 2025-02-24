/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modals;

/**
 *
 * @author admin
 */
public class ImportDetail {
    private  int importID;
    private int productID;
    private String productName;
    private String productImg;
    private String size;
    private int quantity;
    private float price;

    public ImportDetail() {
    }

    
    public ImportDetail(int importID, int productID, int quantity) {
        this.importID = importID;
        this.productID = productID;
        this.quantity = quantity;
    }

    public ImportDetail(int importID, int productID, String productName, String productImg, String size, int quantity, float price) {
        this.importID = importID;
        this.productID = productID;
        this.productName = productName;
        this.productImg = productImg;
        this.size = size;
        this.quantity = quantity;
        this.price = price;
    }

    
    

    public int getImportID() {
        return importID;
    }

    public void setImportID(int importID) {
        this.importID = importID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
    

    @Override
    public String toString() {
        return "ImportDetail{" + "importID=" + importID + ", productID=" + productID + ", quantity=" + quantity + '}';
    }
    
}
