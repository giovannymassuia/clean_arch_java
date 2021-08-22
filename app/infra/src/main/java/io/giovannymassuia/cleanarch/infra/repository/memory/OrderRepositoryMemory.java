package io.giovannymassuia.cleanarch.infra.repository.memory;

import io.giovannymassuia.cleanarch.core.domain.entity.Order;
import io.giovannymassuia.cleanarch.core.domain.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryMemory implements OrderRepository {

    private List<Order> orders;

    public OrderRepositoryMemory() {
        orders = new ArrayList<>();
    }

    @Override
    public void save(Order order) {
        this.orders.add(order);
    }

}
