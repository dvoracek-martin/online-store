package com.dvoracek.exercise.infrastructure.web.product;

import com.dvoracek.exercise.application.product.CreateProductDto;
import com.dvoracek.exercise.application.product.UpdateProductDto;
import com.dvoracek.exercise.domain.product.Product;
import com.dvoracek.exercise.domain.product.ProductRepository;
import com.dvoracek.exercise.infrastructure.web.TestConfig;
import com.dvoracek.exercise.infrastructure.web.fixtures.ProductFixture;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfig.class)
@AutoConfigureMockMvc
public class ProductRestControllerTest {


    private static final String URL = "/api/products";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ProductRepository productRepository;
    @Before
    public void setUp() {
        productRepository.deleteAll();
    }


    @Test
    public void testCreateProduct() throws Exception {
        // GIVEN
        CreateProductDto createProductDto = new CreateProductDto()
                .setProductName(ProductFixture.productName1)
                .setProductPrice(ProductFixture.productPrice1);

        // WHEN
        ResultActions resultActions = this.mockMvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(new ObjectMapper().writeValueAsString(createProductDto)));

        //THEN
        resultActions
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(equalTo(1)))
                .andExpect(jsonPath("$.productName").value(equalTo(createProductDto.getProductName())))
                .andExpect(jsonPath("$.productPrice").value(equalTo(createProductDto.getProductPrice())));
    }

    @Test
    public void testUpdateProduct() throws Exception {
        // GIVEN
        Product product = productRepository.save(new Product()
                .setProductName(ProductFixture.productName1)
                .setProductPrice(ProductFixture.productPrice1)
        );
        UpdateProductDto updateProductDto = new UpdateProductDto()
                .setProductName(ProductFixture.productName2)
                .setProductPrice(ProductFixture.productPrice2);

        // WHEN
        ResultActions resultActions = this.mockMvc.perform(put(URL + "/" + product.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(new ObjectMapper().writeValueAsString(updateProductDto)));

        //THEN
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(equalTo(product.getId().intValue())))
                .andExpect(jsonPath("$.productName").value(equalTo(updateProductDto.getProductName())))
                .andExpect(jsonPath("$.productPrice").value(equalTo(updateProductDto.getProductPrice())));
    }

    @Test
    public void testGetAllProducts() throws Exception {
        // GIVEN
        Product product1 = productRepository.save(new Product()
                .setProductName(ProductFixture.productName1)
                .setProductPrice(ProductFixture.productPrice1)
        );
        Product product2 = productRepository.save(new Product()
                .setProductName(ProductFixture.productName2)
                .setProductPrice(ProductFixture.productPrice2)
        );
        Product product3 = productRepository.save(new Product()
                .setProductName(ProductFixture.productName3)
                .setProductPrice(ProductFixture.productPrice3)
        );

        // WHEN
        ResultActions resultActions = this.mockMvc.perform(get(URL+"/getAllProducts")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"));

        //THEN
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(equalTo(product1.getId().intValue())))
                .andExpect(jsonPath("$.[0].productName").value(equalTo(product1.getProductName())))
                .andExpect(jsonPath("$.[0].productPrice").value(equalTo(product1.getProductPrice())))
                .andExpect(jsonPath("$.[1].id").value(equalTo(product2.getId().intValue())))
                .andExpect(jsonPath("$.[1].productName").value(equalTo(product2.getProductName())))
                .andExpect(jsonPath("$.[1].productPrice").value(equalTo(product2.getProductPrice())))
                .andExpect(jsonPath("$.[2].id").value(equalTo(product3.getId().intValue())))
                .andExpect(jsonPath("$.[2].productName").value(equalTo(product3.getProductName())))
                .andExpect(jsonPath("$.[2].productPrice").value(equalTo(product3.getProductPrice())));
    }
}
