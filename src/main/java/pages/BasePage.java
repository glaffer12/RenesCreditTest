package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import steps.BaseSteps;

import static util.DriverManager.getDriver;


public class BasePage{


    public BasePage(){
        PageFactory.initElements(getDriver(), this);
    }

    public static void click(WebElement element) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(false);", element);
        Wait<WebDriver> wait = new WebDriverWait(getDriver(), 15);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void fillField(WebElement element, String value) {
        element.click();
        element.sendKeys(value);
    }

    public void waitWhileValueChanges(WebElement element) throws InterruptedException {
        String actualValue = element.getText();
        while(true) {
            Thread.sleep(500);
            if(element.getText().equals(actualValue)) break;
            else actualValue = element.getText();
            }
        }
}
