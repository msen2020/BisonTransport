package StepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utilities.ConfigurationReader;
import utilities.Drivers;

import java.lang.module.Configuration;
import java.util.concurrent.TimeUnit;

public class Hook {


    @Before

    public void setup() {

        System.out.println("#### setup! start d######\n");

        Drivers.getDriver().manage().window().maximize();
        Drivers.getDriver().get(ConfigurationReader.getProperty("url"));
        Drivers.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @After
    public void teardown(Scenario scenario) {
        //if test failed - do this
        if (scenario.isFailed()) {
            System.out.println("Test failed! Check your ScreenShot");
            byte[] screenshot = ((TakesScreenshot) Drivers.getDriver()).getScreenshotAs(OutputType.BYTES);
            byte[] pngBytes = new byte[10];
            scenario.attach(pngBytes, "image/png", "Bartholomew and the Bytes of the Oobleck");
        } else {
            System.out.println("Cleanup!");
            System.out.println("Test completed! Thumbs Up");
        }
        System.out.println("###########-THE END-###################");
        //after every test, we gonna close browser
        Drivers.closeDriver();
    }

}


