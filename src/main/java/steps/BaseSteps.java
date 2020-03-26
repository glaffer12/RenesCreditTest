package steps;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import util.DriverManager;

import static util.DriverManager.getDriver;

public class BaseSteps {

    public static void initDriver(){
        getDriver();
    }

    public static void tearDown() throws Exception {
        getDriver().quit();
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] saveScreenshot() {
        return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
