package com.dvoracek.exercise.infrastructure.web.shopping.order;

import com.dvoracek.exercise.application.shopping.order.CreateShoppingOrderDto;
import com.dvoracek.exercise.application.shopping.order.GetShoppingOrdersWithinPeriodDto;
import com.dvoracek.exercise.application.shopping.order.ShoppingOrderApplicationService;
import com.dvoracek.exercise.application.shopping.order.ShoppingOrderDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/api/shoppingOrders")
public class ShoppingOrderRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingOrderRestController.class);

    private final ShoppingOrderApplicationService shoppingOrderApplicationService;

    public ShoppingOrderRestController(ShoppingOrderApplicationService shoppingOrderApplicationService) {
        this.shoppingOrderApplicationService = shoppingOrderApplicationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ShoppingOrderDto createOrder(@RequestBody @Validated CreateShoppingOrderDto createShoppingOrderDto) throws JsonProcessingException {
        LOGGER.debug("Received Http.POST /api/orders : " + new ObjectMapper().writeValueAsString(createShoppingOrderDto));
        return this.shoppingOrderApplicationService.createShoppingOrder(createShoppingOrderDto);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<ShoppingOrderDto> getShoppingOrdersWithinPeriod(@RequestParam("dateFrom") String dateFrom, @RequestParam("dateTo") String dateTo) {
        LOGGER.debug("Received Http.GET /api/orders with requestParams dateFrom " + dateFrom + " & dateTo:" + dateTo);
        GetShoppingOrdersWithinPeriodDto getShoppingOrdersWithinPeriodDto = new GetShoppingOrdersWithinPeriodDto()
                .setDateFrom(LocalDate.parse(dateFrom).atTime(0, 0))
                .setDateTo(LocalDate.parse(dateTo).atTime(0, 0));
        return this.shoppingOrderApplicationService.getShoppingOrdersWithinPeriod(getShoppingOrdersWithinPeriodDto);
    }
}
