package tests.base;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import util.DriverFactory;
import util.TestListener;

@Listeners({
        io.qameta.allure.testng.AllureTestNg.class,
        TestListener.class})
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