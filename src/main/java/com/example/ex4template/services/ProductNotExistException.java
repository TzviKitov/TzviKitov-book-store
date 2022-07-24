package com.example.ex4template.services;

import net.bytebuddy.implementation.bind.annotation.Super;

public class ProductNotExistException extends RuntimeException {
    private String message ="";
    public ProductNotExistException(String message){
       this.message=message;
    }
    public String getMessage(){return message;}
}
