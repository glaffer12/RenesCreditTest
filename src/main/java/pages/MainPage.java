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


public class MainPage extends BasePage {


    @FindBy(xpath = "//div[@class=\"service__title\"]/a[@href=\"/contributions/\"]")
    WebElement contributions;

    public void goToContributionsPage() {
        contributions.click();
    }

    public MainPage(){
        PageFactory.initElements(getDriver(),this);}

}
