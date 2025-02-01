package com.test.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"com.test.stepDefinitions", "com.test.hooks"},
    plugin = {"pretty", "html:target/cucumber-reports"},
    monochrome = false
)
public class TestRunner {
} 