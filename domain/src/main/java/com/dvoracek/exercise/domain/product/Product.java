package com.dvoracek.exercise.domain.product;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Objects;

@Entity
@Table(name = "T_PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;

    private BigDecimal productPrice;

    public Product() {
        // for reflection
    }

    public Product setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public Product setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public Product setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice.round(new MathContext(2));
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
