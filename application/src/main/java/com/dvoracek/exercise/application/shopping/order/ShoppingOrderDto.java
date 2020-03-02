package com.dvoracek.exercise.application.shopping.order;

import com.dvoracek.exercise.domain.shopping.order.ShoppingOrder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ShoppingOrderDto {

    private Long id;

    private String userEMail;

    private LocalDateTime shoppingOrderTimestamp;

    private List<PurchasedProductDto> products;

    private int priceTotal;

    public Long getId() {
        return id;
    }

    public ShoppingOrderDto setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getShoppingOrderTimestamp() {
        return shoppingOrderTimestamp;
    }

    public ShoppingOrderDto setShoppingOrderTimestamp(LocalDateTime shoppingOrderTimestamp) {
        this.shoppingOrderTimestamp = shoppingOrderTimestamp;
        return this;
    }

    public List<PurchasedProductDto> getProducts() {
        return products;
    }

    public ShoppingOrderDto setProducts(List<PurchasedProductDto> products) {
        this.products = products;
        return this;
    }

    public String getUserEMail() {
        return userEMail;
    }

    public ShoppingOrderDto setUserEMail(String userEMail) {
        this.userEMail = userEMail;
        return this;
    }

    public int getPriceTotal() {
        return priceTotal;
    }

    public ShoppingOrderDto setPriceTotal(int priceTotal) {
        this.priceTotal = priceTotal;
        return this;
    }

    public static ShoppingOrderDto toShoppingOrderDto(ShoppingOrder shoppingOrder) {
        return new ShoppingOrderDto()
                .setId(shoppingOrder.getId())
                .setUserEMail(shoppingOrder.getUser().getEmail())
                .setPriceTotal(shoppingOrder.getPriceTotal())
                .setShoppingOrderTimestamp(shoppingOrder.getPurchasedAt())
                .setProducts(toPurchasedProducts(shoppingOrder));
    }

    private static List<PurchasedProductDto> toPurchasedProducts(ShoppingOrder shoppingOrder) {
        return shoppingOrder.getProducts().stream()
                .map(PurchasedProductDto::toPurchasedProductDto).collect(Collectors.toList());
    }
}
