import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseTests {
    private WebDriver driver;
    protected ActionBot bot;

    @BeforeEach
    public void setup() {
        driver = new FirefoxDriver();
        String baseURL = "http://localhost:8080";
        bot = new ActionBot(driver, baseURL);
    }

    @AfterEach
    public void quitDriver() {
        driver.quit();
    }
}
