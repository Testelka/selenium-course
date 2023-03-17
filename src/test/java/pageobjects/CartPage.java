package pageobjects;

import helpers.Browser;
import org.openqa.selenium.By;

public class CartPage extends BasePage {
    private By productItem = By.cssSelector("tr.cart_item");
    private By quantityField = By.cssSelector("input.qty");
    private By updateCartButton = By.cssSelector("[name=update_cart]");
    private By totalPrice = By.cssSelector("[data-title=Total]");
    public CartPage(Browser browser) {
        super(browser);
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
