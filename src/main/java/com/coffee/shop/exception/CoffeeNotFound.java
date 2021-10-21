package com.coffee.shop.exception;

public class CoffeeNotFound extends RuntimeException{
    public CoffeeNotFound() {
        super("Coffee not found.");
    }
    public CoffeeNotFound(String message) {
        super(message);
    }
}
