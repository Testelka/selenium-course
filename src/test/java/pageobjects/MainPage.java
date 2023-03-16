package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage go() {
        driver.get(baseURL);
        return this;
    }

}
