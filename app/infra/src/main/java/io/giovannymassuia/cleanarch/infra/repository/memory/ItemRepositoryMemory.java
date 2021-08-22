package io.giovannymassuia.cleanarch.infra.repository.memory;

import io.giovannymassuia.cleanarch.core.domain.entity.Item;
import io.giovannymassuia.cleanarch.core.domain.repository.ItemRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class ItemRepositoryMemory implements ItemRepository {

    private List<Item> items;

    public ItemRepositoryMemory() {
        items = List.of(
                new Item("1", "Guitarra", BigDecimal.valueOf(1000), 100, 50, 15, 3),
                new Item("2", "Amplificador", BigDecimal.valueOf(5000), 50, 50, 50, 22),
                new Item("3", "Cabo", BigDecimal.valueOf(30), 10, 10, 10, 1)
        );
    }

    @Override
    public Optional<Item> getById(String id) {
        return this.items.stream().filter(i -> i.getId().equals(id)).findAny();
    }

}
