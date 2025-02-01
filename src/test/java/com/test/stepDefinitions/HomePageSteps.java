package com.test.stepDefinitions;

import com.test.base.HomePage;
import com.test.utils.BrowserUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class HomePageSteps {
    private static final Logger logger = LoggerFactory.getLogger(HomePageSteps.class);
    private final HomePage homePage = new HomePage();

    @Given("user is on the Bison Transport homepage")
    public void user_is_on_the_bison_transport_homepage() {
        logger.info("Verifying user is on Bison Transport homepage");
        String expectedTitle = "Bison Transport";
        Assert.assertTrue("Page title should contain '" + expectedTitle + "'",
                BrowserUtils.getTitle().contains(expectedTitle));
    }

    @Then("user should see the following titles in navbar")
    public void user_should_see_the_following_titles_in_navbar(DataTable dataTable) {
        List<String> expectedTitles = dataTable.asList();
        
        logger.info("Verifying navbar titles: {}", expectedTitles);
        
        for (String expectedTitle : expectedTitles) {
            boolean isVisible = homePage.isNavTitleVisible(expectedTitle);
            Assert.assertTrue("Navbar title '" + expectedTitle + "' should be visible",
                    isVisible);

            String actualText = homePage.getNavTitleText(expectedTitle);
            Assert.assertEquals("Navbar title text should match",
                    expectedTitle, actualText.trim());
            logger.info("Successfully verified navbar title: {}", expectedTitle);
        }
    }
} 