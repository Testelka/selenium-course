package helpers;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserFactory {

    private final String targetErrorMessage = "The target provided in configuration.properties is not correct.";

    public Browser createInstance(ConfigurationReader configurationReader) throws NoSuchBrowserException {
        WebDriver driver = createDriverInstance(configurationReader);
        return new Browser(driver, configurationReader);
    }
    private WebDriver createDriverInstance(ConfigurationReader configuration) throws NoSuchBrowserException {
        String browser = configuration.getBrowser();

        switch (browser) {
            case "firefox" -> {
                return createFirefoxInstance(configuration);
            }
            case "chrome" -> {
                return createChromeInstance(configuration);
            }
            case "edge" -> {
                return createEdgeInstance(configuration);
            }
            default -> throw new NoSuchBrowserException(browser);
        }
    }
    private WebDriver createChromeInstance(ConfigurationReader configuration) {
        ChromeOptions options = new ChromeOptions();
        if (configuration.isHeadless()) options.addArguments("--headless=new");

        switch (configuration.getTarget()) {
            case "local" -> {
                return new ChromeDriver(options);
            }
            case "remote" -> {
                return createRemoteInstance(configuration, options);
            }
            default -> throw new RuntimeException(targetErrorMessage);
        }
    }

    private WebDriver createFirefoxInstance(ConfigurationReader configuration) {
        FirefoxOptions options = new FirefoxOptions();
        if (configuration.isHeadless()) options.addArguments("-headless");
        switch (configuration.getTarget()) {
            case "local" -> {
                return new FirefoxDriver(options);
            }
            case "remote" -> {
                return createRemoteInstance(configuration, options);
            }
            default -> throw new RuntimeException(targetErrorMessage);
        }
    }

    private WebDriver createEdgeInstance(ConfigurationReader configuration) {
        EdgeOptions options = new EdgeOptions();
        if (configuration.isHeadless()) options.addArguments("--headless=new");
        switch (configuration.getTarget()) {
            case "local" -> {
                return new EdgeDriver(options);
            }
            case "remote" -> {
                return createRemoteInstance(configuration, options);
            }
            default -> throw new RuntimeException(targetErrorMessage);
        }
    }

    private RemoteWebDriver createRemoteInstance(ConfigurationReader configuration, MutableCapabilities options) {
        try {
            return new RemoteWebDriver(new URL(configuration.getRemoteURL()), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(
                    "RemoteURL provided in configuration.properties is not correct." + "\n" + e);
        }
    }
}
