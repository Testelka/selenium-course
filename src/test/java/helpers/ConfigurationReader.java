package helpers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {
    private String browser;
    private String baseURL;
    private String headless;

    public ConfigurationReader() {
        String configurationPath = "src/test/resources/configuration.properties";

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(configurationPath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration file not found at: " + configurationPath);
        }
        Properties properties = new Properties();
        try {
            properties.load(reader);
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        baseURL = properties.getProperty("baseURL");
        browser = properties.getProperty("browser");
        headless = properties.getProperty("headless");
    }

    public String getBrowser() {
        if (!browser.isEmpty()) return browser;
        else throw new RuntimeException("\"browser\" is not specified in the Configuration.properties file.");
    }
    public boolean isHeadless() {
        if (!headless.isEmpty()) return Boolean.parseBoolean(headless);
        else throw new RuntimeException("\"headless\" is not specified in the Configuration.properties file.");
    }
    public String getBaseUrl() {
        if (!baseURL.isEmpty()) return baseURL;
        else throw new RuntimeException("\"baseUrl\"is not specified in the Configuration.properties file.");
    }


}
