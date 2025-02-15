package com.test.hooks;

import com.test.pages.HomePage;
import com.test.driver.DriverManager;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hooks {
    public static final Logger logger = LoggerFactory.getLogger("BisonTransport");
    public static Actions actions;
    public static WebDriver driver;


    @Before
    public void setUp(Scenario scenario) {
        logger.info("Starting scenario: {}", scenario.getName());
        DriverManager.getInstance().getDriver("chrome");
        actions = new Actions(DriverManager.getInstance().getCurrentDriver());
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            logger.error("Scenario failed: {}", scenario.getName());
            // Add a screenshot capture logic here if needed
        }
        logger.info("Finished scenario: {}", scenario.getName());
        // DriverManager.getInstance().quitDriver();  // Commented out to keep the browser open
        actions = null; // Clean up the actions instance
    }

    // Add the driver() method to access the WebDriver
    public static WebDriver driver() {
        return DriverManager.getInstance().getCurrentDriver();
    }
} 