package com.dvoracek.exercise.application.shopping.order;

import com.dvoracek.exercise.application.product.ProductDto;
import com.dvoracek.exercise.domain.shopping.order.ShoppingOrder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ShoppingOrderDto {

    private Long id;

    private String userEMail;

    private LocalDateTime shoppingOrderTimestamp;

    private List<ProductDto> products;

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

    public List<ProductDto> getProducts() {
        return products;
    }

    public ShoppingOrderDto setProducts(List<ProductDto> products) {
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
                .setShoppingOrderTimestamp(shoppingOrder.getShoppingOrderTimestamp())
                .setProducts(shoppingOrder.getProducts().stream()
                    .map(product -> ProductDto.toProductDto(product)).collect(Collectors.toList()));
    }
}
