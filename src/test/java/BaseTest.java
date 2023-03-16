import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseTest {
    WebDriver driver;
    @BeforeEach
    public void setup() {
        driver = new FirefoxDriver();
    }
    @AfterEach
    public void quitDriver() {
        driver.quit();
    }
}
