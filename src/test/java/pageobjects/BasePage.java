package pageobjects;

import helpers.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public abstract class BasePage {
    private By loadingIcon = By.cssSelector(".blockUI");
    protected final WebDriver driver;
    protected final Browser browser;
    protected final String baseURL;
    protected BasePage(Browser browser) {
        this.browser = browser;
        this.driver = browser.driver;
        baseURL = browser.baseURL;
    }

    protected void waitForLoadingIconDisappear() {
        browser.wait.until(ExpectedConditions.numberOfElementsToBe(loadingIcon, 0));
    }
}
