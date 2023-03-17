import org.junit.jupiter.api.AfterEach;
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
    private final String browser = "firefox";
    private final boolean isHeadless = true;
    @BeforeEach
    public void setup() {
        if (browser.equals("firefox")) {
            if (isHeadless) {
                driver = new FirefoxDriver(new FirefoxOptions().addArguments("-headless"));
            } else {
                driver = new FirefoxDriver();
            }
        } else if (browser.equals("chrome")) {
            if (isHeadless) {
                driver = new ChromeDriver(new ChromeOptions().addArguments("--headless=new"));
            } else {
                driver = new ChromeDriver();
            }
        } else if (browser.equals("edge")) {
            if (isHeadless) {
                driver = new EdgeDriver(new EdgeOptions().addArguments("--headless=new"));
            } else {
                driver = new EdgeDriver();
            }

        }
    }
    @AfterEach
    public void quitDriver() {
        driver.quit();
    }
}
