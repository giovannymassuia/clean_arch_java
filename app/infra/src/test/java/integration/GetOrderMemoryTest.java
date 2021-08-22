package integration;

import io.giovannymassuia.cleanarch.core.application.GetOrder;
import io.giovannymassuia.cleanarch.core.application.GetOrderOutput;
import io.giovannymassuia.cleanarch.core.application.PlaceOrder;
import io.giovannymassuia.cleanarch.core.application.PlaceOrderInput;
import io.giovannymassuia.cleanarch.core.application.PlaceOrderOutput;
import io.giovannymassuia.cleanarch.infra.factory.MemoryRepositoryFactory;
import io.giovannymassuia.cleanarch.infra.gateway.memory.ZipCodeCalculatorAPIMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GetOrderMemoryTest {

    PlaceOrder placeOrder;
    GetOrder getOrder;

    @BeforeEach
    void setUp() {
        MemoryRepositoryFactory repositoryFactoryFake = MemoryRepositoryFactory.getInstance();
        placeOrder = new PlaceOrder(repositoryFactoryFake, new ZipCodeCalculatorAPIMemory());
        getOrder = new GetOrder(repositoryFactoryFake);
    }

    @Test
    @DisplayName("Should return order information")
    public void getOrder() {
        PlaceOrderInput input = new PlaceOrderInput(
                "778.278.412-36",
                "11.111-11",
                List.of(new PlaceOrderInput.Item("1", 2),
                        new PlaceOrderInput.Item("2", 1),
                        new PlaceOrderInput.Item("3", 3)),
                "VALE20");

        PlaceOrderOutput placeOrderOutput = placeOrder.execute(input);
        GetOrderOutput getOrderOutput = getOrder.execute(placeOrderOutput.code());

        assertThat(getOrderOutput.total()).isEqualByComparingTo(BigDecimal.valueOf(5982));
    }

}
