package io.giovannymassuia.cleanarch.fakes;

import io.giovannymassuia.cleanarch.domain.gateway.ZipCodeCalculatorAPI;

import java.math.BigDecimal;

public class ZipCodeCalculatorAPIMemory implements ZipCodeCalculatorAPI {

    @Override
    public BigDecimal calculate(String zipCodeA, String zipCodeB) {
        return BigDecimal.valueOf(1000);
    }

}
