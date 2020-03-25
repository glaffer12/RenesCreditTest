package steps;

import static util.DriverManager.getDriver;

public class BaseSteps {

    public static void initDriver(){
        getDriver();
    }

    public static void tearDown() throws Exception {
        getDriver().close();
    }
}
