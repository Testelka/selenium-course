import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ExampleTests extends BaseTests{
    @Test
    public void shadow_DOM_example_firefox_test() {
        driver.get("https://fakestore.testelka.pl/shadow-dom-w-selenium/");

        WebElement shadowHost = driver.findElement(By.cssSelector("#host"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        List<?> elements = (List<?>) js.executeScript(
                "return arguments[0].shadowRoot.children", shadowHost);
        WebElement shadowRoot = (WebElement) elements.get(0);

        shadowRoot.findElement(By.cssSelector("#input")).sendKeys("Halko");
        shadowRoot.findElement(By.cssSelector("button")).click();
        String displayedText = shadowRoot.findElement(By.cssSelector("#output")).getText();

        Assertions.assertEquals("Wprowadzony tekst: Halko", displayedText,
                "Displayed text is not what was expected.");
    }
}
