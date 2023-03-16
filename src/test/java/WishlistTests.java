import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pageobjects.MainPage;
import pageobjects.ProductPage;
import pageobjects.WishlistPage;

public class WishlistTests extends BaseTest{
    String calculusSlug = "/calculus-made-easy-by-silvanus-p-thompson/";
    @Test
    public void product_added_to_wishlist_should_wishlist_have_one_item() {
        ProductPage productPage = new ProductPage(driver).go(calculusSlug);
        WishlistPage wishlistPage = productPage.addToWishlist().storeHeader.goToWishlist();

        Assertions.assertEquals(1, wishlistPage.getNumberOfProducts(),
                "Number of products in wishlist is not what expected.");
    }

    @Test
    public void no_product_added_to_wishlist_should_wishlist_be_empty() {
        MainPage mainPage = new MainPage(driver);
        WishlistPage wishlistPage = mainPage.go().storeHeader.goToWishlist();
    }
}
