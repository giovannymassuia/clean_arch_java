package io.giovannymassuia.cleanarch.core.domain.entity;

import org.apache.commons.lang3.ObjectUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Order {

    private final Cpf cpf;
    private final List<OrderItem> items;
    private Optional<Coupon> coupon;
    private BigDecimal freight;
    private final OrderCode code;
    private final LocalDate issueDate;
    private final Integer sequence;

    public Order(String cpf) {
        this(cpf, null, null);
    }

    public Order(String cpf, LocalDate issueDate, Integer sequence) {
        this.cpf = new Cpf(cpf);
        this.items = new ArrayList<>();
        this.coupon = Optional.empty();
        this.freight = BigDecimal.ZERO;
        this.issueDate = ObjectUtils.defaultIfNull(issueDate, LocalDate.now());
        this.sequence = ObjectUtils.defaultIfNull(sequence, 1);
        this.code = new OrderCode(this.issueDate, this.sequence);
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

    public OrderCode getCode() {
        return code;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public Optional<Coupon> getCoupon() {
        return coupon;
    }

    public Cpf getCpf() {
        return cpf;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public Integer getSequence() {
        return sequence;
    }
}
