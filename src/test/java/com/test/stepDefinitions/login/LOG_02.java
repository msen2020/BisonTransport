package com.test.stepDefinitions.login;

import com.test.pages.CommonPage;
import com.test.utils.BrowserUtils;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static com.test.hooks.Hooks.driver;
import static com.test.hooks.Hooks.random;

public class LOG_02 extends CommonPage {

    @Then("user verifies Customer Login title is visible")
    public void userVerifiesCustomerLoginTitleIsVisible() {
        logger.info("Verifying Customer Login title visibility");

        // Wait for the page to load
        BrowserUtils.waitForPageToLoad(25);

        // Locate the title element
        String expectedTitleText = "Customer Login";
        boolean isTitleVisible = driver().getPageSource().contains(expectedTitleText);

        // Assert that the title is visible
        Assert.assertTrue("Customer Login title should be visible", isTitleVisible);
        
        logger.info("Successfully verified Customer Login title is visible");
    }

    @When("user clicks the link Sign up to i-Tools")
    public void userClicksSignUpToITools() {
        logger.info("Clicking the 'Sign up to i-Tools' link");
        WebElement signUpLink = driver().findElement(By.xpath("//a[contains(text(), 'Sign up to i-Tools')]"));
        BrowserUtils.waitAndClick(signUpLink);
        logger.info("Successfully clicked the 'Sign up to i-Tools' link");
    }

    @Then("user verifies the titles are visible on the i-Tools Sign Up page")
    public void userVerifiesTitlesOnSignUpPage() {
        logger.info("Verifying titles on the i-Tools Sign Up page");
        BrowserUtils.waitForPageToLoad(25);

        // Define the expected titles
        String[] expectedTitles = {
            "First Name:",
            "Last Name:",
            "Phone:",
            "Email:",
            "Country:",
            "City",
            "Address:",
            "Company Name:"
        };

        // Verify each title is visible
        for (String title : expectedTitles) {
            WebElement titleElement = driver().findElement(By.xpath("//label[contains(text(), '" + title + "')]"));
            Assert.assertTrue(title + " should be visible", titleElement.isDisplayed());
            logger.info("{} is visible", title);
        }

        logger.info("Successfully verified all titles on the i-Tools Sign Up page");
    }

    @And("user fills out the i-Tools Sign Up form with valid data")
    public void userFillsOutSignUpForm() {
        logger.info("Filling out the i-Tools Sign Up form");

        // Input valid data
        driver().findElement(By.id("customersignupform-first_name")).sendKeys("John");
        driver().findElement(By.id("customersignupform-last_name")).sendKeys("Doe");
        driver().findElement(By.id("customersignupform-phone")).sendKeys("1234567890");
        driver().findElement(By.id("user_email")).sendKeys("john.doe@example.com");
        
        // Select a random country
        selectRandomCountry();
        driver().findElement(By.id("customersignupform-province_other")).sendKeys("Toronto");

        // Input valid city, address, and company name
        driver().findElement(By.id("customersignupform-city")).sendKeys("Toronto");
        driver().findElement(By.id("customersignupform-address_1")).sendKeys("123 Main St");
        driver().findElement(By.id("customersignupform-address_2")).sendKeys("16, Toronto");
        driver().findElement(By.id("customersignupform-company")).sendKeys("Bison Transport");

        logger.info("Successfully filled out the i-Tools Sign Up form");
    }

    private void selectRandomCountry() {
        logger.info("Selecting a random country");
        WebElement countryDropdown = driver().findElement(By.id("customersignupform-country"));
        countryDropdown.click();

        // List of countries to select from (you can modify this list as needed)
        String[] countries = {"CA", "US", "MX", "GB", "AU", "FR", "DE", "IN"};
        String randomCountry = countries[random.nextInt(countries.length)];

        // Select the random country
        driver().findElement(By.xpath("//select[@id='customersignupform-country']/option[@value='" + randomCountry + "']")).click();
        logger.info("Selected country: {}", randomCountry);
    }
}
