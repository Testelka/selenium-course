package pageobjects;

import helpers.ConfigurationReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    private By loadingIcon = By.cssSelector(".blockUI");
    protected final WebDriver driver;
    protected final String baseURL;
    protected BasePage(WebDriver driver) {
        this.driver = driver;
        baseURL = new ConfigurationReader().getBaseUrl();
    }

    protected void waitForLoadingIconDisappear() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.numberOfElementsToBe(loadingIcon, 0));
    }
}
