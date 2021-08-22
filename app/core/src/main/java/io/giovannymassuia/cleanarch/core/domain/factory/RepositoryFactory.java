package io.giovannymassuia.cleanarch.core.domain.factory;

import io.giovannymassuia.cleanarch.core.domain.repository.CouponRepository;
import io.giovannymassuia.cleanarch.core.domain.repository.ItemRepository;
import io.giovannymassuia.cleanarch.core.domain.repository.OrderRepository;

public interface RepositoryFactory {

    ItemRepository getItemRepository();

    CouponRepository getCouponRepository();

    OrderRepository getOrderRepository();

}
