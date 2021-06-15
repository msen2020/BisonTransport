package StepDefinitions;

import Pages.BisonTransportPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import utilities.Drivers;

import java.sql.Driver;

public class BisonTransportStepDef {

    BisonTransportPage bisonTransportPage = new BisonTransportPage();


    @Given("User goes to BisonTransport page")
    public void user_goes_to_bison_transport_page() {
       System.out.println("You are in the Bison Transport Page");

        //assert Drivers.get() != null;
        String actualTitle = Drivers.getDriver().getTitle();
        String expectedTitle = "Bison Transport";
        Assert.assertEquals(expectedTitle, actualTitle);
        // testNg de tam tersi yaziliyor(actualTitle,expectedTitle)


    }

    @Then("Verify that {string} are present")
    public void verify_that_are_present(String string) {
        bisonTransportPage.verification(string);
        System.out.println("About, Services, Careers, News, Contact, Blog, Login are present");
    }


    @Then("Verify that {string} is visible")
    public void verify_that_is_visible(String string) {
        bisonTransportPage.verify("READ HERE");
        System.out.println("READ HERE is visible");
    }

}
