package fake.gateway;

import io.giovannymassuia.cleanarch.core.domain.gateway.ZipCodeCalculatorAPI;

import java.math.BigDecimal;

public class ZipCodeCalculatorAPIFake implements ZipCodeCalculatorAPI {

    @Override
    public BigDecimal calculate(String zipCodeA, String zipCodeB) {
        return BigDecimal.valueOf(1000);
    }

}
