package io.giovannymassuia.cleanarch.core.domain.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Order {

    private final Cpf cpf;
    private final List<OrderItem> items;
    private Optional<Coupon> coupon;
    private BigDecimal freight;

    public Order(String cpf) {
        this.cpf = new Cpf(cpf);
        this.items = new ArrayList<>();
        this.coupon = Optional.empty();
        this.freight = BigDecimal.ZERO;
    }

    public void addItem(String itemId, BigDecimal price, int quantity) {
        this.items.add(new OrderItem(itemId, price, quantity));
    }

    public void addCoupon(Coupon coupon) {
        if (!coupon.isExpired()) {
            this.coupon = Optional.of(coupon);
        }
    }

    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        total = total.add(items.stream().map(OrderItem::getTotal).reduce(BigDecimal.ZERO, BigDecimal::add));
        if (this.coupon.isPresent()) {
            total = total.subtract(total.multiply(this.coupon.get().getPercentage()).divide(BigDecimal.valueOf(100)));
        }
        total = total.add(this.freight);
        return total;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public Order setFreight(BigDecimal freight) {
        this.freight = freight;
        return this;
    }
}
