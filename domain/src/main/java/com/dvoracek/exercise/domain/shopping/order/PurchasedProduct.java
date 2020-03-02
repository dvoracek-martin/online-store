package com.dvoracek.exercise.domain.shopping.order;

import com.dvoracek.exercise.domain.product.Product;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@Access(AccessType.FIELD)
public class PurchasedProduct {
    private Long id;
    private int price;

    protected PurchasedProduct() {
        // for reflection
    }

    public Long getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public PurchasedProduct(Product product) {
        this.id = product.getId();
        this.price = product.getProductPrice();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchasedProduct that = (PurchasedProduct) o;
        return price == that.price &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price);
    }
}
