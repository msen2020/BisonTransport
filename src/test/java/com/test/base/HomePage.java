package com.test.base;

import com.test.utils.BrowserUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.By;

import java.util.List;

public class HomePage extends CommonPage {
    private final Actions actions;

    public HomePage() {
        super();
        this.actions = new Actions(driver);
    }

    @FindBy(css = ".x-menu-first-level > li > a .x-anchor-text-primary")
    private List<WebElement> navbarTitles;

    @FindBy(css = "#menu-item-147 > a .x-anchor-text-primary")
    private WebElement aboutLink;

    @FindBy(css = ".sub-menu.x-dropdown.x-active li a.x-anchor.x-anchor-menu-item")
    private List<WebElement> aboutSubmenuLinks;

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

    public String getNavTitleText(String title) {
        try {
            return getNavbarTitles().stream()
                    .filter(element -> {
                        try {
                            String text = element.getText().trim();
                            logger.debug("Checking element text: '{}'", text);
                            return text.equalsIgnoreCase(title);
                        } catch (Exception e) {
                            logger.debug("Error getting element text: {}", e.getMessage());
                            return false;
                        }
                    })
                    .findFirst()
                    .map(WebElement::getText)
                    .orElse("");
        } catch (Exception e) {
            logger.error("Error getting navbar title text for '{}': {}", title, e.getMessage());
            return "";
        }
    }

    public void clickNavTitle(String title) {
        getNavbarTitles().stream()
                .filter(element -> element.getText().trim().equalsIgnoreCase(title))
                .findFirst()
                .ifPresent(WebElement::click);
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
            BrowserUtils.sleep(1);
            aboutLink.click();
            BrowserUtils.waitForPageToLoad(10);
            logger.info("Successfully clicked About link");
        } catch (Exception e) {
            logger.error("Error clicking About link: {}", e.getMessage());
            throw e;
        }
    }

    public boolean isSubmenuLinkVisible(String linkText) {
        try {
            // Hover over on the About link and wait for submenu
            actions.moveToElement(aboutLink).perform();
            BrowserUtils.sleep(1);
            
            // Wait for submenu to be visible
            BrowserUtils.waitForVisibility(aboutSubmenuLinks.getFirst());
            
            return aboutSubmenuLinks.stream()
                .anyMatch(element -> {
                    try {
                        boolean displayed = element.isDisplayed();
                        String text = element.findElement(By.cssSelector(".x-anchor-text-primary"))
                            .getText().trim();
                        logger.debug("Found submenu element - Displayed: {}, Text: '{}'", 
                            displayed, text);
                        return displayed && text.equalsIgnoreCase(linkText);
                    } catch (Exception e) {
                        logger.debug("Error checking submenu element: {}", e.getMessage());
                        return false;
                    }
                });
        } catch (Exception e) {
            logger.error("Error checking submenu link visibility '{}': {}", 
                linkText, e.getMessage());
            return false;
        }
    }

    public void clickSubmenuLink(String linkText) {
        try {
            // Hover over on the About link and wait for submenu animation
            actions.moveToElement(aboutLink).perform();
            BrowserUtils.sleep(1);
            
            // Wait for submenu to be visible and active
            BrowserUtils.waitForVisibility(aboutSubmenuLinks.getFirst());
            
            // Find and click the specific submenu link
            WebElement targetLink = aboutSubmenuLinks.stream()
                .filter(element -> {
                    try {
                        String text = element.findElement(By.cssSelector(".x-anchor-text-primary"))
                            .getText().trim();
                        return text.equalsIgnoreCase(linkText);
                    } catch (Exception e) {
                        return false;
                    }
                })
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Submenu link not found: " + linkText));
            
            // Store current URL before clicking
            String urlBefore = driver.getCurrentUrl();
            
            BrowserUtils.waitForElementToBeClickable(targetLink);
            targetLink.click();
            
            // Wait for page load and URL change
            BrowserUtils.waitForPageToLoad(10);
            BrowserUtils.waitForUrlChange(urlBefore);
            
        } catch (Exception e) {
            logger.error("Error clicking submenu link '{}': {}", linkText, e.getMessage());
            throw e;
        }
    }

    public void hoverOverAboutLink() {
        try {
            actions.moveToElement(aboutLink).perform();
            BrowserUtils.sleep(1); // Wait for submenu animation
            logger.debug("Successfully hovered over About link");
        } catch (Exception e) {
            logger.error("Error hovering over About link: {}", e.getMessage());
            throw e;
        }
    }
} 