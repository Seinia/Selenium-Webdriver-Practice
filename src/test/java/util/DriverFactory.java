package util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {

    private static final Logger logger = LogManager.getLogger(DriverFactory.class);
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverFactory() {
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            WebDriver webDriver;
            switch (System.getProperty("browser", "chrome")) {
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    webDriver = new FirefoxDriver();
                    logger.debug("Firefox driver initialized");
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    webDriver = new EdgeDriver();
                    logger.debug("Edge driver initialized");
                    break;
                case "safari":
                    WebDriverManager.safaridriver().setup();
                    webDriver = new SafariDriver();
                    logger.debug("Safari driver initialized");
                    break;
                case "ie":
                    WebDriverManager.iedriver().setup();
                    webDriver = new InternetExplorerDriver();
                    logger.debug("Internet Explorer driver initialized");
                    break;
                default:
                    WebDriverManager.chromedriver().setup();
                    webDriver = new ChromeDriver();
                    logger.debug("Chrome driver initialized");
            }
            webDriver.manage().window().maximize();
            driver.set(webDriver);
        }
        return driver.get();
    }

    public static void closeDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}