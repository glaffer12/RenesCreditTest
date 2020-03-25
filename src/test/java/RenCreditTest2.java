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

public class RenCreditTest2 extends BaseSteps {

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
                .stepFillContributionField("Сумма вклада","500000")
                .stepSelectPeriod("9")
                .stepFillContributionField("Ежемесячное пополнение","70000")
                .stepSelectCheckbox("Ежемесячная капитализация")
                .stepCheckCalculationResults("Ставка","4.80%")
                .stepCheckCalculationResults("Начислено %", "28 175,53")
                .stepCheckCalculationResults("К снятию", "1 088 175,53");
    }

    @AfterClass
    public static void after() throws Exception {
        BaseSteps.tearDown();
    }

}
