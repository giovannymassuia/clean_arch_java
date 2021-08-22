package io.giovannymassuia.cleanarch.infra.factory;

import io.giovannymassuia.cleanarch.core.domain.factory.RepositoryFactory;
import io.giovannymassuia.cleanarch.core.domain.repository.CouponRepository;
import io.giovannymassuia.cleanarch.core.domain.repository.ItemRepository;
import io.giovannymassuia.cleanarch.core.domain.repository.OrderRepository;
import io.giovannymassuia.cleanarch.infra.repository.memory.CouponRepositoryMemory;
import io.giovannymassuia.cleanarch.infra.repository.memory.ItemRepositoryMemory;
import io.giovannymassuia.cleanarch.infra.repository.memory.OrderRepositoryMemory;

public class MemoryRepositoryFactory implements RepositoryFactory {

    private static MemoryRepositoryFactory instance;

    private final ItemRepositoryMemory itemRepositoryMemory;
    private final CouponRepositoryMemory couponRepositoryMemory;
    private final OrderRepositoryMemory orderRepositoryMemory;

    private MemoryRepositoryFactory() {
        this.itemRepositoryMemory = new ItemRepositoryMemory();
        this.couponRepositoryMemory = new CouponRepositoryMemory();
        this.orderRepositoryMemory = new OrderRepositoryMemory();
    }

    public static MemoryRepositoryFactory getInstance() {
        if (MemoryRepositoryFactory.instance == null) {
            MemoryRepositoryFactory.instance = new MemoryRepositoryFactory();
        }
        return MemoryRepositoryFactory.instance;
    }

    @Override
    public ItemRepository getItemRepository() {
        return this.itemRepositoryMemory;
    }

    @Override
    public CouponRepository getCouponRepository() {
        return this.couponRepositoryMemory;
    }

    @Override
    public OrderRepository getOrderRepository() {
        return this.orderRepositoryMemory;
    }

}
