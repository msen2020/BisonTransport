Feature: Login

  @login
  Scenario: TC_01 user goes to BisonTransport Page
    Given user goes to Bison Transport page
    Then user verifies the title of the page
    And user verifies Login Herd and iTools links are displayed
    When user clicks on Login Herd link
    And user switches the new tab
    Then user verifies the title of the Herd login page
    Then user closes the current tab
    And user clicks on Login iTools link
    And user switches the new tab
    Then user verifies the title of the iTools login page
    Then user closes the current tab