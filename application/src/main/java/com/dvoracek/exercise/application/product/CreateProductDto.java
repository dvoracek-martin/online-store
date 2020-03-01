package com.dvoracek.exercise.application.product;

import com.sun.istack.NotNull;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class CreateProductDto {

    @NotEmpty
    @Size(max = 255)
    private String productName;

    @NotNull
    @Min(value = 0)
    private int productPrice;

    public String getProductName() {
        return productName;
    }

    public CreateProductDto setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public CreateProductDto setProductPrice(int productPrice) {
        this.productPrice = productPrice;
        return this;
    }
}
