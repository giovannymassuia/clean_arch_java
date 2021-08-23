package io.giovannymassuia.cleanarch.infra.repository.database;

import io.giovannymassuia.cleanarch.core.domain.entity.Item;
import io.giovannymassuia.cleanarch.core.domain.repository.ItemRepository;
import io.giovannymassuia.cleanarch.infra.database.Database;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

public class ItemRepositoryDatabase implements ItemRepository {

    private final Database database;

    public ItemRepositoryDatabase(Database database) {
        this.database = database;
    }

    @Override
    public Optional<Item> getById(String id) {
        return database.queryOne("select * from item where id = :id", Map.of("id", id))
                .map(result -> new Item(
                        String.valueOf(result.get("id")),
                        (String) result.get("description"),
                        (BigDecimal) result.get("price"),
                        (Integer) result.get("width"),
                        (Integer) result.get("height"),
                        (Integer) result.get("length"),
                        (Integer) result.get("weight")
                ));
    }

}
