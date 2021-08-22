package unit;

import io.giovannymassuia.cleanarch.core.domain.entity.Coupon;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

class CouponTest {

    @Test
    @DisplayName("Should check if a coupon is expired")
    public void testCouponExpired() {
        Coupon coupon = new Coupon("VALE20", BigDecimal.valueOf(20), LocalDate.of(2020, 10, 10));
        Assertions.assertThat(coupon.isExpired()).isTrue();
    }

}