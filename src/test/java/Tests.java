import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Tests {

    @BeforeEach
    public void setup() {}
    @AfterEach
    public void teardown() {}
    @Test
    public void test() {
        Assertions.assertEquals("a", "a");
    }
}
