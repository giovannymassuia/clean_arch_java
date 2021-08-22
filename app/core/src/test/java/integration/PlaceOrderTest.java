package integration;

import fake.gateway.ZipCodeCalculatorAPIFake;
import fake.repository.CouponRepositoryFake;
import fake.repository.ItemRepositoryFake;
import fake.repository.OrderRepositoryFake;
import io.giovannymassuia.cleanarch.core.application.PlaceOrder;
import io.giovannymassuia.cleanarch.core.application.PlaceOrderInput;
import io.giovannymassuia.cleanarch.core.application.PlaceOrderOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PlaceOrderTest {

    PlaceOrder placeOrder;

    @BeforeEach
    void setUp() {
        placeOrder = new PlaceOrder(new OrderRepositoryFake(), new CouponRepositoryFake(), new ItemRepositoryFake(), new ZipCodeCalculatorAPIFake());
    }

    @Test
    @DisplayName("Should place an order")
    public void placeOrder() {
        PlaceOrderInput input = new PlaceOrderInput(
                "778.278.412-36",
                "11.111-11",
                List.of(new PlaceOrderInput.Item("1", 2),
                        new PlaceOrderInput.Item("2", 1),
                        new PlaceOrderInput.Item("3", 3)),
                "VALE20");

        PlaceOrderOutput output = placeOrder.execute(input);
        assertThat(output.total()).isEqualByComparingTo(BigDecimal.valueOf(5982));
    }

    @Test
    @DisplayName("Should place an order with an expired coupon")
    public void placeOrderWithExpiredCoupon() {
        PlaceOrderInput input = new PlaceOrderInput(
                "778.278.412-36",
                "11.111-11",
                List.of(new PlaceOrderInput.Item("1", 2),
                        new PlaceOrderInput.Item("2", 1),
                        new PlaceOrderInput.Item("3", 3)),
                "VALE20_EXPIRED");

        PlaceOrderOutput output = placeOrder.execute(input);
        assertThat(output.total()).isEqualByComparingTo(BigDecimal.valueOf(7400));
    }

    @Test
    @DisplayName("Should place an order with freight calculated")
    public void placeOrderWithFreightCalculated() {
        PlaceOrderInput input = new PlaceOrderInput(
                "778.278.412-36",
                "11.111-11",
                List.of(new PlaceOrderInput.Item("1", 2),
                        new PlaceOrderInput.Item("2", 1),
                        new PlaceOrderInput.Item("3", 3)),
                "VALE20_EXPIRED");

        PlaceOrderOutput output = placeOrder.execute(input);
        assertThat(output.freight()).isEqualByComparingTo(BigDecimal.valueOf(310));
    }

}