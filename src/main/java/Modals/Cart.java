/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modals;

import DAOs.ProductDAO;
import java.util.List;

/**
 *
 * @author admin
 */
public class Cart {

    private int cartID;
    private int userID;
    private int productID;
    private String size;
    private int quantity;

    private List<Item> items;

    public Cart() {
    }

    public Cart(int cartID, int userID, int productID, String size, int quantity) {
        this.cartID = cartID;
        this.userID = userID;
        this.productID = productID;
        this.size = size;
        this.quantity = quantity;
    }
    
    

    public Cart(int userID, int productID, String size, int quantity) {
        this.userID = userID;
        this.productID = productID;
        this.size = size;
        this.quantity = quantity;
    }

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

  

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
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

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    //Totalprice
    public double getTotalPrice() {
        double t = 0;
        for (Item o : items) {
            t += (o.getProductQuantity() * o.getPrice());
        }
        return t;
    }

//    @Override
//    public String toString() {
//        return "Cart{" + "product=" + product + ", cartID=" + cartID + ", userID=" + userID + ", productID=" + productID + ", size=" + size + ", quantity=" + quantity + '}';
//    }

}
