package pages.campus_portal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.base.BasePage;

public class CampusCoursePage extends BasePage {

    @FindBy(xpath = "(//div[text()='Register'])[1]")
    private WebElement registerButton;

    public CampusCoursePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public CampusLoginPage clickRegisterButton(){
        clickElement(registerButton);
        return new CampusLoginPage(driver);
    }
}
