import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PlaceOrderTest {

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

        PlaceOrder placeOrder = new PlaceOrder();
        PlaceOrderOutput output = placeOrder.execute(input);
        assertThat(output.getTotal()).isEqualByComparingTo(BigDecimal.valueOf(5982));
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

        PlaceOrder placeOrder = new PlaceOrder();
        PlaceOrderOutput output = placeOrder.execute(input);
        assertThat(output.getTotal()).isEqualByComparingTo(BigDecimal.valueOf(7400));
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

        PlaceOrder placeOrder = new PlaceOrder();
        PlaceOrderOutput output = placeOrder.execute(input);
        assertThat(output.getFreight()).isEqualByComparingTo(BigDecimal.valueOf(310));
    }

}