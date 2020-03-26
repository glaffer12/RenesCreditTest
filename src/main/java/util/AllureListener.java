package util;

import io.qameta.allure.Attachment;
import io.qameta.allure.junit4.AllureJunit4;
import org.junit.runner.notification.Failure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class AllureListener extends AllureJunit4 {

    @Override
    public void testFailure(Failure failure) {
        super.testFailure(failure);
        if (!failure.getDescription().isSuite()) { // check is needed to avoid double attaching
            attachFailed();
        }
    }


    @Attachment( type = "image/png")
    private byte[] attachFailed() {
        return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
    }
    }
