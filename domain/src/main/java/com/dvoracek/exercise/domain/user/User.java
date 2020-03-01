package com.dvoracek.exercise.domain.user;

import com.dvoracek.exercise.domain.shopping.order.ShoppingOrder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "T_USER")
public class User {

    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Email
    @Size(max = 255)
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ShoppingOrder> shoppingOrders = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public List<ShoppingOrder> getShoppingOrders() {
        return shoppingOrders;
    }

    public User setShoppingOrders(List<ShoppingOrder> shoppingOrders) {
        this.shoppingOrders = shoppingOrders;
        return this;
    }
}
