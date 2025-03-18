package pages.base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    protected WebDriver driver;

    protected final Duration WAIT_TIMEOUT_SECONDS = Duration.ofSeconds(10);

    protected BasePage(WebDriver driver){
        this.driver = driver;
    }

    protected void clickElement(WebElement element){
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    protected String getTextFromElement(WebElement element){
        return new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.visibilityOf(element)).getText();
    }

    protected void inputText(WebElement element, String text){
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(element)).sendKeys(text);
    }

    protected void scrollToElement(WebElement webElement, String argument) {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.visibilityOf(webElement));
        ((JavascriptExecutor) driver).executeScript(argument, webElement);
    }

    protected void scrollToElement(WebElement webElement){
        scrollToElement(webElement, "arguments[0].scrollIntoView(true);");
    }
}