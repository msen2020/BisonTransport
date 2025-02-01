package com.test.base;

import com.test.utils.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CommonPage {
    protected static final Logger logger = LoggerFactory.getLogger(CommonPage.class);
    protected static WebDriver driver;
    protected static WebDriverManager webDriverManager;

    public CommonPage() {
        webDriverManager = WebDriverManager.getInstance();
        driver = webDriverManager.getCurrentDriver();
        PageFactory.initElements(driver, this);
        logger.debug("Initializing page elements for: {}", this.getClass().getSimpleName());
    }

    public static void initializeDriver(String browser) {
        webDriverManager = WebDriverManager.getInstance();
        driver = webDriverManager.getDriver(browser);
    }

    public static void closeDriver() {
        if (webDriverManager != null) {
            webDriverManager.quitDriver();
        }
    }

    // Common navbar elements shared across pages
    @FindBy(css = "nav.main-nav ul.menu li a")
    protected List<WebElement> navbarTitles;

    @FindBy(linkText = "Services")
    protected WebElement servicesLink;

    @FindBy(linkText = "About Us")
    protected WebElement aboutUsLink;

    @FindBy(linkText = "Careers")
    protected WebElement careersLink;

    @FindBy(linkText = "Contact")
    protected WebElement contactLink;

    @FindBy(linkText = "News & Events")
    protected WebElement newsEventsLink;

    // Common methods that can be used across pages
    public List<WebElement> getNavbarTitles() {
        return navbarTitles;
    }

    public boolean isNavTitleVisible(String title) {
        try {
            return getNavbarTitles().stream()
                    .anyMatch(element -> element.isDisplayed() && 
                            element.getText().trim().equalsIgnoreCase(title));
        } catch (Exception e) {
            logger.error("Error checking visibility of navbar title '{}': {}", 
                    title, e.getMessage());
            return false;
        }
    }

    public String getNavTitleText(String title) {
        return getNavbarTitles().stream()
                .filter(element -> element.getText().trim().equalsIgnoreCase(title))
                .findFirst()
                .map(WebElement::getText)
                .orElse("");
    }

    public void clickNavTitle(String title) {
        getNavbarTitles().stream()
                .filter(element -> element.getText().trim().equalsIgnoreCase(title))
                .findFirst()
                .ifPresent(WebElement::click);
    }
} 