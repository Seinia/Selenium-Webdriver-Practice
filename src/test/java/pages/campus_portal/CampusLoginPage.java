package pages.campus_portal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.base.BasePage;

public class CampusLoginPage extends BasePage {

    @FindBy(xpath = "//h3[text()='Welcome to EPAM']")
    private WebElement loginPageText;

    public CampusLoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getLoginPageText(){
        return getTextFromElement(loginPageText);
    }

}
