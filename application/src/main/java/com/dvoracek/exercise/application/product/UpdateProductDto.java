package com.dvoracek.exercise.application.product;

import com.sun.istack.NotNull;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UpdateProductDto {

    @NotEmpty
    @Valid
    @Size(max = 1024)
    private String productName;

    @NotNull
    @Valid
    @Min(value = 0)
    private int productPrice;

    public String getProductName() {
        return productName;
    }

    public UpdateProductDto setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public UpdateProductDto setProductPrice(int productPrice) {
        this.productPrice = productPrice;
        return this;
    }
}
