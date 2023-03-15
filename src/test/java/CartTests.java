import org.junit.jupiter.api.*;
public class CartTests extends BaseTests {

    String addToCartFromProductButtonSelector = "[name=add-to-cart]";
    String goToCartFromProductButtonSelector = ".woocommerce-message>.button";
    String productsInCartSelector = "tr.cart_item";
    String updateCartButtonSelector = "[name=update_cart]";
    String loadingIconSelector = ".blockUI";
    String quantityFieldInCartSelector = "input.qty";
    String totalPriceInCartSelector = "[data-title=Total]";
    String calculusURL = "/product/calculus-made-easy-by-silvanus-p-thompson/";
    String historyOfAstronomyURL = "/product/history-of-astronomy-by-george-forbes/";

    @Test
    public void no_product_added_to_cart_should_cart_be_empty() {
        bot.go("/cart/");

        Assertions.assertEquals(0, bot.getNumberOfElements(".shop_table"),
                "Products table was found in cart while no product was added.");
    }
    @Test
    public void product_added_to_cart_should_cart_have_one_product() {
        bot.go(calculusURL);
        bot.click(addToCartFromProductButtonSelector);
        bot.click(goToCartFromProductButtonSelector);

        int numberOfProducts = bot.getNumberOfElements(productsInCartSelector);

        Assertions.assertEquals(1, numberOfProducts,
                "Expected number of products in cart: 1" +
                        "\nActual: " + numberOfProducts);
    }

    @Test
    public void two_products_added_to_cart_should_cart_have_two_products() {
        bot.go(calculusURL);
        bot.click(addToCartFromProductButtonSelector);
        bot.go(historyOfAstronomyURL);
        bot.click(addToCartFromProductButtonSelector);
        bot.click(goToCartFromProductButtonSelector);

        int numberOfProducts = bot.getNumberOfElements(productsInCartSelector);
        Assertions.assertEquals(2, numberOfProducts,
                "Expected number of products in cart: 2" +
                        "\nActual: " + numberOfProducts);
    }

    @Test
    public void changing_quantity_in_cart_should_change_total_price() {
        bot.go(calculusURL);
        bot.click(addToCartFromProductButtonSelector);
        bot.click(goToCartFromProductButtonSelector);
        bot.type(quantityFieldInCartSelector, "3");
        bot.click(updateCartButtonSelector);

        bot.waitToDisappear(loadingIconSelector, 5);

        Assertions.assertEquals("39,00 €",
                bot.getText(totalPriceInCartSelector),
                "Total price after quantity update is not what expected.");
    }

    @Test
    public void changing_quantity_in_cart_to_negative_should_not_update_total_price() {
        bot.go(calculusURL);
        bot.click(addToCartFromProductButtonSelector);
        bot.click(goToCartFromProductButtonSelector);
        bot.type(quantityFieldInCartSelector, "-3");
        bot.click(updateCartButtonSelector);

        bot.waitToDisappear(loadingIconSelector, 5);

        Assertions.assertEquals("13,00 €",
                bot.getText(totalPriceInCartSelector),
                "Total price is not what expected.");
    }
}