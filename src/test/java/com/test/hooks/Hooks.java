package com.test.hooks;

import com.test.driver.DriverManager;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class Hooks {
    public static final Logger logger = LoggerFactory.getLogger("BisonTransport");
    public static Actions actions;
    public static WebDriver driver;
    public static String baseUrl;
    public static Random random = new Random();

    @Before
    public void setUp(Scenario scenario) {
        logger.info("Starting scenario: {}", scenario.getName());
        driver = DriverManager.getInstance().getDriver("chrome");
        actions = new Actions(driver);
        baseUrl = "https://www.bisontransport.com/";
        driver.get(baseUrl);
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            logger.error("Scenario failed: {}", scenario.getName());
            // Add a screenshot capture logic here if needed
        }
        logger.info("Finished scenario: {}. The browser will remain open.", scenario.getName());
        actions = null; // Clean up the actions instance
    }

    // Add the driver() method to access the WebDriver
    public static WebDriver driver() {
        return driver;
    }
} 