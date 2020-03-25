package steps;

import io.qameta.allure.Step;
import pages.ContributionPage;

public class ContributionPageSteps extends BaseSteps{

    ContributionPage contributionPage = new ContributionPage();

    @Step("выбрана валюта {0}")
    public ContributionPageSteps stepChooseTheCurrency(String currency) {
        contributionPage.chooseTheCurrency(currency);
        return this;
    }

    @Step("в поле {0} введено значение {1}")
    public ContributionPageSteps stepFillContributionField(String name,String value) throws InterruptedException {
        contributionPage.fillContributionField(name,value);
        return this;
    }
    @Step("выбран срок вклада {0} месяцев")
    public ContributionPageSteps stepSelectPeriod(String periodValue) {
        contributionPage.selectPeriod(periodValue);
        return this;
    }

    @Step("выполнено нажатие на чекбокс {0}")
    public ContributionPageSteps stepSelectCheckbox(String checkboxName) throws InterruptedException {
        contributionPage.selectCheckbox(checkboxName);
        return this;
    }

    @Step("в поле {0} присутствует значение {1}")
    public ContributionPageSteps stepCheckCalculationResults(String title, String value) {
        contributionPage.checkCalculcationResults(title,value);
        return this;
    }

}
