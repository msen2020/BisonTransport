package com.test.base;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage extends CommonPage {
    public HomePage() {
        super();
    }

    @FindBy(css = "nav.main-nav ul.menu li a")
    private List<WebElement> navbarTitles;

    @FindBy(linkText = "Services")
    private WebElement servicesLink;

    @FindBy(linkText = "About Us")
    private WebElement aboutUsLink;

    @FindBy(linkText = "Careers")
    private WebElement careersLink;

    @FindBy(linkText = "Contact")
    private WebElement contactLink;

    @FindBy(linkText = "News & Events")
    private WebElement newsEventsLink;

    public List<WebElement> getNavbarTitles() {
        return navbarTitles;
    }

    public boolean isNavTitleVisible(String title) {
        try {
            return getNavbarTitles().stream()
                    .anyMatch(element -> element.isDisplayed() &&
                            element.getText().trim().equalsIgnoreCase(title));
        } catch (Exception e) {
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