package com.dvoracek.exercise.domain.shopping.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ShoppingOrderRepository extends JpaRepository<ShoppingOrder, Long> {
    @Query("select shoppingOrder from ShoppingOrder shoppingOrder " +
            "where shoppingOrder.purchasedAt BETWEEN :dateFrom and :dateTo")
    List<ShoppingOrder> getShoppingOrdersWithinPeriod(@Param("dateFrom") LocalDateTime dateFrom, @Param("dateTo") LocalDateTime dateTo);

}
