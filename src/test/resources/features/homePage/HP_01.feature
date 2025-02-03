@msen
Feature: Home Page Navigation Bar

  @HP_01 @homepage @smoke
  Scenario: TC_01 Verify navbar titles are visible and functional
    Given user goes to Bison Transport page
    Then user verifies the titles are displayed & functional in the navbar
      | About             |
      | Careers           |
      | Shippers          |
      | Carriers          |
      | Locations         |
      | Contact           |
      | Talk to an Expert |
      | Request Quote     |

  @HP_01 @homepage @smoke
  Scenario: TC_02 Verify About menu items and page content
    Given user goes to Bison Transport page
    When user hovers over the link About
    And user clicks the menu item "Who We Are"
    Then verify the page URL contains "about"
    And verify the page title contains "About - Bison Transport"
    And verify the page heading contains "About Us"
    When user hovers over the link About
    And user clicks the menu item "Leadership"
    Then verify the page URL contains "leadership"
    And verify the page title contains "Leadership - Bison Transport"
    And verify the page heading contains "OUR LEADERSHIP"
    When user hovers over the link About
    And user clicks the menu item "Our Culture"
    Then verify the page URL contains "our-culture"
    And verify the page title contains "Culture - Bison Transport"
    When user hovers over the link About
    And user clicks the menu item "History"
    Then verify the page URL contains "history"
    And verify the page title contains "History - Bison Transport"
    When user hovers over the link About
    And user clicks the menu item "Environmental, Social & Governance"
    Then verify the page URL contains "environmental-social-governance"
    And verify the page title contains "Environmental, Social & Governance - Bison Transport"
    When user hovers over the link About
    And user clicks the menu item "Blog"
    Then verify the page URL contains "blog"
    And verify the page title contains "Blog - Bison Transport"

