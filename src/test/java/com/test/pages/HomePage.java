package com.test.pages;

import com.test.utils.BrowserUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.By;
import static com.test.hooks.Hooks.actions;

import java.util.List;

public class HomePage extends CommonPage {

    @FindBy(css = ".x-menu-first-level > li > a .x-anchor-text-primary")
    private List<WebElement> navbarTitles;

    @FindBy(css = "a[href*='/about'] .x-anchor-text-primary")
    private WebElement aboutLink;

    @FindBy(css = ".sub-menu.x-dropdown.x-active li .x-anchor-text-primary")
    private List<WebElement> aboutSubmenuLinks;

    @FindBy(css = "a[href*='/careers'] .x-anchor-text-primary")
    private WebElement careersLink;

    @FindBy(css = "a[href*='/shippers'] .x-anchor-text-primary")
    private WebElement shippersLink;

    public List<WebElement> getNavbarTitles() {
        return navbarTitles;
    }

    public boolean isNavTitleVisible(String title) {
        try {
            logger.debug("Current number of navbar elements: {}", getNavbarTitles().size());
            
            getNavbarTitles().forEach(element -> {
                try {
                    logger.debug("Found menu item: '{}', Displayed: {}", 
                            element.getText().trim(), element.isDisplayed());
                } catch (Exception e) {
                    logger.debug("Error reading menu item: {}", e.getMessage());
                }
            });
            
            if (!getNavbarTitles().isEmpty()) {
                BrowserUtils.waitForVisibility(getNavbarTitles().getFirst());
            } else {
                logger.error("No navbar elements found");
                return false;
            }

            boolean isVisible = getNavbarTitles().stream()
                    .anyMatch(element -> {
                        try {
                            boolean displayed = element.isDisplayed();
                            String text = element.getText().trim();
                            logger.debug("Found navbar element - Displayed: {}, Text: '{}'", 
                                    displayed, text);
                            return displayed && text.equalsIgnoreCase(title);
                        } catch (Exception e) {
                            logger.debug("Error checking element: {}", e.getMessage());
                            return false;
                        }
                    });

            logger.debug("Visibility check for '{}': {}", title, isVisible);
            return isVisible;

        } catch (Exception e) {
            logger.error("Error checking visibility of navbar title '{}': {}", 
                    title, e.getMessage());
            return false;
        }
    }

    public boolean isNavTitleClickable(String title) {
        try {
            return getNavbarTitles().stream()
                    .filter(element -> element.getText().trim().equalsIgnoreCase(title))
                    .findFirst()
                    .map(element -> {
                        try {
                            BrowserUtils.waitForElementToBeClickable(element);
                            return true;
                        } catch (Exception e) {
                            logger.debug("Element not clickable: {}", e.getMessage());
                            return false;
                        }
                    })
                    .orElse(false);
        } catch (Exception e) {
            logger.error("Error checking if navbar title is clickable '{}': {}", 
                    title, e.getMessage());
            return false;
        }
    }

    public void clickAboutLink() {
        try {
            logger.info("Attempting to click About link");
            BrowserUtils.waitForElementToBeClickable(aboutLink);
            actions.moveToElement(aboutLink).perform();
            BrowserUtils.wait(1);
            BrowserUtils.waitAndClick(aboutLink);
            BrowserUtils.waitForPageToLoad(25);
            logger.info("Successfully clicked About link");
        } catch (Exception e) {
            logger.error("Error clicking About link: {}", e.getMessage());
            throw e;
        }
    }

    public boolean verifyAndClickSubmenuLink(String linkText) {
        try {
            actions.moveToElement(aboutLink).perform();
            BrowserUtils.wait(1);
            
            BrowserUtils.waitForVisibility(aboutSubmenuLinks.getFirst());
            logger.debug("Found {} submenu links", aboutSubmenuLinks.size());
            
            WebElement targetLink = aboutSubmenuLinks.stream()
                .filter(element -> {
                    try {
                        String text = element.getText().trim();
                        logger.debug("Found submenu text: '{}'", text);
                        return text.equalsIgnoreCase(linkText);
                    } catch (Exception e) {
                        logger.debug("Error getting submenu text: {}", e.getMessage());
                        return false;
                    }
                })
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Submenu link not found: " + linkText));
            
            WebElement parentAnchor = targetLink.findElement(By.xpath("./ancestor::a[contains(@class, 'x-anchor-menu-item')]"));
            
            boolean isVisible = BrowserUtils.verifyElementDisplayed(parentAnchor);
            if (!isVisible) {
                logger.error("Submenu link '{}' is not visible", linkText);
                return false;
            }
            
            logger.info("Clicking submenu link: '{}'", linkText);
            boolean clicked = BrowserUtils.clickElement(parentAnchor);
            if (clicked) {
                BrowserUtils.waitForPageToLoad(25);
            }
            return clicked;
            
        } catch (Exception e) {
            logger.error("Error verifying and clicking submenu link '{}': {}", 
                linkText, e.getMessage());
            return false;
        }
    }

    public void hoverOverAboutLink() {
        try {
            actions.moveToElement(aboutLink).perform();
            BrowserUtils.wait(1);
            logger.debug("Successfully hovered over About link");
        } catch (Exception e) {
            logger.error("Error hovering over About link: {}", e.getMessage());
            throw e;
        }
    }

    public void hoverOverCareersLink() {
        try {
            actions.moveToElement(careersLink).perform();
            BrowserUtils.wait(1);
            logger.debug("Successfully hovered over Careers link");
        } catch (Exception e) {
            logger.error("Error hovering over Careers link: {}", e.getMessage());
            throw e;
        }
    }

    public void hoverOverShippersLink() {
        try {
            actions.moveToElement(shippersLink).perform();
            BrowserUtils.wait(1);
            logger.debug("Successfully hovered over Shippers link");
        } catch (Exception e) {
            logger.error("Error hovering over Shippers link: {}", e.getMessage());
            throw e;
        }
    }
} 