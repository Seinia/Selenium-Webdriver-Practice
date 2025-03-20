package tests.base;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import util.DriverFactory;
import util.TestListener;

@Listeners(TestListener.class)
@Slf4j
public abstract class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    @Parameters({"browser"})
    public void setUp(String browser) {
        System.setProperty("browser", browser);
        log.info("Initializing WebDriver for browser: {}", browser);
        driver = DriverFactory.getDriver();
        log.info("Browser window maximized");
    }

    @AfterMethod
    public void stopBrowser() {
        log.info("Closing WebDriver");
        DriverFactory.closeDriver();
    }
}