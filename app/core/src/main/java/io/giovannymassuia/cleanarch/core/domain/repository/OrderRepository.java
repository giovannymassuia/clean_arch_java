package io.giovannymassuia.cleanarch.core.domain.repository;

import io.giovannymassuia.cleanarch.core.domain.entity.Order;

public interface OrderRepository {

    void save(Order order);

}
