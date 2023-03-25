package pageobjects;

import helpers.Browser;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StoreHeaderComponent extends BasePage {
    @FindBy(css = "#menu-item-88 a")
    private WebElement goToWishlistFromHeader;
    protected StoreHeaderComponent(Browser browser) {
        super(browser);
    }

    public WishlistPage goToWishlist() {
        goToWishlistFromHeader.click();
        return new WishlistPage(browser);
    }
}
