import org.junit.jupiter.api.*;
import pageobjects.CartPage;
import pageobjects.ProductPage;

public class CartTests extends BaseTest {
    String calculusSlug = "/calculus-made-easy-by-silvanus-p-thompson/";
    String historyOfAstronomySlug = "/history-of-astronomy-by-george-forbes/";

    @Test
    public void no_product_added_to_cart_should_cart_be_empty() {
        CartPage cartPage = new CartPage(driver).go();

        Assertions.assertEquals(0, cartPage.getNumberOfProducts(),
                "Number of products in cart is not 0.");
    }
    @Test
    public void product_added_to_cart_should_cart_have_one_product() {
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = productPage.go(calculusSlug).addToCart().goToCart();
        int numberOfProducts = cartPage.getNumberOfProducts();

        Assertions.assertEquals(1, numberOfProducts,
                "Expected number of products in cart: 1" +
                        "\nActual: " + numberOfProducts);
    }

    @Test
    public void two_products_added_to_cart_should_cart_have_two_products() {
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = productPage
                .go(calculusSlug).addToCart()
                .go(historyOfAstronomySlug).addToCart().goToCart();

        int numberOfProducts = cartPage.getNumberOfProducts();
        Assertions.assertEquals(2, numberOfProducts,
                "Expected number of products in cart: 2" +
                        "\nActual: " + numberOfProducts);
    }

    @Test
    public void changing_quantity_in_cart_should_change_total_price() {
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = productPage
                .go(calculusSlug)
                .addToCart()
                .goToCart()
                .changeQuantity(3);

        Assertions.assertEquals("39,00 €",
                cartPage.getTotalPrice(),
                "Total price after quantity update is not what expected.");
    }

    @Test
    public void changing_quantity_in_cart_to_negative_should_not_update_total_price() {
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = productPage
                .go(calculusSlug)
                .addToCart()
                .goToCart()
                .changeQuantity(-3);

        Assertions.assertEquals("13,00 €",
                cartPage.getTotalPrice(),
                "Total price after quantity update is not what expected.");
    }
}