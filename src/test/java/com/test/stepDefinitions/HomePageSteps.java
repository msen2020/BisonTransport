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

    @Then("user verifies the titles are displayed & functional in the navbar")
    public void user_verifies_the_titles_are_displayed_and_functional(DataTable dataTable) {
        List<String> expectedTitles = dataTable.asList();
        
        logger.info("Starting verification of navbar titles visibility and functionality");
        BrowserUtils.waitForPageToLoad(25);
        BrowserUtils.sleep(2);
        
        String currentUrl = WebDriverManager.getInstance().getCurrentDriver().getCurrentUrl();
        logger.info("Starting URL: {}", currentUrl);
        
        for (String expectedTitle : expectedTitles) {
            // Verify visibility
            boolean isVisible = homePage.isNavTitleVisible(expectedTitle);
            String actualText = homePage.getNavTitleText(expectedTitle);
            
            logger.info("Checking navbar title: '{}' - Visible: {}, Actual text: '{}'", 
                    expectedTitle, isVisible, actualText);
            
            Assert.assertTrue("Navbar title '" + expectedTitle + "' should be visible", 
                    isVisible);
            Assert.assertEquals("Navbar title text should match", 
                    expectedTitle, actualText.trim());
            
            // Verify clickability and navigation
            boolean isClickable = homePage.isNavTitleClickable(expectedTitle);
            Assert.assertTrue("Navbar title '" + expectedTitle + "' should be clickable", 
                    isClickable);
            
            // Store current URL before clicking
            String urlBeforeClick = WebDriverManager.getInstance().getCurrentDriver().getCurrentUrl();
            
            // Click the title
            homePage.clickNavTitle(expectedTitle);
            BrowserUtils.waitForPageToLoad(10);
            
            // Get new URL after click
            String urlAfterClick = WebDriverManager.getInstance().getCurrentDriver().getCurrentUrl();
            
            // Verify URL changed (navigation occurred)
            Assert.assertNotEquals("URL should change after clicking " + expectedTitle, 
                    urlBeforeClick, urlAfterClick);
            
            logger.info("Successfully verified navbar title functionality: {} - URL changed from {} to {}", 
                    expectedTitle, urlBeforeClick, urlAfterClick);
            
            // Navigate back to main page for next iteration
            WebDriverManager.getInstance().getCurrentDriver().navigate().back();
            BrowserUtils.waitForPageToLoad(10);
        }
        
        logger.info("All navbar titles verified successfully for visibility and functionality");
    }
} 