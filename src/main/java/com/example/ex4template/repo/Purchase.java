package com.example.ex4template.repo;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.sql.Timestamp;

@Entity
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @CreationTimestamp
    private Timestamp timestamp;

    @NotNull
    @Positive
    private double amount;

    @NotNull
    private String purchaserName;

    public Purchase(){}

    public Purchase(double amount,String purchaserName){
        this.amount = amount;
        this.purchaserName=purchaserName;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    public Double getAmount() {
        return amount;
    }


    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setPurchaserName(String name){this.purchaserName=name;}
    public  String getPurchaserName(){return purchaserName;}

    @Override
    public String toString() {
        return "Purchase{" +
                "timestamp='" + timestamp +
                ", amount=" + amount +
                ",purchaserName="+ purchaserName+
                '}';
    }

}
