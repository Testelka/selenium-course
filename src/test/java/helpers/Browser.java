package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public final class Browser {
    public final WebDriver driver;
    public final String baseURL;
    public final WebDriverWait wait;

    public Browser(WebDriver driver, ConfigurationReader configuration) {
        this.driver = driver;
        this.baseURL = configuration.getBaseUrl();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(configuration.getWaitInSeconds()));
    }
}
