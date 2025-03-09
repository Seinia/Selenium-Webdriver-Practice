package pages.learn_portal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.base.BasePage;

public class LearnDotNetCoursePage extends BasePage {

    @FindBy(xpath = "//div[text()='ENG']")
    private WebElement languageText;

    @FindBy(xpath = "//div[text()='145 hr 41 min']")
    private WebElement estimatedEffortsText;

    public LearnDotNetCoursePage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getLanguageText(){
        return getTextFromElement(languageText);
    }

    public String getEstimatedEffortsText(){
        return getTextFromElement(estimatedEffortsText);
    }

}
