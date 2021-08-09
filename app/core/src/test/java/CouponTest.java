import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class CouponTest {

    @Test
    @DisplayName("Should check if a coupon is expired")
    public void testCouponExpired() {
        Coupon coupon = new Coupon("VALE20", BigDecimal.valueOf(20), LocalDate.of(2020, 10, 10));
        assertThat(coupon.isExpired()).isTrue();
    }

}