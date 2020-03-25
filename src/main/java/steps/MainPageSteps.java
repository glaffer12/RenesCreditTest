package steps;

import io.qameta.allure.Step;
import pages.MainPage;

public class MainPageSteps extends BaseSteps {

    MainPage mainPage = new MainPage();

    @Step("выполнен переход на страницу Вклады")
    public void goToContributionPage() {
        mainPage.goToContributionsPage();
    }

}
