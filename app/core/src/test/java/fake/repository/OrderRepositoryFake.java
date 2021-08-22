package fake.repository;

import io.giovannymassuia.cleanarch.core.domain.entity.Order;
import io.giovannymassuia.cleanarch.core.domain.repository.OrderRepository;

public class OrderRepositoryFake implements OrderRepository {

    @Override
    public void save(Order order) {
    }

}
