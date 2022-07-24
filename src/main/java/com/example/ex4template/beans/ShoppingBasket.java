package com.example.ex4template.beans;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;

/* this is a simple bean class instantiated in session */

@Component
public class ShoppingBasket implements Serializable {
    private ArrayList<Long>shoppingBasket;
    private String paymentMessage="";
    private double totalPrice =0;
    public ShoppingBasket() {
        this.shoppingBasket = new ArrayList<>();
    }

    public ArrayList<Long>  getIdProducts() {
        return shoppingBasket;
    }

    public void setIdProducts(ArrayList<Long>  shoppingBasket) {
        this.shoppingBasket = shoppingBasket;
    }

    public void add (Long p) {
        shoppingBasket.add(p);
    }
    public int size () {
        return shoppingBasket.size();
    }

    public void remove (Long p) {
        shoppingBasket.remove(p);
    }
    public void removeAll () {
        shoppingBasket.clear();//=null;//new ArrayList<>();
    }
    public String getPaymentMessage() {return paymentMessage;}
    public void setPaymentMessage(String message) {
        this.paymentMessage = message;
    }

    public double getTotalPrice() {return totalPrice;}
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }


    /* BEAN using ctor - session scope */
    //@Bean
//    @SessionScope
//    public Messages sessionBeanExample () {
//        Messages m = new Messages();
//        m.add("I'm session bean Messages");
//        return m;
//    }
}