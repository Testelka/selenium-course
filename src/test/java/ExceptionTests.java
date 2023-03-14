import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class ExceptionTests extends BaseTests {

    @Test
    public void element_not_interactable_exception_example() {
        driver.get("http://localhost:8080/product/how-to-do-chemical-tricks-by-a-anderson/");
        driver.findElement(By.cssSelector("#submit")).click();
    }
}