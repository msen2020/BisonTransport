package com.test.stepDefinitions;

import com.test.base.HomePage;
import com.test.utils.BrowserUtils;
import com.test.webDriverManager.WebDriverManager;
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
            
            // Navigate back to the main page for the next iteration
            WebDriverManager.getInstance().getCurrentDriver().navigate().back();
            BrowserUtils.waitForPageToLoad(10);
        }
        
        logger.info("All navbar titles verified successfully for visibility and functionality");
    }

    @And("user clicks the link About")
    public void userClicksTheLinkAbout() {
        logger.info("Clicking About link");
        homePage.clickAboutLink();
    }

    @Then("user verifies the links are visible & functional")
    public void userVerifiesTheLinksAreVisibleAndFunctional(DataTable dataTable) {
        List<String> expectedLinks = dataTable.asList();
        
        logger.info("Starting verification of submenu links visibility and functionality");
        BrowserUtils.waitForPageToLoad(25);
        
        for (String expectedLink : expectedLinks) {
            try {
                // Verify visibility
                boolean isVisible = homePage.isSubmenuLinkVisible(expectedLink);
                logger.info("Checking submenu link: '{}' - Visible: {}", expectedLink, isVisible);
                Assert.assertTrue("Submenu link '" + expectedLink + "' should be visible", isVisible);
                
                // Store current URL and title before clicking
                String urlBeforeClick = WebDriverManager.getInstance().getCurrentDriver().getCurrentUrl();
                String titleBeforeClick = WebDriverManager.getInstance().getCurrentDriver().getTitle();
                
                // Click the link
                homePage.clickSubmenuLink(expectedLink);
                BrowserUtils.waitForPageToLoad(25);
                BrowserUtils.sleep(2); // Additional wait for page stability
                
                // Get new URL and title after click
                String urlAfterClick = WebDriverManager.getInstance().getCurrentDriver().getCurrentUrl();
                String titleAfterClick = WebDriverManager.getInstance().getCurrentDriver().getTitle();
                
                // Verify navigation occurred by checking both URL and title changes
                boolean urlChanged = !urlAfterClick.equals(urlBeforeClick);
                boolean titleChanged = !titleAfterClick.equals(titleBeforeClick);
                boolean navigationOccurred = urlChanged || titleChanged;
                
                logger.info("Navigation check for '{}' - URL changed: {}, Title changed: {}", 
                    expectedLink, urlChanged, titleChanged);
                
                Assert.assertTrue(
                    String.format("""
                                    Navigation should occur after clicking '%s'.
                                    Before: URL='%s', Title='%s'
                                    After: URL='%s', Title='%s'""",
                        expectedLink, urlBeforeClick, titleBeforeClick, urlAfterClick, titleAfterClick),
                    navigationOccurred
                );
                
                logger.info("Successfully verified submenu link: {} - Navigation occurred from '{}' to '{}'", 
                    expectedLink, titleBeforeClick, titleAfterClick);
                
                // Navigate back and wait for page to load
                WebDriverManager.getInstance().getCurrentDriver().navigate().back();
                BrowserUtils.waitForPageToLoad(25);
                BrowserUtils.sleep(2);
                
                // Re-hover over the About link to show submenu for the next iteration
                homePage.hoverOverAboutLink();
                BrowserUtils.sleep(1);
                
            } catch (Exception e) {
                logger.error("Error verifying submenu link '{}': {}", expectedLink, e.getMessage());
                throw e;
            }
        }
        
        logger.info("All submenu links verified successfully for visibility and functionality");
    }
} 