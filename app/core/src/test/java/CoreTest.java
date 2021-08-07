import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CoreTest {

    @Test
    @DisplayName("Dummy test")
    public void dummy() {
        assertThat(List.of()).isEmpty();
    }

}
