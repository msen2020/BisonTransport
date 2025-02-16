package com.test.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverManager {
    private static final Logger logger = LoggerFactory.getLogger("BisonTransport");
    private static DriverManager instance;
    private static WebDriver driver;

    private DriverManager() {
        // Private constructor to prevent instantiation
    }

    public static DriverManager getInstance() {
        if (instance == null) {
            instance = new DriverManager();
        }
        return instance;
    }

    public WebDriver getDriver(String browser) {
        if (driver == null) {
            logger.info("Initializing WebDriver for browser: {}", browser);
            switch (browser.toLowerCase()) {
                case "chrome" -> {
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                }
                case "firefox" -> {
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                }
                default -> {
                    logger.warn("Unsupported browser: {}. Defaulting to Chrome", browser);
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                }
            }
            driver.manage().window().maximize();
        }
        return driver;
    }

    public WebDriver getCurrentDriver() {
        return driver;
    }

    public void quitDriver() {
        if (driver != null) {
            logger.info("Cleaning up WebDriver instance. The browser will remain open.");
            // driver.quit();  // Commented out to keep the browser open
//            driver = null;
        }
    }
} 