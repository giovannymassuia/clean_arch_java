package io.giovannymassuia.cleanarch.core.application;

import java.math.BigDecimal;

public record PlaceOrderOutput(BigDecimal freight, BigDecimal total, String code) {
}
