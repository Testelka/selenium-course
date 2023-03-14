import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class ExceptionTests extends BaseTests {

    @Test
    public void no_product_reviews_should_display_no_reviews_message() {
        driver.get("https://fakestore.testelka.pl/product/windsurfing-w-lanzarote-costa-teguise/");
        driver.findElement(By.cssSelector(".woocommerce-store-notice__dismiss-link")).click();
        driver.findElement(By.cssSelector("#tab-title-reviews a")).click();

        Assertions.assertDoesNotThrow(() -> driver.findElement(By.cssSelector(".woocommerce-noreviews")),
                "The no reviews message doesn't exist.");
    }


}