import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductTests extends BaseTests {
    @Test
    public void new_product_quantity_typed_in_should_product_quantity_changed() {
        String chemicalAnalysisSlug = "the-elements-of-qualitative-chemical-analysis-vol-1-parts-1-and-2-by-stieglitz/";
        driver.get(baseUrl + "/product/" + chemicalAnalysisSlug);
        WebElement productQuantity = driver.findElement(By.className("qty"));
        productQuantity.clear();
        productQuantity.sendKeys("3");

        Assertions.assertEquals("3", productQuantity.getDomProperty("value"),
                "Product quantity not changed.");
    }

}
