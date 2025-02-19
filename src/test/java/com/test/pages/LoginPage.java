package com.test.pages;

import com.test.utils.BrowserUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends CommonPage {

    @FindBy(css = "a[href*='herd.bison']")
    private WebElement loginHerdLink;

    @FindBy(css = "a[href*='itools.bison']")
    private WebElement loginIToolsLink;

    public boolean isLoginHerdVisible() {
        try {
            BrowserUtils.waitForVisibility(loginHerdLink);
            boolean isVisible = loginHerdLink.isDisplayed();
            logger.debug("Login Herd link visibility: {}", isVisible);
            return isVisible;
        } catch (Exception e) {
            logger.error("Error checking Login Herd visibility: {}", e.getMessage());
            return false;
        }
    }

    public boolean isLoginIToolsVisible() {
        try {
            BrowserUtils.waitForVisibility(loginIToolsLink);
            boolean isVisible = loginIToolsLink.isDisplayed();
            logger.debug("Login iTools link visibility: {}", isVisible);
            return isVisible;
        } catch (Exception e) {
            logger.error("Error checking Login iTools visibility: {}", e.getMessage());
            return false;
        }
    }

    public void clickLoginHerd() {
        try {
            logger.info("Attempting to click Login Herd link");
            BrowserUtils.waitForElementToBeClickable(loginHerdLink);
            loginHerdLink.click();
            BrowserUtils.waitForPageToLoad(25);
            logger.info("Successfully clicked Login Herd link");
        } catch (Exception e) {
            logger.error("Error clicking Login Herd link: {}", e.getMessage());
            throw e;
        }
    }

    public void clickLoginITools() {
        try {
            logger.info("Attempting to click Login iTools link");
            BrowserUtils.waitForElementToBeClickable(loginIToolsLink);
            loginIToolsLink.click();
            BrowserUtils.waitForPageToLoad(25);
            logger.info("Successfully clicked Login iTools link");
        } catch (Exception e) {
            logger.error("Error clicking Login iTools link: {}", e.getMessage());
            throw e;
        }
    }
}
