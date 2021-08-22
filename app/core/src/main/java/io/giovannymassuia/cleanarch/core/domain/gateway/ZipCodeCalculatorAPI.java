package io.giovannymassuia.cleanarch.core.domain.gateway;

import java.math.BigDecimal;

public interface ZipCodeCalculatorAPI {
    BigDecimal calculate(String zipCodeA, String zipCodeB);
}
