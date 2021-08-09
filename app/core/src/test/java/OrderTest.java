import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class OrderTest {

    @Test
    @DisplayName("Should not create an order with invalid CPF")
    public void testOrderWithInvalidCpf() {
        String cpf = "111.111.111-11";

        assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(() -> new Order(cpf))
                .withMessage("Invalid CPF");
    }

    @Test
    @DisplayName("Should create an order with 3 items")
    public void createOrderWith3Items() {
        String cpf = "778.278.412-36";
        Order order = new Order(cpf);
        order.addItem("1", BigDecimal.valueOf(1000), 2);
        order.addItem("2", BigDecimal.valueOf(5000), 1);
        order.addItem("3", BigDecimal.valueOf(30), 3);

        BigDecimal total = order.getTotal();
        assertThat(total).isEqualTo(BigDecimal.valueOf(7090));
    }

    @Test
    @DisplayName("Should create an order with a discount coupon")
    public void createOrderWithDiscountCoupon() {
        String cpf = "778.278.412-36";
        Order order = new Order(cpf);
        order.addItem("1", BigDecimal.valueOf(1000), 2);
        order.addItem("2", BigDecimal.valueOf(5000), 1);
        order.addItem("3", BigDecimal.valueOf(30), 3);
        order.addCoupon(new Coupon("VALE20", BigDecimal.valueOf(20), LocalDate.of(2021, 10, 10)));

        BigDecimal total = order.getTotal();
        assertThat(total).isEqualTo(BigDecimal.valueOf(5672));
    }

    @Test
    @DisplayName("Should create an order with an expired discount coupon")
    public void createOrderWithExpiredDiscountCoupon() {
        String cpf = "778.278.412-36";
        Order order = new Order(cpf);
        order.addItem("1", BigDecimal.valueOf(1000), 2);
        order.addItem("2", BigDecimal.valueOf(5000), 1);
        order.addItem("3", BigDecimal.valueOf(30), 3);
        order.addCoupon(new Coupon("VALE20", BigDecimal.valueOf(20), LocalDate.of(2020, 10, 10)));

        BigDecimal total = order.getTotal();
        assertThat(total).isEqualTo(BigDecimal.valueOf(7090));
    }

}