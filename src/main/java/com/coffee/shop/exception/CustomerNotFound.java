package com.coffee.shop.exception;

public class CustomerNotFound extends RuntimeException{
    public CustomerNotFound() {
        super("Customer not found.");
    }
    public CustomerNotFound(String message) {
        super(message);
    }
}
