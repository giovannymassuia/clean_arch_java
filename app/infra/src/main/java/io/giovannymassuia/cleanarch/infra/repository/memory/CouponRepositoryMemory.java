package io.giovannymassuia.cleanarch.infra.repository.memory;

import io.giovannymassuia.cleanarch.core.domain.entity.Coupon;
import io.giovannymassuia.cleanarch.core.domain.repository.CouponRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class CouponRepositoryMemory implements CouponRepository {

    private List<Coupon> coupons;

    public CouponRepositoryMemory() {
        this.coupons = List.of(
                new Coupon("VALE20", BigDecimal.valueOf(20), LocalDate.of(2021, 10, 10)),
                new Coupon("VALE20_EXPIRED", BigDecimal.valueOf(20), LocalDate.of(2020, 10, 10)));
    }

    @Override
    public Optional<Coupon> getByCode(String code) {
        return this.coupons.stream().filter(coupon -> coupon.getCode().equals(code)).findAny();
    }

}
