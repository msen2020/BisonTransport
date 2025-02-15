package com.test.stepDefinitions.homepage;

import com.test.driver.DriverManager;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.junit.Assert;
import com.test.utils.BrowserUtils;
import com.test.pages.CommonPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.test.hooks.Hooks.driver;

public class HP_01 extends CommonPage {

    @Then("user verifies the titles are displayed & functional in the navbar")
    public void user_verifies_the_titles_are_displayed_and_functional(DataTable dataTable) {
        List<String> expectedTitles = dataTable.asList();
        
        logger.info("Starting verification of navbar titles visibility and functionality");
        BrowserUtils.wait(2);
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
        BrowserUtils.wait(2);
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
                String actualTitle = driver().getTitle();
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
        WebElement element = driver().findElement(
            By.xpath("//span[contains(@class, 'x-anchor-text-primary') and text()='" + menuItem + "']")
        );
        BrowserUtils.waitAndClick(element);
        logger.info("Successfully clicked menu item: '{}'", menuItem);
    }

    @Then("verify the page URL contains {string}")
    public void verifyThePageURLContains(String expectedUrlPart) {
        BrowserUtils.wait(2);
        BrowserUtils.waitForPageToLoad(25);
        String currentUrl = driver().getCurrentUrl().toLowerCase();
        Assert.assertTrue(
            String.format("URL should contain '%s', but was '%s'", 
                expectedUrlPart, currentUrl),
            currentUrl.contains(expectedUrlPart.toLowerCase())
        );
        logger.info("Successfully verified URL contains: '{}'", expectedUrlPart);
    }

    @And("verify the page title contains {string}")
    public void verifyThePageTitleContains(String expectedTitle) {
        BrowserUtils.wait(2);
        BrowserUtils.waitForPageToLoad(25);
        String actualTitle = driver().getTitle();
        Assert.assertTrue(
            String.format("Page title should contain '%s', but was '%s'", 
                expectedTitle, actualTitle),
            actualTitle.contains(expectedTitle)
        );
        logger.info("Successfully verified page title contains: '{}'", expectedTitle);
    }

    @And("verify the page heading contains {string}")
    public void verifyThePageHeadingContains(String expectedHeading) {
        BrowserUtils.wait(2);
        BrowserUtils.waitForPageToLoad(25);

        WebElement heading = driver().findElement(By.cssSelector("h1.x-text-content-text-primary"));
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

    @Then("verify About menu items and their content")
    public void verifyAboutMenuItemsAndTheirContent(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps();
        
        logger.info("Starting verification of About menu items and their content");
        
        for (Map<String, String> row : data) {
            String menuItem = row.get("menuItem");
            String expectedUrl = row.get("url");
            String expectedTitle = row.get("pageTitle");
            String expectedHeading = row.get("heading");
            
            try {
                // Hover over the About link
                logger.info("Verifying menu item: '{}'", menuItem);
                homePage().hoverOverAboutLink();
                
                // Click menu item
                WebElement element = driver().findElement(
                    By.xpath("//span[contains(@class, 'x-anchor-text-primary') and text()='" + menuItem + "']")
                );
                BrowserUtils.waitAndClick(element);
                logger.info("Clicked menu item: '{}'", menuItem);
                
                // Verify URL
                BrowserUtils.wait(2);
                BrowserUtils.waitForPageToLoad(25);
                String currentUrl = driver().getCurrentUrl().toLowerCase();
                Assert.assertTrue(
                    String.format("URL should contain '%s', but was '%s'", 
                        expectedUrl, currentUrl),
                    currentUrl.contains(expectedUrl.toLowerCase())
                );
                
                // Verify page title
                String actualTitle = driver().getTitle();
                Assert.assertTrue(
                    String.format("Page title should contain '%s', but was '%s'", 
                        expectedTitle, actualTitle),
                    actualTitle.contains(expectedTitle)
                );
                
                // Verify heading
                WebElement heading = driver().findElement(By.cssSelector("h1.x-text-content-text-primary"));
                BrowserUtils.waitForVisibility(heading);
                String actualHeading = heading.getText().trim();
                Assert.assertTrue(
                    String.format("Page heading should contain '%s', but was '%s'", 
                        expectedHeading, actualHeading),
                    actualHeading.equalsIgnoreCase(expectedHeading)
                );
                
                logger.info("Successfully verified menu item '{}' - URL: {}, Title: {}, Heading: {}", 
                    menuItem, expectedUrl, expectedTitle, expectedHeading);
                
            } catch (Exception e) {
                logger.error("Error verifying menu item '{}': {}", menuItem, e.getMessage());
                throw e;
            }
        }
        
        logger.info("Successfully verified all About menu items and their content");
    }

    @Then("verify Careers menu items and their content")
    public void verifyCareersMenuItemsAndTheirContent(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps();
        
        logger.info("Starting verification of Careers menu items and their content");
        
        for (Map<String, String> row : data) {
            String menuItem = row.get("menuItem");
            String expectedUrl = row.get("url");
            String expectedTitle = row.get("pageTitle");
            String expectedHeading = row.get("heading");
            
            try {
                // Hover over Careers link
                logger.info("Verifying menu item: '{}'", menuItem);
                homePage().hoverOverCareersLink();
                
                // Store the current window handle
                String mainWindow = driver().getWindowHandle();
                
                // Click menu item
                WebElement element = driver().findElement(
                    By.xpath("//span[contains(@class, 'x-anchor-text-primary') and text()='" + menuItem + "']")
                );
                BrowserUtils.waitAndClick(element);
                logger.info("Clicked menu item: '{}'", menuItem);
                
                // Switch to new tab
                BrowserUtils.wait(2);
                Set<String> windowHandles = driver().getWindowHandles();
                String newWindow = windowHandles.stream()
                    .filter(handle -> !handle.equals(mainWindow))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("New window was not opened"));
                driver().switchTo().window(newWindow);
                
                // Verify URL
                BrowserUtils.wait(2);
                BrowserUtils.waitForPageToLoad(25);
                String currentUrl = driver().getCurrentUrl();
                Assert.assertEquals(
                    String.format("URL should be '%s', but was '%s'", 
                        expectedUrl, currentUrl),
                    expectedUrl,
                    currentUrl
                );
                
                // Verify page title
                String actualTitle = driver().getTitle();
                Assert.assertTrue(
                    String.format("Page title should contain '%s', but was '%s'", 
                        expectedTitle, actualTitle),
                    actualTitle.contains(expectedTitle)
                );
                
                // Verify heading
                WebElement heading = driver().findElement(By.cssSelector("h1"));
                BrowserUtils.waitForVisibility(heading);
                String actualHeading = heading.getText().trim();
                Assert.assertTrue(
                    String.format("Page heading should contain '%s', but was '%s'", 
                        expectedHeading, actualHeading),
                    actualHeading.contains(expectedHeading)
                );
                
                logger.info("Successfully verified menu item '{}' - URL: {}, Title: {}, Heading: {}", 
                    menuItem, expectedUrl, expectedTitle, expectedHeading);
                
                // Close the new tab and switch back to the main window
                driver().close();
                driver().switchTo().window(mainWindow);
                
            } catch (Exception e) {
                logger.error("Error verifying menu item '{}': {}", menuItem, e.getMessage());
                throw e;
            }
        }
        
        logger.info("Successfully verified all Careers menu items and their content");
    }

