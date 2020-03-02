package com.dvoracek.exercise.application.shopping.order;

import com.dvoracek.exercise.domain.shopping.order.PurchasedProduct;

import java.math.BigDecimal;

public class PurchasedProductDto {

    private Long id;

    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public PurchasedProductDto setId(Long id) {
        this.id = id;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public PurchasedProductDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public static PurchasedProductDto toPurchasedProductDto(PurchasedProduct purchasedProduct) {
        return new PurchasedProductDto()
                .setId(purchasedProduct.getId())
                .setPrice(purchasedProduct.getPrice());
    }
}
