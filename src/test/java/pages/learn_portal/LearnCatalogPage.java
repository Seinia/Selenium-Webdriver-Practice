package pages.learn_portal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.base.BasePage;

public class LearnCatalogPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(LearnCatalogPage.class);

    @FindBy(xpath = "//div[text()='Technical and Technology']")
    private WebElement technicalAndTechnologyCard;

    @FindBy(xpath = "//div[text()='Catalog']")
    private WebElement catalogPageText;

    public LearnCatalogPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getCatalogPageText(){
        logger.debug("Get text for verification of Learn Catalog Page");
        return getTextFromElement(catalogPageText);
    }

    public LearnTechnicalAndTechnologyPage clickTechnicalAndTechnologyCard(){
        clickElement(technicalAndTechnologyCard);
        logger.info("Click technical and technology card");
        return new LearnTechnicalAndTechnologyPage(driver);
    }

}
