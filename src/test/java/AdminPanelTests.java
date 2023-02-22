import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AdminPanelTests extends BaseTests {

    private final String newProductSlug = "post-new.php?post_type=product";

    @BeforeEach
    public void adminLogin() {
        driver.get(baseUrl + "/my-account/");
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    @Test
    public void product_virtual_should_not_show_shipping() {

        driver.get(baseUrl + "/wp-admin/" + newProductSlug);
        driver.findElement(By.id("_virtual")).click();
        WebElement shippingOptions = driver.findElement(By.className("shipping_options"));
        Assertions.assertFalse(shippingOptions.isDisplayed(),
                "Shipping tab is still displayed but should be hidden. The product is a virtual product.");
    }

    @Test
    public void admin_successful_login_should_display_my_account_content() {
        Assertions.assertDoesNotThrow(() -> driver.findElement(By.className("woocommerce-MyAccount-content")),
                "The my account content is missing. User probably is not logged in.");
    }

    @Test
    public void select_all_products_should_select_each_of_them() {
        driver.get(baseUrl + "/wp-admin/" + newProductSlug);
        driver.findElement(By.id("cb-select-all-1")).click();

        List<WebElement> productCheckboxes = driver.findElements(By.name("post[]"));
        long numberOfSelectedCheckboxes = productCheckboxes.stream()
                .filter(WebElement::isSelected).count();
        Assertions.assertEquals(7, numberOfSelectedCheckboxes,
                "Not all the product checkboxes where selected.");
    }
}
