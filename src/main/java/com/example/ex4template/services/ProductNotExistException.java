package com.example.ex4template.services;

import net.bytebuddy.implementation.bind.annotation.Super;

/**
 * An exception class that is thrown in the event that you try to update and pay for a product whose quantity in the database is equal to 0.
 */
public class ProductNotExistException extends RuntimeException {
    private String message ="";
    public ProductNotExistException(String message){
       this.message=message;
    }
    public String getMessage(){return message;}
}
