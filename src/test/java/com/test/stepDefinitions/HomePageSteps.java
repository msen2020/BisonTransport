package com.test.stepDefinitions;

import com.test.base.HomePage;
import com.test.utils.BrowserUtils;
import com.test.utils.WebDriverManager;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class HomePageSteps {
    private static final Logger logger = LoggerFactory.getLogger(HomePageSteps.class);
    private final HomePage homePage = new HomePage();

    @Then("user verifies the titles are displayed in the navbar")
    public void user_verifies_the_titles_are_displayed_in_the_navbar(DataTable dataTable) {
        List<String> expectedTitles = dataTable.asList();
        
        logger.info("Starting verification of navbar titles");
        BrowserUtils.waitForPageToLoad(25);
        
        // Add a small wait after the page load
        BrowserUtils.sleep(2);
        
        // Get current URL for debugging using WebDriverManager
        String currentUrl = WebDriverManager.getInstance().getCurrentDriver().getCurrentUrl();
        logger.info("Current URL: {}", currentUrl);
        
        // Get page source length for debugging using WebDriverManager
        String pageSource = WebDriverManager.getInstance().getCurrentDriver().getPageSource();
        logger.info("Page source length: {}", pageSource.length());
        
        for (String expectedTitle : expectedTitles) {
            boolean isVisible = homePage.isNavTitleVisible(expectedTitle);
            String actualText = homePage.getNavTitleText(expectedTitle);
            
            logger.info("Checking navbar title: '{}' - Visible: {}, Actual text: '{}'", 
                    expectedTitle, isVisible, actualText);
            
            Assert.assertTrue("Navbar title '" + expectedTitle + "' should be visible", 
                    isVisible);
            Assert.assertEquals("Navbar title text should match", 
                    expectedTitle, actualText.trim());
            
            logger.info("Successfully verified navbar title: {}", expectedTitle);
        }
        
        logger.info("All navbar titles verified successfully");
    }
} 