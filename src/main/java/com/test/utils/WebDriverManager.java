package com.test.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebDriverManager {
    private static final Logger logger = LoggerFactory.getLogger(WebDriverManager.class);
    private static WebDriverManager instance;
    static WebDriver driver;

    // Private constructor to prevent instantiation
    private WebDriverManager() {
    }

    // Singleton getInstance method
    public static WebDriverManager getInstance() {
        if (instance == null) {
            instance = new WebDriverManager();
        }
        return instance;
    }

    public WebDriver getDriver(String browser) {
        if (driver == null) {
            createDriver(browser);
        }
        return driver;
    }

    private void createDriver(String browser) {
        try {
            switch (browser.toLowerCase()) {
                case "chrome":
                    io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                default:
                    throw new IllegalArgumentException("Browser " + browser + " is not supported");
            }
            driver.manage().window().maximize();
            logger.info("Created new {} driver instance", browser);
        } catch (Exception e) {
            logger.error("Error creating WebDriver: {}", e.getMessage());
            throw e;
        }
    }

    public void quitDriver() {
        if (driver != null) {
            try {
                driver.quit();
                driver = null;
                instance = null;
                logger.info("WebDriver instance has been destroyed");
            } catch (Exception e) {
                logger.error("Error quitting WebDriver: {}", e.getMessage());
            }
        }
    }

    public WebDriver getCurrentDriver() {
        return driver;
    }
} 