import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ActionBot {
    private final WebDriver driver;
    private final String baseURL;
    public ActionBot(WebDriver driver, String baseURL) {
        this.driver = driver;
        this.baseURL = baseURL;
    }

    public void go(String slug) {
        driver.get(baseURL + slug);
    }

    public int getNumberOfElements(String cssSelector) {
        return driver.findElements(By.cssSelector(cssSelector)).size();
    }
    public void click(String cssSelector) {
        driver.findElement(By.cssSelector(cssSelector)).click();
    }

    public void type(String cssSelector, String text) {
        WebElement field = driver.findElement(By.cssSelector(cssSelector));
        field.clear();
        field.sendKeys(text);
    }

    public void waitToDisappear(String cssSelector, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(cssSelector), 0));
    }

    public String getText(String cssSelector) {
        return driver.findElement(By.cssSelector(cssSelector)).getText();
    }
}
