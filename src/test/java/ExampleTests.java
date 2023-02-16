import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ExampleTests {

    WebDriver driver;
    WebDriverWait wait;
    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @AfterEach
    public void quitDriver() {
        driver.quit();
    }

    @Test
    public void update_quantity_in_cart_should_update_total_price() {
        driver.get("http://localhost:8080/product/" +
                "the-elements-of-qualitative-chemical-analysis-vol-1-parts-1-and-2-by-stieglitz/");
        driver.findElement(By.name("add-to-cart")).click();
        driver.get("http://localhost:8080/cart/");
        WebElement quantityField = driver.findElement(By.className("qty"));
        quantityField.clear();
        quantityField.sendKeys("2");
        driver.findElement(By.name("update_cart")).click();

        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("blockUI"), 0));

        WebElement total = driver.findElement(By.className("order-total"));

        Assertions.assertEquals("Total 28,00 €", total.getText(),
                "Total price is not correct.");
    }

    @Test
    public void add_to_cart_should_minicart_show_product_price() {
        driver.get("http://localhost:8080/product/" +
                "a-popular-history-of-astronomy-during-the-nineteenth-century-by-agnes-m-clerke/");
        WebElement addToCartButton = driver.findElement(By.name("add-to-cart"));
        addToCartButton.click();
        driver.findElement(By.className("wc-block-mini-cart__button")).click();

        WebElement totalPrice = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.className("wc-block-components-totals-item__value"))); 

        Assertions.assertEquals("12,00 €", totalPrice.getText(),
                "The price displayed in minicart is not correct.");
    }

    @Test
    public void add_product_to_cart_should_header_show_product_price() {
        driver.get("http://localhost:8080/product/" +
                "a-popular-history-of-astronomy-during-the-nineteenth-century-by-agnes-m-clerke/");
        WebElement addToCartButton = driver.findElement(By.name("add-to-cart"));
        addToCartButton.click();
        WebElement miniCart = driver.findElement(By.className("wc-block-mini-cart__amount"));
        Assertions.assertEquals("12,00 €", miniCart.getText(),
                "The price displayed in header is not correct.");
    }

    @Test
    public void admin_successful_login_should_display_my_account_content() {
        driver.get("http://localhost:8080/my-account/");
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        Assertions.assertDoesNotThrow(() -> driver.findElement(By.className("woocommerce-MyAccount-content")),
                "The my account content is missing. User probably is not logged in.");
    }
}
