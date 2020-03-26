package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import steps.BaseSteps;

import java.util.List;

import static util.DriverManager.getDriver;

public class ContributionPage extends BasePage {

    @FindBy(xpath = "//span[@class=\"calculator__currency-field-text\"]")
    List<WebElement> currencyList;

    @FindBy(xpath = "//div[@class=\"calculator__slide-input-row\"]")
    List<WebElement> contributionsParams;

    @FindBy(xpath = "//select")
    WebElement period;

    @FindBy(xpath = "//label[@class=\"calculator__check-block\"]")
    List<WebElement> contributionsCheckboxes;

    @FindBy(xpath = "//span[@class=\"js-calc-rate\"]")
    WebElement contributionRate;

    @FindBy(xpath = "//span[@class=\"js-calc-earned\"]")
    WebElement contributionEarned;

    @FindBy(xpath = "//span[@class=\"js-calc-result\"]")
    WebElement contributionResult;


    public void chooseTheCurrency(String currency) {
        for (WebElement item : currencyList) {
            if (item.getText().equalsIgnoreCase(currency)) {
                new WebDriverWait(getDriver(), 10)
                        .until(ExpectedConditions.elementToBeClickable(item)).click();
                return;
            }
        }
    }

    public void fillContributionField(String fieldName, String value) throws InterruptedException {
        fillField(getField(fieldName).findElement(By.xpath(".//input")), value);
        waitWhileValueChanges(contributionResult);
    }

    public void selectPeriod(String periodValue) {
        try {
            new Select(period).selectByValue(periodValue);
            waitWhileValueChanges(contributionResult);
        } catch (Exception e) {
            Assert.fail("Срок вклада " + periodValue + " месяцев не предусмотрен");
        }
    }

    public WebElement getField(String fieldName) {
        for (WebElement item : contributionsParams) {
            if (item.findElement(By.xpath("./label")).getText().equalsIgnoreCase(fieldName)) {
                return item;
            }
        }
        Assert.fail("Поле " + fieldName + " не существует.");
        return null;
    }

    public void selectCheckbox(String checkboxName) throws InterruptedException {
        for (WebElement item : contributionsCheckboxes) {
            if (item.findElement(By.xpath(".//span[@class=\"calculator__check-text\"]")).getText().equalsIgnoreCase(checkboxName)) {
                item.findElement(By.xpath(".//span[@class=\"calculator__check-text\"]")).click();
                waitWhileValueChanges(contributionResult);
                break;
            }
        }
    }

    public void checkCalculcationResults(String title, String value) {
            WebElement element;
            switch(title) {
                case "Ставка":
                    element = contributionRate;
                    break;
                case "Начислено %":
                    element = contributionEarned;
                    break;
                case "К снятию":
                    element = contributionResult;
                    break;
                default:
                    throw new IllegalStateException("Данный параметр не предусмотрен: " + title);
            }
            Assert.assertEquals(value,element.getText());
    }

    public ContributionPage(){
        PageFactory.initElements(getDriver(),this);}
}
