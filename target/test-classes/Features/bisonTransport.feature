
Feature: BisonTransport Page

  @bisonTransport
  Scenario: TC01_User goes to BisonTransport Page
    Given User goes to BisonTransport page
    Then Verify that "About" are present
    Then Verify that "Services" are present
    Then Verify that "Careers" are present
    Then Verify that "News" are present
    Then Verify that "Contact" are present
    Then Verify that "Blog" are present
    Then Verify that "Login" are present
    Then Verify that "READ HERE" is visible

