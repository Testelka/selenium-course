package pageobjects;

import helpers.Browser;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;;

import java.util.List;

public class CartPage extends BasePage {

    @FindBy(css = "tr.cart_item")
    private List<WebElement> productItems;
    @FindBy(css = "input.qty")
    private WebElement quantityField;
    @FindBy(css = "[name=update_cart]")
    private WebElement updateCartButton;
    @FindBy(css = "[data-title=Total]")
    private WebElement totalPrice;
    public CartPage(Browser browser) {
        super(browser);
    }

    public CartPage go() {
        driver.get(baseURL + "/cart/");
        return this;
    }

    public int getNumberOfProducts() {
        return productItems.size();
    }

    public CartPage changeQuantity(int quantity) {
        quantityField.clear();
        quantityField.sendKeys(String.valueOf(quantity));
        updateCartButton.click();

        waitForLoadingIconDisappear();
        return this;
    }

    public String getTotalPrice() {
        return totalPrice.getText();
    }
}
