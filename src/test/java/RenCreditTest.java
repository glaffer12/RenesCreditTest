import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import steps.BaseSteps;
import steps.ContributionPageSteps;
import steps.MainPageSteps;

/*Сценарий 1 (Использовать Allure + Cucumber)
1. Перейти по ссылке - https://rencredit.ru
2. Перейти в меню – Вклады
3. Выбрать – Рубли
4. Сумма вклада – 300 000
5. Срок – 6 месяцев
6. Ежемесячное пополнение – 50 000
7. Отметить – Ежемесячная капитализация
8. Проверить расчеты по вкладу

Сценарий 2(Использовать Allure + Cucumber)
1. https://rencredit.ru
2. Перейти в меню – Вклады
3. Выбрать – Рубли
4. Сумма вклада – 500 000
5. Срок – 9 месяцев
6. Ежемесячное пополнение – 70 000
7. Отметить – Ежемесячная капитализация
8. Проверить расчеты по вкладу

 */

public class RenCreditTest extends BaseSteps {

    @BeforeClass
    public static void before(){
        BaseSteps.initDriver();
    }

    @Test
    public void Test() throws InterruptedException {
        MainPageSteps mainPageSteps = new MainPageSteps();
        ContributionPageSteps contributionPageSteps = new ContributionPageSteps();

        mainPageSteps.goToContributionPage();

        contributionPageSteps.stepChooseTheCurrency("рубли")
                .stepFillContributionField("Сумма вклада","300000")
                .stepFillContributionField("Ежемесячное пополнение","50000")
                .stepSelectPeriod("6")
                .stepSelectCheckbox("Ежемесячная капитализация")
                .stepCheckCalculationResults("Ставка","4.70%")
                .stepCheckCalculationResults("Начислено %", "9 988,80")
                .stepCheckCalculationResults("К снятию", "559 988,80");
    }

    @AfterClass
    public static void after() throws Exception {
        BaseSteps.tearDown();
    }

}
