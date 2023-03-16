package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WishlistPage extends BasePage {
    private By productItems = By.cssSelector(".wishlist-items-wrapper tr td.product-remove");
    protected WishlistPage(WebDriver driver) {
        super(driver);
    }

    public int getNumberOfProducts() {
        return driver.findElements(productItems).size();
    }
}
