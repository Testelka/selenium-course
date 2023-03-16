import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    By productItem = By.cssSelector("tr.cart_item");
    private final WebDriver driver;
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void go() {
        String baseURL = "http://localhost:8080";
        driver.get(baseURL + "/cart/");
    }

    public int getNumberOfProducts() {
        return driver.findElements(productItem).size();
    }
}
