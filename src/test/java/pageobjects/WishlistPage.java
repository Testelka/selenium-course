package pageobjects;

import helpers.Browser;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class WishlistPage extends BasePage {
    @FindBy(css = ".wishlist-items-wrapper tr td.product-remove")
    private List<WebElement> productItems;
    protected WishlistPage(Browser browser) {
        super(browser);
    }

    public int getNumberOfProducts() {
        return productItems.size();
    }
}
