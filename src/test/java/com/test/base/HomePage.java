package com.test.base;

import com.test.utils.BrowserUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends CommonPage {
    public HomePage() {
        super();
    }

    @FindBy(css = ".nav-menu li a")
    private List<WebElement> navbarTitles;

    public List<WebElement> getNavbarTitles() {
        return navbarTitles;
    }

    public boolean isNavTitleVisible(String title) {
        try {
            logger.debug("Current number of navbar elements: {}", getNavbarTitles().size());
            
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
} 