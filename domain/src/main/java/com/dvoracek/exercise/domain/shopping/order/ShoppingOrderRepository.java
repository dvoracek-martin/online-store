package com.dvoracek.exercise.domain.shopping.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

@Repository
public interface ShoppingOrderRepository extends JpaRepository<ShoppingOrder, Long> {
    @Query("select shoppingOrder from ShoppingOrder shoppingOrder " +
            "where shoppingOrder.shoppingOrderTimestamp >= :dateFrom and shoppingOrder.shoppingOrderTimestamp <= :dateTo")
    List<ShoppingOrder> getShoppingOrdersWithinPeriod(@Param("dateFrom") LocalDateTime dateFrom, @Param("dateTo") LocalDateTime dateTo);

}
