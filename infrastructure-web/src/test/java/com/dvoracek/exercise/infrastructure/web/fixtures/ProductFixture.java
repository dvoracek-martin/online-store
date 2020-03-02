package com.dvoracek.exercise.infrastructure.web.fixtures;

import com.dvoracek.exercise.domain.product.Product;

import java.math.BigDecimal;

public class ProductFixture {
    public static final Long id1 = 1L;
    public static final String productName1 = "productName1";
    public static final BigDecimal productPrice1 =BigDecimal.valueOf(1);
    public static final Long id2 = 2L;
    public static final String productName2 = "productName2";
    public static final BigDecimal productPrice2 = BigDecimal.valueOf(2.3);
    public static final Long id3 = 3L;
    public static final String productName3 = "productName3";
    public static final BigDecimal productPrice3 = BigDecimal.valueOf(9.5);



    public static Product product1() {
        return new Product()
                .setId(id1)
                .setProductName(productName1)
                .setProductPrice(productPrice1);
    }

    public static Product product2() {
        return new Product()
                .setId(id2)
                .setProductName(productName2)
                .setProductPrice(productPrice2);
    }

    public static Product product3() {
        return new Product()
                .setId(id3)
                .setProductName(productName3)
                .setProductPrice(productPrice3);
    }
}
