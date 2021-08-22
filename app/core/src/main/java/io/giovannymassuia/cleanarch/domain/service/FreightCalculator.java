package io.giovannymassuia.cleanarch.domain.service;

import io.giovannymassuia.cleanarch.domain.entity.Item;

import java.math.BigDecimal;

public class FreightCalculator {

    public static BigDecimal calculate(BigDecimal distance, Item item) {
        BigDecimal volume = BigDecimal.valueOf(item.getVolume());
        BigDecimal density = BigDecimal.valueOf(item.getDensity());
        BigDecimal price = distance.multiply(volume).multiply(density).divide(BigDecimal.valueOf(100));
        return (price.compareTo(BigDecimal.TEN) == 1) ? price : BigDecimal.TEN;
    }

}
