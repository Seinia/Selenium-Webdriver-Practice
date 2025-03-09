package pages.learn_portal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.base.BasePage;

public class LearnCatalogPage extends BasePage {

    @FindBy(xpath = "//h5[text()='Technical and Technology']")
    private WebElement technicalAndTechnologyCard;

    @FindBy(xpath = "//div[text()='Catalog']")
    private WebElement catalogPageText;

    public LearnCatalogPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getCatalogPageText(){
        return getTextFromElement(catalogPageText);
    }

    public LearnTechnicalAndTechnologyPage clickTechnicalAndTechnologyCard(){
        clickElement(technicalAndTechnologyCard);
        return new LearnTechnicalAndTechnologyPage(driver);
    }

}
