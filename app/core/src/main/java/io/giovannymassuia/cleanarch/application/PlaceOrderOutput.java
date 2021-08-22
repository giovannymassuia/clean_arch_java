package io.giovannymassuia.cleanarch.application;

import java.math.BigDecimal;

public class PlaceOrderOutput {

    private final BigDecimal freight;
    private final BigDecimal total;

    public PlaceOrderOutput(BigDecimal freight, BigDecimal total) {
        this.freight = freight;
        this.total = total;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public BigDecimal getTotal() {
        return total;
    }
}
