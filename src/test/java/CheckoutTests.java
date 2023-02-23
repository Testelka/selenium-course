import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutTests extends BaseTests {

    private final By addToCartButtonLocator = By.name("add-to-cart");
    private final String chemicalAnalysisSlug =
            "the-elements-of-qualitative-chemical-analysis-vol-1-parts-1-and-2-by-stieglitz/";

    @BeforeEach
    public void setWait() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void successful_purchase_should_show_order_received_message() {
        driver.get(baseUrl + "/product/" + chemicalAnalysisSlug);
        driver.findElement(addToCartButtonLocator).click();
        driver.get(baseUrl + "/checkout/");

        driver.findElement(By.cssSelector("#billing_first_name")).sendKeys("Ania");
        driver.findElement(By.cssSelector("#billing_last_name")).sendKeys("Nowak");
        driver.findElement(By.cssSelector("#billing_address_1")).sendKeys("ul. Kwiatowa 33/2");
        driver.findElement(By.cssSelector("#billing_postcode")).sendKeys("22-333");
        driver.findElement(By.cssSelector("#billing_city")).sendKeys("Sopot");
        driver.findElement(By.cssSelector("#billing_phone")).sendKeys("6666666");
        driver.findElement(By.cssSelector("#billing_email")).sendKeys("test@test.pl");

        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("#stripe-card-element iframe")));
        driver.findElement(By.cssSelector("[name=cardnumber]")).sendKeys("4242424242424242");
        driver.switchTo().defaultContent();

        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("#stripe-exp-element iframe")));
        driver.findElement(By.cssSelector("[name=exp-date]")).sendKeys("444");
        driver.switchTo().defaultContent();

        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("#stripe-cvc-element iframe")));
        driver.findElement(By.cssSelector("[name=cvc]")).sendKeys("222");
        driver.switchTo().defaultContent();

        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("blockUI"), 0));
        driver.findElement(By.cssSelector("#place_order")).click();
        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("blockUI"), 0));

        Assertions.assertEquals("Order received",
                driver.findElement(By.cssSelector(".entry-title")).getText(),
                "\"Order received\" text is not found in the header. Order was probably not successful.");
    }
}

