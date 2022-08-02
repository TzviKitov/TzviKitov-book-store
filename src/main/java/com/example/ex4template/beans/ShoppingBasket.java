package com.example.ex4template.beans;

import org.springframework.stereotype.Component;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * The Shopping Basket stored in the session
 */
@Component
public class ShoppingBasket implements Serializable {
    private ArrayList<Long>shoppingBasket;
    private String paymentMessage="";
    private double totalPrice =0;
    /**
     * class constructor
     */
    public ShoppingBasket() {
        this.shoppingBasket = new ArrayList<>();
    }
    /**
     *
     * @return ArrayList of id for products
     */
    public ArrayList<Long>  getIdProducts() {
        return shoppingBasket;
    }
    /**
     *
     * @param p id of product
     */
    public void add (Long p) {
        shoppingBasket.add(p);
    }
    /**
     *
     * @return The number of products in the basket
     */
    public int size () {
        return shoppingBasket.size();
    }
    /**
     *
     * @param p id product for remove
     */
    public void remove (Long p) {
        shoppingBasket.remove(p);
    }
    /**
     * empty the basket
     */
    public void removeAll () {
        shoppingBasket.clear();//=null;//new ArrayList<>();
    }
    /**
     *
     * @return Message for Shopping-basket page
     */
    public String getPaymentMessage() {return paymentMessage;}
    public void setPaymentMessage(String message) {
        this.paymentMessage = message;
    }

    /**
     *
     * @return The total price of the products that are in the basket and available for purchase
     */
    public double getTotalPrice() {return totalPrice;}

    /**
     * Set the total price of the products that are in the basket and available for purchase
     * @param totalPrice
     */
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}