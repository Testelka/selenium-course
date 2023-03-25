package pageobjects;

import helpers.Browser;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage {
    @FindBy(css = "[name=add-to-cart]")
    private WebElement addToCart;
    @FindBy(css = ".woocommerce-message>.button")
    private WebElement goToCart;
    @FindBy(css = "a.add_to_wishlist")
    private WebElement addToWishlist;
    public final StoreHeaderComponent storeHeader;

    public ProductPage(Browser browser) {
        super(browser);
        storeHeader = new StoreHeaderComponent(browser);
    }

    public ProductPage go(String productSlug) {
        driver.get(baseURL + "/product/" + productSlug);
        return this;
    }

    public ProductPage addToCart() {
        addToCart.click();
        return this;
    }

    public CartPage goToCart() {
        goToCart.click();
        return new CartPage(browser);
    }

    public ProductPage addToWishlist() {
        addToWishlist.click();
        waitForLoadingIconDisappear();
        return this;
    }
}
