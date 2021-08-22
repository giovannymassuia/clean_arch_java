package fake.repository;

import io.giovannymassuia.cleanarch.core.domain.entity.Coupon;
import io.giovannymassuia.cleanarch.core.domain.repository.CouponRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class CouponRepositoryFake implements CouponRepository {

    @Override
    public Optional<Coupon> getByCode(String code) {
        List<Coupon> coupons = List.of(
                new Coupon("VALE20", BigDecimal.valueOf(20), LocalDate.of(2021, 10, 10)),
                new Coupon("VALE20_EXPIRED", BigDecimal.valueOf(20), LocalDate.of(2020, 10, 10)));

        return coupons.stream().filter(coupon -> coupon.getCode().equals(code)).findAny();
    }

}
