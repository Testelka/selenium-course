package pageobjects;

import helpers.Browser;
import org.openqa.selenium.By;

public class StoreHeaderComponent extends BasePage {
    private By goToWishlistFromHeader = By.cssSelector("#menu-item-88 a");
    protected StoreHeaderComponent(Browser browser) {
        super(browser);
    }

    public WishlistPage goToWishlist() {
        driver.findElement(goToWishlistFromHeader).click();
        return new WishlistPage(browser);
    }
}
