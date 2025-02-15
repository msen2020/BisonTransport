package com.test.pages;

import com.test.driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public abstract class CommonPage {
    protected static final Logger logger = LoggerFactory.getLogger(CommonPage.class);
    protected static WebDriver driver;

    // Page instances
    private HomePage homePage;
    private LoginPage loginPage;

    public CommonPage() {
        driver = DriverManager.getInstance().getCurrentDriver();
        PageFactory.initElements(driver, this);
        logger.debug("Initializing page elements for: {}", this.getClass().getSimpleName());
    }

    // Common navbar elements shared across pages
    @FindBy(css = "nav.main-nav ul.menu li a")
    protected List<WebElement> navbarTitles;

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

    public HomePage homePage() {
        if (homePage == null) {
            homePage = new HomePage();
        }
        return homePage;
    }

    public LoginPage loginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage();
        }
        return loginPage;
    }

}