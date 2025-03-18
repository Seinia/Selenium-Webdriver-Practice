package pages.epam_portal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.base.BasePage;
import pages.campus_portal.CampusTrainingPage;

import java.time.Duration;

public class EpamJobListPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(EpamJobListPage.class);

    @FindBy(id = "new_form_job_search-keyword")
    private WebElement skillsField;

    @FindBy(className = "select2-selection__rendered")
    private WebElement locationTextBox;

    @FindBy(xpath = "//li[text()='Київ']")
    private WebElement locationTextBoxOpened;

    @FindBy(className = "default-label")
    private WebElement specialisationTextBox;

    @FindBy(xpath = "//span[text()='Business and Data Analysis']")
    private WebElement specialisationCheckBox;

    @FindBy(className = "search-result__item-name")
    private WebElement jobCard;

    public EpamJobListPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public EpamJobListPage inputSkillsField(String text){
        inputText(skillsField, text);
        logger.info("Input skills due to filter job list");
        return this;
    }

    public EpamJobListPage inputLocationTextBox(){
        clickElement(locationTextBox);
        clickElement(locationTextBoxOpened);
        logger.info("Input location");
        return this;
    }

    public EpamJobListPage clickSpecialisationTextBox(){
        clickElement(specialisationTextBox);
        logger.info("Click specialisation text box");
        return this;
    }

    public EpamJobListPage clickSpecialisationCheckBox(){
        clickElement(specialisationCheckBox);
        logger.info("Click specialisation check box");
        return this;
    }

    public String getJobCardText() {
        waitForElementToContainText(jobCard, "Data", 500);
        logger.debug("Get text of job card from EPAM Job Details Page");
        return getTextFromElement(jobCard);
    }

    public EpamJobDetailsPage clickJobCard() {
        waitForElementToContainText(jobCard, "Data", 500);
        clickElement(jobCard);
        logger.info("Click job card");
        return new EpamJobDetailsPage(driver);
    }

    private void waitForElementToContainText(WebElement element, String text, int duration) {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .withMessage("Could not find an item in drop down")
                .pollingEvery(Duration.ofMillis(duration))
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.textToBePresentInElement(element, text));
    }

}
