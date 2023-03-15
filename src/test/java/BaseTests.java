import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseTests {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected String baseURL = "http://localhost:8080";

    @BeforeEach
    public void setup() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @AfterEach
    public void quitDriver() {
        driver.quit();
    }
}
