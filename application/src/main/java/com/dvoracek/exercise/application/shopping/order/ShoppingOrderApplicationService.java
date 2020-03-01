package com.dvoracek.exercise.application.shopping.order;

import com.dvoracek.exercise.application.product.ProductApplicationService;
import com.dvoracek.exercise.application.user.UserApplicationService;
import com.dvoracek.exercise.domain.product.Product;
import com.dvoracek.exercise.domain.product.ProductRepository;
import com.dvoracek.exercise.domain.shopping.order.ShoppingOrder;
import com.dvoracek.exercise.domain.shopping.order.ShoppingOrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class ShoppingOrderApplicationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingOrderApplicationService.class);

    private final ShoppingOrderRepository shoppingOrderRepository;
    private final ProductApplicationService productApplicationService;
    private final UserApplicationService userApplicationService;
    private final ProductRepository productRepository;

    public ShoppingOrderApplicationService(ShoppingOrderRepository shoppingOrderRepository, ProductApplicationService productApplicationService, UserApplicationService userApplicationService, ProductRepository productRepository) {
        this.shoppingOrderRepository = shoppingOrderRepository;
        this.productApplicationService = productApplicationService;
        this.userApplicationService = userApplicationService;
        this.productRepository = productRepository;
    }

    public ShoppingOrderDto createShoppingOrder(CreateShoppingOrderDto createShoppingOrderDto) {

        List<Product> products = this.productRepository.findAllById(createShoppingOrderDto.getProductIds());
        int priceTotal = products
                .stream()
                .map(Product::getProductPrice)
                .reduce(0, Integer::sum);
        ShoppingOrder shoppingOrder = shoppingOrderRepository.save(new ShoppingOrder()
                .setUser(userApplicationService.findById(createShoppingOrderDto.getUserId()))
                .setShoppingOrderTimestamp(LocalDateTime.now())
                .setProducts(products)
                .setPriceTotal(priceTotal));
        LOGGER.info("ShoppingOrder created. ID:" + shoppingOrder.getId() + ", user e-mail:" + shoppingOrder.getUser().getEmail() + ", priceTotal: " + shoppingOrder.getPriceTotal() + ", count of products: " + shoppingOrder.getProducts().size());
        return ShoppingOrderDto.toShoppingOrderDto(shoppingOrder);
    }

    public List<ShoppingOrderDto> getShoppingOrdersWithinPeriod(GetShoppingOrdersWithinPeriodDto getShoppingOrdersWithinPeriodDto) {
        return shoppingOrderRepository.getShoppingOrdersWithinPeriod(getShoppingOrdersWithinPeriodDto.getDateFrom(), getShoppingOrdersWithinPeriodDto.getDateTo())
                .stream().map(ShoppingOrderDto::toShoppingOrderDto).collect(Collectors.toList());
    }
}

