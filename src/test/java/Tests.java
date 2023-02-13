import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public class Tests {

    WebDriver driver;
    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
    }

    @AfterEach
    public void quitDriver() {
        driver.quit();
    }

    @Test
    public void exampleTest() {
        String url = "http://localhost:8080/";
        driver.get(url);
        WebElement searchField = driver.findElement(By.id("wc-block-search__input-1"));
        List<WebElement> addToCartButtons = driver.findElements(By.className("add_to_cart_button"));
    }
}
