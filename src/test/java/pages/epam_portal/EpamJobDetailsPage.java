package pages.epam_portal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.base.BasePage;

public class EpamJobDetailsPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(EpamJobDetailsPage.class);

    @FindBy(css = "span[data-iso2-country-code='UA']")
    private WebElement locationText;

    @FindBy(xpath = "//header[@class='recruiting-page__header']//h1")
    private WebElement jobTitle;

    public EpamJobDetailsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getLocationText(){
        logger.debug("Get text for verification of location from EPAM Careers Page");
        return getTextFromElement(locationText);
    }

    public String getJobTitleText(){
        logger.debug("Get text for verification of job title from EPAM Job Details Page");
        return getTextFromElement(jobTitle);
    }
}
