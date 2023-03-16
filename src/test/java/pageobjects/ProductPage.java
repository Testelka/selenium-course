package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage extends BasePage {
    private By addToCart = By.cssSelector("[name=add-to-cart]");
    private By goToCart = By.cssSelector(".woocommerce-message>.button");
    private By addToWishlist = By.cssSelector("a.add_to_wishlist");
    private By loadingIcon = By.cssSelector(".blockUI");
    private By goToWishlistFromHeader = By.cssSelector("#menu-item-88 a");
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public ProductPage go(String productSlug) {
        driver.get(baseURL + "/product/" + productSlug);
        return this;
    }

    public ProductPage addToCart() {
        driver.findElement(addToCart).click();
        return this;
    }

    public CartPage goToCart() {
        driver.findElement(goToCart).click();
        return new CartPage(driver);
    }

    public ProductPage addToWishlist() {
        driver.findElement(addToWishlist).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.numberOfElementsToBe(loadingIcon, 0));
        return this;
    }

    public WishlistPage goToWishlist() {
        driver.findElement(goToWishlistFromHeader).click();
        return new WishlistPage(driver);
    }
}
