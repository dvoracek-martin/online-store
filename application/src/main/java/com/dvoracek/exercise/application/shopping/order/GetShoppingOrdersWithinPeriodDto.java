package com.dvoracek.exercise.application.shopping.order;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

public class GetShoppingOrdersWithinPeriodDto {

    @NotEmpty
    private LocalDateTime dateFrom;

    @NotEmpty
    private LocalDateTime dateTo;

    public LocalDateTime getDateFrom() {
        return dateFrom;
    }

    public GetShoppingOrdersWithinPeriodDto setDateFrom(LocalDateTime dateFrom) {
        this.dateFrom = dateFrom;
        return this;
    }

    public LocalDateTime getDateTo() {
        return dateTo;
    }

    public GetShoppingOrdersWithinPeriodDto setDateTo(LocalDateTime dateTo) {
        this.dateTo = dateTo;
        return this;
    }
}
