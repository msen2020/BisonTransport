package com.test.stepDefinitions;

import com.test.base.CommonPage;
import com.test.utils.BrowserUtils;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class LoginSteps extends CommonPage {
    @Given("user goes to Bison Transport page")
    public void user_goes_to_bison_transport_page() {
        driver.get("https://www.bisontransport.com/");
        logger.info("Navigated to Bison Transport website");
    }

    @Then("user verifies the title")
    public void userVerifiesTheTitle() {
        // Wait for the title to be present
        BrowserUtils.waitForTitleContains("Bison", 10);

        // Get the actual title
        String actualTitle = driver.getTitle();
        logger.info("Current page title: '{}'", actualTitle);

        // Verify title contains "Bison"
        Assert.assertTrue("Page title should contain 'Bison'",
                actualTitle.contains("Bison"));

        logger.info("Successfully verified page title contains 'Bison'");

    }
}