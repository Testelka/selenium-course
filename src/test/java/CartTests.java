import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.junit.jupiter.api.*;

public class CartTests extends BaseTests {
    By addToCartFromProductButtonLocator = By.cssSelector("[name=add-to-cart]");
    By goToCartFromProductButtonLocator = By.cssSelector(".woocommerce-message>.button");
    By productsInCartLocator = By.cssSelector("tr.cart_item");
    By updateCartButtonLocator = By.cssSelector("[name=update_cart]");
    By loadingIconLocator = By.cssSelector(".blockUI");
    By quantityFieldInCartLocator = By.cssSelector("input.qty");
    By totalPriceInCartLocator = By.cssSelector("[data-title=Total]");

    String calculusURL = baseURL + "/product/calculus-made-easy-by-silvanus-p-thompson/";
    String historyOfAstronomyURL = baseURL + "/product/history-of-astronomy-by-george-forbes/";

    @Test
    public void no_product_added_to_cart_should_cart_be_empty() {
        driver.get(baseURL + "/cart/");

        Assertions.assertThrows(NoSuchElementException.class,
                () -> driver.findElement(By.cssSelector(".shop_table")),
                "Products table was found in cart while no product was added.");
    }
    @Test
    public void product_added_to_cart_should_cart_have_one_product() {
        driver.get(calculusURL);
        driver.findElement(addToCartFromProductButtonLocator).click();
        driver.findElement(goToCartFromProductButtonLocator).click();
        int numberOfProducts = driver.findElements(productsInCartLocator).size();
        Assertions.assertEquals(1, numberOfProducts,
                "Expected number of products in cart: 1" +
                        "\nActual: " + numberOfProducts);
    }

    @Test
    public void two_products_added_to_cart_should_cart_have_two_products() {
        driver.get(calculusURL);
        driver.findElement(addToCartFromProductButtonLocator).click();
        driver.get(historyOfAstronomyURL);
        driver.findElement(addToCartFromProductButtonLocator).click();
        driver.findElement(goToCartFromProductButtonLocator).click();
        int numberOfProducts = driver.findElements(productsInCartLocator).size();
        Assertions.assertEquals(2, numberOfProducts,
                "Expected number of products in cart: 2" +
                        "\nActual: " + numberOfProducts);
    }

    @Test
    public void changing_quantity_in_cart_should_change_total_price() {
        driver.get(calculusURL);
        driver.findElement(addToCartFromProductButtonLocator).click();
        driver.findElement(goToCartFromProductButtonLocator).click();
        WebElement quantityField = driver.findElement(quantityFieldInCartLocator);
        quantityField.clear();
        quantityField.sendKeys("3");
        driver.findElement(updateCartButtonLocator).click();

        wait.until(ExpectedConditions.numberOfElementsToBe(loadingIconLocator, 0));

        Assertions.assertEquals("39,00 €",
                driver.findElement(totalPriceInCartLocator).getText(),
                "Total price after quantity update is not what expected.");
    }

    @Test
    public void changing_quantity_in_cart_to_negative_should_not_update_total_price() {
        driver.get(calculusURL);
        driver.findElement(addToCartFromProductButtonLocator).click();
        driver.findElement(goToCartFromProductButtonLocator).click();
        WebElement quantityField = driver.findElement(quantityFieldInCartLocator);
        quantityField.clear();
        quantityField.sendKeys("-3");
        driver.findElement(updateCartButtonLocator).click();

        wait.until(ExpectedConditions.numberOfElementsToBe(loadingIconLocator, 0));

        Assertions.assertEquals("13,00 €",
                driver.findElement(totalPriceInCartLocator).getText(),
                "Total price after quantity update is not what expected.");
    }
}