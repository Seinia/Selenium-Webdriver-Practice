package pages.campus_portal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.base.BasePage;

public class CampusLoginPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(CampusLoginPage.class);

    @FindBy(xpath = "//h3[text()='Welcome to EPAM']")
    private WebElement loginPageText;

    public CampusLoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getLoginPageText(){
        logger.debug("Get text for verification from Campus Login Page");
        return getTextFromElement(loginPageText);
    }

}
