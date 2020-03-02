package com.dvoracek.exercise.application.product;

import com.dvoracek.exercise.domain.product.Product;

import java.math.BigDecimal;

public class ProductDto {
    private Long id;

    private String productName;

    private BigDecimal productPrice;

    public Long getId() {
        return id;
    }

    public ProductDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getProductName() {
        return productName;
    }

    public ProductDto setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public ProductDto setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
        return this;
    }

    public static ProductDto toProductDto(Product product) {
        return new ProductDto()
                .setId(product.getId())
                .setProductName(product.getProductName())
                .setProductPrice(product.getProductPrice());
    }


    public static Product fromProductDto(ProductDto productDto){
        return new Product()
                .setId(productDto.getId())
                .setProductName(productDto.getProductName())
                .setProductPrice(productDto.getProductPrice());
    }
}
