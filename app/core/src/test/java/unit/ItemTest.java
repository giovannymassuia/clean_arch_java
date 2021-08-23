package unit;

import io.giovannymassuia.cleanarch.core.domain.entity.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class ItemTest {

    @Test
    @DisplayName("Should calculate the volume of the Item")
    public void testCalculateItemVolume1() {

        Item item = new Item("1", "Amplificador", BigDecimal.valueOf(5000), 50, 50, 50, 22);
        double volume = item.getVolume();
        assertThat(volume).isEqualTo(0.125d);
    }

    @Test
    @DisplayName("Should calculate the density of the Item")
    public void testCalculateItemDensity() {
        Item item = new Item("1", "Amplificador", BigDecimal.valueOf(5000), 50, 50, 50, 22);
        double density = item.getDensity();
        assertThat(density).isEqualTo(176d);
    }

}