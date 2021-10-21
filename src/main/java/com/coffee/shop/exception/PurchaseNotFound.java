package com.coffee.shop.exception;

public class PurchaseNotFound extends RuntimeException{
    public PurchaseNotFound() {
        super("Purchase Not Found.");
    }
    public PurchaseNotFound(String message) {
        super(message);
    }
}