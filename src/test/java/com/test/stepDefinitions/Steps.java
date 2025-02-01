package com.test.stepDefinitions;

import com.test.base.BisonTransportPage;
import io.cucumber.java.en.Then;

public class Steps {

    BisonTransportPage bisonTransportPage = new BisonTransportPage();


    @Then("verify {string} are present")
    public void verify_that_are_present(String string) {
        bisonTransportPage.verification(string);
        System.out.println("About, Services, Careers, News, Contact, Blog, Login are present");
    }


    @Then("verify {string} is visible")
    public void verify_that_is_visible(String string) {
        bisonTransportPage.verify("READ HERE");
        System.out.println("READ HERE is visible");
    }

}
