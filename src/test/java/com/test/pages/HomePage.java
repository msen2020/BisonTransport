package com.test.pages;

import com.test.utils.BrowserUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.By;
import static com.test.hooks.Hooks.actions;

import java.util.List;

public class HomePage extends CommonPage {

    @FindBy(linkText = "Services")
    protected WebElement servicesLink;

    @FindBy(linkText = "Contact")
    protected WebElement contactLink;

    @FindBy(linkText = "News & Events")
    protected WebElement newsEventsLink;

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
            BrowserUtils.sleep(1);
            BrowserUtils.waitAndClick(aboutLink);
            BrowserUtils.waitForPageToLoad(25);
            logger.info("Successfully clicked About link");
        } catch (Exception e) {
            logger.error("Error clicking About link: {}", e.getMessage());
            throw e;
        }
    }

    public boolean isSubmenuLinkVisible(String linkText) {
        try {
            actions.moveToElement(aboutLink).perform();
            BrowserUtils.sleep(1);
            
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

    public boolean verifyAndClickSubmenuLink(String linkText) {
        try {
            actions.moveToElement(aboutLink).perform();
            BrowserUtils.sleep(1);
            
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
            BrowserUtils.sleep(1);
            logger.debug("Successfully hovered over About link");
        } catch (Exception e) {
            logger.error("Error hovering over About link: {}", e.getMessage());
            throw e;
        }
    }

    public void clickServicesLink() {
        try {
            logger.info("Attempting to click Services link");
            BrowserUtils.waitForElementToBeClickable(servicesLink);
            actions.moveToElement(servicesLink).perform();
            BrowserUtils.sleep(1);
            servicesLink.click();
            BrowserUtils.waitForPageToLoad(10);
            logger.info("Successfully clicked Services link");
        } catch (Exception e) {
            logger.error("Error clicking Services link: {}", e.getMessage());
            throw e;
        }
    }

    public void clickCareersLink() {
        try {
            logger.info("Attempting to click Careers link");
            BrowserUtils.waitForElementToBeClickable(careersLink);
            actions.moveToElement(careersLink).perform();
            BrowserUtils.sleep(1);
            careersLink.click();
            BrowserUtils.waitForPageToLoad(10);
            logger.info("Successfully clicked Careers link");
        } catch (Exception e) {
            logger.error("Error clicking Careers link: {}", e.getMessage());
            throw e;
        }
    }

    public void clickContactLink() {
        try {
            logger.info("Attempting to click Contact link");
            BrowserUtils.waitForElementToBeClickable(contactLink);
            actions.moveToElement(contactLink).perform();
            BrowserUtils.sleep(1);
            contactLink.click();
            BrowserUtils.waitForPageToLoad(10);
            logger.info("Successfully clicked Contact link");
        } catch (Exception e) {
            logger.error("Error clicking Contact link: {}", e.getMessage());
            throw e;
        }
    }

    public void clickNewsEventsLink() {
        try {
            logger.info("Attempting to click News & Events link");
            BrowserUtils.waitForElementToBeClickable(newsEventsLink);
            actions.moveToElement(newsEventsLink).perform();
            BrowserUtils.sleep(1);
            newsEventsLink.click();
            BrowserUtils.waitForPageToLoad(10);
            logger.info("Successfully clicked News & Events link");
        } catch (Exception e) {
            logger.error("Error clicking News & Events link: {}", e.getMessage());
            throw e;
        }
    }

    public boolean isNavLinkVisible(WebElement navLink) {
        try {
            BrowserUtils.waitForVisibility(navLink);
            boolean isVisible = navLink.isDisplayed();
            String linkText = navLink.getText().trim();
            logger.debug("Nav link '{}' visibility: {}", linkText, isVisible);
            return isVisible;
        } catch (Exception e) {
            logger.error("Error checking nav link visibility: {}", e.getMessage());
            return false;
        }
    }

    public boolean areAllNavLinksVisible() {
        try {
            boolean servicesVisible = isNavLinkVisible(servicesLink);
            boolean careersVisible = isNavLinkVisible(careersLink);
            boolean contactVisible = isNavLinkVisible(contactLink);
            boolean newsEventsVisible = isNavLinkVisible(newsEventsLink);
            boolean aboutVisible = isNavLinkVisible(aboutLink);

            logger.info("Nav links visibility check - Services: {}, Careers: {}, Contact: {}, News & Events: {}, About: {}",
                servicesVisible, careersVisible, contactVisible, newsEventsVisible, aboutVisible);

            return servicesVisible && careersVisible && contactVisible && 
                   newsEventsVisible && aboutVisible;
        } catch (Exception e) {
            logger.error("Error checking all nav links visibility: {}", e.getMessage());
            return false;
        }
    }

    public void hoverOverCareersLink() {
        try {
            actions.moveToElement(careersLink).perform();
            BrowserUtils.sleep(1);
            logger.debug("Successfully hovered over Careers link");
        } catch (Exception e) {
            logger.error("Error hovering over Careers link: {}", e.getMessage());
            throw e;
        }
    }

    public void hoverOverShippersLink() {
        try {
            actions.moveToElement(shippersLink).perform();
            BrowserUtils.sleep(1);
            logger.debug("Successfully hovered over Shippers link");
        } catch (Exception e) {
            logger.error("Error hovering over Shippers link: {}", e.getMessage());
            throw e;
        }
    }
} 