package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage extends BasePage {
    private By productItem = By.cssSelector("tr.cart_item");
    private By quantityField = By.cssSelector("input.qty");
    private By updateCartButton = By.cssSelector("[name=update_cart]");
    private By totalPrice = By.cssSelector("[data-title=Total]");
    public CartPage(WebDriver driver) {
        super(driver);
    }

    public CartPage go() {
        driver.get(baseURL + "/cart/");
        return this;
    }

    public int getNumberOfProducts() {
        return driver.findElements(productItem).size();
    }

    public CartPage changeQuantity(int quantity) {
        driver.findElement(quantityField).clear();
        driver.findElement(quantityField).sendKeys(String.valueOf(quantity));
        driver.findElement(updateCartButton).click();

        waitForLoadingIconDisappear();
        return this;
    }

    public String getTotalPrice() {
        return driver.findElement(totalPrice).getText();
    }
}
