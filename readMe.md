# Bison Transport Test Automation

This project contains automated tests for the Bison Transport website using Selenium WebDriver and Cucumber.

## Project Structure

Create SeleniuM 4.10.0 Java 21 BDD Cucumber Project.
Create a WebDriver class that will be used to create the driver instance.
Create a TestBase class that will be used to initialize the driver and close the driver.
Create a TestRunner class that will be used to run the tests.
Create a Feature file that will be used to write the tests.
Create a StepDefinition file that will be used to write the step definitions.
Create a Hooks file that will be used to write the hooks.
Create a Runner file that will be used to run the tests.
Create an enum file that will be used to write the enum values.
Create the necessary dependencies and plugins in the pom.xml file.
Use the web address https://www.saucedemo.com/ for the tests.

## Dependencies

The project uses the following key dependencies with security patches:

- Selenium WebDriver: 4.14.1 (CVE-2023-5590 fix)
- Jackson Databind: 2.13.4.2 (Multiple CVE fixes)
- Netty: 4.1.115.Final (CVE-2024-47535 fix)
- AsyncHttpClient: 3.0.1 (CVE-2024-53990 fix)
- Guava: 32.0.1-jre (CVE-2023-2976 fix)
- Bouncy Castle: 1.78 (Multiple CVE fixes)
- Commons IO: 2.14.0 (CVE-2024-47554 fix)
- Commons Compress: 1.26.0 (CVE-2024-25710, CVE-2024-26308 fixes)

All dependencies are explicitly declared to override potentially vulnerable transitive versions.

@Codebase

src/
├── main/
│   └── java/
│       └── com/
│           └── test/
│               └── driver/           # Driver management
│                   └── DriverManager.java
└── test/
    └── java/
        └── com/
            └── test/
                ├── base/              # Page Object classes
                │   ├── CommonPage.java
                │   ├── HomePage.java
                │   └── ShippingPage.java
                ├── hooks/             # Test hooks and shared resources
                │   └── Hooks.java     # Contains shared instances
                ├── stepDefinitions/   # Cucumber step definitions
                │   └── HomePageSteps.java
                └── utils/            # Utility classes
                    └── BrowserUtils.java