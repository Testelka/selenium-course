import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartTests extends BaseTests {

    private final By addToCartButtonLocator = By.name("add-to-cart");
    private final By updateCartButtonLocator = By.name("update_cart");
    private final String chemicalAnalysisSlug =
            "the-elements-of-qualitative-chemical-analysis-vol-1-parts-1-and-2-by-stieglitz/";
    private final String historyOfAstronomySlug =
            "a-popular-history-of-astronomy-during-the-nineteenth-century-by-agnes-m-clerke/";

    @Test
    public void cart_not_changed_should_update_button_disabled() {
        driver.get(baseUrl + "/product/" + chemicalAnalysisSlug);
        driver.findElement(addToCartButtonLocator).click();
        driver.get(baseUrl + "/cart/");

        WebElement updateButton = driver.findElement(updateCartButtonLocator);
        Assertions.assertFalse(updateButton.isEnabled(),
                "Update button is enabled while it shouldn't. There were no changes in cart.");
    }

    @Test
    public void add_to_cart_should_minicart_show_product_price() {
        driver.get(baseUrl + "/product/" + historyOfAstronomySlug);
        WebElement addToCartButton = driver.findElement(addToCartButtonLocator);
        addToCartButton.click();
        driver.findElement(By.className("wc-block-mini-cart__button")).click();

        WebElement totalPrice = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.className("wc-block-components-totals-item__value")));

        Assertions.assertEquals("12,00 €", totalPrice.getText(),
                "The price displayed in minicart is not correct.");
    }

    @Test
    public void add_product_to_cart_should_header_show_product_price() {
        driver.get(baseUrl + "/product/" + historyOfAstronomySlug);
        WebElement addToCartButton = driver.findElement(addToCartButtonLocator);
        addToCartButton.click();
        WebElement miniCart = driver.findElement(By.className("wc-block-mini-cart__amount"));
        Assertions.assertEquals("12,00 €", miniCart.getText(),
                "The price displayed in header is not correct.");
    }

    @Test
    public void update_quantity_in_cart_should_update_total_price() {
        driver.get(baseUrl + "/product/" + chemicalAnalysisSlug);
        driver.findElement(addToCartButtonLocator).click();
        driver.get(baseUrl + "/cart/");
        WebElement quantityField = driver.findElement(By.className("qty"));
        quantityField.clear();
        quantityField.sendKeys("2");
        driver.findElement(updateCartButtonLocator).click();

        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("blockUI"), 0));

        WebElement total = driver.findElement(By.className("order-total"))
                .findElement(By.className("amount"));

        Assertions.assertEquals("28,00 €", total.getText(),
                "Total price is not correct.");
    }
}
