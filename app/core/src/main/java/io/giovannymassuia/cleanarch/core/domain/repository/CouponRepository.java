package io.giovannymassuia.cleanarch.core.domain.repository;

import io.giovannymassuia.cleanarch.core.domain.entity.Coupon;

import java.util.Optional;

public interface CouponRepository {

    Optional<Coupon> getByCode(String code);

}
