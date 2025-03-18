package pages.campus_portal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.base.BasePage;
import tests.CourseRegistrationOnCampusTest;

public class CampusCoursePage extends BasePage {

    private static final Logger logger = LogManager.getLogger(CampusCoursePage.class);

    @FindBy(xpath = "(//div[text()='Register'])[1]")
    private WebElement registerButton;

    public CampusCoursePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public CampusLoginPage clickRegisterButton(){
        clickElement(registerButton);
        logger.info("Click registration button");
        return new CampusLoginPage(driver);
    }
}
