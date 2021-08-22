package io.giovannymassuia.cleanarch.infra.repository.memory;

import io.giovannymassuia.cleanarch.core.domain.entity.Order;
import io.giovannymassuia.cleanarch.core.domain.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderRepositoryMemory implements OrderRepository {

    private List<Order> orders;

    public OrderRepositoryMemory() {
        orders = new ArrayList<>();
    }

    @Override
    public void save(Order order) {
        this.orders.add(order);
    }

    @Override
    public Integer count() {
        return this.orders.size();
    }

    @Override
    public Optional<Order> get(String code) {
        return this.orders.stream().filter(order -> order.getCode().getValue().equals(code)).findAny();
    }

    @Override
    public void clean() {
        this.orders.clear();
    }
}
