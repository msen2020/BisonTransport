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

