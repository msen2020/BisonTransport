Feature: Home Page Navigation Bar

  @HP_01 @homepage @smoke
  Scenario: TC_01 Verify navbar titles are visible
    Given user goes to Bison Transport page
    Then user verifies the titles are displayed in the navbar
      | About             |
      | Careers           |
      | Shippers          |
      | Carriers          |
      | Locations         |
      | Contact           |
      | Talk to an Expert |
      | Request Quote     |

