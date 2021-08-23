package integration;

import io.giovannymassuia.cleanarch.infra.database.JdbiDatabase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class JdbiDatabaseTest {

    @Test
    @DisplayName("Should connect to the database and return one")
    public void connectDatabaseAndReturnOne() {
        JdbiDatabase jdbiDatabase = JdbiDatabase.getInstance();
        Optional<Map<String, Object>> result = jdbiDatabase.queryOne("select * from item where id = :id", Map.of("id", 1));
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().get("description")).isEqualTo("Guitarra");
    }

    @Test
    @DisplayName("Should connect to the database and return many")
    public void connectDatabaseAndReturnMany() {
        JdbiDatabase jdbiDatabase = JdbiDatabase.getInstance();
        List<Map<String, Object>> result = jdbiDatabase.queryMany("select * from item", Map.of());
        assertThat(result.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("Should connect to the database and execute statement")
    public void connectDatabaseAndExecuteStatement() {
        JdbiDatabase jdbiDatabase = JdbiDatabase.getInstance();

        int insertResult = jdbiDatabase.execute("insert into item (id, category, description, price, width, height, length, weight) values (:id, :category, :description, :price, :width, :height, :length, :weight);",
                Map.of("id", 99, "category", "categoryTest", "description", "descTest", "price", 12.34, "width", 1, "height", 1, "length", 1, "weight", 1));
        Optional<Map<String, Object>> resultAfterInsert = jdbiDatabase.queryOne("select * from item where id = :id", Map.of("id", 99));

        int deleteResult = jdbiDatabase.execute("delete from item where id = :id", Map.of("id", 99));
        Optional<Map<String, Object>> resultAfterDelete = jdbiDatabase.queryOne("select * from item where id = :id", Map.of("id", 99));

        assertThat(insertResult).isEqualTo(1);
        assertThat(deleteResult).isEqualTo(1);
        assertThat(resultAfterInsert.isPresent()).isTrue();
        assertThat(resultAfterInsert.get().get("description")).isEqualTo("descTest");
        assertThat(resultAfterDelete.isPresent()).isFalse();
    }

}
