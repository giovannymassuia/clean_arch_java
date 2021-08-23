package io.giovannymassuia.cleanarch.infra.factory;

import io.giovannymassuia.cleanarch.core.domain.factory.RepositoryFactory;
import io.giovannymassuia.cleanarch.core.domain.repository.CouponRepository;
import io.giovannymassuia.cleanarch.core.domain.repository.ItemRepository;
import io.giovannymassuia.cleanarch.core.domain.repository.OrderRepository;
import io.giovannymassuia.cleanarch.infra.database.JdbiDatabase;
import io.giovannymassuia.cleanarch.infra.repository.database.CouponRepositoryDatabase;
import io.giovannymassuia.cleanarch.infra.repository.database.ItemRepositoryDatabase;
import io.giovannymassuia.cleanarch.infra.repository.database.OrderRepositoryDatabase;

public class DatabaseRepositoryFactory implements RepositoryFactory {

    private static DatabaseRepositoryFactory instance;

    private final ItemRepositoryDatabase itemRepositoryDatabase;
    private final CouponRepositoryDatabase couponRepositoryDatabase;
    private final OrderRepositoryDatabase orderRepositoryDatabase;

    private DatabaseRepositoryFactory() {
        JdbiDatabase jdbiDatabase = JdbiDatabase.getInstance();

        this.itemRepositoryDatabase = new ItemRepositoryDatabase(jdbiDatabase);
        this.couponRepositoryDatabase = new CouponRepositoryDatabase(jdbiDatabase);
        this.orderRepositoryDatabase = new OrderRepositoryDatabase(jdbiDatabase);
    }

    public static DatabaseRepositoryFactory getInstance() {
        if (DatabaseRepositoryFactory.instance == null) {
            DatabaseRepositoryFactory.instance = new DatabaseRepositoryFactory();
        }
        return DatabaseRepositoryFactory.instance;
    }

    @Override
    public ItemRepository getItemRepository() {
        return this.itemRepositoryDatabase;
    }

    @Override
    public CouponRepository getCouponRepository() {
        return this.couponRepositoryDatabase;
    }

    @Override
    public OrderRepository getOrderRepository() {
        return this.orderRepositoryDatabase;
    }

}
