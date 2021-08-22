package io.giovannymassuia.cleanarch.core.domain.repository;

import io.giovannymassuia.cleanarch.core.domain.entity.Order;

import java.util.Optional;

public interface OrderRepository {

    void save(Order order);

    Integer count();

    Optional<Order> get(String code);

    void clean();
}
