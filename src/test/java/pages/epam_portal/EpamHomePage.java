package pages.epam_portal;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.base.BasePage;

@Slf4j
public class EpamHomePage extends BasePage {

    private final String HOMEPAGE_URL = "https://www.epam.com/";

    @FindBy(linkText = "Careers")
    private WebElement careersButton;

    @FindBy(xpath = "//button[@id='onetrust-accept-btn-handler']")
    private WebElement acceptCookieButton;

    public EpamHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public EpamHomePage openPage() {
        driver.get(HOMEPAGE_URL);
        return this;
    }

    public EpamHomePage acceptCookie() {
        clickElement(acceptCookieButton);
        log.info("Accepted cookies for the EPAM home page");
        return this;
    }

    public EpamCareersPage clickCareersButton(){
        clickElement(careersButton);
        log.info("Clicked careers button on the top navigation menu");
        return new EpamCareersPage(driver);
    }

}
