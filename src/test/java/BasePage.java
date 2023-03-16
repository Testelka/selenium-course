import org.openqa.selenium.WebDriver;

public class BasePage {
    protected final WebDriver driver;
    protected BasePage(WebDriver driver) {
        this.driver = driver;
    }
}
