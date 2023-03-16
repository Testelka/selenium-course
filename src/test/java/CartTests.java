import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.jupiter.api.*;

import java.time.Duration;

public class CartTests {
    WebDriver driver;
    WebDriverWait wait;
    String baseURL = "http://localhost:8080";
    By addToCartFromProductButtonLocator = By.cssSelector("[name=add-to-cart]");
    By goToCartFromProductButtonLocator = By.cssSelector(".woocommerce-message>.button");
    By productsInCartLocator = By.cssSelector("tr.cart_item");
    By updateCartButtonLocator = By.cssSelector("[name=update_cart]");
    By loadingIconLocator = By.cssSelector(".blockUI");
    By quantityFieldInCartLocator = By.cssSelector("input.qty");
    By totalPriceInCartLocator = By.cssSelector("[data-title=Total]");
    By productItem = By.cssSelector("tr.cart_item");

    String calculusURL = baseURL + "/product/calculus-made-easy-by-silvanus-p-thompson/";
    String historyOfAstronomyURL = baseURL + "/product/history-of-astronomy-by-george-forbes/";

    @BeforeEach
    public void setup() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }
    @AfterEach
    public void quitDriver() {
        driver.quit();
    }

    @Test
    public void no_product_added_to_cart_should_cart_be_empty() {
        driver.get(baseURL + "/cart/");

        Assertions.assertEquals(0, driver.findElements(productItem).size(),
                "Number of products in cart is not 0.");
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