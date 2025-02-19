package com.test.stepDefinitions.login;

import com.test.pages.CommonPage;
import com.test.utils.BrowserUtils;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.test.hooks.Hooks.driver;

public class LOG_01 extends CommonPage {

    @Then("user verifies the title of the page")
    public void userVerifiesTheTitleOfThePage() {
        logger.info("Waiting for the page to load...");
        BrowserUtils.waitForPageToLoad(60); // Wait for the page to load completely

        logger.info("Verifying page title");
        String actualTitle = BrowserUtils.getPageTitle(); // Get the actual title
        logger.info("Actual page title: {}", actualTitle); // Log the actual title

        String expectedTitle = "Bison | North America's Trusted Supply Chain Solution";
        Assert.assertEquals(String.format("Page title should be '%s'", expectedTitle), expectedTitle, actualTitle);

        logger.info("Successfully verified page title");
    }

    @And("user verifies Login Herd and iTools links are displayed")
    public void userVerifiesLoginLinksAreDisplayed() {
        logger.info("Verifying Login Herd and iTools links visibility");
        Assert.assertTrue("Login Herd link should be visible", loginPage().isLoginHerdVisible());
        Assert.assertTrue("Login iTools link should be visible", loginPage().isLoginIToolsVisible());
    }

    @When("user clicks on Login Herd link")
    public void userClicksOnLoginHerdLink() {
        logger.info("Clicking on Login Herd link");
        loginPage().clickLoginHerd();
    }

    @And("user clicks on Login iTools link")
    public void userClicksOnLoginIToolsLink() {
        logger.info("Clicking on Login iTools link");
        loginPage().clickLoginITools();
    }

    @Then("user verifies the title on the iTools login page")
    public void userVerifiesTheTitleOnTheIToolsLoginPage() {
        logger.info("Verifying iTools login page");
        BrowserUtils.waitForPageToLoad(25);
        String pageText = driver().getPageSource();
        Assert.assertTrue("Should see 'Customer Login' on iTools page",
                pageText.contains("Customer Login"));

        // Check the URL
        String currentUrl = driver().getCurrentUrl();
        String expectedUrl = "https://itools.bisontransport.com/";
        Assert.assertTrue("URL should start with '" + expectedUrl + "', but was '" + currentUrl + "'",
                currentUrl.startsWith(expectedUrl));
    }

    @And("user switches the new tab")
    public void userSwitchesTheNewTab() {
        logger.info("Switching to new tab");
        BrowserUtils.waitForPageToLoad(25);
        BrowserUtils.switchToNewWindow();
    }

    @Then("user verifies the title on Herd login page")
    public void userVerifiesTheTitleOnHerdLoginPage() {
        logger.info("Verifying Herd login page title");
        String pageTitle = BrowserUtils.getPageTitle();
        boolean titleMatches = pageTitle.contains("Welcome to Bison Transport: Login") ||
                             pageTitle.contains("Welcome to Bison Transport: Lc") ||
                             pageTitle.contains("auth.bisontransport.com");
                             
        Assert.assertTrue("Should be on Herd login page. Current title: " + pageTitle, 
            titleMatches);
    }

    @Then("user closes the current tab")
    public void userClosesTheCurrentTab() {
        logger.info("Closing current tab");
        driver().close();
        BrowserUtils.switchToMainWindow();
        BrowserUtils.waitForPageToLoad(25);
    }
}