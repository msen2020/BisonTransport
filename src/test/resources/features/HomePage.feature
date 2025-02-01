Feature: Home Page Navigation Bar

  @homepage @smoke
  Scenario: TC_01 Verify navbar titles are visible
    Given user is on the Bison Transport homepage
    Then user should see the following titles in navbar
      | Services      |
      | About Us      |
      | Careers       |
      | Contact       |
      | News & Events |