    @Then("verify Shippers menu items and their content")
    public void verifyShippersMenuItemsAndTheirContent(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps();
        
        logger.info("Starting verification of Shippers menu items and their content");
        
        for (Map<String, String> row : data) {
            String menuItem = row.get("menuItem");
            String expectedUrl = row.get("url");
            String expectedTitle = row.get("pageTitle");
            String expectedHeading = row.get("heading");
            
            try {
                // Hover over Shippers link and wait for submenu
                logger.info("Verifying menu item: '{}'", menuItem);
                homePage().hoverOverShippersLink();
                BrowserUtils.wait(3); // Increased wait for submenu animation
                
                // Store the current window handle
                String mainWindow = driver().getWindowHandle();
                
                // Click menu item with retry logic
                int maxRetries = 3;
                int retryCount = 0;
                boolean clicked = false;
                
                while (!clicked && retryCount < maxRetries) {
                    try {
                        WebElement element = driver().findElement(
                            By.xpath("//span[contains(@class, 'x-anchor-text-primary') and text()='" + menuItem + "']")
                        );
                        if (!element.isDisplayed()) {
                            homePage().hoverOverShippersLink(); // Hover again if element not visible
                            BrowserUtils.wait(2); // Increased wait after hover
                        }
                        BrowserUtils.waitAndClick(element);
                        clicked = true;
                        logger.info("Clicked menu item: '{}'", menuItem);
                    } catch (Exception e) {
                        retryCount++;
                        if (retryCount == maxRetries) {
                            throw e;
                        }
                        BrowserUtils.wait(2); // Increased wait between retries
                    }
                }
                
                // Switch to new tab with increased wait
                BrowserUtils.wait(5); // Increased wait for new tab
                Set<String> windowHandles = driver().getWindowHandles();
                String newWindow = windowHandles.stream()
                    .filter(handle -> !handle.equals(mainWindow))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("New window was not opened"));
                driver().switchTo().window(newWindow);
                
                // Wait for a page load with increased timeout
                BrowserUtils.waitForPageToLoad(45); // Increased page load timeout
                
                // Additional wait for JavaScript completion
                ((JavascriptExecutor) driver()).executeScript("return document.readyState").equals("complete");
                
                // Verify URL with retry
                BrowserUtils.wait(3);
                String currentUrl = driver().getCurrentUrl();
                int urlRetries = 0;
                while (!currentUrl.equals(expectedUrl) && urlRetries < 3) {
                    BrowserUtils.wait(2);
                    currentUrl = driver().getCurrentUrl();
                    urlRetries++;
                }
                Assert.assertEquals(
                    String.format("URL should be '%s', but was '%s'", 
                        expectedUrl, currentUrl),
                    expectedUrl,
                    currentUrl
                );
                
                // Verify page title
                String actualTitle = driver().getTitle();
                Assert.assertTrue(
                    String.format("Page title should contain '%s', but was '%s'", 
                        expectedTitle, actualTitle),
                    actualTitle.contains(expectedTitle)
                );
                
                // Verify heading
                WebElement heading = driver().findElement(By.cssSelector("h1.x-text-content-text-primary"));
                BrowserUtils.waitForVisibility(heading);
                String actualHeading = heading.getText().trim();
                Assert.assertTrue(
                    String.format("Page heading should contain '%s', but was '%s'", 
                        expectedHeading, actualHeading),
                    actualHeading.equalsIgnoreCase(expectedHeading)
                );
                
                logger.info("Successfully verified menu item '{}' - URL: {}, Title: {}, Heading: {}", 
                    menuItem, expectedUrl, expectedTitle, expectedHeading);
                
                // Close new tab and switch back with increased wait
                driver().close();
                driver().switchTo().window(mainWindow);
                BrowserUtils.wait(3); // Increased wait after switching back
                
            } catch (Exception e) {
                logger.error("Error verifying menu item '{}': {}", menuItem, e.getMessage());
                throw e;
            }
        }
        
        logger.info("Successfully verified all Shippers menu items and their content");
    }
} 