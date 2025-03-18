package pages.learn_portal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.base.BasePage;
import pages.campus_portal.CampusHomePage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class LearnHomePage extends BasePage {

    private static final Logger logger = LogManager.getLogger(LearnHomePage.class);

    private final String HOMEPAGE_URL = "https://learn.epam.com/start";

    @FindBy(linkText = "Catalog")
    private WebElement catalogButton;

    @FindBy(linkText = "Campus")
    private WebElement campusButton;

    @FindBy(xpath = "//div[text()='Home']")
    private WebElement homePageText;

    @FindBy(xpath = "//button[@id='onetrust-accept-btn-handler']")
    private WebElement acceptCookieButton;


    public LearnHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public LearnHomePage openPage() {
        driver.get(HOMEPAGE_URL);
        return this;
    }

    public LearnCatalogPage clickCatalogButton(){
        clickElement(catalogButton);
        logger.info("Click catalog button");
        return new LearnCatalogPage(driver);
    }

    public LearnHomePage acceptCookie(){
        clickElement(acceptCookieButton);
        logger.info("Accept cookies");
        return this;
    }

    public String getHomePageText(){
        logger.debug("Get text for verification of Learn Home Page");
        return getTextFromElement(homePageText);
    }

    public CampusHomePage clickCampusButton(){
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.visibilityOf(campusButton));
        clickElement(campusButton);
        logger.info("Click campus button");
        return new CampusHomePage(driver);
    }

    public void switchToNewTab() {
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1));
        logger.info("Switch to a new tab from Learn Home Page");
    }

}
