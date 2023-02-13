import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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
        Assertions.assertEquals(url, driver.getCurrentUrl(),
                "URL address of the main page is not what expected.");
    }
}
