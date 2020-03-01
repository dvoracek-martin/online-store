package com.dvoracek.exercise.application.product;

import com.dvoracek.exercise.domain.product.Product;
import com.dvoracek.exercise.domain.product.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductApplicationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductApplicationService.class);

    private final ProductRepository productRepository;

    public ProductApplicationService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDto createProduct(CreateProductDto createProductDto) {
        Product product = productRepository.save(new Product()
                .setProductName(createProductDto.getProductName())
                .setProductPrice(createProductDto.getProductPrice()));
        LOGGER.info("Product created. ID: " + product.getId() + ", productName: " + product.getProductName() + ", productPrice: " + product.getProductPrice());
        return ProductDto.toProductDto(product);
    }

    public ProductDto updateProduct(Long id, UpdateProductDto updateProductDto) {
        Product product = findById(id);
        product.setProductName(updateProductDto.getProductName());
        product.setProductPrice(updateProductDto.getProductPrice());
        LOGGER.info("Product updated. ID: " + product.getId() + ", name: " + product.getProductName() + ", price: " + product.getProductPrice());
        return ProductDto.toProductDto(product);
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    public List<ProductDto> getAllProducts() {
        return productRepository.findAllByOrderByIdAsc().stream()
                .map(ProductDto::toProductDto).collect(Collectors.toList());
    }
}
