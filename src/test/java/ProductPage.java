import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends BasePage{
    private By addToCart = By.cssSelector("[name=add-to-cart]");
    private By goToCart = By.cssSelector(".woocommerce-message>.button");
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public ProductPage go(String productSlug) {
        String baseURL = "http://localhost:8080";
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
}
