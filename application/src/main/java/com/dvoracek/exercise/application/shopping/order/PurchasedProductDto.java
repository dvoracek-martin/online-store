package com.dvoracek.exercise.application.shopping.order;

import com.dvoracek.exercise.domain.shopping.order.PurchasedProduct;

public class PurchasedProductDto {
    private Long id;

    private int price;

    public Long getId() {
        return id;
    }

    public PurchasedProductDto setId(Long id) {
        this.id = id;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public PurchasedProductDto setPrice(int price) {
        this.price = price;
        return this;
    }

    public static PurchasedProductDto toPurchasedProductDto(PurchasedProduct purchasedProduct) {
        return new PurchasedProductDto()
                .setId(purchasedProduct.getId())
                .setPrice(purchasedProduct.getPrice());
    }
}
