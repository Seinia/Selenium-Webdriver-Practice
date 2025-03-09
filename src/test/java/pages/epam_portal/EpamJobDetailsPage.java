package pages.epam_portal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.base.BasePage;

public class EpamJobDetailsPage extends BasePage {

    @FindBy(css = "span[data-iso2-country-code='UA']")
    private WebElement locationText;

    @FindBy(xpath = "//header[@class='recruiting-page__header']//h1")
    private WebElement jobTitle;

    public EpamJobDetailsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getLocationText(){
        return getTextFromElement(locationText);
    }

    public String getJobTitleText(){
        return getTextFromElement(jobTitle);
    }
}
