package com.test.hooks;

import com.test.base.CommonPage;
import com.test.enums.Browser;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hooks {
    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);

    @Before
    public void setup() {
        CommonPage.initializeDriver(Browser.CHROME.getBrowserName());
        logger.info("Browser initialized using Singleton WebDriverManager");
    }

    @After
    public void tearDown() {
        // Commenting out browser close to keep the UI visible after test
        // CommonPage.closeDriver();
        logger.info("Keeping browser open for UI inspection");
    }
} 