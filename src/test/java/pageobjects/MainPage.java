package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {
    private By goToWishlistFromHeader = By.cssSelector("#menu-item-88 a");
    public MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage go() {
        driver.get(baseURL);
        return this;
    }

    public WishlistPage goToWishlist() {
        driver.findElement(goToWishlistFromHeader).click();
        return new WishlistPage(driver);
    }
}
