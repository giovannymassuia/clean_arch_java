package fake.factory;

import fake.repository.CouponRepositoryFake;
import fake.repository.ItemRepositoryFake;
import fake.repository.OrderRepositoryFake;
import io.giovannymassuia.cleanarch.core.domain.factory.RepositoryFactory;
import io.giovannymassuia.cleanarch.core.domain.repository.CouponRepository;
import io.giovannymassuia.cleanarch.core.domain.repository.ItemRepository;
import io.giovannymassuia.cleanarch.core.domain.repository.OrderRepository;

public class RepositoryFactoryFake implements RepositoryFactory {

    private static RepositoryFactoryFake instance;

    private final ItemRepositoryFake itemRepositoryFake;
    private final CouponRepositoryFake couponRepositoryFake;
    private final OrderRepositoryFake orderRepositoryFake;

    private RepositoryFactoryFake() {
        this.itemRepositoryFake = new ItemRepositoryFake();
        this.couponRepositoryFake = new CouponRepositoryFake();
        this.orderRepositoryFake = new OrderRepositoryFake();
    }

    public static RepositoryFactoryFake getInstance() {
        if (RepositoryFactoryFake.instance == null) {
            RepositoryFactoryFake.instance = new RepositoryFactoryFake();
        }
        return RepositoryFactoryFake.instance;
    }

    @Override
    public ItemRepository getItemRepository() {
        return this.itemRepositoryFake;
    }

    @Override
    public CouponRepository getCouponRepository() {
        return this.couponRepositoryFake;
    }

    @Override
    public OrderRepository getOrderRepository() {
        return this.orderRepositoryFake;
    }
}
