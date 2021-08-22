package io.giovannymassuia.cleanarch.core.application;

import java.math.BigDecimal;
import java.util.List;

public record GetOrderOutput(String code, BigDecimal freight, BigDecimal total, List<Item> orderItems) {

    public record Item(String itemDescription, BigDecimal price, int quantity) {
    }

}
