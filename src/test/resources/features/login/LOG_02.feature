@LOG_02 @msen @login
Feature: Login with iTools

  @LOG_02_TC_01
  Scenario: LOG_02_TC_01 iTools
    Then user verifies the title of the page
    And user verifies Login Herd and iTools links are displayed
    And user clicks on Login iTools link
    And user switches the new tab
    Then user verifies the title on the iTools login page
    And user clicks the link Sign up to i-Tools
    Then user verifies the titles are visible on the i-Tools Sign Up page
  And user fills out the i-Tools Sign Up form with valid data
