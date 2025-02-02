package com.test.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Duration;

import static com.test.webDriverManager.WebDriverManager.driver;

public class BrowserUtils {
    private static final Logger logger = LoggerFactory.getLogger(BrowserUtils.class);
    private static final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(10);
    private static final Duration LONG_TIMEOUT = Duration.ofSeconds(25);

    public static void scrollDown(WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            Thread.sleep(500);
        } catch (InterruptedException e) {
            logger.error("Error while scrolling to element: {}", e.getMessage());
        }
    }

    public static void waitAndClick(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (Exception e) {
            logger.error("Error while waiting and clicking element: {}", e.getMessage());
        }
    }

    public static void waitForVisibility(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            logger.error("Error while waiting for element visibility: {}", e.getMessage());
        }
    }

    public static void switchToIframe(WebElement iframe) {
        try {
            driver.switchTo().frame(iframe);
            logger.info("Successfully switched to iframe");
        } catch (Exception e) {
            logger.error("Error while switching to iframe: {}", e.getMessage());
        }
    }

    public static void switchToDefaultContent() {
        try {
            driver.switchTo().defaultContent();
            logger.info("Successfully switched to default content");
        } catch (Exception e) {
            logger.error("Error while switching to default content: {}", e.getMessage());
        }
    }

    public static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            logger.error("Sleep interrupted: {}", e.getMessage());
        }
    }

    public static void implicitWait(int seconds) {
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
            logger.debug("Set implicit wait to {} seconds", seconds);
        } catch (Exception e) {
            logger.error("Error while setting implicit wait: {}", e.getMessage());
        }
    }

    public static String getTitle() {
        try {
            return driver.getTitle();
        } catch (Exception e) {
            logger.error("Error getting page title: {}", e.getMessage());
            return "";
        }
    }

    /**
     * Waits for the page title to contain the specified text
     * @param titleText text to wait for in the page title
     * @param timeoutInSeconds maximum time to wait in seconds
     */
    public static void waitForTitleContains(String titleText, int timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            wait.until(ExpectedConditions.titleContains(titleText));
            logger.debug("Successfully waited for title to contain: '{}'", titleText);
        } catch (Exception e) {
            logger.error("Error waiting for title to contain '{}': {}", titleText, e.getMessage());
            throw e;
        }
    }

    /**
     * Waits for the page to load completely by checking document.readyState
     * @param timeoutInSeconds maximum time to wait in seconds
     */
    public static void waitForPageToLoad(int timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                    .executeScript("return document.readyState").equals("complete"));
            logger.debug("Page loaded completely");
        } catch (Exception e) {
            logger.error("Error waiting for page to load: {}", e.getMessage());
            throw e;
        }
    }

    public static void waitForElementToBeClickable(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            logger.debug("Element is now clickable");
        } catch (Exception e) {
            logger.error("Error waiting for element to be clickable: {}", e.getMessage());
            throw e;
        }
    }

    /**
     * Waits for the URL to change from a given URL with a configurable timeout.
     * 
     * @param oldUrl The URL to change from
     * @param timeoutSeconds Maximum time to wait in seconds
     * @return true if URL changed, false if timeout occurred
     */
    public static boolean waitForUrlChange(String oldUrl, int timeoutSeconds) {
        try {
            logger.debug("Waiting for URL to change from: {} (timeout: {}s)", oldUrl, timeoutSeconds);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            
            return wait.until(webDriver -> {
                String newUrl = webDriver.getCurrentUrl();
                boolean changed = !newUrl.equals(oldUrl);
                logger.debug("Current URL: {} - Changed: {}", newUrl, changed);
                return changed;
            });
            
        } catch (Exception e) {
            logger.error("Error waiting for URL change from {}: {}", oldUrl, e.getMessage());
            return false;
        }
    }

    /**
     * Waits for the URL to change from a given URL with default timeout.
     * 
     * @param oldUrl The URL to change from
     * @throws RuntimeException if URL doesn't change within default timeout
     */
    public static void waitForUrlChange(String oldUrl) {
        if (!waitForUrlChange(oldUrl, DEFAULT_TIMEOUT.getSeconds())) {
            String currentUrl = driver.getCurrentUrl();
            throw new RuntimeException(String.format(
                "URL did not change from '%s' within %d seconds. Current URL: '%s'",
                oldUrl, DEFAULT_TIMEOUT.getSeconds(), currentUrl
            ));
        }
    }
} 