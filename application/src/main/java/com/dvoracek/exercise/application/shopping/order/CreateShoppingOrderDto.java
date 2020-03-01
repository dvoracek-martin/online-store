package com.dvoracek.exercise.application.shopping.order;

import com.sun.istack.NotNull;

import java.util.List;

public class CreateShoppingOrderDto {

    @NotNull
    private List<Long> productIds;


    @NotNull
    private Long userId;

    public List<Long> getProductIds() {
        return productIds;
    }

    public CreateShoppingOrderDto setProductIds(List<Long> productIds) {
        this.productIds = productIds;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public CreateShoppingOrderDto setUserId(Long userId) {
        this.userId = userId;
        return this;
    }
}
