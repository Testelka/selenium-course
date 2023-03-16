package pageobjects;

import org.openqa.selenium.WebDriver;

public abstract class BasePage {
    protected final WebDriver driver;
    protected final String baseURL = "http://localhost:8080";
    protected BasePage(WebDriver driver) {
        this.driver = driver;
    }
}
