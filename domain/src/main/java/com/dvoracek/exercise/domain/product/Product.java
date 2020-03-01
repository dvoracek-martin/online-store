package  com.dvoracek.exercise.domain.product;

import javax.persistence.*;

@Entity
@Table(name = "T_PRODUCT")
public class Product {

    public Product() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;

    private int productPrice;

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

    public int getProductPrice() {
        return productPrice;
    }

    public Product setProductPrice(int productPrice) {
        this.productPrice = productPrice;
        return this;
    }
}
