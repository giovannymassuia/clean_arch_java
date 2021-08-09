import java.math.BigDecimal;

public class ZipCodeCalculatorAPIMemory implements ZipCodeCalculatorAPI {

    @Override
    public BigDecimal calculate(String zipCodeA, String zipCodeB) {
        return BigDecimal.valueOf(1000);
    }
    
}
