package pages.epam_portal;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.base.BasePage;

import java.util.ArrayList;
import java.util.List;

public class EpamCareersPage extends BasePage {

    @FindBy(xpath = "//span[text()='visit epam.ua' and contains(@class, 'desktop')]")
    private WebElement visitButton;

    @FindBy(xpath = "//h3[@class='search-result__redirect-heading-23' and contains (text(), 'View Career Opportunities at EPAM Ukraine by Visiting EPAM.ua')]")
    private WebElement textUpperVisitButton;

    @FindBy(linkText = "Find Your Dream Job")
    private WebElement epamCareersPageText;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement findButton;

    public EpamCareersPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public EpamJobListPage clickVisitButton(){
        scrollToElement(visitButton);
        clickElement(visitButton);
        return new EpamJobListPage(driver);
    }

    public EpamCareersPage clickFindButton(){
        scrollToElement(findButton);
        clickElement(findButton);
        return this;
    }


    public String getEpamCareersPageText(){
        return getTextFromElement(epamCareersPageText);
    }

    public void switchToNewTab() {
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1));
    }

    @Override
    protected void scrollToElement(WebElement webElement) {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.visibilityOf(webElement));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", webElement);
    }

}
