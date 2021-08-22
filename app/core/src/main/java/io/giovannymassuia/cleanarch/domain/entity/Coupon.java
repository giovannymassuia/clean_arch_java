package io.giovannymassuia.cleanarch.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Coupon {

    private final String code;
    private final BigDecimal percentage;
    private final LocalDate expireDate;

    public Coupon(String code, BigDecimal percentage, LocalDate expireDate) {
        this.code = code;
        this.percentage = percentage;
        this.expireDate = expireDate;
    }

    public boolean isExpired() {
        LocalDate today = LocalDate.now();
        return this.expireDate.isBefore(today);
    }

    public String getCode() {
        return code;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }
}
