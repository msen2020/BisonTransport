package com.test.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.test.driver.DriverManager;

import java.time.Duration;
import java.util.Set;

public class BrowserUtils {
    private static final Logger logger = LoggerFactory.getLogger(BrowserUtils.class);
    private static final int DEFAULT_TIMEOUT_SECONDS = 25;
    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS);
    private static final Duration LONG_TIMEOUT = Duration.ofSeconds(25);

    public static WebDriver getDriver() {
        return DriverManager.getInstance().getCurrentDriver();
    }

    public static void scrollDown(WebElement element) {
        try {
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
            Thread.sleep(500);
        } catch (InterruptedException e) {
            logger.error("Error while scrolling to element: {}", e.getMessage());
        }
    }

    public static void waitAndClick(WebElement element) {
        try {
            new WebDriverWait(getDriver(), DEFAULT_TIMEOUT)
                .until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (Exception e) {
            logger.error("Error while waiting and clicking element: {}", e.getMessage());
            throw e;
        }
    }

    public static void waitForVisibility(WebElement element) {
        try {
            new WebDriverWait(getDriver(), DEFAULT_TIMEOUT)
                .until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            logger.error("Error while waiting for element visibility: {}", e.getMessage());
        }
    }

    public static void switchToIframe(WebElement iframe) {
        try {
            getDriver().switchTo().frame(iframe);
            logger.info("Successfully switched to iframe");
        } catch (Exception e) {
            logger.error("Error while switching to iframe: {}", e.getMessage());
            throw e;
        }
    }

    public static void switchToDefaultContent() {
        try {
            getDriver().switchTo().defaultContent();
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
            getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
            logger.debug("Set implicit wait to {} seconds", seconds);
        } catch (Exception e) {
            logger.error("Error while setting implicit wait: {}", e.getMessage());
            throw e;
        }
    }

    public static String getTitle() {
        try {
            return getDriver().getTitle();
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
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds));
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
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds));
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
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
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
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutSeconds));
            
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
        if (!waitForUrlChange(oldUrl, DEFAULT_TIMEOUT_SECONDS)) {
            String currentUrl = getDriver().getCurrentUrl();
            throw new RuntimeException(String.format(
                "URL did not change from '%s' within %d seconds. Current URL: '%s'",
                oldUrl, DEFAULT_TIMEOUT_SECONDS, currentUrl
            ));
        }
    }

    /**
     * Gets the current page title with proper error handling
     * 
     * @return the page title or empty string if there's an error
     */
    public static String getPageTitle() {
        try {
            String title = getDriver().getTitle();
            logger.debug("Current page title: '{}'", title);
            return title;
        } catch (Exception e) {
            logger.error("Error getting page title: {}", e.getMessage());
            return "";
        }
    }

    /**
     * Verifies if the current page title matches or contains expected text
     * 
     * @param expectedTitle the expected title or text within title
     * @param exactMatch whether to check for exact match or partial match
     * @return true if title matches according to the specified criteria
     */
    public static boolean verifyPageTitle(String expectedTitle, boolean exactMatch) {
        try {
            String actualTitle = getPageTitle();
            boolean matches = exactMatch ? 
                actualTitle.equals(expectedTitle) : 
                actualTitle.contains(expectedTitle);
            
            logger.debug("Title verification - Expected: '{}', Actual: '{}', Exact Match: {}, Result: {}", 
                expectedTitle, actualTitle, exactMatch, matches);
            
            return matches;
        } catch (Exception e) {
            logger.error("Error verifying page title: {}", e.getMessage());
            return false;
        }
    }

    /**
     * Switches to the newest window/tab
     */
    public static void switchToNewWindow() {
        try {
            String currentHandle = getDriver().getWindowHandle();
            Set<String> handles = getDriver().getWindowHandles();
            for (String handle : handles) {
                if (!handle.equals(currentHandle)) {
                    getDriver().switchTo().window(handle);
                    logger.debug("Switched to new window. Title: '{}'", getDriver().getTitle());
                    break;
                }
            }
        } catch (Exception e) {
            logger.error("Error switching to new window: {}", e.getMessage());
            throw e;
        }
    }

    /**
     * Switches back to the main window/tab
     */
    public static void switchToMainWindow() {
        try {
            Set<String> handles = getDriver().getWindowHandles();
            getDriver().switchTo().window(handles.iterator().next());
            logger.debug("Switched back to main window. Title: '{}'", getDriver().getTitle());
        } catch (Exception e) {
            logger.error("Error switching to main window: {}", e.getMessage());
            throw e;
        }
    }
} 