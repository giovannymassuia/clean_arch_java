package io.giovannymassuia.cleanarch.infra.repository.database;

import io.giovannymassuia.cleanarch.core.domain.entity.Coupon;
import io.giovannymassuia.cleanarch.core.domain.entity.Order;
import io.giovannymassuia.cleanarch.core.domain.repository.OrderRepository;
import io.giovannymassuia.cleanarch.infra.database.Database;

import java.util.Map;
import java.util.Optional;

public class OrderRepositoryDatabase implements OrderRepository {

    private final Database database;

    public OrderRepositoryDatabase(Database database) {
        this.database = database;
    }

    @Override
    public void save(Order order) {
        Integer orderId = database.insertAndReturnGeneratedKey(
                        "insert into `order` (coupon_code, code, cpf, issue_date, freight, serial) " +
                                "values (:coupon_code, :code, :cpf, :issue_date, :freight, :serial)",
                        Map.of("coupon_code", order.getCoupon().map(Coupon::getCode),
                                "code", order.getCode().getValue(),
                                "cpf", order.getCpf().getValue(),
                                "issue_date", order.getIssueDate(),
                                "freight", order.getFreight(),
                                "serial", order.getSequence()
                        ),
                        "id")
                .map(result -> (Integer) result.get("id"))
                .orElseThrow(() -> new IllegalStateException("Fail order insert"));

        order.getItems().forEach(item -> {
            database.execute("insert into order_item (id_order, id_item, price, quantity) values (:id_order, :id_item, :price, :quantity)",
                    Map.of("id_order", orderId, "id_item", item.getItemId(), "price", item.getPrice(), "quantity", item.getQuantity()));
        });
    }

    @Override
    public Integer count() {
        Long count = (Long) database.queryOne("select count(*) as count from `order`", Map.of()).get().get("count");
        return count.intValue();
    }

    @Override
    public Optional<Order> get(String code) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void clean() {
        database.execute("delete from `order`", Map.of());
    }
}
