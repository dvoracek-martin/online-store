package com.dvoracek.exercise.infrastructure.web.product;

import com.dvoracek.exercise.application.product.CreateProductDto;
import com.dvoracek.exercise.application.product.ProductApplicationService;
import com.dvoracek.exercise.application.product.ProductDto;
import com.dvoracek.exercise.application.product.UpdateProductDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/products")
public class ProductRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductRestController.class);

    private final ProductApplicationService productApplicationService;

    public ProductRestController(ProductApplicationService productApplicationService) {
        this.productApplicationService = productApplicationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto createProduct(@RequestBody @Validated CreateProductDto createProductDto) throws JsonProcessingException {
        LOGGER.debug("Received Http.POST /api/products : {}", new ObjectMapper().writeValueAsString(createProductDto));
        return this.productApplicationService.createProduct(createProductDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDto updateProduct(@PathVariable("id") Long id, @RequestBody @Validated UpdateProductDto updateProductDto) throws JsonProcessingException {
        LOGGER.debug("Received Http.PUT /api/products : {} with id: {}", new ObjectMapper().writeValueAsString(updateProductDto), id);
        return this.productApplicationService.updateProduct(id, updateProductDto);
    }

    @GetMapping("/getAllProducts")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDto> getAllProducts() {
        return this.productApplicationService.getAllProducts();
    }
}
