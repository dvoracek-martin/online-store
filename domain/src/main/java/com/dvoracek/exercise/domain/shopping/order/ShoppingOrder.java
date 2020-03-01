package com.dvoracek.exercise.domain.shopping.order;

import com.dvoracek.exercise.domain.product.Product;
import com.dvoracek.exercise.domain.user.User;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "T_SHOPPING_ORDER")
public class ShoppingOrder {
    public ShoppingOrder() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne (cascade=CascadeType.ALL)
    @NotNull
    private User user;

    @NotNull
    private LocalDateTime shoppingOrderTimestamp;


    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "t_shopping_order_products", joinColumns = @JoinColumn(name = "shopping_order_id"))
    @Column(name = "products_id")
    private List<Product> products = new ArrayList<>();

    private int priceTotal;

    public Long getId() {
        return id;
    }



    public User getUser() {
        return user;
    }

    public ShoppingOrder setUser(User owner) {
        this.user = owner;
        return this;
    }

    public LocalDateTime getShoppingOrderTimestamp() {
        return shoppingOrderTimestamp;
    }

    public ShoppingOrder setShoppingOrderTimestamp(LocalDateTime orderTimestamp) {
        this.shoppingOrderTimestamp = orderTimestamp;
        return this;
    }

    public List<Product> getProducts() {
        return products;
    }

    public ShoppingOrder setProducts(List<Product> products) {
        this.products = products;
        return this;
    }

    public int getPriceTotal() {
        return priceTotal;
    }

    public ShoppingOrder setPriceTotal(int priceTotal) {
        this.priceTotal = priceTotal;
        return this;
    }
}
