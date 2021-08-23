package io.giovannymassuia.cleanarch.infra.database;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface Database {

    record DatabaseProperties(String url, String username, String password) {
    }

    List<Map<String, Object>>

    queryMany(String query, Map<String, Object> parameters);

    Optional<Map<String, Object>> queryOne(String query, Map<String, Object> parameters);

    int execute(String query, Map<String, Object> parameters);

    Optional<Map<String, Object>> insertAndReturnGeneratedKey(String query, Map<String, Object> parameters, String generatedKeyColumnName);

}
