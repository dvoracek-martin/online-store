package com.dvoracek.exercise.application.shopping.order;

import com.dvoracek.exercise.application.user.UserNotFoundException;
import com.dvoracek.exercise.domain.product.Product;
import com.dvoracek.exercise.domain.product.ProductRepository;
import com.dvoracek.exercise.domain.shopping.order.ShoppingOrder;
import com.dvoracek.exercise.domain.shopping.order.ShoppingOrderRepository;
import com.dvoracek.exercise.domain.user.User;
import com.dvoracek.exercise.domain.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class ShoppingOrderApplicationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingOrderApplicationService.class);

    private final ShoppingOrderRepository shoppingOrderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public ShoppingOrderApplicationService(ShoppingOrderRepository shoppingOrderRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.shoppingOrderRepository = shoppingOrderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public ShoppingOrderDto createShoppingOrder(CreateShoppingOrderDto createShoppingOrderDto) {
        List<Product> products = this.productRepository.findAllById(createShoppingOrderDto.getProductIds());
        User user =  this.userRepository.findById(createShoppingOrderDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException(createShoppingOrderDto.getUserId()));
        ShoppingOrder shoppingOrder = this.shoppingOrderRepository.save(new ShoppingOrder(user, products));
        LOGGER.info("ShoppingOrder created. ID: {}, user e-mail: {}, priceTotal: {}, count of products: {}", shoppingOrder.getId(), shoppingOrder.getUser().getEmail(), shoppingOrder.getPriceTotal(), shoppingOrder.getProducts().size());
        return ShoppingOrderDto.toShoppingOrderDto(shoppingOrder);
    }

    public List<ShoppingOrderDto> getShoppingOrdersWithinPeriod(GetShoppingOrdersWithinPeriodDto getShoppingOrdersWithinPeriodDto) {
        return this.shoppingOrderRepository.getShoppingOrdersWithinPeriod(getShoppingOrdersWithinPeriodDto.getDateFrom(), getShoppingOrdersWithinPeriodDto.getDateTo())
                .stream()
                .map(ShoppingOrderDto::toShoppingOrderDto)
                .collect(Collectors.toList());
    }
}

