import helpers.BrowserFactory;
import helpers.ConfigurationReader;
import helpers.NoSuchBrowserException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

public class BaseTest {
    protected WebDriver driver;
    private static ConfigurationReader configuration;
    @BeforeAll
    public static void loadConfiguration() {
        configuration = new ConfigurationReader();
    }
    @BeforeEach
    public void setup() {
        BrowserFactory browser = new BrowserFactory();
        try {
            driver = browser.createInstance(configuration);
        } catch (NoSuchBrowserException e) {
            throw new RuntimeException(e);
        }
    }
    @AfterEach
    public void quitDriver() {
        driver.quit();
    }
}
