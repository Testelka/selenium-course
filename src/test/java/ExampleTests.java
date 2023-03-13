import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

public class ExampleTests extends BaseTests{
    @Test
    public void shadow_DOM_example_chrome_edge_test() {
        driver.get("https://fakestore.testelka.pl/shadow-dom-w-selenium/");

        WebElement shadowHost = driver.findElement(By.cssSelector("#host"));
        SearchContext shadowRoot = shadowHost.getShadowRoot();
        shadowRoot.findElement(By.cssSelector("#input")).sendKeys("Halko");
        shadowRoot.findElement(By.cssSelector("button")).click();

        String displayedText = shadowRoot.findElement(By.cssSelector("#output")).getText();

        Assertions.assertEquals("Wprowadzony tekst: Halko", displayedText,
                "Displayed text is not what was expected.");
    }
}
