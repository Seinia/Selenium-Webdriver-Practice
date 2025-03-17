package tests.base;

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
public abstract class BaseTest {

    protected WebDriver driver;
    private static final Logger logger = LogManager.getLogger(BaseTest.class);

    @BeforeMethod
    @Parameters({"browser"})
    public void setUp(String browser) {
        System.setProperty("browser", browser);
        logger.info("Initializing WebDriver for browser: {}", browser);
        driver = DriverFactory.getDriver();
        driver.manage().window().maximize();
        logger.info("Browser window maximized");
    }

    @AfterMethod
    public void stopBrowser() {
        logger.info("Closing WebDriver");
        DriverFactory.closeDriver();
    }
}