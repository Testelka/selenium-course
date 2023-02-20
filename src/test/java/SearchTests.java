import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchTests extends BaseTests {

    @Test
    public void search_field_should_have_placeholder_text() {
        driver.get("http://localhost:8080/");
        WebElement searchField = driver.findElement(By.id("wc-block-search__input-1"));
        Assertions.assertEquals("Search products…",
                searchField.getDomAttribute("placeholder"),
                "Placeholder for search field is not correct.");
    }
}
