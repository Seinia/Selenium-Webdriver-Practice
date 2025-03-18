package pages.learn_portal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.base.BasePage;

public class LearnDotNetCoursePage extends BasePage {

    private static final Logger logger = LogManager.getLogger(LearnDotNetCoursePage.class);

    @FindBy(xpath = "//div[text()='ENG']")
    private WebElement languageText;

    @FindBy(xpath = "//div[text()='145 hr 41 min']")
    private WebElement estimatedEffortsText;

    public LearnDotNetCoursePage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getLanguageText(){
        logger.debug("Get language text for verification of course from Learn Catalog Page");
        return getTextFromElement(languageText);
    }

    public String getEstimatedEffortsText(){
        logger.debug("Get estimated efforts text text for verification of course from Learn Catalog Page");
        return getTextFromElement(estimatedEffortsText);
    }

}
