package com.test.stepDefinitions.homepage;

import com.test.driver.DriverManager;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.junit.Assert;
import com.test.utils.BrowserUtils;
import com.test.pages.CommonPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

import static com.test.utils.BrowserUtils.getDriver;

public class HP_01 extends CommonPage {

    @Then("user verifies the titles are displayed & functional in the navbar")
    public void user_verifies_the_titles_are_displayed_and_functional(DataTable dataTable) {
        List<String> expectedTitles = dataTable.asList();
        
        logger.info("Starting verification of navbar titles visibility and functionality");
        BrowserUtils.sleep(2);
        BrowserUtils.waitForPageToLoad(25);
        
        String currentUrl = DriverManager.getInstance().getCurrentDriver().getCurrentUrl();
        logger.info("Starting URL: {}", currentUrl);
        
        for (String expectedTitle : expectedTitles) {
            // Verify visibility
            boolean isVisible = homePage().isNavTitleVisible(expectedTitle);
            String actualText = homePage().getNavTitleText(expectedTitle);
            
            logger.info("Checking navbar title: '{}' - Visible: {}, Actual text: '{}'", 
                    expectedTitle, isVisible, actualText);
            
            Assert.assertTrue("Navbar title '" + expectedTitle + "' should be visible",
                    isVisible);
            Assert.assertEquals("Navbar title text should match", 
                    expectedTitle, actualText.trim());
            
            // Verify clickability and navigation
            boolean isClickable = homePage().isNavTitleClickable(expectedTitle);
            Assert.assertTrue("Navbar title '" + expectedTitle + "' should be clickable",
                    isClickable);
            
            // Store current URL before clicking
            String urlBeforeClick = DriverManager.getInstance().getCurrentDriver().getCurrentUrl();
            
            // Click the title
            homePage().clickNavTitle(expectedTitle);
            BrowserUtils.waitForPageToLoad(25);
            
            // Get new URL after click
            String urlAfterClick = DriverManager.getInstance().getCurrentDriver().getCurrentUrl();
            
            // Verify URL changed (navigation occurred)
            Assert.assertNotEquals("URL should change after clicking " + expectedTitle,
                    urlBeforeClick, urlAfterClick);
            
            logger.info("Successfully verified navbar title functionality: {} - URL changed from {} to {}", 
                    expectedTitle, urlBeforeClick, urlAfterClick);
            
            // Navigate back to the main page for the next iteration
            DriverManager.getInstance().getCurrentDriver().navigate().back();
            BrowserUtils.waitForPageToLoad(25);
        }
        
        logger.info("All navbar titles verified successfully for visibility and functionality");
    }

    @And("user clicks the link About")
    public void userClicksTheLinkAbout() {
        logger.info("Clicking About link");
        homePage().clickAboutLink();
    }

    @Then("user verifies the links are visible & functional")
    public void userVerifiesTheLinksAreVisibleAndFunctional(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        
        logger.info("Starting verification of submenu links visibility and functionality");
        BrowserUtils.sleep(2);
        BrowserUtils.waitForPageToLoad(25);
        
        for (Map<String, String> row : data) {
            String linkText = row.keySet().iterator().next();
            String expectedPageTitle = row.values().iterator().next();
            
            try {
                // Click the link and verify it's visible and clickable
                Assert.assertTrue(
                    String.format("Submenu link '%s' should be visible and clickable", linkText),
                    homePage().verifyAndClickSubmenuLink(linkText)
                );
                logger.info("Successfully clicked submenu link: '{}'", linkText);
                
                // Wait for a page load and verify the title
                BrowserUtils.waitForPageToLoad(25);
                String actualTitle = getDriver().getTitle();
                Assert.assertTrue(
                    String.format("Page title should contain '%s', but was '%s'", 
                        expectedPageTitle, actualTitle),
                    actualTitle.contains(expectedPageTitle)
                );
                logger.info("Successfully verified page title for '{}': '{}'", linkText, actualTitle);
                
            } catch (Exception e) {
                logger.error("Error verifying submenu link '{}': {}", linkText, e.getMessage());
                throw e;
            }
        }
        
        logger.info("All submenu links and page titles verified successfully");
    }

    @And("user clicks the menu item {string}")
    public void userClicksTheMenuItem(String menuItem) {
        logger.info("Clicking menu item: '{}'", menuItem);
        WebElement element = getDriver().findElement(
            By.xpath("//span[contains(@class, 'x-anchor-text-primary') and text()='" + menuItem + "']")
        );
        BrowserUtils.waitAndClick(element);
        logger.info("Successfully clicked menu item: '{}'", menuItem);
    }

    @Then("verify the page URL contains {string}")
    public void verifyThePageURLContains(String expectedUrlPart) {
        BrowserUtils.sleep(2);
        BrowserUtils.waitForPageToLoad(25);
        String currentUrl = getDriver().getCurrentUrl().toLowerCase();
        Assert.assertTrue(
            String.format("URL should contain '%s', but was '%s'", 
                expectedUrlPart, currentUrl),
            currentUrl.contains(expectedUrlPart.toLowerCase())
        );
        logger.info("Successfully verified URL contains: '{}'", expectedUrlPart);
    }

    @And("verify the page title contains {string}")
    public void verifyThePageTitleContains(String expectedTitle) {
        BrowserUtils.sleep(2);
        BrowserUtils.waitForPageToLoad(25);
        String actualTitle = getDriver().getTitle();
        Assert.assertTrue(
            String.format("Page title should contain '%s', but was '%s'", 
                expectedTitle, actualTitle),
            actualTitle.contains(expectedTitle)
        );
        logger.info("Successfully verified page title contains: '{}'", expectedTitle);
    }

    @And("verify the page heading contains {string}")
    public void verifyThePageHeadingContains(String expectedHeading) {
        BrowserUtils.sleep(2);
        BrowserUtils.waitForPageToLoad(25);

        WebElement heading = getDriver().findElement(By.cssSelector("h1.x-text-content-text-primary"));
        BrowserUtils.waitForVisibility(heading);
        
        String actualHeading = heading.getText().trim();
        Assert.assertTrue(
            String.format("Page heading should contain '%s', but was '%s'", 
                expectedHeading, actualHeading),
            actualHeading.equalsIgnoreCase(expectedHeading)
        );
        logger.info("Successfully verified page heading contains: '{}'", expectedHeading);
    }

    @When("user hovers over the link About")
    public void userHoversOverTheLinkAbout() {
        logger.info("Hovering over About link");
        homePage().hoverOverAboutLink();
    }
} 