package io.giovannymassuia.cleanarch.core.application;

import io.giovannymassuia.cleanarch.core.domain.entity.Item;
import io.giovannymassuia.cleanarch.core.domain.entity.Order;
import io.giovannymassuia.cleanarch.core.domain.factory.RepositoryFactory;
import io.giovannymassuia.cleanarch.core.domain.gateway.ZipCodeCalculatorAPI;
import io.giovannymassuia.cleanarch.core.domain.repository.CouponRepository;
import io.giovannymassuia.cleanarch.core.domain.repository.ItemRepository;
import io.giovannymassuia.cleanarch.core.domain.repository.OrderRepository;
import io.giovannymassuia.cleanarch.core.domain.service.FreightCalculator;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

public class PlaceOrder {

    private final OrderRepository orderRepository;
    private final CouponRepository couponRepository;
    private final ItemRepository itemRepository;
    private final ZipCodeCalculatorAPI zipCodeCalculatorAPI;

    public PlaceOrder(RepositoryFactory repositoryFactory, ZipCodeCalculatorAPI zipCodeCalculatorAPI) {
        this.orderRepository = repositoryFactory.getOrderRepository();
        this.couponRepository = repositoryFactory.getCouponRepository();
        this.itemRepository = repositoryFactory.getItemRepository();
        this.zipCodeCalculatorAPI = zipCodeCalculatorAPI;
    }

    public PlaceOrderOutput execute(PlaceOrderInput input) {
        Integer sequence = this.orderRepository.count() + 1;
        Order order = new Order(input.cpf(), input.issueDate(), sequence);
        BigDecimal distance = this.zipCodeCalculatorAPI.calculate(input.zipcode(), "99.999-99");

        input.items().forEach(inputItem -> {
            Item item = this.itemRepository.getById(inputItem.itemId())
                    .orElseThrow(() -> new IllegalStateException("Item not found"));
            order.addItem(item.getId(), item.getPrice(), inputItem.quantity());
            order.setFreight(order.getFreight()
                    .add(FreightCalculator.calculate(distance, item)
                            .multiply(BigDecimal.valueOf(inputItem.quantity()))));
        });

        if (StringUtils.isNotEmpty(input.coupon())) {
            this.couponRepository.getByCode(input.coupon()).ifPresent(order::addCoupon);
        }

        this.orderRepository.save(order);

        return new PlaceOrderOutput(order.getFreight(), order.getTotal(), order.getCode().getValue());
    }

}
