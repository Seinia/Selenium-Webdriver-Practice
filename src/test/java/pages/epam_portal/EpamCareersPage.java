package pages.epam_portal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.base.BasePage;
import pages.campus_portal.CampusTrainingPage;

import java.util.ArrayList;
import java.util.List;

public class EpamCareersPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(EpamCareersPage.class);

    @FindBy(xpath = "//span[text()='visit epam.ua' and contains(@class, 'desktop')]")
    private WebElement visitButton;

    @FindBy(xpath = "//h3[@class='search-result__redirect-heading-23' and contains (text(), 'View Career Opportunities at EPAM Ukraine by Visiting EPAM.ua')]")
    private WebElement textUpperVisitButton;

    @FindBy(linkText = "Find Your Dream Job")
    private WebElement epamCareersPageText;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement findButton;

    public EpamCareersPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public EpamJobListPage clickVisitButton(){
        scrollToElement(visitButton, "arguments[0].scrollIntoView({block: 'center'});");
        clickElement(visitButton);
        logger.info("Click visit button on EPAM Careers Page");
        return new EpamJobListPage(driver);
    }

    public EpamCareersPage clickFindButton(){
        scrollToElement(findButton, "arguments[0].scrollIntoView({block: 'center'});");
        clickElement(findButton);
        logger.info("Click find button");
        return this;
    }


    public String getEpamCareersPageText(){
        logger.debug("Get text for verification of EPAM Careers Page");
        return getTextFromElement(epamCareersPageText);
    }

    public void switchToNewTab() {
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1));
        logger.info("Switch to a new tab from EPAM Careers Page");
    }

}
