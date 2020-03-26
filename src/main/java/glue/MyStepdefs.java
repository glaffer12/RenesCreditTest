package glue;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.AfterStep;
import cucumber.api.java.Before;
import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Тогда;
import io.cucumber.datatable.DataTable;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import steps.BaseSteps;
import steps.ContributionPageSteps;
import steps.MainPageSteps;

import java.util.Map;

import static steps.BaseSteps.initDriver;
import static steps.BaseSteps.tearDown;
import static util.DriverManager.getDriver;
import static util.DriverManager.properties;

public class MyStepdefs {

    MainPageSteps mainPageSteps = new MainPageSteps();
    ContributionPageSteps contributionPageSteps = new ContributionPageSteps();

    @Когда("^выполнен переход на страницу Вклады$")
    public void выполнен_переход_на_страницу_Вклады(){
        mainPageSteps.goToContributionPage();
    }

    @Когда("^выбрана валюта \"(.+)\"")
    public void выбрана_валюта(String currency){
        contributionPageSteps.stepChooseTheCurrency(currency);
    }

    @Когда("^заполняются поля:$")
    public void заполняются_поля(DataTable fields){
        Map<String,String> dataMap = fields.asMap(String.class, String.class);
        dataMap.forEach((field, value) -> {
            try {
                contributionPageSteps.stepFillContributionField(field, value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    @Когда("^выбран срок вклада \"(.+)\" месяцев$")
    public void выбран_срок_вклада(String periodValue){
        contributionPageSteps.stepSelectPeriod(periodValue);
    }

    @Когда("^выполнено нажатие на чекбокс \"(.+)\"$")
    public void нажатие_на_кнопку(String checkboxName) throws InterruptedException {
        contributionPageSteps.stepSelectCheckbox(checkboxName);
    }

    @Тогда("^значения полей равны:$")
    public void значения_полей_равны(DataTable fields) {
        Map<String,String> dataMap = fields.asMap(String.class, String.class);
        dataMap.forEach((Title, Value) -> contributionPageSteps.stepCheckCalculationResults(Title, Value));
    }
    @Before(value = "@first")
    public void before(){
        initDriver();
    }

    @Before(value = "@notfirst")
    public void startPage() {getDriver().get(properties.getProperty("url"));}

    @After(value = "@last")
    public void after() throws Exception {
        tearDown();
    }

    @After(value = "@all")
    public void embedScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                byte[] screenshot = ((TakesScreenshot) getDriver())
                        .getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}

