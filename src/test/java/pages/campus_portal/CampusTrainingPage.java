package pages.campus_portal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.base.BasePage;

import java.time.Duration;
import java.util.List;

public class CampusTrainingPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(CampusTrainingPage.class);

    @FindBy(xpath =
            "//div[contains(@class, 'FMk1Jo') and contains(@class, 'uui-size-36')][text()='Locations']"
    )
    private WebElement locationsDropDown;

    @FindBy(xpath =
            "//div[contains(@class, 'FMk1Jo') and contains(@class, 'uui-size-36')][text()='Skills']"
    )
    private WebElement skillsDropDown;

    @FindBy(css = "input[type='search']")
    private WebElement searchField;

    @FindBy(xpath = "//h1[text()='Training programs']")
    private WebElement trainingPageText;

    @FindBy(xpath = "//div[text()='Automated Testing in JavaScript']")
    private WebElement courseCard;

    @FindBy(className = "yByDq3")
    private List<WebElement> dropDownCheckBox;

    public CampusTrainingPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public CampusTrainingPage clickLocationsDropDown(){
        clickElement(locationsDropDown);
        logger.info("Click drop down for location");
        return this;
    }

    public CampusTrainingPage clickSkillsDropDown(){
        clickElement(skillsDropDown);
        logger.info("Click drop down for skills");
        return this;
    }

    public CampusCoursePage clickCourseCard(){
        clickElement(courseCard);
        logger.info("Click necessary course card");
        return new CampusCoursePage(driver);
    }

    public String getCourseCardText(){
        logger.debug("Get text for verification of course card");
        return getTextFromElement(courseCard);
    }

    public CampusTrainingPage inputSearchField(String text){
        inputText(searchField, text);
        logger.info("Input text due to filter courses");
        return this;
    }

    public CampusTrainingPage clickDropDownCheckBox(){
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .withMessage("Could not find an item in drop down")
                .pollingEvery(Duration.ofMillis(50))
                .ignoring(StaleElementReferenceException.class)
                .until(driver1 -> dropDownCheckBox.size() == 1);
        dropDownCheckBox.get(0).click();
        logger.info("Click item from drop down");
        return this;
    }

    public String getTrainingPageText(){
        logger.debug("Get text for verification of Campus Training Page");
        return getTextFromElement(trainingPageText);
    }




}
