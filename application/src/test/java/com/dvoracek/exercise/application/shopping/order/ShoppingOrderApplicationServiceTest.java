package com.dvoracek.exercise.application.shopping.order;

import com.dvoracek.exercise.application.TestConfig;
import com.dvoracek.exercise.application.fixtures.ProductFixture;
import com.dvoracek.exercise.domain.product.ProductRepository;
import com.dvoracek.exercise.domain.shopping.order.ShoppingOrderRepository;
import com.dvoracek.exercise.application.fixtures.UserFixture;
import com.dvoracek.exercise.domain.user.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ShoppingOrderApplicationServiceTest {

    @Autowired
    private ShoppingOrderApplicationService shoppingOrderApplicationService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShoppingOrderRepository shoppingOrderRepository;

    @Before
    public void setUp() {
        this.userRepository.deleteAll();
        this.productRepository.deleteAll();
        this.userRepository.save(UserFixture.user1());
        this.userRepository.save(UserFixture.user2());
        this.userRepository.save(UserFixture.user3());
        this.productRepository.save(ProductFixture.product1());
        this.productRepository.save(ProductFixture.product2());
        this.productRepository.save(ProductFixture.product3());
    }

    @Test
    public void testShoppingOrderCreated() {
        // GIVEN
        List<Long> productIds = asList(new Long[]{ProductFixture.id1, ProductFixture.id2, ProductFixture.id3});
        CreateShoppingOrderDto createShoppingOrderDto = new CreateShoppingOrderDto()
                .setUserId(UserFixture.user1().getId())
                .setProductIds(productIds);

        // WHEN
        ShoppingOrderDto shoppingOrderDto = this.shoppingOrderApplicationService.createShoppingOrder(createShoppingOrderDto);

        // THEN
        assertThat(shoppingOrderDto).isNotNull();
        assertThat(shoppingOrderDto.getShoppingOrderTimestamp()).isBefore(LocalDateTime.now());
        assertThat(shoppingOrderDto.getUserEMail()).isEqualTo(UserFixture.email1);
        assertThat(shoppingOrderDto.getProducts().size()).isEqualTo(3);
        assertThat(shoppingOrderDto.getPriceTotal()).isEqualTo(
                ProductFixture.productPrice1
                        + ProductFixture.productPrice2
                        + ProductFixture.productPrice3
        );
    }

    @Test
    public void testGetShoppingOrdersWithinPeriod() {
        // GIVEN
        // stop the clock
        //  Clock constantClock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
        List<Long> productIds1 = asList(new Long[]{ProductFixture.id1, ProductFixture.id2, ProductFixture.id3});
        CreateShoppingOrderDto createShoppingOrderDto1 = new CreateShoppingOrderDto()
                .setUserId(UserFixture.user1().getId())
                .setProductIds(productIds1);
        this.shoppingOrderApplicationService.createShoppingOrder(createShoppingOrderDto1);

        // rewind back in time
        // Clock.offset(constantClock, Duration.ofDays(-10));
        List<Long> productIds2 = asList(new Long[]{ProductFixture.id2, ProductFixture.id2, ProductFixture.id2, ProductFixture.id3});
        CreateShoppingOrderDto createShoppingOrderDto2 = new CreateShoppingOrderDto()
                .setUserId(UserFixture.user1().getId())
                .setProductIds(productIds2);
        this.shoppingOrderApplicationService.createShoppingOrder(createShoppingOrderDto2);

        // go to the future
        // Clock.offset(constantClock, Duration.ofDays(+1));
        List<Long> productIds3 = asList(new Long[]{ProductFixture.id1});
        CreateShoppingOrderDto createShoppingOrderDto3 = new CreateShoppingOrderDto()
                .setUserId(UserFixture.user1().getId())
                .setProductIds(productIds3);
        this.shoppingOrderApplicationService.createShoppingOrder(createShoppingOrderDto3);

        // reset the clock
        // Clock.offset(constantClock, Duration.ZERO);

        // WHEN
        GetShoppingOrdersWithinPeriodDto getShoppingOrdersWithinPeriodDto = new GetShoppingOrdersWithinPeriodDto()
                //  .setDateFrom(LocalDateTime.now(Clock.offset(constantClock, Duration.ofDays(-9))))
                // .setDateTo(LocalDateTime.now(Clock.offset(constantClock, Duration.ofDays(+1))));
                .setDateFrom(LocalDateTime.now().minusDays(9))
                .setDateTo(LocalDateTime.now().plusDays(1));

        assertThat(productRepository.findAll().size()).isEqualTo(3);
        assertThat(shoppingOrderRepository.findAll().size()).isEqualTo(3);
        List<ShoppingOrderDto> shoppingOrderDtos = this.shoppingOrderApplicationService.getShoppingOrdersWithinPeriod(getShoppingOrdersWithinPeriodDto);

        // THEN
        assertThat(shoppingOrderDtos).isNotNull();
        assertThat(shoppingOrderDtos).size().isEqualTo(2);
    }
}
