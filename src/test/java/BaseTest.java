import helpers.ConfigurationReader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BaseTest {
    protected WebDriver driver;
    private static ConfigurationReader configuration;
    @BeforeAll
    public static void loadConfiguration() {
        configuration = new ConfigurationReader();
    }
    @BeforeEach
    public void setup() {
        String browser = configuration.getBrowser();
        boolean isHeadless = configuration.isHeadless();

        switch (browser) {
            case "firefox" -> {
                if (isHeadless) {
                    driver = new FirefoxDriver(new FirefoxOptions().addArguments("-headless"));
                } else {
                    driver = new FirefoxDriver();
                }
            }
            case "chrome" -> {
                if (isHeadless) {
                    driver = new ChromeDriver(new ChromeOptions().addArguments("--headless=new"));
                } else {
                    driver = new ChromeDriver();
                }
            }
            case "edge" -> {
                if (isHeadless) {
                    driver = new EdgeDriver(new EdgeOptions().addArguments("--headless=new"));
                } else {
                    driver = new EdgeDriver();
                }
            }
        }
    }
    @AfterEach
    public void quitDriver() {
        driver.quit();
    }
}
