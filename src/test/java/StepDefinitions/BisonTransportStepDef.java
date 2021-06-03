package StepDefinitions;

import Pages.BisonTransportPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.sql.Driver;

public class BisonTransportStepDef {

    BisonTransportPage bisonTransportPage = new BisonTransportPage();


    @Given("User goes to BisonTransport page")
    public void user_goes_to_bison_transport_page() {
        Driver.getDriver().get("https://www.bisontransport.com/");

    }

    @Then("Verify that {string} are present")
    public void verify_that_are_present(String string) {
        System.out.println("Second Step");
    }

    @Then("Verify that {string} is visible")
    public void verify_that_is_visible(String string) {
        System.out.println("third Step");
    }

}
