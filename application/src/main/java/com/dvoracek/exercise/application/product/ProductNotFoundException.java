package com.dvoracek.exercise.application.product;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long id) {
        super("Product with an id: " + id + " wasn't found.");
    }
}
