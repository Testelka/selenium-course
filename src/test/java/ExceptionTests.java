import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ExceptionTests extends BaseTests {

    @Test
    public void add_product_to_cart_and_to_wishlist_should_display_on_wishlist() {
        driver.get("http://localhost:8080/product/" +
                "a-popular-history-of-astronomy-during-the-nineteenth-century-by-agnes-m-clerke/");
        WebElement addToCart = driver.findElement(By.cssSelector("[name=add-to-cart]"));
        addToCart.click();
        WebElement addToWishList = driver.findElement(By.cssSelector(".add_to_wishlist"));
        addToWishList.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".view-wishlist"))).click();

        Assertions.assertDoesNotThrow(() -> driver.findElement(By.cssSelector("#yith-wcwl-row-76")),
                "The product is not on the wishlist.");
    }

    @Test
    public void remove_all_from_cart_should_show_cart_empty_message() {
        driver.get("http://localhost:8080/product/" +
                "a-popular-history-of-astronomy-during-the-nineteenth-century-by-agnes-m-clerke/");
        driver.findElement(By.cssSelector("[name=add-to-cart]")).click();
        driver.get("http://localhost:8080/product/" +
                "makers-of-electricity-by-brother-potamian-and-james-j-walsh/");
        driver.findElement(By.cssSelector("[name=add-to-cart]")).click();
        driver.get("http://localhost:8080/cart/");

        driver.findElement(By.cssSelector("a[data-product_id='76']")).click();
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(".blockUI"), 0));
        driver.findElement(By.cssSelector("a[data-product_id='72']")).click();
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(".blockUI"), 0));

        Assertions.assertDoesNotThrow(() -> driver.findElement(By.cssSelector(".cart-empty")),
                "There is no empty cart message.");

    }
}