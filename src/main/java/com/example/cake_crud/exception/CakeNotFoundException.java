package com.example.cake_crud.exception;

public class CakeNotFoundException extends RuntimeException{
    public CakeNotFoundException(String message) {
        super(message);
    }
}
