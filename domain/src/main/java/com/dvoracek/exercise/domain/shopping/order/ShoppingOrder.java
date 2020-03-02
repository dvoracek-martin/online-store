package com.dvoracek.exercise.domain.shopping.order;

import com.dvoracek.exercise.domain.product.Product;
import com.dvoracek.exercise.domain.user.User;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "T_SHOPPING_ORDER")
public class ShoppingOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @NotNull
    private User user;

    @NotNull
    private LocalDateTime purchasedAt;


    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "t_shopping_order_products", joinColumns = @JoinColumn(name = "shopping_order_id"))
    @Column(name = "products_id")
    private List<PurchasedProduct> products = new ArrayList<>();

    private BigDecimal priceTotal;

    protected ShoppingOrder() {
        // used for reflection
    }

    public ShoppingOrder(User user, List<Product> products) {
        this.user = user;
        this.handleProducts(products);
        this.purchasedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }


    public User getUser() {
        return user;
    }

    public LocalDateTime getPurchasedAt() {
        return purchasedAt;
    }

    public List<PurchasedProduct> getProducts() {
        return products;
    }

    private void handleProducts(List<Product> products) {
        this.priceTotal = products.stream()
                .map(x -> x.getProductPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        this.products = products.stream()
                .map(PurchasedProduct::new)
                .collect(Collectors.toList());
    }

    public BigDecimal getPriceTotal() {
        return priceTotal;
    }
}
