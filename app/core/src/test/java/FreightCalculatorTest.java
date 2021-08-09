import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class FreightCalculatorTest {

    @Test
    @DisplayName("Should calculate the freight of the guitar")
    public void calculateGuitarFreight() {
        Item item = new Item("1", "Guitarra", BigDecimal.valueOf(1000), 100, 50, 15, 3);
        double distance = 1000;
        double price = FreightCalculator.calculate(distance, item);
        assertThat(price).isEqualTo(30d);
    }

    @Test
    @DisplayName("Should calculate the freight of the amplifier")
    public void calculateAmplifierFreight() {
        Item item = new Item("1", "Amplificador", BigDecimal.valueOf(5000), 50, 50, 50, 22);
        double distance = 1000;
        double price = FreightCalculator.calculate(distance, item);
        assertThat(price).isEqualTo(220);
    }

    @Test
    @DisplayName("Should calculate the freight of the wire")
    public void calculateWireFreight() {
        Item item = new Item("1", "Amplificador", BigDecimal.valueOf(30), 9, 9, 9, 0.1);
        double distance = 1000;
        double price = FreightCalculator.calculate(distance, item);
        assertThat(price).isEqualTo(10);
    }

}