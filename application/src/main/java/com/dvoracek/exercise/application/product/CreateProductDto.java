package com.dvoracek.exercise.application.product;

import com.sun.istack.NotNull;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class CreateProductDto {

    @NotEmpty
    @Size(max = 255)
    private String productName;

    @NotNull
    @Min(value = 0)
    private BigDecimal productPrice;

    public String getProductName() {
        return productName;
    }

    public CreateProductDto setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public CreateProductDto setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
        return this;
    }
}
