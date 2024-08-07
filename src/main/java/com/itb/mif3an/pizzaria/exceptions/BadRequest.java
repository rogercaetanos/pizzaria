package com.itb.mif3an.pizzaria.exceptions;



public class BadRequest extends RuntimeException{

    public BadRequest(String message){
         super(message);
    }
}
