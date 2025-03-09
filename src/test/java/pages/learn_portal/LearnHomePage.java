package pages.learn_portal;

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
        return new LearnCatalogPage(driver);
    }

    public LearnHomePage acceptCookie(){
        clickElement(acceptCookieButton);
        return this;
    }

    public String getHomePageText(){
        return getTextFromElement(homePageText);
    }

    public CampusHomePage clickCampusButton(){
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.visibilityOf(campusButton));
        clickElement(campusButton);
        return new CampusHomePage(driver);
    }

    public void switchToNewTab() {
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1));
    }

}
