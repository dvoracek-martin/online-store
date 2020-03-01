package com.dvoracek.exercise.application.product;

import com.dvoracek.exercise.application.TestConfig;
import com.dvoracek.exercise.application.fixtures.ProductFixture;
import com.dvoracek.exercise.domain.product.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfig.class)
public class ProductApplicationServiceTest {

    @Autowired
    private ProductApplicationService productApplicationService;

    @Autowired
    ProductRepository productRepository;

    @Before
    public void setUp() {
        productRepository.deleteAll();
    }

    @Test
    public void testProductCreated() {
        // GIVEN
        CreateProductDto createProductDto = new CreateProductDto().setProductName(ProductFixture.productName1).setProductPrice(ProductFixture.productPrice1);

        // WHEN
        ProductDto productDto = this.productApplicationService.createProduct(createProductDto);

        // THEN
        assertThat(productDto).isNotNull();
        assertThat(productDto.getProductName()).isEqualTo(createProductDto.getProductName());
        assertThat(productDto.getProductPrice()).isEqualTo(createProductDto.getProductPrice());
    }

    @Test
    public void testProductUpdated() {
        // GIVEN
        CreateProductDto createProductDto = new CreateProductDto().setProductName(ProductFixture.productName1).setProductPrice(ProductFixture.productPrice1);
        ProductDto productDto = productApplicationService.createProduct(createProductDto);
        UpdateProductDto updateProductDto = new UpdateProductDto().setProductName(ProductFixture.productName2).setProductPrice(ProductFixture.productPrice2);

        // WHEN
        ProductDto productDtoAfterUpdate = this.productApplicationService.updateProduct(productDto.getId(), updateProductDto);

        // THEN
        assertThat(productDtoAfterUpdate).isNotNull();
        assertThat(productDtoAfterUpdate.getId()).isEqualTo(productDto.getId());
        assertThat(productDtoAfterUpdate.getProductName()).isEqualTo(updateProductDto.getProductName());
        assertThat(productDtoAfterUpdate.getProductPrice()).isEqualTo(updateProductDto.getProductPrice());
    }

    @Test
    public void testProductGetAll() {
        // GIVEN
        CreateProductDto createProductDto1 = new CreateProductDto().setProductName(ProductFixture.productName1).setProductPrice(ProductFixture.productPrice1);
        this.productApplicationService.createProduct(createProductDto1);
        CreateProductDto createProductDto2 = new CreateProductDto().setProductName(ProductFixture.productName2).setProductPrice(ProductFixture.productPrice2);
        this.productApplicationService.createProduct(createProductDto2);
        CreateProductDto createProductDto3 = new CreateProductDto().setProductName(ProductFixture.productName3).setProductPrice(ProductFixture.productPrice3);
        this.productApplicationService.createProduct(createProductDto3);

        // WHEN
        List<ProductDto> allProducts = this.productApplicationService.getAllProducts();

        // THEN
        assertThat(allProducts).isNotNull();
        assertThat(allProducts).size().isEqualTo(3);
        assertThat(allProducts.get(0).getProductName()).isEqualTo(createProductDto1.getProductName());
        assertThat(allProducts.get(0).getProductPrice()).isEqualTo(createProductDto1.getProductPrice());
        assertThat(allProducts.get(1).getProductName()).isEqualTo(createProductDto2.getProductName());
        assertThat(allProducts.get(1).getProductPrice()).isEqualTo(createProductDto2.getProductPrice());
        assertThat(allProducts.get(2).getProductName()).isEqualTo(createProductDto3.getProductName());
        assertThat(allProducts.get(2).getProductPrice()).isEqualTo(createProductDto3.getProductPrice());
    }
}
