import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class ExceptionTests extends BaseTests {
    @Test
    public void cart_with_products_should_show_total_price() {
        driver.get("http://localhost:8080/product/history-of-astronomy-by-george-forbes/");
        driver.findElement(By.cssSelector("[name=add-to-cart]")).click();
        driver.get("http://localhost:8080/product/the-theory-of-heat-radiation-by-max-planck/");
        driver.findElement(By.cssSelector("[name=add-to-cart]")).click();
        driver.get("http://localhost:8080/cart");
        String totalPrice = driver.findElement(By.cssSelector(".order-totall .woocommerce-Price-amount")).getText();
        Assertions.assertEquals("33,00 €", totalPrice,
                "Total price is not what expected.");
    }
}
