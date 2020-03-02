package com.dvoracek.exercise.infrastructure.web.shopping.order;

import com.dvoracek.exercise.application.shopping.order.CreateShoppingOrderDto;
import com.dvoracek.exercise.application.shopping.order.ShoppingOrderApplicationService;
import com.dvoracek.exercise.application.user.UserNotFoundException;
import com.dvoracek.exercise.domain.product.ProductRepository;
import com.dvoracek.exercise.domain.shopping.order.ShoppingOrderRepository;
import com.dvoracek.exercise.domain.user.User;
import com.dvoracek.exercise.domain.user.UserRepository;
import com.dvoracek.exercise.infrastructure.web.TestConfig;
import com.dvoracek.exercise.infrastructure.web.fixtures.ProductFixture;
import com.dvoracek.exercise.infrastructure.web.fixtures.UserFixture;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.util.Arrays.asList;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfig.class)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ShoppingOrderRestControllerTest {

    private static final String URL = "/api/shoppingOrders";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ShoppingOrderRepository shoppingOrderRepository;


    @Autowired
    ShoppingOrderApplicationService shoppingOrderApplicationService;

    @Before
    public void setUp() {
        this.productRepository.deleteAll();
        this.shoppingOrderRepository.deleteAll();
        this.userRepository.deleteAll();
        this.userRepository.save(UserFixture.user1());
        this.userRepository.save(UserFixture.user2());
        this.userRepository.save(UserFixture.user3());
        this.productRepository.save(ProductFixture.product1());
        this.productRepository.save(ProductFixture.product2());
        this.productRepository.save(ProductFixture.product3());
    }

    @Test
    public void testCreateShoppingOrder() throws Exception {
        // GIVEN
        List<Long> productIds = asList(new Long[]{ProductFixture.id1, ProductFixture.id2, ProductFixture.id3});
        User user = userRepository.findById(UserFixture.id1).orElseThrow(() -> new UserNotFoundException(UserFixture.id1));
        CreateShoppingOrderDto createShoppingOrderDto = new CreateShoppingOrderDto()
                .setUserId(user.getId())
                .setProductIds(productIds);

        // WHEN
        ResultActions resultActions = this.mockMvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(new ObjectMapper().writeValueAsString(createShoppingOrderDto)));

        //THEN
        resultActions
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(equalTo(1)))
                .andExpect(jsonPath("$.userEMail").value(equalTo(user.getEmail())))
                .andExpect(jsonPath("$.purchasedAt").isNotEmpty())
                .andExpect(jsonPath("$.products[0].id").value(equalTo(createShoppingOrderDto.getProductIds().get(0).intValue())))
                .andExpect(jsonPath("$.products[1].id").value(equalTo(createShoppingOrderDto.getProductIds().get(1).intValue())))
                .andExpect(jsonPath("$.products[2].id").value(equalTo(createShoppingOrderDto.getProductIds().get(2).intValue())));
    }

    @Test
    public void testGetShoppingOrdersWithinPeriod() throws Exception {
        // GIVEN
        List<Long> productIds1 = asList(new Long[]{ProductFixture.id1, ProductFixture.id2, ProductFixture.id3});
        User user1 = userRepository.findById(UserFixture.id1).orElseThrow(() -> new UserNotFoundException(UserFixture.id1));
        CreateShoppingOrderDto createShoppingOrderDto1 = new CreateShoppingOrderDto()
                .setUserId(user1.getId())
                .setProductIds(productIds1);
        shoppingOrderApplicationService.createShoppingOrder(createShoppingOrderDto1);
        await().timeout(1000, TimeUnit.MILLISECONDS);

        List<Long> productIds2 = asList(new Long[]{ProductFixture.id1, ProductFixture.id3});
        User user2 = userRepository.findById(UserFixture.id1).orElseThrow(() -> new UserNotFoundException(UserFixture.id1));
        CreateShoppingOrderDto createShoppingOrderDto2 = new CreateShoppingOrderDto()
                .setUserId(user2.getId())
                .setProductIds(productIds2);
        shoppingOrderApplicationService.createShoppingOrder(createShoppingOrderDto2);
        await().timeout(1000, TimeUnit.MILLISECONDS);

        List<Long> productIds3 = asList(new Long[]{ProductFixture.id3});
        User user3 = userRepository.findById(UserFixture.id1).orElseThrow(() -> new UserNotFoundException(UserFixture.id1));
        CreateShoppingOrderDto createShoppingOrderDto3 = new CreateShoppingOrderDto()
                .setUserId(user3.getId())
                .setProductIds(productIds3);
        shoppingOrderApplicationService.createShoppingOrder(createShoppingOrderDto3);

        // WHEN
        ResultActions resultActions = this.mockMvc.perform(get(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("dateFrom","2019-01-01")
                .param("dateTo", "2020-12-12")
        );

        //THEN
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].userEMail").value(equalTo(user1.getEmail())))
                .andExpect(jsonPath("$.[1].userEMail").value(equalTo(user2.getEmail())))
                .andExpect(jsonPath("$.[2].userEMail").value(equalTo(user3.getEmail())));
    }
}
