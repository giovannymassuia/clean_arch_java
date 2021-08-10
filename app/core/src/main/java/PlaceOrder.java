import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlaceOrder {

    private final List<Coupon> coupons;
    private final List<Item> items;
    private final List<Order> orders;
    private final ZipCodeCalculatorAPI zipCodeCalculatorAPI;

    public PlaceOrder() {
        this.coupons = List.of(
                new Coupon("VALE20", BigDecimal.valueOf(20), LocalDate.of(2021, 10, 10)),
                new Coupon("VALE20_EXPIRED", BigDecimal.valueOf(20), LocalDate.of(2020, 10, 10)));
        this.items = List.of(
                new Item("1", "Guitarra", BigDecimal.valueOf(1000), 100, 50, 15, 3),
                new Item("2", "Amplificador", BigDecimal.valueOf(5000), 50, 50, 50, 22),
                new Item("3", "Cabo", BigDecimal.valueOf(30), 10, 10, 10, 1)
        );
        this.orders = new ArrayList<>();
        this.zipCodeCalculatorAPI = new ZipCodeCalculatorAPIMemory();
    }

    public PlaceOrderOutput execute(PlaceOrderInput input) {
        Order order = new Order(input.getCpf());
        BigDecimal distance = this.zipCodeCalculatorAPI.calculate(input.getZipcode(), "99.999-99");

        input.getItems().forEach(inputItem -> {
            Optional<Item> optionalItem = this.items.stream().filter(i -> i.getId().equals(inputItem.getItemId())).findAny();
            if (optionalItem.isEmpty()) throw new IllegalStateException("Item not found");
            order.addItem(optionalItem.get().getId(), optionalItem.get().getPrice(), inputItem.getQuantity());
            order.setFreight(order.getFreight()
                    .add(FreightCalculator.calculate(distance, optionalItem.get())
                            .multiply(BigDecimal.valueOf(inputItem.getQuantity()))));
        });

        if (StringUtils.isNotEmpty(input.getCoupon())) {
            Optional<Coupon> couponOptional = this.coupons.stream().filter(coupon -> coupon.getCode().equals(input.getCoupon())).findAny();
            couponOptional.ifPresent(coupon -> order.addCoupon(coupon));
        }

        this.orders.add(order);

        return new PlaceOrderOutput(order.getFreight(), order.getTotal());
    }

}
