package io.giovannymassuia.cleanarch.infra.database;

import io.giovannymassuia.cleanarch.infra.util.FileUtils;
import org.apache.commons.collections4.MapUtils;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.h2.H2DatabasePlugin;
import org.jdbi.v3.core.statement.Query;
import org.jdbi.v3.core.statement.SqlStatement;
import org.jdbi.v3.core.statement.Update;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

public class JdbiDatabase implements Database {

    private static JdbiDatabase instance;

    private final Jdbi jdbi;

    private JdbiDatabase() {
        var props = getDatabaseProperties();
        this.jdbi = Jdbi.create(props.url(), props.username(), props.password());
        this.jdbi.installPlugin(new H2DatabasePlugin());

        // Init Script
        this.jdbi.useHandle(handle -> handle.createScript(FileUtils.readFileFromResource("database/init.sql")).execute());
    }

    private DatabaseProperties getDatabaseProperties() {
        Properties properties = FileUtils.getProperties("database/database.properties");
        return new DatabaseProperties(properties.getProperty("url"), properties.getProperty("username"), properties.getProperty("password"));
    }

    public static JdbiDatabase getInstance() {
        if (JdbiDatabase.instance == null) {
            JdbiDatabase.instance = new JdbiDatabase();
        }
        return JdbiDatabase.instance;
    }

    @Override
    public List<Map<String, Object>> queryMany(String queryString, Map<String, Object> parameters) {
        return this.jdbi.withHandle(handle -> {
            Query query = handle.createQuery(queryString);
            setParametersQuery(parameters, query);
            return query.mapToMap().stream().toList();
        });
    }

    @Override
    public Optional<Map<String, Object>> queryOne(String queryString, Map<String, Object> parameters) {
        return this.jdbi.withHandle(handle -> {
            Query query = handle.createQuery(queryString);
            setParametersQuery(parameters, query);
            return query.mapToMap().findOne();
        });
    }

    @Override
    public int execute(String queryString, Map<String, Object> parameters) {
        return this.jdbi.withHandle(handle -> {
            Update query = handle.createUpdate(queryString);
            setParametersQuery(parameters, query);
            return query.execute();
        });
    }

    @Override
    public Optional<Map<String, Object>> insertAndReturnGeneratedKey(String queryString, Map<String, Object> parameters, String generatedKeyColumnName) {
        return this.jdbi.withHandle(handle -> {
            Update query = handle.createUpdate(queryString);
            setParametersQuery(parameters, query);
            return query.executeAndReturnGeneratedKeys(generatedKeyColumnName).mapToMap().findOne();
        });
    }

    private void setParametersQuery(Map<String, Object> parameters, SqlStatement query) {
        if (MapUtils.isNotEmpty(parameters)) {
            parameters.forEach((key, value) -> {
                if (value instanceof Optional optionalValue) {
                    if (optionalValue.isPresent()) {
                        query.bind(key, optionalValue.get());
                    } else {
                        query.bind(key, "null");
                    }
                } else {
                    query.bind(key, value);
                }
            });
        }
    }
}
