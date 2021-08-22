package io.giovannymassuia.cleanarch.core.domain.repository;

import io.giovannymassuia.cleanarch.core.domain.entity.Item;

import java.util.Optional;

public interface ItemRepository {

    Optional<Item> getById(String id);

}
