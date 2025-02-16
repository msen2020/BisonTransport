@LOG_01
@msen
@login
Feature: Login

  @LOG_01_TC_01
  Scenario: LOG_01_TC_01 Verify Login Herd and iTools functionality
    Then user verifies the title of the page
    And user verifies Login Herd and iTools links are displayed
    When user clicks on Login Herd link
    And user switches the new tab
    Then user verifies the title on Herd login page
    Then user closes the current tab
    And user clicks on Login iTools link
    And user switches the new tab
    Then user verifies the title on the iTools login page
    Then user closes the current tab