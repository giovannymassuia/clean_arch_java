package io.giovannymassuia.cleanarch.infra.database;

import java.util.List;
import java.util.Optional;

public interface Database {

    List<Object> many(String query, Object[] parameters);

    Optional<Object> one(String query, Object[] parameters);

}
