package io.giovannymassuia.cleanarch.core.application;

import io.giovannymassuia.cleanarch.core.domain.entity.Item;
import io.giovannymassuia.cleanarch.core.domain.entity.Order;
import io.giovannymassuia.cleanarch.core.domain.entity.OrderItem;
import io.giovannymassuia.cleanarch.core.domain.factory.RepositoryFactory;
import io.giovannymassuia.cleanarch.core.domain.repository.ItemRepository;
import io.giovannymassuia.cleanarch.core.domain.repository.OrderRepository;

import java.util.List;

public class GetOrder {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    public GetOrder(RepositoryFactory repositoryFactory) {
        this.orderRepository = repositoryFactory.getOrderRepository();
        this.itemRepository = repositoryFactory.getItemRepository();
    }

    public GetOrderOutput execute(String code) {
        Order order = this.orderRepository.get(code).orElseThrow(() -> new IllegalStateException("Order not found"));
        List<GetOrderOutput.Item> outputItems = order.getItems().stream().map(this::buildItem).toList();
        return new GetOrderOutput(order.getCode().getValue(), order.getFreight(), order.getTotal(), outputItems);
    }

    private GetOrderOutput.Item buildItem(OrderItem orderItem) {
        Item item = this.itemRepository.getById(orderItem.getItemId()).orElseThrow(() -> new IllegalStateException("Item not found"));
        return new GetOrderOutput.Item(item.getDescription(), item.getPrice(), orderItem.getQuantity());
    }
}
